export default {
    data() {
        return {
            rules: {
                cmCode: [
                    {required: true, message: '请选择参数对应模块', trigger: 'blur'}
                ],
                cpGroup: [
                    {required: true, message: '请输入参数组', trigger: 'blur'}
                ],
                cpName: [
                    {required: true, message: '请输入参数名', trigger: 'blur'}
                ],
                cpValue: [
                    {required: true, message: '请输入参数值', trigger: 'blur'}
                ],
                cpRemark: [
                    {required: true, message: '请输入备注', trigger: 'blur'}
                ]
            },
            editModel: {
                attrs: [{
                    id: 1,
                    name: "颜色"
                }, {
                    id: 2,
                    name: "内存"
                }]
            }
        }
    },
    created: function () {
        this.handlerAjaxData();
    },
    methods: {
        handlerAjaxData() {
            var data = {condition: this.condition};

            this.$http.post(goods.findListResult, data).then(response => {
                this.tableData = response.body.data;
            }, response => {
                this.$message.error('加载商品分类数据失败');
            });
        },
        handleAttrInputConfirm() {
            let attrName = this.editModel.attr;
            if (attrName) {
                this.editModel.attrs.push({id: 3, name: attrName});
            }
            this.attrInputVisible = false;
            this.editModel.attr = '';
        },
        showAttrInput() {
            this.attrInputVisible = true;
            this.$nextTick(_ => {
                this.$refs.saveTagInput.$refs.input.focus();
            });
        },
        handleDelAttr(attr) {

        },
        handleEdit(index, row) {
            if (!row) {
                // row = {};
            }
            // this.editModel = row;
            this.editDialogVisible = true;
        },
        handleDelete(index, row) {
            this.$confirm('此操作将永久删除该分类, 是否继续?', '操作确认', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$http.get(common.paramDelete + "/" + row.cpCode).then(response => {
                    if (response.body.code == 1) {
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
        spliceRow(parent) {
            let tableData = this.tableData;
            for (let i = tableData.length - 1; i >= 0; i --) {
                let child = tableData[i];
                if (child.parent === parent.id) {
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