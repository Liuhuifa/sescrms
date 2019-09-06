$(function () {
    //翻页
    // $(".pagination ul li").on("click",function () {
    //     let pageNum = $(this).attr("value");
    //     window.location.href=url+"/customer/list?pageindex="+pageNum;
    // });

    // $("#submit").on("click",function () {
    //     // let data = $("#solr-form").serialize();
    //     $("#solr-form").submit();
    // })
    $("#add-customer").on("click",function () {
        window.location.href=url+"/customer/add"
    });

//    分配坐席


})

function customerTail(e) {
    let isLook = e.dataset.look;
    let tail = e.dataset.tail;

    if (isLook == 0) {
        $.ajax({
            url:"/customer/updateLook",
            type:"post",
            data:{
                cid:tail
            },
            dataType:'json',
            success:function (response) {
                if (response.code == 1) {
                    e.parentElement.parentElement.children.item(1).removeChild(e.parentElement.parentElement.children.item(1).children.item(0));
                }
            }
        })
    }

    window.location.href = url+"/customer/tail/"+tail;
}

/**
 * 查看
 */
// function tailInfo(e) {
//     let logid = e.dataset.show;
//     window.location.href=url+"/customer-log/tail-info/"+logid;
// }








