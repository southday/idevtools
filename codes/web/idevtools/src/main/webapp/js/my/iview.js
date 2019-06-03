/**
 * bootstrap treeview 配置
 * southday 2019.05.16
 */
let options = {
    color: "#428bca",
    selectedColor: "#ff0000",
    selectedBackColor: "#ffffff",
    showBorder: false,
    emptyIcon: "glyphicon glyphicon-file",
    data: [],
    loadingIcon: "fa fa-hourglass", // 懒加载过程中显示的沙漏字符图标
    lazyLoad: loaddata, // 结点懒加载
    onNodeSelected: showSelectNode // 显示选择的结点
}

/**
 * 渲染 Treeview
 * southday 2019.05.16
 */
function renderTreeview(data) {
    options.data = data
    $("#tree").treeview(options)
}

/**
 * 将后端传回的toolDir JSON对象转为 treeview 可识别的 node 对象
 * southday 2019.05.16
 */
function toTreeNode(index, dirItem) {
    let snode = {
        text: (dirItem.fileType == "DIR" ? dirItem.dirName : dirItem.toolName),
        idx: index, // 属性名不能取为index，否则无法使用，会出现index: undefined
        dirId: dirItem.dirId,
        toolId: dirItem.toolId,
        fileType: dirItem.fileType,
        website: dirItem.website,
        description: dirItem.description,
        lastUpdateTime: getDateTime(dirItem.lastUpdateTime),
        lazyLoad: (dirItem.fileType == "DIR")
    }
    return snode
}

/**
 * 显示选中的结点信息
 * southday 2019.05.16
 */
function showSelectNode(event, node) {
    if (node.fileType == 'DIR') {
        vmViewModule.curNode = vmViewModule.treeData[node.idx]
        return;
    }
    // 如果fileType是TOOL的话，从后端获取数据
    axios({
        method: 'get',
        url: cookurl('/c/tools/' + node.toolId)
    }).then(function(resp) {
        let ret = resp.data
        if (ret == 'FAILURE')
            toastr.warn(ret.msg)
        else {
            vmViewModule.curNode = ret.data
            vmViewModule.downloadLinks = JSON.parse(ret.data.downloadLinks)
        }
    }).catch(function(error) {
        console.log(error)
    })
}

/**
 * loaddata为点击懒加载节点目录时，运行的函数名称，把后端的数据添加到这个节点下面
 * southday 2019.05.16
 */
function loaddata(node, func) {
    axios({
        method: 'get',
        url: cookurl('/c/view/toolDirs/sub/' + node.dirId)
    }).then(function(resp) {
        if (resp.data.code == 'FAILURE')
            return;
        let dirs = resp.data.data
        let len = vmViewModule.treeData.length
        for (let i = 0; i < dirs.length; i++, len++) {
            let snode = toTreeNode(len, dirs[i])
            vmViewModule.treeData.push(snode)
            func(snode)
        }
    }).catch(function(error) {
        console.log(error)
    })
}

/**
 * iview.html 模块
 * southday 2019.05.13
 */
let vmViewModule = new Vue({
    el: "#view-module",
    data: {
        wd: '',
        selectedDirId: '',
        toolDirs: [],
        treeData: [],
        curNode: {},
        downloadLinks: {}
    },
    methods: {
        search: function() {
            axios({
                method: 'get',
                url: cookurl('/c/view/search'),
                params: {
                    wd: vmViewModule.wd
                }
            }).then(function(resp) {
                let ret = resp.data
                if (ret.code == 'FAILURE') {
                    toastr.info("未匹配到工具目录")
                    vmViewModule.toolDirs = []
                } else {
                    vmViewModule.toolDirs = ret.data
                }
            }).catch(function(error) {
                console.log(error)
            })
        },
        selectRow: function(toolDir) {
            vmViewModule.selectedDirId = toolDir.dirId
            vmViewModule.treeData = [toTreeNode(0, toolDir)]
            renderTreeview(vmViewModule.treeData)
        },
        isShowSelected: function(dirId) {
            return vmViewModule.selectedDirId == dirId
        }
    }
})