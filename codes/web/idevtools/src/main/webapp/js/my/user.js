/* 用户模块 js
 * @author southday
 * @date 2019.02.27
 * @version v0.1
 */

let vmUser = new Vue({
    el: "#user",
    data: {
        jcaptchaURL: cookurl('/idevtools/jcaptcha.jpg'),
        remindInfo: '',
        userName: '',
        password: '',
        password2: '',
        email: '',
        jcaptcha: ''
    },
    methods: {
        changeJCaptcha: function() {
            this.jcaptchaURL = changeVerifyCode()
        },
        login: function() {
            if (this.userName.length === 0 || this.password.length === 0 || this.jcaptcha.length === 0)
                return
            console.log("login")
            // 登录成功
            vmIndexNavbar.logined = true
            $("#user-login-modal").modal("hide")
        },
        join: function() {
            if (this.userName.length === 0 || this.length === 0
                || this.password2.length === 0 || this.length === 0 || this.length === 0)
                return
            if (this.password !== this.password2) {
                this.remindInfo = '两次密码不匹配，请确认！'
                $("#remind-modal").modal("show")
                return
            }
            console.log("join")
            // 注册成功
            vmIndexNavbar.logined = true
            $("#user-join-modal").modal("hide")
        }
    }
})