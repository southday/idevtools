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

2./resources/config/spring-mybatis.xml
```xml
<!-- 加载配置文件 -->
<context:property-placeholder location="classpath:config/jdbc-pe.properties"/> <!-- 远程 -->
<context:property-placeholder location="classpath:config/jdbc-dev.properties"/> <!-- 本地 -->
```

3./webapp/js/my/common.js
```js
function cookurl(url) {
    return url; // 部署到远程服务器上时使用，因为远程服务器中配置了反向代理，可以将项目名idevtools去掉
    // return '/idevtools' + url; // 部署到本地服务器时使用
    // return 'http://localhost:8080' + url; // 前端单独开发，测试时使用
}
```
