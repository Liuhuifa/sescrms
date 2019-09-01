$(function () {
    $(".pagination ul li").on("click",function () {
        let pageNum = $(this).attr("value");
        window.location.href=url+"/customer/sleep/"+pageNum;
    });
})