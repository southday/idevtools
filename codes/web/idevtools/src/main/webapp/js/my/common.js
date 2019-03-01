/* 通用 js
 * @author southday
 * @date 2019.02.27
 * @version v0.1
 */

/* url更改器
 * 1) 前端单独开发，测试时，url前面需要加http://localhost:8080
 * 2) 集成到java web项目中时，url前面不用加http://localhost:8080
 * 该是为了方便以上两种情况的相互转换，真正部署时，要取消该方法的调用
 */
function cookurl(url) {
    return url;
    // return 'http://localhost:8080' + url;
}

/* 更换验证码 */
function changeVerifyCode() {
    return cookurl('/idevtools/jcaptcha.jpg?r=' + (Math.random()))
}
