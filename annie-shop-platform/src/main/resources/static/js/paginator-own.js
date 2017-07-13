var params = "";
$().ready(function () {
    $("#selectDiv [selectValue='true']").each(function(){
        if($(this).val().trim() != ''){
            params += $(this).attr('id') + "=" + $(this).val().trim() + "&";
        }
    });
    var currentPage = $("#currentPage").val().trim();
    var totalPages = $("#totalPages").val().trim();
    var options = {
        currentPage: currentPage,
        totalPages: totalPages,
        numberOfPages: 5,
        pageUrl: function(type, page, currentPage){
            if (page==currentPage) {
                return "javascript:void(0)";
            } else {
                var requestPath = $("#requestPath").val();
                path = requestPath + "?" + params + "pageNum=" + page;
                return path;
            }
        }
    }

    $('#paginator').bootstrapPaginator(options);
});