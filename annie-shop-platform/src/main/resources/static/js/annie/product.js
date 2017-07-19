$().ready(function () {
});

function createProduct() {
    var url = "/product/createProduct";
    var options = {
        url : url,
        dataType : "text/html",
        success : function(status){
        },
        error:function(data){

        }
    };
    $("#productForm").ajaxSubmit(options);
}