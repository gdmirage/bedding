export default {
    data() {
        return {
            editDialogVisible: false,
            condition: {},
            editModel: {},
            cModules: [],
            pageInfo: {},
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
            }
        }
    },
    created: function () {
        this.loadModules();
        this.handlerAjaxData();
    },
    methods: {
        loadModules() {
            this.$http.post(common.moduleFindAll).then(response => {
                this.cModules = response.body.data;
            }, response => {
                this.$message.error('加载模块数据失败');
            });
        },
        handlerAjaxData(pageNum, pageSize) {
            var data = {condition: this.condition};
            if (pageNum || this.pageInfo.pageNum) {
                data.page = pageNum || this.pageInfo.pageNum;
            }
            if (this.pageInfo.pageSize) {
                data.size = this.pageInfo.pageSize;
            }

            this.$http.post(common.paramFindPage, data).then(response => {
                this.pageInfo = response.body.data;
            }, response => {
                this.$message.error('加载系统参数数据失败');
            });
        },
        handleEdit(index, row) {
            if (!row) {
                row = {};
            }
            this.editModel = row;
            this.editDialogVisible = true;
        },
        handleSubmit(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.$http.post(this.editModel.cpCode ? common.paramUpdate : common.paramSave, this.editModel).then(response => {
                        if (response.body.code == 1) {
                            this.$message('保存参数成功');
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
        handleSearch() {
            this.handlerAjaxData(1);
        },
        handleDelete(index, row) {
            this.$confirm('此操作将永久删除该参数, 是否继续?', '操作确认', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$http.get(common.paramDelete + "/" + row.cpCode).then(response => {
                    if (response.body.code == 1) {
                        this.$message('删除参数成功');
                        this.handlerAjaxData();
                        this.editDialogVisible = false;
                    } else {
                        this.$message.error(response.body.msg);
                    }
                }, response => {
                    this.$message.error('删除参数失败');
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        handlePageChange() {
        }
    }
}