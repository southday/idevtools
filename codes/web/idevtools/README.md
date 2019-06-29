# IDevTools

## 相关环境

- JDK：jdk1.8.0_192
- css
    + bootstrap.min.css (v3.3.7)
    + bootstrap-treeview.min.css ([patternfly-bootstrap-treeview 2.1.5](https://github.com/patternfly/patternfly-bootstrap-treeview))
    + toastr.min.css (v2.1.3)
- js
    + axios.min.js (v0.18.0)
    + jquery-3.3.1.min.js
    + bootstrap.min.js (v3.3.7)
    + bootstrap-treeview.min.js ([patternfly-bootstrap-treeview 2.1.5](https://github.com/patternfly/patternfly-bootstrap-treeview))
    + vue.min.js (v2.5.22)
    + toastr.min.js (v2.1.3)

---

## 注意事项

由于远程服务器中使用了Nginx做反向代理，URL会变化，所以从仓库中clone代码后可能要根据需要（本地测试 or 远程测试）更改部分代码；

URL变化如下：
- 本地开发测试时，URL为：http://localhost:8080/idevtools/u/userInfo
- 部署到远程服务器中，URL为：https://idevtools.cn/u/userInfo

很明显，从本地测试 -> 远程测试，URL中去除了多余项目名`idevtools`

需要更改的部分代码如下：

1.cn.idevtools.util.CommonUtil.java
```java
public static String cookurl(String url) {
    return url; // 部署到远程服务器上时使用，因为远程服务器中配置了反向代理，可以将项目名idevtools去掉
//    return "/idevtools" + url; // 部署到本地服务器时使用
//    return 'http://localhost:8080' + url; // 前端单独开发，测试时使用
}
```

2.cn.idevtools.swagger.SwaggerConfig.java，在用swagger-ui做测试时，测试远程服务器接口使用HTTPS协议，测试本地服务器接口使用HTTP协议。在本地开发测试时，可以把`“部署到服务器的配置”`那部分代码注释。
```java
@Bean
public Docket createRestApi() {
    ParameterBuilder tokenPar = new ParameterBuilder();
    List<Parameter> pars = new ArrayList<>();
    tokenPar.name("token").description("Token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
    pars.add(tokenPar.build());
    return new Docket(DocumentationType.SWAGGER_2)
            /* -------- 部署到服务器的配置 -------------- */
            .protocols(new HashSet<>(Arrays.asList("https", "http")))
            .host("idevtools.cn")
            .pathProvider(new CustomPathProvider())
            /* -------- 部署到服务器的配置 -------------- */
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("cn.idevtools.controller"))
            .paths(PathSelectors.any())
            .build()
            .globalOperationParameters(pars);
}
```

3.cn.idevtools.service.impl.EmailServiceImpl.java，修改激活地址`link`：
```java
@Override
public void sendValidEmail(UserT user) {
    //截止时间
    Calendar now = Calendar.getInstance();
    now.add(Calendar.MINUTE,ACTIVE_DURATION);
    Date expireDate = now.getTime();
    try {
        String userId = String.valueOf(user.getUserId());
        String expireDateString = MAIL_DATE_FORMAT.format(expireDate);
        //拼接id与日期并加密 生成激活码
        String activeCode = DESCipher.getInstance().encrypt(userId + expireDateString);
        //标题
        String subject = "用户" + user.getUserName() + "注册idevtools的验证邮件";
        // 验证链接，激活码可能会包含/ 因此不用路径获取值，部署到本地服务器上的激活链接
//            String link = String.format("http://localhost:8080/idevtools/u/active?activeCode=%s",activeCode);
        // 部署到服务器上的激活链接
        String link = String.format("https://idevtools.cn/u/active?activeCode=%s",activeCode);
        //内容
        String text = "点击本条链接进行验证" + link;
        //发邮件
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom(MAIL_HOST);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(text);
        sendEmail(message);
    }
    catch (MessagingException e){
        e.printStackTrace();
    }
    catch (Exception e){
        e.printStackTrace();
    }
}
```

4./resources/config/spring-mybatis.xml
```xml
<!-- 加载配置文件 -->
<context:property-placeholder location="classpath:config/jdbc-pe.properties"/> <!-- 远程 -->
<context:property-placeholder location="classpath:config/jdbc-dev.properties"/> <!-- 本地 -->
```

5./webapp/js/my/common.js
```js
function cookurl(url) {
    return url; // 部署到远程服务器上时使用，因为远程服务器中配置了反向代理，可以将项目名idevtools去掉
    // return '/idevtools' + url; // 部署到本地服务器时使用
    // return 'http://localhost:8080' + url; // 前端单独开发，测试时使用
}
```
