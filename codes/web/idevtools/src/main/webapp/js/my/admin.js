function getToken() {
    return localStorage.getItem("token")
}

function saveToken(token) {
    localStorage.setItem("token", token)
}

// 1.管理员登录
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
                console.log(resp.data)
                console.log(resp.data.data) // admin
                console.log(resp.headers.token) // token
                saveToken(resp.headers.token)
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
                headers: {'token': getToken()}
            }).then(function(resp) {
                console.log(resp.data)
                saveToken(resp.headers.token)
                vmAdminLogin.changeJCaptcha()
            }).catch(function(error) {
                console.log(error)
                vmAdminLogin.changeJCaptcha()
            })
        },
        showToastr: function() {
            toastr.warning('你有新消息了！');
        }
    }
})
