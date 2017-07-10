<template>
    <div>
        <el-form :model="editModel" ref="ruleEditForm" :rules="rules" label-width="120px">
            <el-form-item label="分类名称" prop="cpGroup">
                <el-input v-model="editModel.name"/>
            </el-form-item>
            <el-form-item label="类目级别" prop="cmCode">
                <el-select v-model="editModel.level" placeholder="类目级别">
                    <el-option
                            v-for="level in levels"
                            :key="level.id"
                            :label="level.name"
                            :value="level.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="上级类目名称" prop="cpName">
                <el-input v-model="editModel.cpName"/>
            </el-form-item>
            <el-form-item label="分类生效时间" prop="cpValue">
                <el-date-picker
                        v-model="editModel.cpValue"
                        type="datetime"
                        placeholder="选择分类生效时间">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="分类图片" prop="cpValue">
                <el-input v-model="editModel.cpValue"/>
            </el-form-item>
            <el-form-item label="属性组" prop="cpValue">
                <el-tag
                        :key="attr.id"
                        v-for="attr in editModel.attrs"
                        :closable="true"
                        :close-transition="false"
                        @close="handleDelAttr(attr)">
                    {{attr.name}}
                </el-tag>
                <el-input
                        class="input-new-tag"
                        v-if="attrInputVisible"
                        v-model="editModel.attr"
                        ref="saveTagInput"
                        size="mini"
                        style="width: 50px;"
                        @keyup.enter.native="handleAttrInputConfirm"
                        @blur="handleAttrInputConfirm"
                >
                </el-input>
                <el-button v-else class="button-new-tag" size="small" @click="showAttrInput">+ 新增</el-button>
            </el-form-item>
            <el-form-item label="规格" prop="cpValue">
                <el-input v-model="editModel.cpValue"/>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="editDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="handleSubmit('ruleEditForm')">确 定</el-button>
        </div>
    </div>
</template>

<script>
    import categoryEdit from "./categoryEdit.js"
    export default categoryEdit
</script>