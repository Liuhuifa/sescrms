
//公用的url
url="http://localhost:8080";

//首页公用js
$(function () {
    //跳转用户列表页面
    $("#user").on("click",function () {
        window.location.href=url+"/user_list.html";
    })
//    跳转角色列表页面
    $("#role").on("click",function () {
        window.location.href=url+"/role/listRoles1";
    })
//    跳转权限列表页面
    $("#permission").on("click",function () {
        window.location.href=url+"/per/listPers";
    })
//    跳转客户列表页面
    $("#customers").on("click",function () {
        window.location.href=url+"/customer/list?pageindex=1";
    })
})

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}