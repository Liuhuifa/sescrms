$(function () {
    $("#paginations li").on("click",function () {
        let val=$(this).attr("value");

        window.location.href=url+"/per/listPers?pageindex="+val;
    })
})