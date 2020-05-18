<template>
    <div>
        <!--搜索行-->
        <el-row>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
            <el-col :span="14">
                <div class="grid-content-goods bg-purple-dark-goods">
                    <el-row>
                        <el-col :span="16" class="grid">
                            <el-input placeholder="请输入搜索内容 search bar" v-model="input" class="input-with-select">
                                <el-select v-model="select" slot="prepend" placeholder="选择">
                                    <el-option label="Goods" value="1"></el-option>
                                    <!--                                    <el-option label="店铺" value="2"></el-option>-->
<!--                                    <el-option label="用户" value="3"></el-option>-->
                                </el-select>
                                <el-button slot="append" icon="el-icon-search" @click.prevent="searchByInput()"></el-button>
                            </el-input>
                        </el-col>
                        <el-col :span="2" class="grid" style="margin: 8px">
                            <el-tooltip placement="top" effect="light">
                                <div slot="content">
                                    未选中,搜索内容可少字,可乱序，但不要多字<br/>
                                    选中,搜索内容可多字,可乱序<br/>
                                </div>
                                <el-checkbox v-model="checked">rough search 粗略搜索</el-checkbox>
                            </el-tooltip>
                        </el-col>
                    </el-row>
                </div>
            </el-col>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
        </el-row>
        <br>
        <!--分类行-->
        <el-row>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
            <el-col :span="15">
                <div class="grid-content-goods bg-purple-dark-goods">
                    <el-row>
                        <el-col :span="1">
                            <!--非控件制定好的,选择字体的样式-->
                            <div class="grid-content-goods bg-purple-dark-goods"
                                 style="margin-top: 10px ;text-align: center; font-size: 14px;">分类  :
                            </div>
                        </el-col>
                        <el-col :span="20">
                            <div class="grid-content-goods bg-purple-dark-goods" style="text-align: left;">
                                <el-button type="text" @click.prevent="searchByItem(0)"> 所有/ALL</el-button>
                                <el-button type="text" @click.prevent="searchByItem(1)">男装/女装</el-button>
                                <el-button type="text" @click.prevent="searchByItem(2)">鞋靴/箱包</el-button>
                                <el-button type="text" @click.prevent="searchByItem(3)">童装玩具</el-button>
                                <el-button type="text" @click.prevent="searchByItem(4)">家电/数码</el-button>
                                <el-button type="text" @click.prevent="searchByItem(5)">美妆/洗护</el-button>
                                <el-button type="text" @click.prevent="searchByItem(6)">美食/生鲜</el-button>
                                <el-button type="text" @click.prevent="searchByItem(7)">运动/户外</el-button>
                                <el-button type="text" @click.prevent="searchByItem(8)">工具/建材</el-button>
                                <el-button type="text" @click.prevent="searchByItem(9)">家具/家饰</el-button>
                                <el-button type="text" @click.prevent="searchByItem(10)">汽车/用品</el-button>
                                <el-button type="text" @click.prevent="searchByItem(11)">百货/餐厨</el-button>
                                <el-button type="text" @click.prevent="searchByItem(12)">学习/卡券</el-button>
                            </div>
                        </el-col>
                    </el-row>
                </div>
            </el-col>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
        </el-row>
        <!--卡片行-->
        <el-row>
            <el-col :span="3">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
            <el-col :span="18">
                <div class="grid-content-goods bg-purple-dark-goods">
                    <el-row>
                        <el-col :span="6" v-for="(o, index) in sumNum" :key="o"
                                :offset="index > 0 && index%4 != 0 ? 0 : 0">
                            <el-card :body-style="{ padding: '8px' }" style="height: 365px">
                                <el-row>
                                    <el-button
                                            type="text"
                                            @click.prevent="handleBuyDetail(cardData[index].resourceId)">
                                        <div style="text-align: center">
                                            <img :src="'data:image/jpeg;base64,'+ cardData[index].photoDetail"
                                                 style="width: 250px; height: 250px" class="image coverImg"/>
                                        </div>
                                    </el-button>
                                </el-row>
                                <el-row>
                                    <div style="padding: 8px;">
                                        <span  style="font-size: 14px">{{cardData[index].resourceName}}</span>
                                        <div class="bottom" style="margin-top: 8px;line-height: 8px;">

                                            <el-button
                                                    type="text"
                                                    style="padding: 0;float: left;font-size: 16px;"
                                                    @click.prevent="searchBySeller(cardData[index].userId)">
                                                {{cardData[index].userName}}
                                            </el-button>
                                        </div>
                                    </div>
                                </el-row>
                            </el-card>
                        </el-col>
                    </el-row>
                </div>
            </el-col>
            <el-col :span="3">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
        </el-row>

        <!--分页行-->
        <el-row>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
            <el-col :span="14">
                <div class="grid-content-goods bg-purple-dark-goods">
                    <div style="text-align: right;margin-top: 20px; margin-right: 40px">
                        <el-pagination
                                background
                                layout="total, prev, pager, next, jumper"
                                :page-size="pageSizeDefault"
                                :total="total"
                                @current-change="current_change">
                        </el-pagination>
                    </div>
                </div>
            </el-col>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
        </el-row>

        <!--页尾连接信息行-->
        <el-row>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
            <el-col :span="14">

            </el-col>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
        </el-row>

        <!-- 购买的detail -->
        <el-dialog title= "" :visible.sync="resourceDetailVisible">
            <el-row>
                <span  style="font-size: 14px">{{form.resourceName}}</span>
            </el-row>
            <!--走马灯（这里注意要和商品图片大小保持一致！）-->
            <el-carousel :interval="5000" type="card" height="360px">
                <el-carousel-item v-for="(o, index) in 5" :key="o">
                    <h3 class="small" style="text-align: center">
                        <div style="text-align: center">
                            <img :src="'data:image/jpeg;base64,'+ formPhoto[index]"
                                 style="width: 350px; height: 350px" class="image coverImg"/>
                        </div>
                    </h3>
                </el-carousel-item>
            </el-carousel>
            <!--表单-->
            <el-form :model="form">
