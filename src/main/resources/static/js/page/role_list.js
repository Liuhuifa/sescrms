$(function () {
    //翻页
    $("#paginations li").on("click",function () {
        var val=$(this).attr("value");
        console.log(val);
        window.location.href=url+"/role/listRoles1?pageindex="+val;
    })
    //关闭模态框时删除节点
    $("#myModal").on("hide.bs.modal",function () {
        $("#myModal .modal-body #authorized ul li").remove()
        $("#myModal .modal-body #authorize ul li").remove()
    })

    //点击角色授权查询角色授权信息
    $(".roleaddper").on("click",function () {
        var val=$(this).attr("value");

        $("#myModal").modal("show");
        $.ajax({
            url:url+"/per/listPers1",
            type:"get",
            data:{
                rid:val
            },
            dataType:"json",
            success:function (response) {
                console.log(response);
                if (response.code == 1) {
                    console.log(response);
                    var li1='';
                    $.each(response.data.authorized,function (i, item) {
                        li1+='<li id="authorized'+item.pid+'">'+item.pname+'<a href="javascript:;" onclick="removeRerForRole('+item.pid+','+val+',\''+item.pname+'\')">取消</a></li>'
                    })
                    $("#myModal .modal-body #authorized ul").append(li1)

                    var li2='';
                    $.each(response.data.authorize,function (i, item) {
                        li2+='<li id="authorize'+item.pid+'"+'+item.pid+'>'+item.pname+'<a href="javascript:;" onclick="addPerForRole('+item.pid+','+val+',\''+item.pname+'\')">添加</a></li>'
                    })
                    $("#myModal .modal-body #authorize ul").append(li2)
                }else{

                }
            }
        })
    })

})
//为用户添加角色
function addPerForRole(pid, val,pname) {

    $.ajax({
        url:url+"/role-per/add",
        type:"post",
        data:{
            rid:val,
            pid:pid,
            // _method:"PUT"
        },
        dataType:"json",
        success:function (response) {
            if (response.code == 1) {
                var li = '<li id="authorized'+pid+'">'+pname+'<a href="javascript:;" onclick="removeRerForRole('+pid+','+val+',\''+pname+'\')">取消</a></li>';
                $("#myModal #authorized ul").append(li);
                $("#authorize"+pid).remove();
            }else{
                Swal.fire(
                    "提示",
                    "添加失败",
                    "warning"
                )
            }
        }
    })

}
//为用户取消角色
function removeRerForRole(pid, val,pname) {
    // var li = '<li id="authorize'+rid+'">'+rname+'<a href="javascript:;" onclick="addroleforuser('+rid+','+uid+',\''+rname+'\')">添加</a></li>';
    // $("#myModal1 #authorize ul").append(li);
    // $("#authorized"+rid).remove();
    $.ajax({
        url:url+"/role-per/del",
        type:"post",
        data:{
            rid:val,
            pid:pid,
            // _method:"DELETE"
        },
        dataType:"json",
        success:function (response) {
            if (response.code == 1) {
                var li = '<li id="authorize'+pid+'">'+pname+'<a href="javascript:;" onclick="addPerForRole('+pid+','+val+',\''+pname+'\')">添加</a></li>';
                $("#myModal #authorize ul").append(li);
                $("#authorized"+pid).remove();
            }else{
                Swal.fire(
                    "提示",
                    "添加失败",
                    "warning"
                )
            }
        }
    })
}