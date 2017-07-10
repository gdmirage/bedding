<template>
    <div>
        <el-form :inline="true" class="demo-form-inline">
            <el-form-item label="商品搜素">
                <el-input v-model="condition.name" placeholder="SPU/SKU/品牌/名称/货号/条形码/副标题"></el-input>
            </el-form-item>
            <el-form-item label="品牌">
                <el-input v-model="condition.name" placeholder="品牌名称"></el-input>
            </el-form-item>
            <el-form-item label="一级分类">
                <template>
                    <el-select v-model="value6" placeholder="请选择">
                        <el-option
                                v-for="item in cities"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                            <span style="float: left">{{ item.label }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                        </el-option>
                    </el-select>
                </template>
            </el-form-item>
            <el-form-item label="二级分类">
                <template>
                    <el-select v-model="value6" placeholder="请选择">
                        <el-option
                                v-for="item in cities"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                            <span style="float: left">{{ item.label }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                        </el-option>
                    </el-select>
                </template>
            </el-form-item>
            <el-form-item label="三级分类">
                <template>
                    <el-select v-model="value6" placeholder="请选择">
                        <el-option
                                v-for="item in cities"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                            <span style="float: left">{{ item.label }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                        </el-option>
                    </el-select>
                </template>
            </el-form-item>
            <el-form-item label="商品状态">
                <template>
                    <el-select v-model="value6" placeholder="请选择">
                        <el-option
                                v-for="item in cities"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                            <span style="float: left">{{ item.label }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                        </el-option>
                    </el-select>
                </template>
            </el-form-item>
            <el-form-item label="供应商">
                <template>
                    <el-select v-model="value6" placeholder="请选择">
                        <el-option
                                v-for="item in cities"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                            <span style="float: left">{{ item.label }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                        </el-option>
                    </el-select>
                </template>
            </el-form-item>
            <el-form-item label="毛利率">
                <el-input v-model="condition.name"></el-input>
            </el-form-item>
            <el-form-item label="~">
                <el-input v-model="condition.name"></el-input>
            </el-form-item>
            <el-form-item label="红包商品">
                <template>
                    <el-select v-model="value6" placeholder="请选择">
                        <el-option
                                v-for="item in cities"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                            <span style="float: left">{{ item.label }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                        </el-option>
                    </el-select>
                </template>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="search">搜索</el-button>
                <el-button type="primary" icon="edit" @click="handleEdit()">新增</el-button>
            </el-form-item>
        </el-form>

        <el-table :data="tableData" border style="width: 100%">
            <el-table-column prop="image"
                    label="图片" >
                <template scope="scope">
                    <img :src="scope.row.aeIdFront" @click="clickImg($event)"
                         style="height:60px;width:60px;cursor:pointer;">
                </template>
            </el-table-column>
            <el-table-column label="SPU码" prop="spu">
            </el-table-column>
            <el-table-column label="商品品牌"  prop="brank">
            </el-table-column>
            <el-table-column label="商品名称/副标题" prop="name">
            </el-table-column>
            <el-table-column label="商品类别"  prop="cata">
            </el-table-column>
            <el-table-column label="毛利/毛利率" prop="name">
            </el-table-column>
            <el-table-column label="商品售价"  prop="name">
            </el-table-column>
            <el-table-column label="商品状态" prop="name">
            </el-table-column>
            <el-table-column label="二维码" prop="name">
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

    </div>

</template>

<script>
    import singleProductManager from "./singleProductManager.js"
    export default singleProductManager
</script>