<!--                永久隐藏-->
                <div style='display: inline-block;' v-if=false>
                    <el-form-item label="resourceId" :label-width="formLabelWidth">
                        <el-input v-model="form.resourceId" autocomplete="off"></el-input>
                    </el-form-item>
                </div>
<!--                价格和卖家-->
                <el-row>
                    <el-col :span="10">
                        <el-form-item label="价格（£） :" :label-width="formLabelWidth">
                            <el-input :disabled="seeEdit" v-model="form.price" autocomplete="off"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="卖家 :" :label-width="formLabelWidth">
                            <el-input :disabled="seeEdit" v-model="form.userName" autocomplete="off"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 运送方式和运费-->
                <el-row>
                    <el-col :span="10">
                        <el-form-item label="重量（kg） :" :label-width="formLabelWidth">
<!--                            <el-input v-model="form.deliverPrice" autocomplete="off"></el-input>-->
                            <el-input :disabled="seeEdit" v-model="defaultWeight" autocomplete="off"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="运送方式 :" :label-width="formLabelWidth">
<!--                            <el-input v-model="form.deliverType" autocomplete="off"></el-input>-->
                            <el-input :disabled="seeEdit" v-model="defaultDeliver" autocomplete="off"></el-input>
                        </el-form-item>
                    </el-col>
