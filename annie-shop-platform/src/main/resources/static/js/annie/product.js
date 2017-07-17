$().ready(function () {
});

function createProduct() {
    var url = "/product/createProduct";
    console.log($("#productForm"));
    var options = {
        url : url,
        dataType : "text/html",
        success : function(status){
            alert("success");
        },
        error:function(data){

        }
    };
    $("#productForm").ajaxSubmit(options);
}