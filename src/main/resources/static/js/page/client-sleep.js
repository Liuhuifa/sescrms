$(function () {
    //翻页
    // $(".pagination ul li").on("click",function () {
    //     let pageNum = $(this).attr("value");
    //     window.location.href=url+"/customer/list?pageindex="+pageNum+"&flag=2";
    // });
})

/**
 * 移动到未分配状态
 */
function moveUnallocated(e) {
    Swal.fire({
        title: '提示',
        text: '确定要移动到未分配状态吗?',
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '是',
        cancelButtonText:'否',
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url:url+"/customer/move",
                type: "post",
                data: {
                    id:e.dataset.move,
                },
                dataType: 'json',
                success:function (response) {
                    if (response.code == 1) {
                        Swal.fire(
                            '提示',
                            '移动成功',
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

/**
 * 删除一个
 */
function removeCus(e) {
    Swal.fire({
        title: '提示',
        text: '确定要删除该客户吗?',
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '是',
        cancelButtonText:'否',
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url:url+"/customer/del",
                type: "post",
                data: {
                    id:e.dataset.del,
                },
                dataType: 'json',
                success:function (response) {
                    if (response.code == 1) {
                        Swal.fire(
                            '提示',
                            '删除成功',
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