<!--                    <h5 style="margin: 2px">-->
<!--                        Hints:<br/>-->
<!--                        点击"加入购物车"会添加一份该商品进购物车<br/>-->
<!--                        如果想增加/减少数量可以去购物车界面操作<br/>-->
<!--                    </h5>-->
                </el-row>
                <!-- 库存和已销量-->
                <el-row v-if="seeStock">
                    <el-col :span="10">
                        <el-form-item label="库存 :" :label-width="formLabelWidth">
                            <el-input :disabled="seeEdit" v-model="form.stock" autocomplete="off"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="已售 :" :label-width="formLabelWidth">
                            <el-input :disabled="seeEdit" v-model="form.hot" autocomplete="off"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <!-- 购买和购物车按钮-->
                <el-row>
                    <el-col :span="10">
                    <div style="text-align: right">
                        <el-button
                                type="primary"
                                @click.prevent="showDetailPicture(form.resourceId)">商品细节图
                        </el-button>
                    </div>
                    </el-col>
                    <el-col :span="10">
                        <div style="text-align: right">
                        <el-button
                                type="primary"
                                icon="el-icon-goods"
                                @click.prevent="addToCart(form.resourceId)">加入购物车</el-button>
                        </div>
                    </el-col>
                </el-row>
                <!-- 描述-->
                <el-row style="margin-top: 10px">
                <el-form-item label="细节描述 :" :label-width="formLabelWidth">
                    <el-input :disabled="seeEdit" type="textarea" autosize = 1 v-model="form.resourceDesc"></el-input>
                </el-form-item>
                <el-form-item :disabled="seeEdit" label="评论 :" :label-width="formLabelWidth" v-if=false>
                    <el-input placeholder="尚无评论"></el-input>
                </el-form-item>
                </el-row>

                <!--                    修改按钮-->
                <el-row v-if="seeStock">
                    <el-tooltip placement="top" effect="light">
                        <div slot="content">
                            此排的修改/删除等按钮仅会被管理员看到，用户无权操作<br/>
                            你仅可以修改价格，库存，细节描述<br/>
                            你无法修改卖家名称，运送方式，已售(你对它们的修改不会被保存)<br/>
                        </div>
                        <el-button
                                type="primary"
                                style="float:left; font-size:16px; margin-left: 50px"
                                @click.prevent="editResourceDetail(form.resourceId)">修改库存/价格/描述
                        </el-button>
                    </el-tooltip>

                    <el-button
                            type="primary"
                            style="float:left; font-size:16px; margin-left: 50px"
                            @click.prevent="deleteThisResource(form.resourceId)">删除这个物品
                    </el-button>

                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                                    <h5 style="margin: 2px">
                                        Hints 小提示:<br/>
                                        每次点击"加入购物车"会添加1份该商品进购物车<br/>
                                        想添加多份可以多次点击"加入购物车"<br/>
                                        后续想 +1/-1 商品数量也可去购物车界面操作<br/>
                                    </h5>
                <el-button icon="el-icon-star-off" circle @click.prevent="handleLike(form.resourceId)"></el-button>
                <el-button icon="el-icon-error" circle @click.prevent="handleBad(form.resourceId)"></el-button>
                <el-button icon="el-icon-success" circle @click.prevent="handleGood(form.resourceId)"></el-button>
                <el-button icon="el-icon-download" circle @click.prevent="handleDownload(form.resourceId)"></el-button>
                <el-button icon="el-icon-warning" circle @click.prevent="handleWarning(form.resourceId)"></el-button>
            </div>
        </el-dialog>

        <!--商品细节大图-->
        <el-dialog title="" :visible.sync="goodsPictureDetailVisible" width="90%">
            <div style="text-align: center">
                <img :src="'data:image/jpeg;base64,'+ form.photoDetail"
                     style="width: 700px" class="image coverImg"/>
            </div>
        </el-dialog>

    </div>
</template>
<style>
    /*搜索行，样式全局影响，要特殊命名*/
    .bg-purple-dark-goods {
        background: Transparent;
    }

    .grid-content-goods {
        border-radius: 0px;
        min-height: 30px;
    }

    .el-carousel__item h3 {
        color: Transparent;
        font-size: 14px;
        opacity: 0.75;
        line-height: 200px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: Transparent;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: Transparent;
    }

    .el-select .el-input {
        width: 90px;
    }

    .input-with-select .el-input-group__prepend {
        background-color: #fff;
    }
    /*图片保持横宽比且自适应 https://www.cnblogs.com/soyxiaobi/p/9640729.html */
    .coverImg{
        object-fit: contain;
    }
