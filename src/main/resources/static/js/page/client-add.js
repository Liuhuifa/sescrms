$(function () {
    let boo;
    //验证名字
    $("#name").on("blur",function () {
        boo = nameReg()
        console.log('name:'+boo)
    });
    //验证地址是否填写
    $("#address").on("blur",function () {
        boo = addressReg();
        console.log('address:'+boo)
    });


    //验证电话和手机号
    $("#tel").on("blur",function () {
        boo = telReg();
        console.log('tel:'+boo)
    });
    // 验证企业名称
    $("#cfrom").on("blur",function () {
        boo = cfromReg();
        console.log('cfrom:'+boo)
    });

    $("#customer-add").on("click",function () {
        handleCilck(boo);
    })
})
function nameReg() {
    //验证姓名
    let name = trim($("#name").val());
    let eyes=false;
    $("#name").siblings(".error").remove();
    if (name) {
        eyes=true;
        $("#name").parents(".control-group").attr("class","control-group success");
    }else{
        $("#name").parents(".control-group").attr("class","control-group error");
        $("#name").after("<div class='error'>" +
            "<span class='error'>请填写客户姓名！！！</span>" +
            "</div>");
    }
    return eyes
}
function addressReg() {
    //验证地址
    let address = trim($("#address").val());
    let eyes=false;
    $("#address").siblings(".error").remove();
    if (address) {
        eyes=true;
        $("#address").parents(".control-group").attr("class","control-group success");
    }else{
        $("#address").parents(".control-group").attr("class","control-group error");
        $("#address").after("<div class='error'>" +
            "<span class='error'>地址不能为空！！！</span>" +
            "</div>");
    }
    return eyes;
}
function telReg() {
    let tel = trim($("#tel").val());
    let eyes=false;
    const phoneReg=/^1(3|4|5|6|7|8|9)\d{9}$/;
    const telReg=/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
    let boo;
    let flag;
    console.log(tel)
    $("#tel").siblings(".error").remove();
    if (tel) {
        //先验证手机
        boo = phoneReg.test(tel);
        // 再验证电话
        flag = telReg.test(tel);

        if (flag == true || boo == true) {
            $.ajax({
                url:url+"/customer/findOneByCustomer",
                type:"post",
                async:false,
                data:{
                    tel:tel
                },
                dataType:"json",
                success:function (response) {
                    if (response.code == 1) {
                        eyes=true;
                        $("#tel").parents(".control-group").attr("class","control-group success");
                    }else{
                        $("#tel").parents(".control-group").attr("class","control-group error");
                        $("#tel").after("<div class='error'>" +
                            "<span class='error'>电话号或者手机号已存在</span>" +
                            "</div>");
                    }
                },
            })
        }else{
            $("#tel").parents(".control-group").attr("class","control-group error");
            $("#tel").after("<div class='error'>" +
                "<span class='error'>电话号或者手机号输入有误</span>" +
                "</div>");
        }
    }else{
        $("#tel").parents(".control-group").attr("class","control-group error");
        $("#tel").after("<div class='error'>" +
            "<span class='error'>请输入手机号或者电话号！！！</span>" +
            "</div>");
    }
    return eyes;
}
//验证公司
function cfromReg() {
    let cfrom = trim($("#cfrom").val());
    let eyes=false;
    $("#cfrom").siblings(".error").remove();
    if (cfrom) {
        $.ajax({
            url:url+"/customer/findOneByCustomer",
            type:"post",
            data:{
                cfrom:cfrom
            },
            dataType:"json",
            success:function (response) {
               if (response.code == 1) {
                   eyes=true;
                   $("#cfrom").parents(".control-group").attr("class","control-group success");
               }else{
                   $("#cfrom").parents(".control-group").attr("class","control-group error");
                   $("#cfrom").after("<div class='error'>" +
                       "<span class='error'>该企业已经被添加过了,不可重复添加！！！</span>" +
                       "</div>");
               }
            },
        })
    }else{
        $("#cfrom").parents(".control-group").attr("class","control-group error");
        $("#cfrom").after("<div class='error'>" +
            "<span class='error'>企业名称不能为空！！！</span>" +
            "</div>");
    }
    return eyes;
}

function handleCilck(boo) {
    if (boo) {
        var name= $("#name").val();
        var address =$("#address").val();
        var tel = $("#tel").val();
        var cfrom=$("#cfrom").val();
        var qq = $("#qq").val();
        var email= $("#emailandwx").val();
        $.ajax({
            url:url+"/customer/addCustomer",
            type:"post",
            data:{
                name:name,
                address:address,
                tel:tel,
                cfrom:cfrom,
                qq:qq,
                email:email
            },
            dataType:"json",
            success:function (response) {
                console.log(response);
                if (response.code == 1) {
                    Swal.fire({
                        title: '提示?',
                        text: "是否返回客户列表!",
                        type: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: '是的!',
                        cancelButtonText:'继续添加'
                    }).then((result) => {
                        console.log(result.value);
                        if(result.value){
                            window.location.href=url+"/customer/list";
                        }else{
                            window.location.href=url+"/templates/client/client-add.html"
                        }
                    });
                }
            }
        })
    }else{
        if (nameReg()) {

        }else if (addressReg()) {
            
        }else if (telReg()) {
            
        }else if (cfromReg()) {

        }
    }
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
