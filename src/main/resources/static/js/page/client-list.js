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
        let checkedCustomer = $(".checked");
        let customerArray = new Array();
        $.each(checkedCustomer,function (i, item) {
            if (item.checked == true) {
                customerArray.push(item.value);
            }
        })
        //如果有选择
        if (customerArray.length >0) {
            $("#myModal").modal("show");
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



