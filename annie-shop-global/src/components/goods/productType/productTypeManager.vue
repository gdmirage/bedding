<template>
    <div>
        <el-form :inline="true" class="demo-form-inline">
            <el-form-item label="模块">
                <el-select v-model="condition.cmCode" clearable placeholder="系统模块">
                    <el-option
                            v-for="module in cModules"
                            :key="module.cmCode"
                            :label="module.cmName"
                            :value="module.cmCode">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="参数组">
                <el-input v-model="condition.cpGroup" placeholder="参数组"></el-input>
            </el-form-item>
            <el-form-item label="参数名">
                <el-input v-model="condition.cpName" placeholder="参数名"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="search" @click="handleSearch()">查询</el-button>
                <el-button type="primary" icon="edit" @click="handleEdit()">新增</el-button>
            </el-form-item>
        </el-form>

        <el-table
                :data="pageInfo.list"
                border
                style="width: 100%">
            <el-table-column prop="cmName" label="模块" sortable width="180" />
            <el-table-column label="参数组" prop="cpGroup" width="180"/>
            <el-table-column label="参数名" prop="cpName" width="180"/>
            <el-table-column label="参数值" prop="cpValue" width="180"/>
            <el-table-column label="描述" prop="cpRemark" width="250"/>
            <el-table-column label="创建时间">
                <template scope="scope">
                    <el-icon name="time"></el-icon>
                    <span style="margin-left: 10px">{{ scope.row.createTime}}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button
                            size="small"
                            @click="handleEdit(scope.$index, scope.row)">编辑
                    </el-button>
                    <el-button
                            size="small"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-row type="flex" class="row-bg" justify="end">
            <el-col :span="8">
                <el-pagination
                        @size-change="handlerAjaxData"
                        @current-change="handlerAjaxData"
                        :current-page="pageInfo.pageNum"
                        :page-sizes="[20, 50, 100]"
                        layout="total, slot, sizes, prev, pager, next, jumper"
                        :total="pageInfo.total">
                        <!--:page-size="pageInfo.size"-->
                    <!--:page-count="pageInfo.pages"-->
                </el-pagination>
            </el-col>
        </el-row>


        <el-dialog title="参数信息" :visible.sync="editDialogVisible">
            <el-form :model="editModel" ref="ruleEditModelForm" :rules="rules" label-width="80px">
                <el-form-item label="系统模块" prop="cmCode">
                    <el-select v-model="editModel.cmCode" placeholder="系统模块">
                        <el-option
                                v-for="module in cModules"
                                :key="module.cmCode"
                                :label="module.cmName"
                                :value="module.cmCode">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="参数组" prop="cpGroup">
                    <el-input v-model="editModel.cpGroup"/>
                </el-form-item>
                <el-form-item label="参数名" prop="cpName">
                    <el-input v-model="editModel.cpName"/>
                </el-form-item>
                <el-form-item label="参数值" prop="cpValue">
                    <el-input v-model="editModel.cpValue"/>
                </el-form-item>
                <el-form-item label="备注" prop="cpRemark">
                    <el-input
                            type="textarea"
                            :autosize="{ minRows: 2, maxRows: 6}"
                            placeholder="请输入内容"
                            v-model="editModel.cpRemark">
                    </el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleSubmit('ruleEditModelForm')">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    import {goods} from "../../../config/constant.js"
    import paramManager from "./productTypeManager.js"

    export default paramManager
</script>
