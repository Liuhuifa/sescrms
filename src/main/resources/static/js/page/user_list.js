
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
            if (response.code == 1) {
                var li='';
                $.each(response.data,function (i, item) {
                    li='<li>'+item.rname+'</li>'
                })
                $("#myModal .modal-body ul").append(li)
                $("#myModal").modal("show");
            }else{

            }

        }
    })
}
//用户授权
function giverole(uid) {
    $("#myModal1").modal("show");
    $("#myModal1 .modal-body #authorized ul li").remove();
    $("#myModal1 .modal-body #authorize ul li").remove();
    $.ajax({
        url:url+"/role/listRoles",
        type:"post",
        data:{
            uid:uid,
            flag:1
        },
        dataType:"json",
        success:function (response) {
            if (response.code == 1) {
                console.log(response);
                var li1='';
                $.each(response.data.authorized,function (i, item) {
                    li1+='<li id="authorized'+item.rid+'">'+item.rname+'<a href="javascript:;" onclick="removeroleforuser('+item.rid+','+uid+',\''+item.rname+'\')">取消</a></li>'
                })
                $("#myModal1 .modal-body #authorized ul").append(li1)

                var li2='';
                $.each(response.data.authorize,function (i, item) {
                    li2+='<li id="authorize'+item.rid+'"+'+item.rid+'>'+item.rname+'<a href="javascript:;" onclick="addroleforuser('+item.rid+','+uid+',\''+item.rname+'\')">添加</a></li>'
                })
                $("#myModal1 .modal-body #authorize ul").append(li2)
            }else{

            }

        }
    })
}
//为用户添加角色
function addroleforuser(rid, uid,rname) {
    var li = '<li id="authorized'+rid+'">'+rname+'<a href="javascript:;" onclick="removeroleforuser('+rid+','+uid+',\''+rname+'\')">取消</a></li>';
    $("#myModal1 #authorized ol").append(li);
    $("#authorize"+rid).remove();
}
//为用户取消角色
function removeroleforuser(rid, uid,rname) {
    var li = '<li id="authorize'+rid+'">'+rname+'<a href="javascript:;" onclick="addroleforuser('+rid+','+uid+',\''+rname+'\')">添加</a></li>';
    $("#myModal1 #authorize ol").append(li);
    $("#authorized"+rid).remove();
}