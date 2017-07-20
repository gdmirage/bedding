$().ready(function () {
});

function toCreateOrUpdateProductType(productTypeId) {
    var url = "/productType/toCreateOrUpdateProductType";
    var params = {
        productTypeId: productTypeId
    };
    $.ajax({
        type : "POST",
        url : url,
        async : true,
        global : true,
        data : params, // 要传递的数据
        success : function(msg) {
            $("#ProductTypeModal").empty();
            $("#ProductTypeModal").append(msg);
        }
    });
}

function createProductType() {
    var url = "/productType/createProductType";
    formSubmit("productTypeForm", url, closeModal);
}

function closeModal() {
    $("#closeModal").click();
    window.location.reload(true);
}

function updateProductType() {
    var url = "/productType/updateProductType";
    formSubmit("productTypeForm", url, closeModal);
}

function deleteProductType(productTypeId) {
    swal({
        title: "您确定要删除这条信息吗",
        text: "删除后将无法恢复，请谨慎操作！",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "删除",
        closeOnConfirm: false
    }, function () {
        var url = "/productType/deleteProductType";
        var params = {
            productTypeId: productTypeId
        };
        $.ajax({
            type : "POST",
            url : url,
            async : true,
            global : true,
            data : params, // 要传递的数据
            success : function(data) {
                var resultCode = data.resultCode;
                if(resultCode == '0000'){
                    swal("删除成功！", "您已经永久删除了这条信息。", "success");
                }else {
                    swal("删除失败！", "", "error");
                }
            }
        });
    });
}