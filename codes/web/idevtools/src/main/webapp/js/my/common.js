/** 通用 js
 * @author southday
 * @date 2019.02.27
 * @version v0.1
 */

/** url更改器 southday 2019.03.01
 * 1) 前端单独开发，测试时，url前面需要加http://localhost:8080
 * 2) 集成到java web项目中时，url前面不用加http://localhost:8080
 * 该是为了方便以上两种情况的相互转换，真正部署时，要取消该方法的调用
 */
function cookurl(url) {
    return url;
    // return 'http://localhost:8080' + url;
}

/** 更换验证码 */
function changeVerifyCode() {
    return cookurl('/idevtools/jcaptcha.jpg?r=' + (Math.random()))
}

/** code = VALID_ERROR，表单验证失败，提示消息
 * southday 2019.03.01
 */
function showValidMsgs(validMsgs) {
    for (i = 0, len = validMsgs.length; i < len; i++)
        toastr.warning(validMsgs[i].errorMsg)
}

/** 从localStorage中获取token
 * southday 2019.03.05
 */
function getToken() {
    return localStorage.getItem("token")
}

/** 将token保存到localStorage中
 * southday 2019.03.05
 * @param token
 */
function saveToken(token) {
    localStorage.setItem("token", token)
}

/**
 * 将user保存到localStorage
 * southday 2019.03.05
 * @param user
 */
function saveUser(user) {
    localStorage.setItem("user", ($.isEmptyObject(user) ? null : JSON.stringify(user)))
}

/**
 * 从localStorage中取user
 * southday 2019.03.05
 * @returns {user}
 */
function getUser() {
    let u = localStorage.getItem("user")
    return $.isEmptyObject(u) ? null : JSON.parse(u)
}
