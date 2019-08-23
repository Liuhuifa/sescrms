/**
 * 添加角色页面跳转
 */
function role_add() {
    // window.location.href=url+"/templates/role/role-add.html";
    // window.location.href=url+"/user_list.html";window.location.href=url+"/templates/role/role-add.html";
    window.location.href=url+"/per/listPers2";
}

$(function () {
    $("#role-add").on("click",function () {
        var rname = $("#rname").val();
        var pids = new Array();
        $("#uc_03 li").each(function (i,item) {
            if ($(this).attr("class") == 'selected') {
                var pid = $(this).attr("value");
                console.log(pid)
                pids[i]=pid;
            }
        })
        var  boo =verifyRole();
        if (boo) {
            Swal.fire({
                title: '提示',
                text: "确定添加该角色吗?",
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '确定!',
                cancelButtonText:'返回'
            }).then((result) => {
                if (result.value) {
                $.ajax({
                    url:url+"/role/addRole",
                    type:"post",
                    data:{
                        rname:rname,
                        pers:JSON.stringify(pids)
                    },
                    dataType:"json",
                    success:function (response) {
                        if(response.code==1){
                            Swal.fire({
                                title: '提示?',
                                text: "是否返回角色列表!",
                                type: 'warning',
                                showCancelButton: true,
                                confirmButtonColor: '#3085d6',
                                cancelButtonColor: '#d33',
                                confirmButtonText: '是的!',
                                cancelButtonText:'继续添加'
                            }).then((result) => {
                                console.log(result.value);
                            if(result.value){
                                window.location.href=url+"/role/listRoles1";
                            }else{
                                role_add()
                            }
                        });
                        }
                    }
                })//ajax
            }//if
        })//then
        }//else

    })

})

function verifyRole(){
    var boo=true;
    $("#error").remove()
    $("#rname").parents(".control-group").attr("class","control-group");
    var rname = $("#rname").val();
    $.ajax({
        url:url+"/role/selectOne",
        type:"get",
        async:false,
        data:{
            rname:rname
        },
        dataType:'json',
        success:function (response) {
            console.log(response)
            if (response.code == 1) {
                if (response.data){
                    $("#rname").parents(".control-group").attr("class","control-group error");
                    $("#rname").after("<div class='error' id='error'>" +
                        "<span class='error'>该角色已存在不可重复添加</span>" +
                        "</div>");
                    boo = false;
                }
            }
        }
    })
    return boo;
}

