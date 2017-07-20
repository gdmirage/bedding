$().ready(function () {
});

function createProduct() {
    var url = "/product/createProduct";
    var options = {
        url : url,
        dataType : "text/html",
        success : function(result){
            var code = result.resultCode;
            if(code == '0000'){

            }
        },
        error:function(data){

        }
    };
    $("#productForm").ajaxSubmit(options);
}