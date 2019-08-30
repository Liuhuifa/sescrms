$(function () {
    // alert([[${solr.group}]])
    //查询所有坐席
    // $("[name='group']").on("change",function () {
    //     let group = $(this).val();
    //     let elem='';
    //     $("[name='uname']").remove()
    //     $("#uname").remove()
    //     if ( group==1 ){
    //         $.ajax({
    //             url:url+"/user/allUser",
    //             type:"post",
    //             data:{},
    //             dataType:"json",
    //             success:function (response) {
    //                 elem+="<label id='uname' class=\"control-label\" style=\"display: inline-block\">坐席:</label>";
    //                 elem+='<select name="belongs" style="margin: 0px">';
    //                 elem+='<option value="-3" selected>全部</option>';
    //                 $.each(response.list,function (i, item) {
    //                     elem+='<option value="'+item.uid+'">'+item.uname+'</option>'
    //                 })
    //                 elem+='</select>';
    //                 $("[name='group']").after(elem);
    //             }
    //         })
    //     }
    // });
    //翻页
    $(".pagination ul li").on("click",function () {
        let pageNum = $(this).attr("value");
        window.location.href=url+"/customer/list?pageindex="+pageNum;
    });

    // $("#submit").on("click",function () {
    //     // let data = $("#solr-form").serialize();
    //     $("#solr-form").submit();
    // })
    $("#add-customer").on("click",function () {
        window.location.href=url+"/templates/client/client-add.html"
    });

//    分配坐席
    $("#handleCustomer").on("click",function () {
        let checkedCustomer = $(".checked");//选择的客户
        let customerArray = new Array();//数组,存放客户id
        $.each(checkedCustomer,function (i, item) {
            if (item.checked == true) {
                customerArray.push(item.value.toString());
            }
        });


        //如果有选择
        if (customerArray.length >0) {
            userByRole(1)
            //去人按钮绑定提交事件
            $("#primary").on("click",function () {
                let uid = $("[name='uid']");
                let checked;
                $.each(uid,function (i, item) {
                    if (item.checked) {
                        checked=item.dataset.uid
                    }
                });
                if (checked) {
                    //如果checked不为空
                    $.ajax({
                        url:url+"/customer/update",
                        type: "post",
                        traditional: true,
                        data: {
                            uid:checked,
                            ids:customerArray
                        },
                        dataType: 'json',
                        success:function (response) {
                            if (response.code == 1) {
                                window.location.reload();
                            }
                        }
                    });
                }else{
                    Swal.fire(
                        '提示',
                        '请选择市场专员',
                        'warning'
                    )
                }
            })
        }else{
            //没有选择提示他
            Swal.fire(
                "提示",
                "请选择要分配的客户!",
                "error"
            )
        }
    })

})

function customerTail(e) {
    let tail = e.dataset.tail;
    window.location.href=url+"/customer/tail/"+tail;
}

/**
 * 查看
 */
function tailInfo(e) {
    let logid = e.dataset.show;
    window.location.href=url+"/customer-log/tail-info/"+logid;
}

/**
 * 查看拥有角色的用户列表
 */
function userByRole(pageindex) {
    let uname = $("#uname").val();
    if (pageindex == 0) {
        pageindex=1;
    }
    if (!uname){
        uname=null;
    }
    $(".user-role").remove();
    $("#myModal .modal-body .pagination ul li").remove();
    $.ajax({
        url:url+"/user/listUserByRole/6/"+pageindex+'/'+uname,//6是市场专员的rid
        type:"get",
        data:{},
        dataType:"json",
        success:function (response) {
            console.log(response)
            if (response.code == 1) {
                let tr = '';
                $.each(response.data.list,function (i, item) {
                    console.log(item)
                    tr+='<tr class="user-role">\n' +
                        '<th><input name="uid" type="radio" data-uid="'+item.uid+'"/> </th>\n' +
                        '<th>'+item.realName+'</th>\n' +
                        '<th>'+item.phone+'</th>\n' +
                        '</tr>';
                });
                $(".modal-body table").append(tr);
                let li='';
                li+='<li th:data-value="'+response.data.prePage+'" onclick="userByRole('+response.data.prePage+')"><a href="javascript:;">上一页</a></li>'+
                    '<li th:data-value="'+response.data.nextPage+'" onclick="userByRole('+response.data.nextPage+')"><a href="javascript:;">下一页</a></li>'
                $("#myModal .modal-body .pagination ul").append(li);
                $("#myModal").modal("show");
            }else{

            }
        }
    })
}


