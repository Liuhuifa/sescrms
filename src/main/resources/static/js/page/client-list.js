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
    $("#paginations .pagination ul li").on("click",function () {
        let pageNum = $(this).attr("value");
        window.location.href=url+"/customer/list?pageindex="+pageNum;
    });

    // $("#submit").on("click",function () {
    //     // let data = $("#solr-form").serialize();
    //     $("#solr-form").submit();
    // })
    $("#add-customer").on("click",function () {
        window.location.href=url+"/templates/client/client-add.html"
    })
})