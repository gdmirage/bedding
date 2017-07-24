$().ready(function () {
});

function createProduct() {
    var url = "/product/createProduct";
    formSubmit("productForm", url, reload());
}

function updateProduct() {
    var url = "/product/updateProduct";
    formSubmit("productForm", url, reload());
}

function reload() {
    window.location.href = "/product/findProductPage"
}

function deleteProduct(productId) {
    var url = "/product/deleteProduct";
    var params = {
        productId: productId
    };

    confirm(url, params, reload);
}
