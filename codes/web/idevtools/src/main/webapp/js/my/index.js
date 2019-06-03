/** 首页模块 js
 * @author southday
 * @date 2019.02.27
 * @version v0.1
 */

/* 强制要求每次打开首页都要去请求数据，而不管之前是否已经saveUser(user)
 * 原因：
 * 如果写为：
 *      user = getUser()
 *      if (user != null) {...}
 * 则：
 * 1.如果user保存在sessionStorage中，不同标签页(tab)或不同页面，不共用sessionStorage，
 *   可能造成标tab1中用户退出了，而tab2中用户还没退出，此时刷新tab2中的页面，也不会自动退出，并且此时用户进行其他操作token会验证失败；
 * 2.如果user保存在localStorage中，当服务器重启后，会从localStorage中拿到用户数据，显示已登录，其实token会验证失败，出现和<1>一样的情况；
 */
$(function() {
    axios({
        method: 'get',
        url: cookurl('/u/userInfo'),
        headers: {'token': getUserToken()}
    }).then(function(resp) {
        let ret = resp.data
        if (ret.code == 'SUCCESS') {
            vmIndexNavbar.fillUser(ret.data)
            saveUser(ret.data)
        }
    }).catch(function(error) {
        vmIndexNavbar.logined = false
        console.log(error)
    })
})

/**
 * index-navbar
 * southday 2019.03.12
 */
let vmIndexNavbar = new Vue({
    el: "#index-navbar",
    data: {
        logined: false,
        userURL: '#',
        userName: ''
    },
    methods: {
        logout: function() {
            vmUser.logout()
        },
        fillUser: function(user) {
            if (!$.isEmptyObject(user)) {
                vmIndexNavbar.userName = user.userName
                vmIndexNavbar.userURL = cookurl('/u/' + user.userName)
                vmIndexNavbar.logined = true
            }
        }
    }
})

/**
 * 对搜索到的工具进行排序， southday 2019.03.12
 * 排序规则：
 * 1) 如果 下载量不相等，则按下载量降序排序，否则(2)
 * 2) 如果 收藏量不相等，则按收藏量降序排序，否则(3)
 * 3) 按toolName字符串默认排序
 * @param tools
 * @returns {*}
 */
function sortTools(tools) {
    return tools.sort(function(a, b) {
        let d1 = a['downloadCount']
        let c1 = a['collectCount']
        let d2 = b['downloadCount']
        let c2 = b['collectCount']
        if (d1 != d2)
            return d2 - d1
        else if (c1 != c2)
            return c2 - c1
        else
            return a['toolName'].localeCompare(b['toolName'], 'zh-CN')
    })
}

/**
 * isearch.html 搜索模块
 * southday 2019.03.12
 */
let vmSearchModule = new Vue({
    el: '#search-module',
    data: {
        wd: '',
        tools: []
    },
    computed: {
      sortedTools: function() {
          return sortTools(this.tools)
      }
    },
    methods: {
        showToolInfo: function(toolId) {
            axios({
                method: 'get',
                url: cookurl('/c/tools/' + toolId)
            }).then(function(resp) {
                let ret = resp.data
                if (ret == 'FAILURE')
                    toastr.warn(ret.msg)
                else {
                    vmToolInfo.tool = ret.data
                    vmToolInfo.downloadLinks = JSON.parse(ret.data.downloadLinks)
                    vmToolInfo.show = true
                }
            }).catch(function(error) {
                console.log(error)
            })
        },
        search: function() {
            axios({
                method: 'get',
                url: cookurl('/c/search/search'),
                params: {
                    wd: vmSearchModule.wd
                }
            }).then(function(resp) {
                let ret = resp.data
                if (ret.code == 'FAILURE') {
                    toastr.info("未匹配到工具")
                    vmSearchModule.tools = []
                } else {
                    vmSearchModule.tools = ret.data
                }
            }).catch(function(error) {
                console.log(error)
            })
        }
    }
})

/**
 * tool-info 右侧边栏
 * southday 2019.03.12
 */
let vmToolInfo = new Vue({
    el: "#tool-info",
    data: {
        show: false,
        tool: {},
        downloadLinks: {}
    }
})

/**
 * 只有在用户登陆后才会显示目标模态框，否则提示用户登陆，并弹出登录框
 * southday 2019.05.17
 * @param modalId
 */
function showModalNeedLogined(modalId) {
    // $(modalId).modal("show")
    if (getUser() == null) {
        toastr.info("请先登录")
        $("#user-login-modal").modal("show")
    } else {
        $(modalId).modal("show")
    }
}