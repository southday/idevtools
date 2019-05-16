let dateFmtyyyyMMddhhmmss = "yyyy-MM-dd hh:mm:ss";
let dateFmtyyyyMMdd = "yyyy-MM-dd";

Date.prototype.format = function (format) {
    let o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (let k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}

function getDateTime(timestamp) {
    return (new Date(timestamp)).format(dateFmtyyyyMMddhhmmss).toString();
}

function getDate(timestamp) {
    return (new Date(timestamp)).format(dateFmtyyyyMMdd).toString();
}