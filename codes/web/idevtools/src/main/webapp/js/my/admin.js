let ret = {}
// 1.管理员登录
let vmAdminLogin = new Vue({
    el: "#admin-login",
    data: {
        adminName: '',
        password: '',
        jcaptcha: '',
        jcaptchaURL: '/idevtools/jcaptcha.jpg'
    },
    methods: {
        login: function() {
            axios({
                method: 'post',
                url: '/idevtools/a/login',
                params: {
                    adminName: vmAdminLogin.adminName,
                    password: vmAdminLogin.password,
                    jcaptcha: vmAdminLogin.jcaptcha
                }
            }).then(function(resp) {
                ret = resp.data
                console.log(ret)
                console.log(document.cookie)
                vmAdminLogin.changeVerifyCode()
            }).catch(function(error) {
                console.log(error)
            })
        },
        changeVerifyCode: function() {
            vmAdminLogin.jcaptchaURL = '/idevtools/jcaptcha.jpg?r=' + (Math.random())
        }
    }
})
