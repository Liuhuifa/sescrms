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








