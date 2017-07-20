/**
 * 表单提交
 * @param formId
 * @param url
 */
function formSubmit(formId, url, complete1) {
    var options = {
        url: url,
        dataType: "json",
        success: function (result) {
            var code = result.resultCode;
            if(code == '0000'){
                console.log(complete1);
                // successAlert("", complete);
                var complete = complete1;
                console.log(complete);
                swal("操作成功！", msg, "success", complete);
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

function successAlert(msg, complete) {
    // window.location.reload(true);
    swal("操作成功！", msg, "success", complete);
}

function failAlert(msg, complete) {
    swal("操作失败！", msg, "error", complete);
}
