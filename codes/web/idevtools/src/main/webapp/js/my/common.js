/** 通用 js
 * @author southday
 * @date 2019.02.27
 * @version v0.1
 */

/** url更改器 southday 2019.03.01
 * 1) 前端单独开发，测试时，url前面需要加http://localhost:8080
 * 2) 集成到java web项目中时，url前面不用加http://localhost:8080
 * 3) 部署到远程服务器时，由于远程服务器中配置了反向代理，可以不写项目名，直接return url就可以
 * 该方法是为了方便以上3种情况的相互转换
 */
function cookurl(url) {
    // return url; // 部署到远程服务器上时使用，因为远程服务器中配置了反向代理，可以将项目名idevtools去掉
    return '/idevtools' + url; // 部署到本地服务器时使用
    // return 'http://localhost:8080' + url; // 前端单独开发，测试时使用
}

/** 更换验证码 */
function changeVerifyCode() {
    return cookurl('/jcaptcha.jpg?r=' + (Math.random()))
}

/** code = VALID_ERROR，表单验证失败，提示消息
 * southday 2019.03.01
 */
function showValidMsgs(validMsgs) {
    for (i = 0, len = validMsgs.length; i < len; i++)
        toastr.warning(validMsgs[i].errorMsg)
}

/** 从localStorage中获取userToken
 * southday 2019.03.05
 */
function getUserToken() {
    return localStorage.getItem("userToken")
}

/** 将userToken保存到localStorage中
 * southday 2019.03.05
 * @param token
 */
function saveUserToken(token) {
    localStorage.setItem("userToken", token)
}

/**
 * 从localStorage中获取adminToken
 * southday 2019.05.17
 * @returns {string}
 */
function getAdminToken() {
    return localStorage.getItem("adminToken")
}

/**
 * 将adminToken保存到localStorage中
 * @param token
 */
function saveAdminToken(token) {
    localStorage.setItem("adminToken", token)
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

/**
 * 将admin保存到localStorage
 * southday 2019.05.17
 * @param admin
 */
function saveAdmin(admin) {
    localStorage.setItem("admin", ($.isEmptyObject(admin) ? null : JSON.stringify(admin)))
}

/**
 * 从localStorage中取user
 * southday 2019.05.17
 * @returns {admin}
 */
function getAdmin() {
    let a = localStorage.getItem("admin")
    return $.isEmptyObject(a) ? null : JSON.parse(a)
}

/**
 * idevtools-footer组件
 * southday 2019.03.06
 */
Vue.component('idevtools-footer', {
    template: `
    <footer class="navbar-fixed-bottom">
        <div class="container">
            <hr>
            <div class="text-center">
                @IDevTools Team
                <a href="http://www.miitbeian.gov.cn/" target="view_window">滇ICP备18009876号</a>
            </div>
        </div>
    </footer>
    `
})
new Vue({ el: '#idevtools-footer' })

/**
 * Toastr 提示框配置
 * southday 2019.06.13
 */
toastr.options = {
    closeButton: false,                      // 是否显示关闭按钮，（提示框右上角关闭按钮）
    debug: false,                            // 是否使用deBug模式
    progressBar: true,                       // 是否显示进度条，（设置关闭的超时时间进度条）
    positionClass: "toast-bottom-right",     // 设置提示款显示的位置
    onclick: null,                           // 点击消息框自定义事件
    showDuration: "300",                     // 显示动画的时间
    hideDuration: "1000",                    // 消失的动画时间
    timeOut: "2000",                         // 自动关闭超时时间
    extendedTimeOut: "1000",                 // 加长展示时间
    showEasing: "swing",                     // 显示时的动画缓冲方式
    hideEasing: "linear",                    // 消失时的动画缓冲方式
    showMethod: "fadeIn",                    // 显示时的动画方式
    hideMethod: "fadeOut"                    // 消失时的动画方式
}
