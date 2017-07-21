/**
 * 表单提交
 * @param formId
 * @param url
 */
function formSubmit(formId, url, complete) {
    var options = {
        url: url,
        dataType: "json",
        success: function (result) {
            var code = result.resultCode;
            if(code == '0000'){
                complete();
                /** 弄不了提示的。。所以只能刷新 **/
                // swal("操作成功！", msg, "success", complete);
            }else{
                var msg = result.resultMsg;
                failAlert(msg);
            }
        },
        error: function (data) {
            swal("操作异常！", "", "error");
        }
    };
    $("#" + formId).ajaxSubmit(options);
}

function confirm(url, params, complete) {
    swal({
        title: "您确定要删除这条信息吗",
        text: "删除后将无法恢复，请谨慎操作！",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "删除",
        closeOnConfirm: false
    }, function () {
        $.ajax({
            type : "POST",
            url : url,
            async : true,
            global : true,
            data : params, // 要传递的数据
            success : function(data) {
                var resultCode = data.resultCode;
                if(resultCode == '0000'){
                    // swal("删除成功！", "您已经永久删除了这条信息。", "success");
                    complete();
                }else {
                    swal("删除失败！", "", "error");
                }
            }
        });
    });
}

function successAlert(msg, complete) {
    // window.location.reload(true);
    swal("操作成功！", msg, "success", complete);
}

function failAlert(msg, complete) {
    swal("操作失败！", msg, "error", complete);
}
