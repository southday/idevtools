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
let user = {}
$(function() {
    axios({
        method: 'get',
        url: cookurl('/idevtools/u/userInfo'),
        headers: {'token': getToken()}
    }).then(function (resp) {
        let ret = resp.data
        if (ret.code == 'SUCCESS') {
            user = ret.data
            vmIndexNavbar.fillUser(user)
            saveUser(user)
        }
    }).catch(function (error) {
        vmIndexNavbar.logined = false
        console.log(error)
    })
})

// index-navbar
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
                vmIndexNavbar.userURL = cookurl('/idevtools/u/' + user.userName)
                vmIndexNavbar.logined = true
            }
        }
    }
})

// isearch.html 模拟数据
let vmIDevTools = new Vue({
    el: '#idevtools',
    data: {
        tools: [
            {
                "description": "Package suited for development of Eclipse itself at Eclipse.org; based on the Eclipse Platform adding PDE, Git, Marketplace Client, source code and developer documentation.",
                "downloadLinks": "{\"macos\": \"http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-committers-photon-R-macosx-cocoa-x86_64.dmg\", \"win32\": \"http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-committers-photon-R-win32.zip\", \"win64\": \"http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-committers-photon-R-win32-x86_64.zip\", \"linux32\": \"http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-committers-photon-R-linux-gtk.tar.gz\", \"linux64\": \"http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-committers-photon-R-linux-gtk-x86_64.tar.gz\"}",
                "toolId": 1,
                "toolName": "Eclipse",
                "toolVersion": "4.8",
                "codeName": "Photon",
                "website": "https://www.eclipse.org/downloads/"
            },
            {
                "description": "Java SE Development Kit 8u191",
                "downloadLinks": "{\"Linux 86\": [\"https://download.oracle.com/otn-pub/java/jdk/8u191-b12/2787e4a523244c269598db4e85c51e0c/jdk-8u191-linux-i586.rpm\", \"https://download.oracle.com/otn-pub/java/jdk/8u191-b12/2787e4a523244c269598db4e85c51e0c/jdk-8u191-linux-i586.tar.gz\"], \"Linux x64\": [\"https://download.oracle.com/otn-pub/java/jdk/8u191-b12/2787e4a523244c269598db4e85c51e0c/jdk-8u191-linux-x64.rpm\", \"https://download.oracle.com/otn-pub/java/jdk/8u191-b12/2787e4a523244c269598db4e85c51e0c/jdk-8u191-linux-x64.tar.gz\"], \"Windows x64\": \"https://download.oracle.com/otn-pub/java/jdk/8u191-b12/2787e4a523244c269598db4e85c51e0c/jdk-8u191-windows-x64.exe\", \"Windows x86\": \"https://download.oracle.com/otn-pub/java/jdk/8u191-b12/2787e4a523244c269598db4e85c51e0c/jdk-8u191-windows-i586.exe\", \"Mac OS X x64\": \"https://download.oracle.com/otn-pub/java/jdk/8u191-b12/2787e4a523244c269598db4e85c51e0c/jdk-8u191-macosx-x64.dmg\", \"Linux ARM 32 Hard Float ABI\": \"https://download.oracle.com/otn-pub/java/jdk/8u191-b12/2787e4a523244c269598db4e85c51e0c/jdk-8u191-linux-arm32-vfp-hflt.tar.gz\", \"Linux ARM 64 Hard Float ABI\": \"https://download.oracle.com/otn-pub/java/jdk/8u191-b12/2787e4a523244c269598db4e85c51e0c/jdk-8u191-linux-arm64-vfp-hflt.tar.gz\"}",
                "toolId": 2,
                "toolName": "JDK",
                "toolVersion": "8u191",
                "codeName": "",
                "website": "https://www.oracle.com/technetwork/java/javase/downloads/isearch.html"
            }
        ]
    },
    methods: {
        showToolInfo: function(curTool) {
            vmToolInfo.show = true
            vmToolInfo.tool = curTool
            vmToolInfo.downloadLinks = JSON.parse(curTool.downloadLinks)
        }
    }
})

// tool-info 右侧边栏
let vmToolInfo = new Vue({
    el: "#tool-info",
    data: {
        show: false,
        tool: {},
        downloadLinks: {}
    }
})
