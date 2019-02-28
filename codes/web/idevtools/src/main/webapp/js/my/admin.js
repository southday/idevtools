let ret = {}
let ck = ''
// 1.管理员登录
let vmAdminLogin = new Vue({
    el: "#admin-login",
    data: {
        adminName: '',
        password: '',
        jcaptcha: '',
        jcaptchaURL: 'http://localhost:8080/idevtools/jcaptcha.jpg'
    },
    methods: {
        login: function() {
            axios({
                method: 'post',
                url: 'http://localhost:8080/idevtools/a/login',
                params: {
                    adminName: vmAdminLogin.adminName,
                    password: vmAdminLogin.password,
                    jcaptcha: vmAdminLogin.jcaptcha
                }
            }).then(function(resp) {
                ret = resp.data
                console.log(ret)
                ck = document.cookie
                console.log(ck)
            }).catch(function(error) {
                console.log(error)
            })
        },
        changeVerifyCode: function() {
            vmAdminLogin.jcaptchaURL = 'http://localhost:8080/idevtools/jcaptcha.jpg?r=' + (Math.random())
        }
    }
})
