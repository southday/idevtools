/* 用户模块 js
 * @author southday
 * @date 2019.02.27
 * @version v0.1
 */

let vmUser = new Vue({
    el: "#user",
    data: {
        jcaptchaURL: cookurl('/idevtools/jcaptcha.jpg'),
        jcaptcha: '',
        userName: '',
        email: '',
        password: '',
        password2: '',
        user: {}
    },
    methods: {
        changeJCaptcha: function() {
            this.jcaptchaURL = changeVerifyCode()
        },
        login: function() {
            if (!vmUser.checkParams('login'))
                return
            axios({
                method: 'post',
                url: cookurl('/idevtools/u/login'),
                params: {
                    userName: this.userName,
                    password: this.password,
                    jcaptcha: this.jcaptcha
                }
            }).then(function(resp) {
                let ret = resp.data
                if (ret.code == -8) { // 后端表单验证失败
                    showValidMsgs(ret.data)
                } else if (ret.code == -1) {
                    toastr.error(ret.msg)
                } else {
                    this.user = ret.data
                    vmIndexNavbar.userName = this.user.userName
                    vmIndexNavbar.userURL = cookurl('/idevtools/u/' + this.user.userName)
                    vmIndexNavbar.logined = true
                    $("#user-login-modal").modal("hide")
                    vmUser.clearParams()
                }
            }).catch(function(error) {
                console.log(error)
            })
            vmUser.changeJCaptcha()
        },
        join: function() {
            if (!vmUser.checkParams('join'))
                return
            axios({
                method: 'post',
                url: cookurl('/idevtools/u/join'),
                params: {
                    userName: this.userName,
                    email: this.email,
                    password: this.password,
                    password2: this.password2,
                    jcaptcha: this.jcaptcha
                }
            }).then(function(resp) {
                let ret = resp.data
                if (ret.code == -8) { // 后端表单验证失败
                    showValidMsgs(ret.data)
                } else if (ret.code == -1) {
                    toastr.error(ret.msg)
                } else {
                    this.user = ret.data
                    vmIndexNavbar.userName = this.user.userName
                    vmIndexNavbar.userURL = cookurl('/idevtools/u/' + this.user.userName)
                    vmIndexNavbar.logined = true
                    $("#user-join-modal").modal("hide")
                    vmUser.clearParams()
                }
            }).catch(function(error) {
                console.log(error)
            })
            vmUser.changeJCaptcha()
        },
        logout: function() {
            axios({
                method: 'post',
                url: cookurl('/idevtools/u/logout')
            }).then(function(resp) {
                let ret = resp.data
                if (ret.code == 1)
                    vmIndexNavbar.logined = false
                else
                    toastr.error(ret.msg)
            }).catch(function(error) {
                console.log(error)
            })
        },
        checkParams: function(procType) {
            let checkPass = true
            if (this.userName.trim() == 0) {
                toastr.warning("用户名不能为空!")
                checkPass = false
            }
            if (this.password.trim() == 0) {
                toastr.warning("密码不能为空!")
                checkPass = false
            }
            if (this.jcaptcha.trim() == 0) {
                toastr.warning("验证码不能为空!")
                checkPass = false
            }
            if (procType == 'join') {
                if (this.email.trim() == 0) {
                    toastr.warning("邮箱不能为空!")
                    checkPass = false
                }
                if (this.password != this.password2) {
                    toastr.warning("两次输入的密码不一致!")
                    checkPass = false
                }
            }
            return checkPass
        },
        clearParams: function() {
            this.userName = ''
            this.password = ''
            this.password2 = ''
            this.email = ''
            this.jcaptcha = ''
        }
    }
})