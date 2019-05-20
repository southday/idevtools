/** 管理员模块 js
 * @author southday
 * @date 2019.03.05
 * @version v0.2
 */

/**
 * 管理员登陆模态框 /pages/admin/login.html
 * southday 2019.05.17
 */
let vmAdminLogin = new Vue({
    el: "#admin-login",
    data: {
        adminName: '',
        password: '',
        jcaptcha: '',
        jcaptchaURL: cookurl('/idevtools/jcaptcha.jpg')
    },
    methods: {
        login: function() {
            axios({
                method: 'post',
                url: cookurl('/idevtools/a/login'),
                params: {
                    adminName: vmAdminLogin.adminName,
                    password: vmAdminLogin.password,
                    jcaptcha: vmAdminLogin.jcaptcha
                }
            }).then(function(resp) {
                let ret = resp.data
                if (ret.code == 'VALID_ERROR') {
                    showValidMsgs(ret.data)
                } else if (ret.code == 'FAILURE') {
                    toastr.error(ret.msg)
                } else {
                    saveAdmin(ret.data)
                    saveAdminToken(resp.headers.token)
                    window.location.href = "/idevtools/pages/admin/index.html"
                }
                vmAdminLogin.changeJCaptcha()
            }).catch(function(error) {
                console.log(error)
                vmAdminLogin.changeJCaptcha()
            })
        },
        changeJCaptcha: function() {
            vmAdminLogin.jcaptchaURL = changeVerifyCode()
        },
        logout: function() {
            axios({
                method: 'post',
                url: cookurl('/idevtools/a/logout'),
                headers: {'token': getAdminToken()}
            }).then(function(resp) {
                let ret = resp.data
                if (ret.code == "SUCCESS") {
                    saveAdmin(null)
                    saveAdminToken(null)
                } else {
                    toastr.error(ret.msg)
                }
            }).catch(function(error) {
                console.log(error)
            })
        }
    }
})
