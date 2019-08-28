$(function () {
    $("#log-add").on("click",function () {
        let name = $("#name").val();//姓名
        let address = $('#address').val();//地址
        let tel = $("#address").val();//地址
        let cfrom = $('#cfrom').val();//公司
        let qq = $('#qq').val();//qq
        let email = $("#emailandwx").val();//邮箱，或者微信
        let money = $("#money").val();//费用
        let rate = $("#rate").val();//状态
        let caim = $("#caim").val();//级别
        let question = $('#question').val();//问题
        let remark = $("#remark").val();//回答

        let boo=false;

        if (!trim(name)) {
            Swal.fire(
                "提示",
                "名字必须填写",
                "error"
            )
        }else if (!trim(address)) {
            Swal.fire(
                "提示",
                "地址必须填写",
                "error"
            )
        }else if (!trim(tel)) {
            Swal.fire(
                "提示",
                "电话必须填写",
                "error"
            )
        }else if (!trim(cfrom)) {
            Swal.fire(
                "提示",
                "公司名称必须填写",
                "error"
            )
        }else if (!trim(remark)) {
            Swal.fire(
                "提示",
                "交流内容必须填写",
                "error"
            )
        }else if (!trim(question)) {
            Swal.fire(
                "提示",
                "客户问题必须填写",
                "error"
            )
        }else if (caim == 0) {
            Swal.fire(
                "提示",
                "请选择客户级别",
                "error"
            )
        }else{
            $.ajax({
                url:url+"/customer-log/add",
                type:"post",
                data:{
                    name:name,
                    address:address,
                    tel:tel,
                    cfrom:cfrom,
                    qq:trim(qq),
                    email:trim(email),
                    money:money,
                    rate:rate,
                    caim:caim,
                    question:question,
                    remark:remark
                },
                dataType:'json',
                success:function (response) {
                    if (response.code == 1) {
                        //调专页面
                        console.log('yes')
                    }
                }
            })
        }
    })
})