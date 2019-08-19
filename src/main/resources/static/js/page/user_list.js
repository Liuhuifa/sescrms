
$(function () {

    Theme.init ();
    //刷新用户列表
    list_user(1,1);
});

$(function () {

})

//获取用户列表
function list_user(pageindex) {
    $("#paginations .pagination ul li").remove();
    $("#tables tbody tr").remove();
    $.ajax({
        url:url+"/user/listUser",
        type:"post",
        data:{
            pageNum:pageindex,
            pageSize:1
        },
        dataType:"json",
        success:function (response) {
            console.log(response)
            var li="";
            $.each(response.list,function (i, item) {
                li="<tr>\n" +
                    "    <th>"+(++i)+"</th>\n" +
                    "    <th>"+(item.realName)+"</th>\n" +
                    "    <th>"+(item.uname)+"</th>\n" +
                    "    <th>"+(item.phone)+"</th>\n" +
                    "    <th>"+(item.email)+"</th>\n" +
                    "    <th>"+(item.createTime)+"</th>\n" +
                    "    <th>"+(item.updateTime)+"</th>\n" +
                    "    <th><a href=\"javascript:;\" class=\"btn btn-small btn-secondary\" onclick='giverole("+item.uid+")'>用户授权</a>&nbsp;&nbsp;" +
                    "        <a href=\"javascript:;\" class=\"btn btn-small btn-inverse\" onclick='seerole("+item.uid+")'>查看角色</a>" +
                    "</th>\n" +
                    "</tr>";
            })
            //添加节点
            $("#tables tbody").append(li);

            //分页节点
            var pages="";
            if (response.hasPreviousPage == true){
                pages+="<li><a href=\"javascript:;\" onclick='list_user("+response.prePage+")'>上一页</a></li>";
            }
            $.each(response.navigatepageNums,function (i, item) {
                if (pageindex == item) {
                    pages+=" <li class='active'><a href=\"javascript:;\" onclick='list_user("+item+")'>"+item+"</a></li>";
                }else{
                    pages+=" <li><a href=\"javascript:;\" onclick='list_user("+item+")'>"+item+"</a></li>";
                }
            })
            if (response.hasNextPage == true) {
                pages+="<li><a href=\"javascript:;\" onclick='list_user("+response.nextPage+")'>下一页</a></li>"
            }

            $("#paginations .pagination ul").append(pages);

        }
    })
}
//添加用户跳转页面
function user_add() {
    window.location.href=url+"/user-add";
}
//查看角色弹出模态框
function seerole(uid) {
    $("#myModal .modal-body ul li").remove();
    $.ajax({
        url:url+"/role/listRoles",
        type:"post",
        data:{
            uid:uid
        },
        dataType:"json",
        success:function (response) {
            $("#myModal").modal("show");
            var li='';
            console.log(response)
            if (response != null) {
                $.each(response,function (i, item) {
                    li='<li>'+item.rname+'</li>'
                })
            }else{

            }
            $("#myModal .modal-body ul").append(li)
        }
    })
}
//用户授权
function giverole(uid) {

}