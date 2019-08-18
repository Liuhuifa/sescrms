
//公用的url
url="http://localhost:8080";

//首页公用js
$(function () {
    $("#user").on("click",function () {
        console.log("123")
        window.location.href=url+"/user_list";
    })
})