<template>
    <div>
        <el-form :inline="true" class="demo-form-inline">
            <el-form-item label="分类名称">
                <el-input v-model="condition.name" placeholder="分类名称" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="search">搜索</el-button>
                <el-button type="primary" icon="edit" @click="handleEdit">新增</el-button>
            </el-form-item>
        </el-form>

        <el-table :data="tableData" border style="width: 100%">
            <el-table-column label="类目名称">
                <template scope="scope">
                    <template v-for="n in scope.row.gradeId"><template v-if="n > 1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</template></template>
                    <span v-if="scope.row.children" style="cursor: pointer;" @click="expandOrCollapse(scope.$index, scope.row)">
                        <i v-if="scope.row.expand" class="el-icon-arrow-down" />
                        <i v-else class="el-icon-arrow-right" />
                    </span>
                    <span style="margin-left: 10px">{{ scope.row.name }}</span>
                </template>
            </el-table-column>
            <el-table-column label="排序移动" width="100">
                <template scope="scope">
                    <i class="el-icon-caret-top"></i>
                    <i class="el-icon-caret-bottom"></i>
                </template>
            </el-table-column>
            <el-table-column
                    label="创建时间"
                    width="200">
                <template scope="scope">
                    <el-icon name="time"></el-icon>
                    <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="生效时间"
                    width="200">
                <template scope="scope">
                    <el-icon name="time"></el-icon>
                    <span style="margin-left: 10px">{{ scope.row.realEffectiveDate }}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="180">
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

        <el-dialog title="分类编辑" :visible.sync="editDialogVisible">
            <el-form :model="editModel" ref="ruleEditForm" :rules="rules" label-width="120px">
                <el-form-item label="分类名称" prop="name">
                    <el-input v-model="editModel.name"/>
                </el-form-item>
                <el-form-item label="类目级别">
                    <el-select v-model="editModel.gradeId" placeholder="类目级别" disabled>
                        <el-option
                                v-for="grade in grades"
                                :key="grade.id"
                                :label="grade.name"
                                :value="grade.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="上级类目名称" prop="parentId">
                    <el-cascader
                            expand-trigger="hover"
                            :options="rootCategoryOptions"
                            v-model="categoryParentIds"
                            @change="handleCategoryOptionChange"
                            change-on-select>
                    </el-cascader>
                </el-form-item>
                <el-form-item label="分类生效时间" prop="effectiveDate">
                    <el-date-picker
                            v-model="editModel.effectiveDate"
                            type="datetime"
                            format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择分类生效时间">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="分类图片" prop="imageUrl">
                    <el-input v-model="editModel.imageUrl"/>
                </el-form-item>
                <el-form-item label="属性组">
                    <el-tag
                            :key="attr"
                            v-for="attr in attributes"
                            :closable="true"
                            :close-transition="false"
                            @close="handleDelAttr(attr)">
                        {{attr}}
                    </el-tag>
                    <el-input
                            v-if="attrInputVisible"
                            v-model="attrInput"
                            ref="saveAttrTagInput"
                            size="small"
                            style="width: 80px;"
                            @keyup.enter.native="handleAttrInputConfirm"
                            @blur="handleAttrInputConfirm"
                    >
                    </el-input>
                    <el-button v-else class="button-new-tag" size="small" @click="showAttrInput">+ 属性</el-button>
                </el-form-item>
                <el-form-item label="规格">
                    <div v-for="(spec, index) in specifications">
                        <el-tag
                                type="gray"
                                :key="spec.name"
                                :closable="true"
                                :close-transition="false"
                                @close="handleDelSpec(spec)">
                            {{spec.name}}
                        </el-tag>

                        <!--新增规格值-->
                        <el-tag
                                :key="specificationValue"
                                v-for="specificationValue in spec.valueList"
                                :closable="true"
                                :close-transition="false"
                                @close="handleDelSpecVal(spec, specificationValue)">
                            {{specificationValue}}
                        </el-tag>
                        <el-input
                                v-if="specValInputVisible"
                                v-model="specValInput"
                                ref="saveSpecValTagInput"
                                style="width: 80px;"
                                size="small"
                                @keyup.enter.native="handleSpecValInputConfirm(spec)"
                                @blur="handleSpecValInputConfirm(spec)"
                        >
                        </el-input>
                        <el-button v-else size="small" @click="showSpecValInput(index)">+ {{spec.name}}</el-button>
                    </div>
                    <!--新增规格值-->
                    <el-input
                            v-if="specInputVisible"
                            v-model="specInput"
                            ref="saveSpecTagInput"
                            style="width: 100px;"
                            @keyup.enter.native="handleSpecInputConfirm"
                            @blur="handleSpecInputConfirm"
                    >
                    </el-input>
                    <el-button v-else @click="showSpecInput">+ 规格</el-button>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleSubmit('ruleEditForm')">确 定</el-button>
            </div>
        </el-dialog>
    </div>

</template>

<script>
    import categoryManager from "./categoryManager.js"
    import {goods} from "../../../config/constant.js"
    export default categoryManager
</script>