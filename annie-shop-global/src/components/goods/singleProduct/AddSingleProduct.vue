<template>
    <div>
        <div>
        商品主信息
        </div>
        <div style="margin-left: 50px;border:1px solid #CFCFCF; ">
    <el-form :inline="true" class="demo-form-inline" style="margin: 20px 20px">
                <el-form-item label="商品类型" >
                    <el-radio-group v-model="radio2">
                        <el-radio :label="3">实物</el-radio>
                        <el-radio :label="6">虚拟商品</el-radio>
                        <el-radio :label="9">服务</el-radio>
                    </el-radio-group>
                </el-form-item>
        <br>
            <el-form-item label="商品分类">
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
        <br>
            <el-form-item label="商品品牌">
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
        <br>
            <el-form-item label="商品名称">
                <el-input v-model="condition.name"></el-input>非营销标题，长度限制为50
            </el-form-item>

        <br>
            <el-form-item label="贸易方式">
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

            <el-form-item label="经营方式">
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

            <el-form-item label="场地">
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

            <el-form-item label="单位">
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
        <br>
            <el-form-item label="产品说明">
                <el-input v-model="condition.name"></el-input>
            </el-form-item>
        <br>
            <el-form-item label="储存条件">
                <el-input v-model="condition.name"></el-input>
            </el-form-item>
        <br>
            <el-form-item label="" prop="cpValue">
                <el-checkbox v-model="checked">有保质期 <el-input v-model="editModel.cpName"  style="width: 80px" />年
<el-input v-model="editModel.cpName"  style="width: 80px" />月<el-input v-model="editModel.cpName"  style="width: 80px" />天</el-checkbox>
            </el-form-item>
        <br>
            <el-form-item label="七天无理由退货">
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
        <br>
            <el-form-item label="采购发票">
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
            <el-form-item label="进项税率">
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
        <br>
            <el-form-item label="销售发票">
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
            <el-form-item label="销项税率">
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
        <br>
            <el-form-item label="运费模板">
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
        <br>
            <el-form-item label="售卖区域" >
                <el-radio-group v-model="radio2">
                    <el-radio :label="3">全国</el-radio>
                    <br>
                    <el-radio :label="3">设置可配区域</el-radio>
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
                </el-radio-group>
            </el-form-item>
        <br>
            <el-form-item label="质检方式">
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
        <br>
            <el-form-item label="售卖基数">
                <el-input v-model="condition.name"></el-input>
            </el-form-item>
        <br>
            <el-form-item label="起售量">
                <el-input v-model="condition.name"></el-input>
            </el-form-item>
        </el-form>
    </div>

        <div>
            商品属性
        </div>
        <div style="margin-left: 50px;border:1px solid #CFCFCF; ">
            <el-form :inline="true" class="demo-form-inline" style="margin: 20px 20px">
                <el-form-item label="材质">
                    <el-input v-model="condition.name"></el-input>
                </el-form-item>
                <br>
                <el-form-item label="适应人群">
                    <el-input v-model="condition.name"></el-input>
                </el-form-item>
                <br>
                <el-form-item>
                    <el-button type="primary" icon="edit" @click="">添加属性+</el-button>
                </el-form-item>
            </el-form>
        </div>

        <div>
            商品规格
        </div>
        <div style="margin-left: 50px;border:1px solid #CFCFCF; ">
            <el-form :inline="true" class="demo-form-inline" style="margin: 20px 20px">
                <el-form-item >
                    <el-checkbox v-model="checked">颜色</el-checkbox>
                </el-form-item>
                <el-form-item label="">
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
                    <el-button type="primary" icon="edit" @click="">上传图片+</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="edit" @click="">自定义规格+</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div>
            规格信息
        </div>
        <div style="margin-left: 50px;border:1px solid #CFCFCF; ">
            <el-form :inline="true" class="demo-form-inline" style="margin: 20px 20px">

            <el-checkbox v-model="checked">微信商城</el-checkbox>
            <el-form-item label="">
                    <el-select v-model="value6" placeholder="选择颜色">
                        <el-option
                                v-for="item in cities"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                            <span style="float: left">{{ item.label }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                        </el-option>
                    </el-select>
            </el-form-item>
            </el-form>
        </div>
        <div>
            营销信息
        </div>
        <div style="margin-left: 50px;border:1px solid #CFCFCF; ">
            <el-form :inline="true" class="demo-form-inline" style="margin: 20px 20px">

            <el-form-item label="副标题">
                <el-input v-model="condition.name"></el-input> 前台展示副标题，限50个字以内
            </el-form-item>
            <br>
                <el-form-item label="SEO关键词">
                    <el-input v-model="condition.name"></el-input>
            </el-form-item>
                <br>
                <el-form-item label="售卖渠道">
                    <el-checkbox v-model="checked">微信商城</el-checkbox>
                    <el-checkbox v-model="checked">APP</el-checkbox>
                    <el-checkbox v-model="checked">PC</el-checkbox>
                    <el-checkbox v-model="checked">门店</el-checkbox>
                </el-form-item>
                <br>
                <el-form-item label="商品标签">

                </el-form-item>
                <br>
                <el-form-item label="TOP图">
                    <el-upload
                            action="https://jsonplaceholder.typicode.com/posts/"
                            list-type="picture-card"
                            :on-preview="handlePictureCardPreview"
                            :on-remove="handleRemove">
                        <i class="el-icon-plus"></i>
                    </el-upload>
                    <el-dialog v-model="dialogVisible" size="tiny">
                        <img width="100%" :src="dialogImageUrl" alt="">
                    </el-dialog>
                    <el-checkbox v-model="checked1" >设为主图</el-checkbox>

                </el-form-item>
            </el-form>
            <template>
                <div id="app">
                    <VueUEditor @ready="editorReady" style="width: 1200px;height: 800px"></VueUEditor>
                </div>
            </template>
        </div>

    </div>

</template>

<script>
    import VueUEditor from '../../UEditor.vue';


    export default {
        data() {
            return {
                value6:'',
                cities:[],
                radio2:'',
                condition:{},
                checked:true,
                checked1:true,
                editModel:{cpName:null}
            }
        },
        name: 'app',
        components: { VueUEditor },
        methods: {
            editorReady (editorInstance) {
                editorInstance.setContent('Hello world!<br>你可以在这里初始化编辑器的初始内容。');
                editorInstance.addListener('contentChange', () => {
                    console.log('编辑器内容发生了变化：', editorInstance.getContent());
                });
            }
        }
    };
</script>
