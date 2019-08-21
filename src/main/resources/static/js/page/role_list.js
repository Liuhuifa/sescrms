$(function () {
    //翻页
    $("#paginations li").on("click",function () {
        var val=$(this).attr("value");
        console.log(val);
        window.location.href=url+"/role/listRoles1?pageindex="+val;
    })


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
        url:url+"/user-role/addOne",
        type:"post",
        data:{
            rid:val,
            pid:pid
        },
        dataType:"json",
        success:function (response) {
            if (response.code == 1) {
                var li = '<li id="authorized'+pid+'">'+pname+'<a href="javascript:;" onclick="removeroleforuser('+pid+','+val+',\''+pname+'\')">取消</a></li>';
                $("#myModal1 #authorized ul").append(li);
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
        url:url+"/user-role/del",
        type:"post",
        data:{
            rid:val,
            pid:pid
        },
        dataType:"json",
        success:function (response) {
            if (response.code == 1) {
                var li = '<li id="authorize'+rid+'">'+rname+'<a href="javascript:;" onclick="addroleforuser('+pid+','+val+',\''+pname+'\')">添加</a></li>';
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