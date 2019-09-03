
$(function () {
    var boo=false;
    //验证用户名
    $("#name").blur(function () {
        $(this).siblings(".clean").remove();
        $(this).siblings(".error").remove();
        $(this).parents(".control-group").attr("class","control-group");
        var username=$("#name").val();
        var usernameReg=/^[a-zA-Z0-9_-]{4,16}$/;
        if (username) {
            var flag=usernameReg.test(username);
            if (flag) {
                boo=true;
                $(this).parents(".control-group").attr("class","control-group success");
            }else {
                boo=false;
                $(this).parents(".control-group").attr("class","control-group error");
                $("#name").after("<div class='error'>" +
                                    "<span class='error'>请输入4到16为英文加数字的用户名</span>" +
                                    "</div>");
            }
            $("#name").after("<div class=\"clean\"></div>\n")
        }

    })
//    验证密码
    $("#password").blur(function () {
        $(this).siblings(".clean").remove();
        $(this).siblings(".error").remove();
        $(this).parents(".control-group").attr("class","control-group");
        var password=$("#password").val();
        var passwordReg=/^[a-zA-Z0-9_-]{4,16}$/;
        if (password) {
            var flag=passwordReg.test(password);
            if (flag) {
                boo=true;
                $(this).parents(".control-group").attr("class","control-group success");
            }else {
                boo=false;
                $(this).parents(".control-group").attr("class","control-group error");
                $(this).after("<div class='error'>" +
                    "<span class='error'>密码输入不合法</span>" +
                    "</div>");
            }
            $(this).after("<div class=\"clean\"></div>\n")
        }

    })
    //    手机号验证
    $("#phone").blur(function () {
        $(this).siblings(".clean").remove();
        $(this).siblings(".error").remove();
        $(this).parents(".control-group").attr("class","control-group");
        var phone=$("#phone").val();
        var phoneReg=/^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9])|(17[0,5-9]))\d{8}$/;
        if (phone) {
            var flag=phoneReg.test(phone);
            if (flag) {
                boo=true;
                $(this).parents(".control-group").attr("class","control-group success");
            }else {
                boo=false;
                $(this).parents(".control-group").attr("class","control-group error");
                $(this).after("<div class='error'>" +
                    "<span class='error'>手机号输入不合法</span>" +
                    "</div>");
            }
            $(this).after("<div class=\"clean\"></div>\n")
        }

    })
    //    邮箱验证
    $("#email").blur(function () {
        $(this).siblings(".clean").remove();
        $(this).siblings(".error").remove();
        $(this).parents(".control-group").attr("class","control-group");
        var email=$("#email").val();
        var emailReg=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (email) {
            var flag=emailReg.test(email);
            if (flag) {
                boo=true;
                $(this).parents(".control-group").attr("class","control-group success");
            }else {
                boo=false;
                $(this).parents(".control-group").attr("class","control-group error");
                $(this).after("<div class='error'>" +
                    "<span class='error'>邮箱输入不合法</span>" +
                    "</div>");
            }
            $(this).after("<div class=\"clean\"></div>\n")
        }

    })

//   点击提交

    $("#user-add").on("click",function () {
        if (boo == false) {
            //信息未完善
            Swal.fire(
                "提示",
                "请完善信息",
                "warning"
            )
        }else{
        //    提交代码
            var data = $("#contact-form").serialize();
            console.log(data)
            $.ajax({
                url:url+"/user/addUser",
                type:"post",
                data:data,
                dataType:"json",
                success:function (response) {
                    Swal.fire({
                        title: '提示!',
                        text: "是否返回用户列表!",
                        type: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: '是的!',
                        cancelButtonText:'继续添加'
                    }).then((result) => {
                        console.log(result.value);
                        if(result.value){
                            window.location.href=document.referrer;//返回并刷新
                        }else{
                            window.location.reload();
                        }
                    });
                }
            })
        }
    })

})