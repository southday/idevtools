/** 首页模块 js
 * @author southday
 * @date 2019.02.27
 * @version v0.1
 */

// 打开首页就请求用户数据
let user = {}
$(function() {
    user = getUser()
    if (user != null) {
        vmIndexNavbar.fillUser(user)
    } else {
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
            console.log(error)
        })
    }
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
