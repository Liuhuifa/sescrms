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
    $(".handleCustomer").on("click",function (e) {
        let checkedCustomer = $(".checked");//选择的客户
        let customerArray = new Array();//数组,存放客户id
        $.each(checkedCustomer,function (i, item) {
            if (item.checked == true) {
                customerArray.push(item.value.toString());
            }
        });


        //如果有选择
        if (customerArray.length >0) {
            let flag = e.target.dataset.flag;
            if (flag == 'allocation') {
                userByRole(1).then(function (data) {
                    if (data == true) {
                        allocation(customerArray);
                    }
                })
            }else if (flag == 'sleep') {
                sleep(customerArray);
            }

            //去人按钮绑定提交事件


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

    let p = new Promise(function (resolve, reject) {
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
            async:false,
            data:{},
            dataType:"json",
            success:function (response) {
                if (response.code == 1) {
                    let tr = '';
                    $.each(response.data.list,function (i, item) {
                        tr+='<tr class="user-role">\n' +
                            '<th><input name="uid" type="radio" data-uid="'+item.uid+'"/> </th>\n' +
                            '<th class="name9">'+item.realName+'</th>\n' +
                            '<th>'+item.phone+'</th>\n' +
                            '</tr>';
                    });
                    $(".modal-body table").append(tr);
                    let li='';
                    li+='<li th:data-value="'+response.data.prePage+'" onclick="userByRole('+response.data.prePage+')"><a href="javascript:;">上一页</a></li>'+
                        '<li th:data-value="'+response.data.nextPage+'" onclick="userByRole('+response.data.nextPage+')"><a href="javascript:;">下一页</a></li>'
                    $("#myModal .modal-body .pagination ul").append(li);
                    resolve(true);
                    $("#myModal").modal("show");
                }else{

                }
            }
        })
    })
    return p;
}

/**
 * 分配到坐席
 */
function allocation(customerArray) {

    $("#primary").on("click",function () {
        let uid = $("[name='uid']");//当前页所有的列表节点
        let checked;//选择的专员id
        let name9;//选择的专员名字

        $.each(uid,function (i, item) {
            if (item.checked) {
                checked=item.dataset.uid
                name9 = $(item).parent().siblings('.name9').text()
            }
        });



        if (checked) {
            //如果checked不为空
            Swal.fire({
                title: '提示',
                html: "你确定要添加给<strong>"+name9+"</strong>?",
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '是',
                cancelButtonText:'否',
            }).then((result) => {
                if (result.value) {
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
                                Swal.fire(
                                    '提示',
                                    '分配成功!!!',
                                    'success'
                                ).then((result)=>{
                                    window.location.reload();
                                })
                            }
                        }
                    });
                }

            })

        }else{
            Swal.fire(
                '提示',
                '请选择市场专员',
                'warning'
            )
        }
    })

}

/**
 * 添加到休眠公海
 * @param customerArray
 */
function sleep(customerArray) {
        //如果checked不为空
    Swal.fire({
        title: '提示',
        text: "你确定要添加到休眠公海吗?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '是',
        cancelButtonText:'否',
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url:url+"/customer/update",
                type: "post",
                traditional: true,
                data: {
                    uid:-1,
                    ids:customerArray
                },
                dataType: 'json',
                success:function (response) {
                    if (response.code == 1) {
                        Swal.fire(
                            '提示',
                            '成功添加到休眠公海!!!',
                            'success'
                        ).then((result)=>{
                            window.location.reload();
                        })
                    }
                }
            });
        }

    })
}


