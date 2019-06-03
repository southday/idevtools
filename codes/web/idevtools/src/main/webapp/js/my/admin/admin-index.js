/* 每次访问管理员模块时，都会用token去获取管理员数据，若获取失败则跳转到管理员登录页面
 * southday 2019.05.17
 */
$(function() {
    axios({
        method: 'get',
        url: cookurl('/a/adminInfo'),
        headers: {'token': getAdminToken()}
    }).then(function(resp) {
        let ret = resp.data
        if (ret.code == 'SUCCESS') {
            saveAdmin(ret.data)
        } else {
            console.log(ret)
            window.location.href = "/pages/admin/login.html"
        }
    }).catch(function(error) {
        console.log(error)
    })
})