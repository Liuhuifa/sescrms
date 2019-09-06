
//公用的url
url="http://192.168.1.57:8888";

//首页公用js
$(function () {
    //跳转用户列表页面
    $("#user").on("click",function () {
        window.location.href=url+"/user/listUser";
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
        window.location.href=url+"/customer/list?pageindex=1&flag=1";
    })
//    跳转休眠公海页面
    $("#sleep").on('click',function () {
        window.location.href=url+"/customer/list?pageindex=1&flag=2";
    })
//    跳转合作中客户列表页面
    $("#partner").on("click",function () {
        window.location.href=url+"/customer/list?pageindex=1&flag=3";
    })
//    跳转合作中客户列表页面
    $("#pause").on("click",function () {
        window.location.href=url+"/customer/list?pageindex=1&flag=4";
    })

    // //翻页
    $(".pagination ul li").on("click",function () {
        let pageNum = $(this).attr("value");
        // let flag = $(this).parents(".pagination").children('[name="page"]').val();
        let data = $("#hidden-form").serialize();
        // console.log(data)
        window.location.href=url+"/customer/list?pageindex="+pageNum+"&"+data;
    });

    $("#tzpage button").on("click",function () {
        // let page = $("#tzpage input").val();
        // let endPage = $("#end-page").attr('value');
        // if (page<1){
        //     $("#tzpage input").val(1);
        // }else if (page > endPage) {
        //     $("#tzpage input").val(endPage);
        // }
    })



//    未查看信息条数查询
    $.ajax({
        url:'/customer/countLook',
        type:'get',
        data:{},
        dataType:'json',
        success:function (response) {
            console.log(response)
            $('.pull-right .badge-primary').text(response.total)
            $('#realName').text(response.data);
        }
    })

    /**
     * 返回按钮
     */
    $("#go-back").on('click',function () {
        window.location.href=document.referrer;
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

$(function () {
    //分配坐席
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
                //添加到公海
                updateBelongs(customerArray,-1,1);
            }else if (flag == 'pull') {
                //专员自己在公海中拉取信息
                updateBelongs(customerArray,0,2);
            }else if (flag == 'delete') {
                //批量删除客户
                delAny(customerArray);
            }

            //去人按钮绑定提交事件


        }else{
            //没有选择提示他
            Swal.fire(
                "提示",
                "请选择客户!",
                "error"
            )
        }
    })
})
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
                    Swal.fire(
                        '提示',
                        '分配失败',
                        'warning'
                    );
                }
            }
        })
    })
    return p;
}

/**
 * 查看
 */
function tailInfo(e) {
    let logid = e.dataset.show;
    window.location.href=url+"/customer-log/tail-info/"+logid;
}

/**
 * 添加到休眠公海
 * @param customerArray
 */
function updateBelongs(customerArray,uid,flag) {
    //如果checked不为空
    let question;
    let success;
    if (flag == 1) {
        //添加休眠公海
        question='你确定要添加到休眠公海吗?'
        success='成功添加到休眠公海!!!'
    }else if (flag==2) {
        //用户在公海自己拉取信息
        question='你确定要获取这些信息?'
        success='成功添加到您的客户列表!!!'
    }
    Swal.fire({
        title: '提示',
        text: question,
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
                    uid:uid,
                    ids:customerArray
                },
                dataType: 'json',
                success:function (response) {
                    if (response.code == 1) {
                        Swal.fire(
                            '提示',
                            success,
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

/**
 * 批量删除客户
 * @param customerArray
 */
function delAny(customerArray) {
    Swal.fire({
        title: '提示',
        text: '你确定要删除所选的客户吗?',
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '是',
        cancelButtonText:'否',
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url:url+"/customer/delAny",
                type: "post",
                traditional: true,
                data: {
                    ids:customerArray
                },
                dataType: 'json',
                success:function (response) {
                    if (response.code == 1) {
                        Swal.fire(
                            '提示',
                            '删除成功',
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

/**
 * 监听键盘事件
 */
$(function () {

    $("#tzpage input").keyup(function (event) {
        let page = $("#tzpage input").val();
        let endPage = $("#end-page").parent('li').attr('value');
        if (event.keyCode == 48 || event.keyCode == 49 ||
            event.keyCode == 50 || event.keyCode == 51 ||
            event.keyCode == 52 || event.keyCode == 53 ||
            event.keyCode == 54 || event.keyCode == 55 ||
            event.keyCode == 56 || event.keyCode == 57 ||
            event.keyCode == 96 || event.keyCode == 97 ||
            event.keyCode == 98 || event.keyCode == 99 ||
            event.keyCode == 100 || event.keyCode == 101 ||
            event.keyCode == 102 || event.keyCode == 103 ||
            event.keyCode == 104 || event.keyCode == 105
        ){

            if (page<1){
                $("#tzpage input").val(1);
            }
            if (Number(page) > Number(endPage)) {

                $("#tzpage input").val(endPage);
            }
        }

    })
})