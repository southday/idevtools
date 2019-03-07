/** 用户模块 js
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
    },
    methods: {
        changeJCaptcha: function() {
            vmUser.jcaptchaURL = changeVerifyCode()
        },
        join: function() {
            if (!vmUser.checkParams('join'))
                return
            axios({
                method: 'post',
                url: cookurl('/idevtools/u/join'),
                params: {
                    userName: vmUser.userName,
                    email: vmUser.email,
                    password: vmUser.password,
                    password2: vmUser.password2,
                    jcaptcha: vmUser.jcaptcha
                }
            }).then(function(resp) {
                let ret = resp.data
                if (ret.code == "VALID_ERROR") { // 后端表单验证失败
                    showValidMsgs(ret.data)
                } else if (ret.code == "FAILURE") {
                    toastr.error(ret.msg)
                } else {
                    $("#user-join-modal").modal("hide")
                    let vUser = ret.data
                    vmIndexNavbar.fillUser(vUser)
                    saveUser(vUser)
                    saveToken(resp.headers.token)
                    vmUser.clearParams()
                }
                vmUser.changeJCaptcha()
            }).catch(function(error) {
                console.log(error)
                vmUser.changeJCaptcha()
            })
        },
        login: function() {
            if (!vmUser.checkParams('login'))
                return
            axios({
                method: 'post',
                url: cookurl('/idevtools/u/login'),
                params: {
                    userName: vmUser.userName,
                    password: vmUser.password,
                    jcaptcha: vmUser.jcaptcha
                }
            }).then(function(resp) {
                let ret = resp.data
                if (ret.code == "VALID_ERROR") { // 后端表单验证失败
                    showValidMsgs(ret.data)
                } else if (ret.code == "FAILURE") {
                    toastr.error(ret.msg)
                } else {
                    $("#user-login-modal").modal("hide")
                    let vUser = ret.data
                    vmIndexNavbar.fillUser(vUser)
                    saveUser(vUser)
                    saveToken(resp.headers.token)
                    vmUser.clearParams()
                }
                vmUser.changeJCaptcha()
            }).catch(function(error) {
                console.log(error)
                vmUser.changeJCaptcha()
            })
        },
        logout: function() {
            axios({
                method: 'post',
                url: cookurl('/idevtools/u/logout'),
                headers: {'token': getToken()}
            }).then(function(resp) {
                let ret = resp.data
                if (ret.code == "SUCCESS") {
                    vmIndexNavbar.logined = false
                    saveUser(null)
                    saveToken(resp.headers.token)
                } else {
                    toastr.error(ret.msg)
                }
            }).catch(function(error) {
                console.log(error)
            })
        },
        checkParams: function(procType) {
            let checkPass = true
            if (vmUser.userName.trim() == 0) {
                toastr.warning("用户名不能为空!")
                checkPass = false
            }
            if (vmUser.password.trim() == 0) {
                toastr.warning("密码不能为空!")
                checkPass = false
            }
            if (vmUser.jcaptcha.trim() == 0) {
                toastr.warning("验证码不能为空!")
                checkPass = false
            }
            if (procType == 'join') {
                if (vmUser.email.trim() == 0) {
                    toastr.warning("邮箱不能为空!")
                    checkPass = false
                }
                if (vmUser.password != vmUser.password2) {
                    toastr.warning("两次输入的密码不一致!")
                    checkPass = false
                }
            }
            return checkPass
        },
        clearParams: function() {
            vmUser.userName = ''
            vmUser.password = ''
            vmUser.password2 = ''
            vmUser.email = ''
            vmUser.jcaptcha = ''
        }
    }
})