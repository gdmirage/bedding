package com.annie.entity;

import java.util.Date;

public class Product {
    private Long productId;

    private String productName;

    private String productUnit;

    private String productMainPic;

    private Integer rootTypeId;

    private Integer parentTypeId;

    private Integer productTypeId;

    private Integer createMan;

    private Date createDate;

    private Integer modifyMan;

    private Date modifyDate;

    private String isDelete;

    private String productContent;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit == null ? null : productUnit.trim();
    }

    public String getProductMainPic() {
        return productMainPic;
    }

    public void setProductMainPic(String productMainPic) {
        this.productMainPic = productMainPic == null ? null : productMainPic.trim();
    }

    public Integer getRootTypeId() {
        return rootTypeId;
    }

    public void setRootTypeId(Integer rootTypeId) {
        this.rootTypeId = rootTypeId;
    }

    public Integer getParentTypeId() {
        return parentTypeId;
    }

    public void setParentTypeId(Integer parentTypeId) {
        this.parentTypeId = parentTypeId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getCreateMan() {
        return createMan;
    }

    public void setCreateMan(Integer createMan) {
        this.createMan = createMan;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getModifyMan() {
        return modifyMan;
    }

    public void setModifyMan(Integer modifyMan) {
        this.modifyMan = modifyMan;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent == null ? null : productContent.trim();
    }
}