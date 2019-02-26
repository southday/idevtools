// 1.管理员登录
let vmAdminLogin = new Vue({
    el: "#admin-login",
    data: {
        adminName: '',
        password: ''
    },
    methods: {
        login: function() {
            axios({
                method: 'post',
                url: 'http://localhost:8080/idevtools/a/login',
                params: {
                    adminName: vmAdminLogin.adminName,
                    password: vmAdminLogin.password
                }
            }).then(function(resp) {
                if (resp.data == null || resp.data.length === 0)
                    alert("登陆失败，用户名或密码错误")
                else {
                    console.log(resp.data)
                    let cookie = document.cookie
                    console.log(cookie)
                }
            }).catch(function(error) {
                console.log(error)
            })
        }
    }
})
