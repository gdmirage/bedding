/* URL配置 */
const HTTP_URL2 = "http://192.168.100.55:9090";
const HTTP_URL = "http://localhost:9090";
const IMG_PATH = "https://images.lovego.com/";

const common = {
    moduleFindAll: HTTP_URL + "/common/module/findAll",//  查询所有模块
    paramFindPage: HTTP_URL + "/common/param/findPage",//  查询系统参数
    paramSave: HTTP_URL + "/common/param/save",//  保存参数
    paramUpdate: HTTP_URL + "/common/param/update",//  修改参数
    paramDelete: HTTP_URL + "/common/param/deleteById",//  删除参数
};

const goods = {
    loadManagerData: HTTP_URL + "/goods/categoryApi/loadManagerData",// 管理页初始化数据
    findListResult: HTTP_URL + "/goods/categoryApi/findList2TreeResult",// 分类管理页
    saveResult: HTTP_URL + "/goods/categoryApi/saveResult",// 保存分类
    updateResult: HTTP_URL + "/goods/categoryApi/updateResult",// 更新分类
    findByIdResult: HTTP_URL + "/goods/categoryApi/findByIdResult",// 更新分类

    findProductTypePage: HTTP_URL + "/productType/findProductTypePage" // 商品分类列表
};

export {common, goods};