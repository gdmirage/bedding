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

    var url = "/productType/deleteProductType";
    var params = {
        productTypeId: productTypeId
    };

    confirm(url, params, closeModal);
}