
//公用的url
url="http://localhost:8888";

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
//    跳转休眠公海页面
    $("#sleep").on('click',function () {
        window.location.href=url+"/customer/sleep/1";
    })
})

/**
 * 删除左右两端的空格
 * @param str
 * @returns {void | string | never}
 */
function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

/**
 * 全选和全部取消
 * @param e
 */
function checkAll(e) {
    // let dataset = e.currentTarget;
    // console.log(dataset)
    let dataset = e.checked;
    if (dataset) {
        //全选
        $(".checked").each(function (i, item) {
            item.checked=true;
        })
    }else{
        //全部取消
        $(".checked").each(function (i, item) {
            item.checked=false;
        })
    }
}
//单选影响全选
function checkOne(e) {
    let boo = true;
    let elem = e.checked;//当前节点
    let checked = $(".check-all")[0].checked;//查看全选框是否选中
    if (checked) {
        //全选情况下
        if (!elem) {
            $(".check-all")[0].checked=false;
        }
    }else{
        //在非全选情况下
        $(".checked").each(function (i, item) {
            if (!item.checked)boo=false;
        })
        if (boo) {
            $(".check-all")[0].checked=true;
        }else{
            $(".check-all")[0].checked=false;
        }
    }
}