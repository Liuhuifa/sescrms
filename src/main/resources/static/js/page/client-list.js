$(function () {
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


})

function customerTail(e) {
    let tail = e.dataset.tail;
    window.location.href=url+"/customer/tail/"+tail;
}

/**
 * 查看
 */
// function tailInfo(e) {
//     let logid = e.dataset.show;
//     window.location.href=url+"/customer-log/tail-info/"+logid;
// }





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


