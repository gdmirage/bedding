import {goods} from "../../../config/constant.js"

export default {
    data() {
        return {
            editDialogVisible: false,

            attrInputVisible: false,
            attrInput: null,
            attributes: [],

            specInputVisible: false,
            specInput: null,
            specifications: [],
            specificationMap: {},

            specValInputVisible: false,
            specValInput: null,

            rootCategoryOptions: [],
            categoryParentIds: [],

            rules: {
                name: [
                    {required: true, message: '请输入分类名称', trigger: 'blur'}
                ],
                effectiveDate: [
                    {type: 'date', required: true, message: '请设置分类生效时间', trigger: 'blur'}
                ],
                imageUrl: [
                    {required: true, message: '请选择分类图片 ', trigger: 'blur'}
                ]
            },
            editModel: {},
            condition: {},
            grades: [],
            tableData: []
        }
    },
    created: function () {
        this.loadManagerData();
        this.handlerAjaxData();
    },
    methods: {
        loadManagerData() {
            this.$http.post(goods.loadManagerData).then(response => {
                this.grades = response.body.data.grades;
            }, response => {
                this.$message.error('初始化数据失败');
            });
        },
        handlerAjaxData() {
            let data = {condition: this.condition};

            this.$http.post(goods.findListResult, data).then(response => {
                // 设置表格数据
                this.tableData = response.body.data;

                //  组装父类下拉框数据
                let convert2Option = this.convert2Option;
                let rootCategoryOptions = this.rootCategoryOptions;
                this.tableData.forEach(root => {
                    rootCategoryOptions.push(convert2Option(root));
                });

            }, response => {
                this.$message.error('加载商品分类数据失败');
            });
        },
        /**
         * 转换分类为Option需要的数据格式
         * @param category 分类实体
         * @returns {{value, label}}
         */
        convert2Option(category) {
            let option = {
                value: category.id,
                label: category.name,
                gradeId: category.gradeId,
            };
            if (category.children && category.gradeId <= 1) {
                let children = [];
                category.children.forEach(child => children.push(this.convert2Option(child)));
                option.children = children;
            }
            return option;
        },
        handleCategoryOptionChange(value) {
            this.editModel.gradeId = value.length + 1;
        },
        handleAttrInputConfirm() {
            if (this.attrInput && this.attributes.indexOf(this.attrInput) === -1) {
                this.attributes.push(this.attrInput);
            }
            this.attrInputVisible = false;
            this.attrInput = '';
        },
        handleSpecInputConfirm() {
            let specInput = this.specInput;
            if (specInput) {
                let isExists = false;
                this.specifications.forEach(_ => {
                    if (_.name === specInput) {
                        isExists = true;
                        return true;
                    }
                });
                if (! isExists) {
                    this.specifications.push({name: this.specInput, valueList: []});
                }
            }
            this.specInputVisible = false;
            this.specInput = '';
        },
        handleSpecValInputConfirm(spec) {
            if (this.specValInput  && spec.valueList.indexOf(this.specValInput) === -1) {
                spec.valueList.push(this.specValInput);
            }
            this.specValInputVisible = false;
            this.specValInput = '';
        },
        showAttrInput() {
            this.attrInputVisible = true;
            this.$nextTick(_ => {
                this.$refs.saveAttrTagInput.$refs.input.focus();
            });
        },
        showSpecInput() {
            this.specInputVisible = true;
            this.$nextTick(_ => {
                this.$refs.saveSpecTagInput.$refs.input.focus();
            });
        },
        showSpecValInput(index) {
            this.specValInputVisible = true;
            this.$nextTick(_ => {
                this.$refs.saveSpecValTagInput[index].$refs.input.focus();
            });
        },
        handleDelAttr(delAttr) {
            this.remove(this.attributes, delAttr);
        },
        handleDelSpecVal(spec, delSpecificationValue) {
            this.remove(spec.valueList, delSpecificationValue);
        },
        handleDelSpec(delSpecification) {
            this.remove(this.specifications, delSpecification);
        },
        remove(array, delItem) {
            let index = 0;
            array.forEach(function (item) {
                if (item === delItem) {
                    array.splice(index, 1);
                }
                index ++;
                return true;
            });
        },
        handleEdit(index, row) {
            if (row) {
                this.$http.get(goods.findByIdResult + "/" + row.id).then(response => {
                    if (response.body.code === 1) {
                        this.editModel = response.body.data;
                        debugger
                        // 加载属性Tag
                        this.attributes = [];
                        let attributes = this.attributes;
                        if (this.editModel.attributeList) {
                            this.editModel.attributeList.forEach(attr => attributes.push(attr.name));
                        }

                        // 加载规格、规格值Tag
                        this.specifications = [];
                        let specifications = this.specifications;
                        if (this.editModel.specificationList) {
                            this.editModel.specificationList.forEach(spec => {
                                let valueList = [];
                                if (spec.valueList) {
                                    spec.valueList.forEach(value => valueList.push(value.value))
                                }
                                specifications.push({name: spec.name, valueList: valueList});
                            });
                        }

                        // 显示编辑框
                        this.editDialogVisible = true;
                    } else {
                        this.$message.error(response.body.msg);
                    }
                }, response => {
                    this.$message.error('删除分类失败');
                });
            } else {
                this.editModel = {
                    parentId: "",
                    effectiveDate: "",
                    gradeId: null
                };
                this.attributes = [];
                this.specifications = [];
                this.editDialogVisible = true;
            }
        },
        handleDelete(index, row) {
            this.$confirm('此操作将永久删除该分类, 是否继续?', '操作确认', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$http.get(goods.saveResult + "/" + row.cpCode).then(response => {
                    if (response.body.code === 1) {
                        this.$message('删除分类成功');
                        this.handlerAjaxData();
                        this.editDialogVisible = false;
                    } else {
                        this.$message.error(response.body.msg);
                    }
                }, response => {
                    this.$message.error('删除分类失败');
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        handleSubmit(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    let editModel = this.editModel;
                    debugger
                    if (this.categoryParentIds && this.categoryParentIds.length) {
                        editModel.parentId = this.categoryParentIds[this.categoryParentIds.length - 1];
                    }

                    // 组装属性组
                    editModel.attributeList = [];
                    this.attributes.forEach(attribute => {
                        editModel.attributeList.push({name: attribute});
                    });

                    // 组装规格
                    editModel.specificationList = [];
                    this.specifications.forEach(spec => {
                        let valueList = [];
                        spec.valueList.forEach(value => {
                            valueList.push({value: value});
                        });
                        editModel.specificationList.push({name: spec.name, valueList: valueList});
                    });

                    this.$http.post(this.editModel.id ? goods.updateResult : goods.saveResult, this.editModel).then(response => {
                        if (response.body.code === 1) {
                            this.$message('保存分类成功');
                            this.handlerAjaxData();
                            this.editDialogVisible = false;
                        } else {
                            this.$message.error(response.body.msg);
                        }
                    }, response => {
                        this.$message.error('保存参数失败');
                    });
                }
            });
        },
        // 展开或收缩
        expandOrCollapse(index, row) {
            let tableData = this.tableData;
            let childIndex = index;

            if (row.expand) {
                this.spliceRow(row);
                return ;
            }

            row.expand = true;
            row.children.forEach(function (child) {
                tableData.splice(++ childIndex, 0, child);
            });
        },
        // 收缩， 未知层级， 所以需要递归
        spliceRow(parent) {
            let tableData = this.tableData;
            for (let i = tableData.length - 1; i >= 0; i --) {
                let child = tableData[i];
                if (child.parentId === parent.id) {
                    if(child.children) {
                        this.spliceRow(child)
                    }
                    tableData.splice(i, 1);
                }
            }
            parent.expand = false;
        }
    }
}