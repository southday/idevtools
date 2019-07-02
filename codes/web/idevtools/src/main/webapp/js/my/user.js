/** 用户模块 js
 * @author southday
 * @date 2019.02.27
 * @version v0.1
 */

let vmUser = new Vue({
    el: "#user",
    data: {
        jcaptchaURL: cookurl('/jcaptcha.jpg'),
        jcaptcha: '',
        userName: '',
        email: '',
        password: '',
        password2: '',
        suggestion: {
            content: ''
        },
        recommendation: {
            toolName: '',
            website: '',
            reason: ''
        }
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
                url: cookurl('/u/join'),
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
                    saveUserToken(resp.headers.token)
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
                url: cookurl('/u/login'),
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
                    saveUserToken(resp.headers.token)
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
                url: cookurl('/u/logout'),
                headers: {'token': getUserToken()}
            }).then(function(resp) {
                let ret = resp.data
                if (ret.code == "SUCCESS") {
                    vmIndexNavbar.logined = false
                    saveUser(null)
                    saveUserToken(null)
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
        },
        suggest: function() {
            axios({
                method: 'post',
                url: cookurl('/u/suggestions'),
                params: {
                    content: vmUser.suggestion.content,
                    jcaptcha: vmUser.jcaptcha
                },
                headers: {'token': getUserToken()}
            }).then(function(resp) {
                let ret = resp.data
                if (ret.code == "VALID_ERROR") { // 后端表单验证失败
                    showValidMsgs(ret.data)
                } else if (ret.code == "FAILURE") {
                    toastr.error(ret.msg)
                } else {
                    toastr.success(ret.msg)
                    $("#user-suggestion-modal").modal("hide")
                    vmUser.clearParams()
                }
                vmUser.changeJCaptcha()
            }).catch(function(error) {
                console.log(error)
                vmUser.changeJCaptcha()
            })
        },
        recommendTool: function() {
            axios({
                method: 'post',
                url: cookurl('/u/recommendations'),
                params: {
                    toolName: vmUser.recommendation.toolName,
                    website: vmUser.recommendation.website,
                    reason: vmUser.recommendation.reason,
                    jcaptcha: vmUser.jcaptcha
                },
                headers: {'token': getUserToken()}
            }).then(function(resp) {
                let ret = resp.data
                if (ret.code == "VALID_ERROR") { // 后端表单验证失败
                    showValidMsgs(ret.data)
                } else if (ret.code == "FAILURE") {
                    toastr.error(ret.msg)
                } else {
                    toastr.success(ret.msg)
                    $("#user-recommendation-modal").modal("hide")
                    vmUser.clearParams()
                }
                vmUser.changeJCaptcha()
            }).catch(function(error) {
                console.log(error)
                vmUser.changeJCaptcha()
            })
        }
    }
})