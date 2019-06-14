package cn.idevtools.util;

/**
 * 通用工具
 * @author southday
 * @date 2019/6/13
 */
public class CommonUtil {

    /**
     * 由于本地开发测试、部署到服务器时项目URL会变化（关于项目名称的去除），所以设置一个通用方法来统一管理变化。<br>
     * 注：Linux服务器中配置了Nginx反向代理来去除项目名称，而本地开发时则没有去除，如下：<br>
     * 1) 远程服务器URL示例：https://idevtools.cn/search<br>
     * 2) 本地测试URL示例：http://localhost:8080/idevtools/search
     * @param url
     * @return
     */
    public static String cookurl(String url) {
//        return url; // 部署到远程服务器上时使用，因为远程服务器中配置了反向代理，可以将项目名idevtools去掉
        return "/idevtools" + url; // 部署到本地服务器时使用
//        return 'http://localhost:8080' + url; // 前端单独开发，测试时使用
    }
}