</style>
<script>
    export default {
        created() {
            this.getResourceList(null,null,null,false,null);
            this.initUserInfo();
            this.adminView();
        },
        data() {
            return {
                value: '',
                form: {
                    resourceId: null,
                    resourceName: null,
                    resourceDesc: null,
                    deliverType: null,
                    deliverPrice: null,
                    price: null,
                    stock: null,
                    hot: null,
                    photoOne: null,
                    photoTwo: null,
                    photoThree: null,
                    photoFour: null,
                    photoFive: null,
                    photoDetail: null,
                },
                formPhoto: [],
                dialogFormVisible: false,
                resourceDetailVisible: false,
                formLabelWidth: '100px',
                //这里类似一个全局变量，表示输入框的value,初始化的值为null，可以通过this.input来获取value或者给它赋值
                input: "",
                // 搜索内嵌下拉框的value,这里给一个初始化的默认值1，也就是对应的all
                select: "1",
                // 是否精确匹配
                checked: false,

                textAutosize: true,
                // 存放表格的数据
                list: [],
                //总数量
                total: 0,
                //分页数
                pageSizeDefault: 12,
                //当前页
                currentPage: 1,
                //卡片数组
                cardData: [],
                //卡片数组的size
                sumNum: 0,

                demoUserImage: JSON.parse(localStorage.getItem('loginResult')).userDetail,
                //商品详情大图弹出
                goodsPictureDetailVisible: false,

                //24 h 过期
                expireTime: 24,

                defaultWeight:"unknown",
                defaultDeliver:"deliver&collect",

                //adminView 选择是否需要隐藏（对非 admin隐藏）
                seeStock : false,
                seeEdit :true,
            };
        },
        methods: {

            //每个界面都有这个，即判断当前登录用户是否是管理员，是的话展示的可操作内容会多i一些
            adminView() {
                if (localStorage.getItem('loginResult') === null) {
                    this.seeStock = false;
                    this.seeEdit = true;
                }
                else {
                    let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                    if(currentUserId <= 100 && currentUserId != 2) {
                        this.seeStock = true;
                        this.seeEdit = false;
                    }
                    else {
                        this.seeStock = false;
                        this.seeEdit = true;
                    }
                }
            },

            //get IP
            getIP() {
                fetch('https://api.ipify.org?format=json')
                    .then(x => x.json())
                    .then(({ ip }) => {
                        this.term = ip;
                    });
                this.textAreaUserName = JSON.parse(localStorage.getItem('loginResult')).userName;
            },

            // 界面进来就会被调用执行的函数,检测缓存localStorage.loginResult，为空查询gust信息,放入localStorage.loginResult
            initUserInfo() {
                if (localStorage.getItem('loginResult') === null) {
                    this.initGustLocalStorage();
                }
            },
            // 先清空localStorage.loginResult，再请求服务器，把gust用户的信息写入localStorage.loginResult
            initGustLocalStorage() {
                localStorage.removeItem('loginResult');
                this.$http.put("mainView/getUserInfo", {
                    userId: 2 // 1是管理员admin,2是gust
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        localStorage.setItem('loginResult', JSON.stringify(result.data))
                    } else {
                        this.$message('服务器异常');
                    }
                });
            },

            // 界面进来就会被调用执行的函数，空条件调用查询接口，返回资源列表
            getResourceList(value1, value2, value3, value4, value5) {
                this.list = [];
                this.$http.put("mainView/getDataByInput", {
                    userId: value1,
                    input: value2,
                    searchType: value3, // 1 商品  3 用户，没有2
                    roughMatch: value4, //< 随便匹配是否选中，不可为空，默认值为false>
                    item: value5 //物品类别，从1--12，可空，空则不指定类别
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        //如果结果为空，则给个提示
                        if(result.data.length == 0) {
                            this.$message('查找无结果');
                        }
                        else {
                            this.total = result.data.length;
                            this.list = result.data;
                            if (this.list.length > 0 && this.list.length <= this.pageSizeDefault) {
                                this.sumNum = this.list.length;
                                this.cardData = this.list;
                            } else if (this.list.length > this.pageSizeDefault) {
                                this.sumNum = this.pageSizeDefault;
                                this.cardData = this.list.slice((this.currentPage - 1) * this.pageSizeDefault, this.currentPage * this.pageSizeDefault);
                                // let h = this.cardData[2].userName;
                            } else {

                            }
                        }
                    } else {
                        this.$message('查找失败');
                    }
                });
            },
            // 分页pagination改变页数后触发的事件
            current_change: function (currentPage) {
                this.currentPage = currentPage;
                this.cardData = this.list.slice((currentPage - 1) * this.pageSizeDefault, currentPage * this.pageSizeDefault);
                this.sumNum = this.cardData.length;
            },

            // 输入查询按钮 触发的查询动作
            searchByInput() {
                this.getResourceList(null, this.input, this.select, this.checked, null);
            },

            // 类别查询按钮 触发的查询动作
            searchByItem(itemValue) {
                if(itemValue == 0) { // 不分类别，全部加载
                    this.getResourceList(null,null,null,false,null);
                }
                else {
                    this.getResourceList(null, null, null, false, itemValue);
                }
            },

            // 类别查询按钮 触发的查询动作
            searchBySeller(sellerId) {
                this.getResourceList(sellerId, null, null, false, null);
            },


            handleBuyDetail(resourceId) {
                this.$http.get("mainView/getResourceDetail/" + resourceId).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        this.form = result.data;
                        this.form.price = this.form.price/100;
                        this.formPhoto[0] = this.form.photoOne;
                        this.formPhoto[1] = this.form.photoTwo;
                        this.formPhoto[2] = this.form.photoThree;
                        this.formPhoto[3] = this.form.photoFour;
                        this.formPhoto[4] = this.form.photoFive;
                        this.resourceDetailVisible = true;
                    } else {
                        // 失败了
                        alert("服务器异常！");
                    }
                });
            },

            deleteThisResource(resourceId) {
                this.$message('这个功能还未实现，你可以将库存改为0，这样别人就无法购买了！');
            },

            editResourceDetail(resourceId) {
                        this.$confirm('你可在在上面的输入框中修改商品数值，然后点击这个修改按钮保存。点击修改会保存你修改的内容，譬如价格/库存/细节描述，你确定要保存吗？', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'info '
                        }).then(() => {
                            this.$http.put("mainView/editResourceDetail", {
                                resourceId: resourceId,
                                resourceDesc: this.form.resourceDesc,
                                price: this.form.price*100,
                                stock: this.form.stock
                            }).then(result => {
                                var result = result.body;
                                if (result.code === 200) {
                                    this.handleBuyDetail();
                                    this.$message('操作成功！');
                                } else {
                                    this.$message('操作数据失败！');
                                }
                            });
                        }).catch(() => {});
            },


            // 根据ID查到对应的详情大图是啥
            showDetailPicture(resourceId) {
                this.goodsPictureDetailVisible = true;
            },

            addToCart(resourceId) {
                let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                if(currentUserId ==2) {
                    this.$message('请先登录!');
                    return;
                }
                if(this.form.stock < 1) {
                    this.$message('库存不够了！添加失败!');
                }
                else {
                    let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                    this.$http.put("mainView/addToCart", {
                        userId: currentUserId,
                        resourceId: resourceId //物品类别，从1--12，可空，空则不指定类别
                    }).then(result => {
                        var result = result.body;
                        if (result.code === 200) {
                            //如果结果为空，则给个提示
                            if(result.data.length == 0) {
                                this.$message('添加失败!');
                            }
                            else {
                                this.$message({
                                    showClose: true,
                                    message: '成功添加一份进购物车！',
                                    type: 'success'
                                });
                                // this.$message('成功添加一份进购物车！');
                            }
                        } else {
                            this.$message('添加失败!');
                        }
                    });
                }
            },

            handleLike(inputResourceId) {
                this.$message('尚未开发');
            },

            handleBad(inputResourceId) {
                this.$message('尚未开发');
            },
            handleGood(inputResourceId) {
                this.$message('尚未开发');
            },
            handleDownload(inputResourceId) {
                this.$message('尚未开发');
            },
            handleWarning(inputResourceId) {
                this.$message('尚未开发');
            },
        }
    };
</script>