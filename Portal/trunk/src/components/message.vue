<template>
    <div>
        <!--        //用户行-->
        <!--    当 isNotAdmin 为true时展示         -->
        <el-row>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
            <el-col :span="16">
                <div class="grid-content-goods bg-purple-dark-goods">

                    <!--   待审核的表格-->
                    <el-table
                            element-loading-text="loading"
                            element-loading-spinner="el-icon-loading"
                            element-loading-background="rgba(0, 0, 0, 0.8)"
                            tooltip-effect="dark"
                            fit
                            :data="checkList"
                            style="width: 100%"
                            @selection-change="handleSelectionChange" v-if="seeCheck">
                        <div style='display: inline-block;' v-if=false>
                            <el-table-column type="userId" width="30"></el-table-column>
                            <el-table-column type="deliverType" width="30"></el-table-column>
                            <el-table-column type="deliverTime" width="30"></el-table-column>
                            <el-table-column type="addressType" width="30"></el-table-column>
                        </div>
                        <el-table-column prop="typeDesc" label="待审核" width="100"></el-table-column>
                        <el-table-column label="operate" width="90">
                            <template slot-scope="scope">
                                <el-button
                                        icon="el-icon-view"
                                        size="mini"
                                        @click.prevent="handleCheck(scope.row.userId,scope.row.deliverType,
                                        scope.row.deliverTime,scope.row.addressType)">
                                </el-button>
                            </template>
                        </el-table-column>

                        <el-table-column prop="itemsNum" label="items" width="90"></el-table-column>
                        <el-table-column prop="itemsPrice" label="price" width="110"></el-table-column>
                        <el-table-column prop="userName" label="buyer name" width="150"></el-table-column>
                        <el-table-column prop="orderDetail" label="detail" width="290"></el-table-column>

                    </el-table>

                    <!--   预订单的表格-->
                    <el-table
                            element-loading-text="loading"
                            element-loading-spinner="el-icon-loading"
                            element-loading-background="rgba(0, 0, 0, 0.8)"
                            tooltip-effect="dark"
                            fit
                            :data="preOrderList"
                            style="width: 100%"
                            @selection-change="handleSelectionChange">
                        <el-table-column prop="typeDesc" label="预订单" width="100"></el-table-column>

                        <el-table-column prop="itemsNum" label="items" width="90"></el-table-column>
                        <el-table-column prop="itemsPrice" label="price" width="110"></el-table-column>
                        <el-table-column prop="orderDetail" label="detail" width="530"></el-table-column>
                    </el-table>
                    <br>
                    <!--   待付款的表格-->
                    <el-table
                            element-loading-text="loading"
                            element-loading-spinner="el-icon-loading"
                            element-loading-background="rgba(0, 0, 0, 0.8)"
                            tooltip-effect="dark"
                            fit
                            :data="orderList"
                            style="width: 100%"
                            @selection-change="handleSelectionChange">
                        <div style='display: inline-block;' v-if=false>
                            <el-table-column type="userId" width="30"></el-table-column>
                            <el-table-column type="deliverType" width="30"></el-table-column>
                            <el-table-column type="deliverTime" width="30"></el-table-column>
                            <el-table-column type="addressType" width="30"></el-table-column>
                        </div>
                        <el-table-column prop="typeDesc" label="待付款" width="100"></el-table-column>
                        <el-table-column label="operate" width="90">
                            <template slot-scope="scope">
                                <el-button
                                        icon="el-icon-view"
                                        size="mini"
                                        @click.prevent="handlePayView(scope.row.userId, scope.row.deliverType,
                                        scope.row.deliverTime,scope.row.addressType)">
                                </el-button>
                            </template>
                        </el-table-column>
                        <el-table-column prop="itemsNum" label="items" width="80"></el-table-column>
                        <el-table-column prop="itemsPrice" label="price" width="80"></el-table-column>
                        <el-table-column prop="extraCost" label="extra cost" width="90"></el-table-column>
                        <el-table-column prop="discount" label="discount" width="90"></el-table-column>
                        <el-table-column prop="sumPay" label="sum" width="90"></el-table-column>
                        <el-table-column prop="userName" label="买家" width="90"></el-table-column>
                        <el-table-column prop="orderDetail" label="detail" width="240"></el-table-column>
                    </el-table>
                    <br>


                </div>
            </el-col>
            <el-col :span="3">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
        </el-row>


        <!--  创建新物品-->
        <el-row v-if="seeCheck" style="margin: 10px">
            <el-button
                    type="primary"
                    style="float:left; font-size:16px; margin-left: 300px"
                    @click.prevent="openCreate()">创建新物品
            </el-button>
        </el-row>

        <!-- 弹出 创建新物品-->
        <el-dialog title="创建新物品" :visible.sync="newItemViewAble">
            <el-row>
                <el-form :model="formNewItem">
                    <el-form-item label="商品名称:" :label-width="formLabelWidth">
                        <el-input  v-model="formNewItem.resourceName" placeholder="必填" autocomplete="off" ></el-input>
                    </el-form-item>
                    <el-form-item label="商品描述:" :label-width="formLabelWidth">
                        <el-input  v-model="formNewItem.resourceDesc" placeholder="必填" autocomplete="off" ></el-input>
                    </el-form-item>
                    <el-form-item label="商品价格:" :label-width="formLabelWidth">
                        <el-input  v-model="formNewItem.price" placeholder="必填" autocomplete="off" ></el-input>
                    </el-form-item>
                    <el-form-item label="分类编号:" :label-width="formLabelWidth">
                        <el-input  v-model="formNewItem.classification" placeholder="1-12,可空，默认6" autocomplete="off" ></el-input>
                    </el-form-item>
                    <el-form-item label="库存:" :label-width="formLabelWidth">
                        <el-input  v-model="formNewItem.stock" placeholder="可空，默认100" autocomplete="off" ></el-input>
                    </el-form-item>
                </el-form>
            </el-row>

            <el-row style="margin: 5px">
                <el-upload
                        action=""
                        multiple
                        :limit="5"
                        accept=".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF"
                        :on-change="handle_change"
                        :on-remove="handle_remove"
                        :on-exceed="handleExceed"
                        :before-upload="uploadBefore"
                        :auto-upload = "false"
                        :file-list="fileList"
                        list-type="picture">
                    <el-button slot="trigger" size="medium" type="primary">上传商品图片（可不上传，最多传五张）</el-button>
                    <div slot="tip" class="el-upload__tip">图片类型支持:.jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF</div>
                </el-upload>
                <br>
                <br>
                <br>
                <el-button style="margin: 10px" type="primary" @click="createNewResource">点击这里确认创建</el-button>
            </el-row>

            <el-row>
                <h4 style="margin: 5px">
                    不可为空的信息:商品名称 ,细节描述,价格,<br/>
                    可为空的信息:分类编号 （1-12，参考下表，默认为 6）,库存（默认为100）,<br/>
                    图片:上传最多五张图片,对图片名称无特殊要求,不上传使用默认图片<br/>
                    分类编号表：<br/>
                    --(1)男装/女装 <br/>
                    --(2)鞋靴/箱包<br/>
                    --(3)童装玩具<br/>
                    --(4)家电/数码<br/>
                    --(5)美妆/洗护<br/>
                    --(6)美食/生鲜<br/>
                    --(7)运动/户外<br/>
                    --(8)工具/建材<br/>
                    --(9)家具/家饰<br/>
                    --(10)汽车/用品<br/>
                    --(11)百货/餐厨<br/>
                    --(12)学习/卡券<br/>
                </h4>
            </el-row>

        </el-dialog>

        <!-- 弹出pay view-->
        <el-dialog title="please confirm 请确认" :visible.sync="payViewAble">
            <el-row>
                <div class="grid-content-goods bg-purple-dark-goods">
                    <el-table
                            element-loading-text="loading"
                            element-loading-spinner="el-icon-loading"
                            element-loading-background="rgba(0, 0, 0, 0.8)"
                            tooltip-effect="dark"
                            fit
                            :data="payViewList"
                            style="width: 100%"
                            @selection-change="handleSelectionChange">
                        <div style='display: inline-block;' v-if=false>
                            <el-table-column prop="userId" label="" width="30"></el-table-column>
                            <el-table-column prop="resourceId" label="ID" width="30"></el-table-column>
                        </div>
                        <el-table-column prop="image" label="" width="130">
                            <!-- 图片的显示 -->
                            <template slot-scope="scope">
                                <img :src="'data:image/jpeg;base64,'+ scope.row.photoDetail"
                                     style="width: 110px; height: 110px" class="image coverImg"/>
                            </template>
                        </el-table-column>
                        <el-table-column prop="resourceName" label="Goods Info" width="310"></el-table-column>
                        <!--                        <el-table-column prop="price" label="单价" width="50" sortable></el-table-column>-->
                        <el-table-column prop="price" label="Price" width="80"></el-table-column>
                        <el-table-column prop="num" label="Num" width="60"></el-table-column>
                        <el-table-column prop="sumPrice" label="Sum Price" width="100"></el-table-column>
                    </el-table>
                </div>
            </el-row>
            <br>
            <el-row>
                <el-form :model="formPayView">
                    <el-form-item label="deliver cost 包装/运输费:" :label-width="formLabelWidth">
                        <el-input :disabled="true" v-model="formPayView.extraCost" placeholder=""
                                  autocomplete="off" style="width: 80px"></el-input>
                    </el-form-item>
                    <el-form-item label="discount 额外折扣:" :label-width="formLabelWidth">
                        <el-input :disabled="true" v-model="formPayView.discount" placeholder=""
                                  autocomplete="off" style="width: 80px"></el-input>
                    </el-form-item>
                    <el-form-item label="comment 备注:" :label-width="formLabelWidth">
                        <el-input :disabled="true" v-model="formPayView.comment" placeholder=""
                                  autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="you need to pay 总共需要付:" :label-width="formLabelWidth">
                        <el-input :disabled="true" v-model="formPayView.sumPay" placeholder=""
                                  autocomplete="off"></el-input>
                    </el-form-item>
                </el-form>
            </el-row>
            <el-row>
                <el-tooltip placement="top" effect="light">
                    <div slot="content">
                        点击确认代表你已经付过钱了<br/>
                    </div>
                    <el-button
                            type="primary"
                            style="float:left; font-size:16px; margin-left: 50px"
                            @click.prevent="orderPaid()">I have paid 我已付款
                    </el-button>
                </el-tooltip>

                <el-tooltip placement="top" effect="light">
                    <div slot="content">
                        点击取消代表你要取消这个顶单<br/>
                    </div>
                    <el-button
                            type="primary"
                            style="float:left; font-size:16px; margin-left: 50px"
                            @click.prevent="orderCancel()">I want cancel this order 不满意，我要取消
                    </el-button>
                </el-tooltip>
            </el-row>
        </el-dialog>

        <!-- 弹出审查框-->
        <el-dialog title="请审核数量,额外费用，折扣等" :visible.sync="checkViewAble">
            <el-row>
                <div class="grid-content-goods bg-purple-dark-goods">
                    <el-table
                            element-loading-text="loading"
                            element-loading-spinner="el-icon-loading"
                            element-loading-background="rgba(0, 0, 0, 0.8)"
                            tooltip-effect="dark"
                            fit
                            :data="checkGoodsList"
                            style="width: 100%"
                            @selection-change="handleSelectionChange">
                        <div style='display: inline-block;' v-if=false>
                            <el-table-column prop="userId" label="" width="30"></el-table-column>
                            <el-table-column prop="resourceId" label="ID" width="30"></el-table-column>
                        </div>
                        <el-table-column prop="image" label="" width="130">
                            <!-- 图片的显示 -->
                            <template slot-scope="scope">
                                <img :src="'data:image/jpeg;base64,'+ scope.row.photoDetail"
                                     style="width: 110px; height: 110px" class="image coverImg"/>
                            </template>
                        </el-table-column>
                        <el-table-column prop="resourceName" label="商品信息" width="240"></el-table-column>
                        <!--                        <el-table-column prop="price" label="单价" width="50" sortable></el-table-column>-->
                        <el-table-column prop="price" label="单价" width="80"></el-table-column>
                        <el-table-column prop="num" label="数量" width="50"></el-table-column>
                        <el-table-column prop="sumPrice" label="金额" width="90"></el-table-column>
                        <el-table-column label="操作" width="80">
                            <template slot-scope="scope">
                                <el-tooltip placement="top" effect="light">
                                    <div slot="content">
                                        请谨慎操作！<br/>
                                        点击会使用户购买该商品的数量-1<br/>
                                        你无法增加用户购买该商品的数量<br/>
                                    </div>
                                    <el-button
                                            icon="el-icon-remove"
                                            size="mini"
                                            @click.prevent="checkDeleteOne(scope.row.userId, scope.row.resourceId)">
                                    </el-button>
                                </el-tooltip>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </el-row>
            <br>
            <el-row>
                <el-form :model="formExtra">
                    <el-form-item label="包装/运输费:" :label-width="formLabelWidth">
                        <el-input v-model="formExtra.extraCost" placeholder=""
                                  autocomplete="off" style="width: 80px"></el-input>
                    </el-form-item>
                    <el-form-item label="额外折扣:" :label-width="formLabelWidth">
                        <el-input v-model="formExtra.discount" placeholder=""
                                  autocomplete="off" style="width: 80px"></el-input>
                    </el-form-item>
                    <el-form-item label="备注:" :label-width="formLabelWidth">
                        <el-input v-model="formExtra.comment" placeholder=""
                                  autocomplete="off"></el-input>
                    </el-form-item>
                </el-form>
            </el-row>
            <el-row>
                <el-tooltip placement="top" effect="light">
                    <div slot="content">
                        点击确认代表你接受buyer的这个订单<br/>
                    </div>
                    <el-button
                            type="primary"
                            style="float:left; font-size:16px; margin-left: 145px"
                            @click.prevent="sureThisOrder()">确定此单
                    </el-button>
                </el-tooltip>

                <el-tooltip placement="top" effect="light">
                    <div slot="content">
                        点击取消代表你不接受buyer的这个订单<br/>
                    </div>
                    <el-button
                            type="primary"
                            style="float:left; font-size:16px; margin-left: 145px"
                            @click.prevent="cancelThisOrder()">取消此单
                    </el-button>
                </el-tooltip>
            </el-row>
        </el-dialog>

    </div>
</template>
<style>
    .bg-purple-dark-goods {
        background: Transparent;
    }

    .grid-content-goods {
        border-radius: 0px;
        min-height: 30px;
    }

    .bg-purple-dark-goods2 {
        background: #E5E5E5;
    }

    .grid-content-goods2 {
        border-radius: 0px;
        min-height: 100px;
    }
</style>
<script>
    export default {
        data() {
            return {
                formLabelWidth: '200px',
                isNotAdmin: true,
                isAdmin: false,
                preOrderList: [],
                orderList: [],
                checkList: [],
                checkGoodsList: [],
                payViewList: [],
                sumPrice: null,
                extraCost: null,
                demoUserImage: JSON.parse(localStorage.getItem('loginResult')).userDetail,
                formExtra: {
                    extraCost: 0,
                    comment: "",
                    discount: 0
                },
                formPayView: {
                    extraCost: 0,
                    comment: "",
                    sumPay: 0,
                    discount: 0
                },

                //付款详情弹出框
                payViewAble: false,
                //当前 待付款表格选中行 点开后 ， 这个单子的确定信息
                orderUserId: null,
                orderDeliverType: null,
                orderDeliverTime: null,
                orderAddressType: null,


                //审核弹出框
                checkViewAble: false,
                //当前 待审核表格选中行 点开后 ， 这个待审核的单子的 buyer 的 ID
                checkOneUserId: null,
                checkOneDeliverType: null,
                checkOneDeliverTime: null,
                checkOneAddressType: null,

                //弹出的新建商品
                newItemViewAble: false,
                formNewItem: {
                    resourceName: null,
                    resourceDesc: null,
                    price: null,
                    classification: null,
                    stock: null
                },
                fileList: [],
                // upload 组件不支持自动更新 file list，所以设置 on-success 和 on-remove 的回调处理，将当前文件放到fileList2 副本中
                fileList2 :[],


                //adminView 选择是否需要隐藏（对非 admin隐藏）
                seeCheck: false,
            };
        },
        created() {
            this.queryPreOrder();
            this.queryOrder();
            this.queryCheckOrder();
            this.adminView();
        },
        methods: {

            //每个界面都有这个，即判断当前登录用户是否是管理员，是的话展示的可操作内容会多一些
            adminView() {
                if (localStorage.getItem('loginResult') === null) {
                    this.seeCheck = false;
                } else {
                    let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                    if (currentUserId <= 100 && currentUserId != 2) {
                        this.seeCheck = true;
                    } else {
                        this.seeCheck = false;
                    }
                }
            },


            //查询当前用户的预订单
            queryPreOrder() {
                let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                this.$http.put("mainView/queryPreOrder", {
                    userId: currentUserId
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        this.preOrderList = result.data;
                        if ('undefined' != typeof (this.preOrderList) && this.preOrderList !== null) {
                            let retLength = this.preOrderList.length;
                            for (let i = retLength - 1; i >= 0; i--) {
                                this.preOrderList[i].itemsPrice = this.preOrderList[i].itemsPrice / 100;
                            }
                        }
                    } else {
                        this.$message('获取购数据失败！');
                    }
                });
            },

            //查询当前用户的待付款
            queryOrder() {
                let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                this.$http.put("mainView/queryOrder", {
                    userId: currentUserId
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        this.orderList = result.data;

                        if ('undefined' != typeof (this.orderList) && this.orderList !== null) {
                            let retLength = this.orderList.length;
                            for (let i = retLength - 1; i >= 0; i--) {
                                this.orderList[i].itemsPrice = this.orderList[i].itemsPrice / 100;
                                this.orderList[i].extraCost = this.orderList[i].extraCost / 100;
                                this.orderList[i].discount = this.orderList[i].discount / 100;
                                this.orderList[i].sumPay = this.orderList[i].sumPay / 100;
                            }
                        }


                    } else {
                        this.$message('获取购数据失败！');
                    }
                });
            },

            //查询待审核列表（仅admin 能用）（所有人的预订单）
            queryCheckOrder() {
                let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                // admin see check only
                if (currentUserId == 1) {
                    this.$http.put("mainView/queryCheckOrder", {
                        userId: currentUserId
                    }).then(result => {
                        var result = result.body;
                        if (result.code === 200) {
                            this.checkList = result.data;

                            if ('undefined' != typeof (this.checkList) && this.checkList !== null) {
                                let retLength = this.checkList.length;
                                for (let i = retLength - 1; i >= 0; i--) {
                                    this.checkList[i].itemsPrice = this.checkList[i].itemsPrice / 100;
                                }
                            }

                        } else {
                            this.$message('获取购数据失败！');
                        }
                    });
                }
            },

            //打开付款的弹出界面
            handlePayView(userId, deliverType, deliverTime, addressType) {
                let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                if(userId != currentUserId) {
                    this.$message('这个订单不是你的，你不需要对它付款');
                    return;
                }

                this.$http.put("mainView/handlePayView", {
                    userId: userId,
                    deliverType: deliverType,
                    deliverTime: deliverTime,
                    addressType: addressType
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        this.payViewList = result.data;

                        if ('undefined' != typeof (this.payViewList) && this.payViewList !== null) {
                            let retLength = this.payViewList.length;
                            for (let i = retLength - 1; i >= 0; i--) {
                                this.payViewList[i].price = this.payViewList[i].price / 100;
                                this.payViewList[i].sumPrice = this.payViewList[i].sumPrice / 100;
                            }
                        }

                        //初始化折扣和评论,再加载
                        this.formPayView.extraCost = 0;
                        this.formPayView.discount = 0;
                        this.formPayView.comment = "";
                        this.formPayView.sumPay = 0;
                        this.formPayView.comment = this.payViewList[0].comment;
                        this.formPayView.extraCost = this.payViewList[0].extraCost / 100;
                        this.formPayView.discount = this.payViewList[0].discount / 100;
                        this.formPayView.sumPay = this.payViewList[0].sumPay / 100;

                        this.orderUserId = userId;
                        this.orderDeliverType = deliverType;
                        this.orderDeliverTime = deliverTime;
                        this.orderAddressType = addressType;

                        this.payViewAble = true;
                    } else {
                        this.$message('获取购数据失败！');
                    }
                });
            },

            //此单可行
            orderPaid() {
                if (this.payViewList.length > 0) {
                    this.$confirm('请确定你已经付款给商家之后，再点击已付款。如果你还没有付款就点击你已付款，经商家核实后会对你账号信用产生负面影响）', '提示', {
                        confirmButtonText: 'sure to do this',
                        cancelButtonText: 'not sure',
                        type: 'info '
                    }).then(() => {
                        let extraCostR = this.formPayView.extraCost * 100;
                        let discountR = this.formPayView.discount * 100;
                        let sumPayR = this.formPayView.sumPay * 100;

                        this.$http.put("mainView/orderPaid", {
                            extraCost: extraCostR,
                            discount: discountR,
                            comment: this.formPayView.comment,
                            sumPay: sumPayR,
                            userId: this.orderUserId,
                            deliverType: this.orderDeliverType,
                            deliverTime: this.orderDeliverTime,
                            addressType: this.orderAddressType,
                        }).then(result => {
                            var result = result.body;
                            if (result.code === 200) {
                                //刷新这个单子()
                                this.payViewList = [];
                                //初始化折扣和评论
                                this.formPayView.extraCost = 0;
                                this.formPayView.discount = 0;
                                this.formPayView.comment = "";
                                this.formPayView.sumPay = 0;
                                //刷新外面的 待审核的单子列表
                                this.queryOrder();
                                this.$message('确认成功！');
                            } else {
                                this.$message('操作数据失败！');
                            }
                        });
                    }).catch(() => {
                    });
                } else {
                    this.$message('无商品，不需要确定！');
                }
            },

            //付款弹出界面的取消顶单
            orderCancel() {
                if (this.payViewList.length > 0) {
                    this.$confirm('你确定要取消此单吗?）', '提示', {
                        confirmButtonText: 'sure to do this',
                        cancelButtonText: 'not sure',
                        type: 'info '
                    }).then(() => {

                        let extraCostR = this.formPayView.extraCost * 100;
                        let discountR = this.formPayView.discount * 100;
                        let sumPayR = this.formPayView.sumPay * 100;

                        //取消此单，会直接存入his中，状态为 user cancel
                        this.$http.put("mainView/orderCancel", {
                            extraCost: extraCostR,
                            discount: discountR,
                            comment: this.formPayView.comment,
                            sumPay: sumPayR,
                            userId: this.orderUserId,
                            deliverType: this.orderDeliverType,
                            deliverTime: this.orderDeliverTime,
                            addressType: this.orderAddressType,
                        }).then(result => {
                            var result = result.body;
                            if (result.code === 200) {
                                //刷新这个单子()
                                this.payViewList = [];
                                //初始化折扣和评论
                                this.formPayView.extraCost = 0;
                                this.formPayView.discount = 0;
                                this.formPayView.comment = "";
                                this.formPayView.sumPay = 0;
                                //刷新外面的 待审核的单子列表
                                this.queryOrder();
                                this.$message('操作成功！');
                            } else {
                                this.$message('操作数据失败！');
                            }
                        });
                    }).catch(() => {
                    });
                } else {
                    this.$message('无商品，不需要操作！');
                }
            },


            //打开一个指定用户的审核单子
            handleCheck(userId, deliverType, deliverTime, addressType) {
                this.$http.put("mainView/handleCheck", {
                    userId: userId,
                    deliverType: deliverType,
                    deliverTime: deliverTime,
                    addressType: addressType
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        this.checkGoodsList = result.data;
                        this.checkViewAble = true;
                        this.checkOneUserId = userId;
                        this.checkOneDeliverType = deliverType;
                        this.checkOneDeliverTime = deliverTime;
                        this.checkOneAddressType = addressType;
                        //初始化折扣和评论
                        this.formExtra.extraCost = 0;
                        this.formExtra.discount = 0;
                        this.formExtra.comment = "";

                        if ('undefined' != typeof (this.checkGoodsList) && this.checkGoodsList !== null) {
                            let retLength = this.checkGoodsList.length;
                            for (let i = retLength - 1; i >= 0; i--) {
                                this.checkGoodsList[i].price = this.checkGoodsList[i].price / 100;
                                this.checkGoodsList[i].sumPrice = this.checkGoodsList[i].sumPrice / 100;
                            }
                        }


                    } else {
                        this.$message('获取购数据失败！');
                    }
                });
            },

            //打开一个指定用户的审核单子--给某个商品-1
            checkDeleteOne(userId, resourceId) {
                this.$http.put("mainView/checkDeleteOne", {
                    userId: this.checkOneUserId,
                    resourceId: resourceId,
                    deliverType: this.checkOneDeliverType,
                    deliverTime: this.checkOneDeliverTime,
                    addressType: this.checkOneAddressType
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        this.checkGoodsList = result.data;

                        if ('undefined' != typeof (this.checkGoodsList) && this.checkGoodsList !== null) {
                            let retLength = this.checkGoodsList.length;
                            for (let i = retLength - 1; i >= 0; i--) {
                                this.checkGoodsList[i].price = this.checkGoodsList[i].price / 100;
                                this.checkGoodsList[i].sumPrice = this.checkGoodsList[i].sumPrice / 100;
                            }
                        }


                    } else {
                        this.$message('操作数据失败！');
                    }
                });
            },

            //此单可行
            sureThisOrder() {
                if (this.checkGoodsList.length > 0) {
                    this.$confirm('你确定要确认此单吗?（你已经检查了商品数，包装运输费，备注等信息都OK吗？）', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'info '
                    }).then(() => {
                        let extraCostR = this.formExtra.extraCost * 100;
                        let discountR = this.formExtra.discount * 100;
                        this.$http.put("mainView/sureThisOrder", {
                            extraCost: extraCostR,
                            discount: discountR,
                            comment: this.formExtra.comment,
                            userId: this.checkOneUserId,
                            deliverType: this.checkOneDeliverType,
                            deliverTime: this.checkOneDeliverTime,
                            addressType: this.checkOneAddressType
                        }).then(result => {
                            var result = result.body;
                            if (result.code === 200) {
                                //刷新这个单子()
                                this.checkGoodsList = [];
                                //初始化折扣和评论
                                this.formExtra.extraCost = 0;
                                this.formExtra.discount = 0;
                                this.formExtra.comment = "";
                                //刷新外面的 待审核的单子列表
                                this.queryCheckOrder();
                                this.$message('确认成功！');
                            } else {
                                this.$message('操作数据失败！');
                            }
                        });
                    }).catch(() => {
                    });
                } else {
                    this.$message('无商品，不需要确定！');
                }
            },

            //此单不行
            cancelThisOrder() {
                if (this.checkGoodsList.length > 0) {
                    this.$confirm('你确定要取消此单吗?你可以在备注中填写你取消此顶单的原因，如大量商品无货/送货不便等）', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'info '
                    }).then(() => {
                        let extraCostR = this.formExtra.extraCost * 100;
                        let discountR = this.formExtra.discount * 100;
                        //取消此单，会直接存入his中，状态为 saler取消
                        this.$http.put("mainView/cancelThisOrder", {
                            extraCost: extraCostR,
                            discount: discountR,
                            comment: this.formExtra.comment,
                            userId: this.checkOneUserId,
                            deliverType: this.checkOneDeliverType,
                            deliverTime: this.checkOneDeliverTime,
                            addressType: this.checkOneAddressType
                        }).then(result => {
                            var result = result.body;
                            if (result.code === 200) {
                                //刷新这个单子()
                                this.checkGoodsList = [];
                                //初始化折扣和评论
                                this.formExtra.extraCost = 0;
                                this.formExtra.discount = 0;
                                this.formExtra.comment = "";
                                //刷新外面的 待审核的单子列表
                                this.queryCheckOrder();
                                this.$message('操作成功！');
                            } else {
                                this.$message('操作数据失败！');
                            }
                        });
                    }).catch(() => {
                    });
                } else {
                    this.$message('无商品，不需要确定！');
                }
            },

            resetFormNew() {
                this.formNewItem.resourceName = null;
                this.formNewItem.resourceDesc = null;
                this.formNewItem.price = null;
                this.formNewItem.classification = null;
                this.formNewItem.stock = null;
            },

            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 5 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList2.length} 个文件`);
            },

            //https://element.eleme.cn/#/zh-CN/component/upload
            //fileList 存的是移除之后的文件，file是选择移除的那个文件，这个函数在  “移除文件时被调用”
            handle_remove(file, fileList) {
                this.fileList2 = fileList;
            },

            //这个函数在  上传文件时被调用
            handle_change(file, fileList) {
                this.fileList2 = fileList;
            },
            uploadBefore(file) {
                if (file.size > 10 * 1024 * 1024) {
                    this.$message.error("File size exceeded 10M!");
                    return false;
                }
            },

            openCreate() {
                this.newItemViewAble = true;
                this.resetFormNew();
            },


            createNewResource() {
                if(this.formNewItem.resourceName == null || this.formNewItem.resourceDesc == null || this.formNewItem.price == null) {
                    this.$message('创建失败，名称，描述和价格不能为空！');
                    return;
                }
                else {
                    this.$confirm('你确定要新建这个商品吗？（如果你没有上传图片,系统会使用默认图片代替）', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '我再想想',
                        type: 'info '
                    }).then(() => {
                        if(this.fileList2.length > 0) {
                            this.uploadImg(this.fileList2);
                            this.resetFormNew();
                        }
                        // 没有传图片
                        else {
                            // this.$message('没图片');
                            let _this = this;
                            let formData = new FormData();
                            formData.append("resourceName", this.formNewItem.resourceName);
                            formData.append("resourceDesc", this.formNewItem.resourceDesc);
                            formData.append("price", this.formNewItem.price*100);
                            formData.append("classification", this.formNewItem.classification);
                            formData.append("stock", this.formNewItem.stock);
                                _this.$upload.post("file/upload0", formData)
                                    .then(res => {
                                        _this.$message("success！");
                                        this.resetFormNew();
                                    }).catch(data=>{
                                    _this.$message("failed!");
                                })
                        }
                    }).catch(() => {});
                }
            },

            uploadImg(fileList2){
                let _this = this;
                let formData = new FormData();
                formData.append("resourceName", this.formNewItem.resourceName);
                formData.append("resourceDesc", this.formNewItem.resourceDesc);
                formData.append("price", this.formNewItem.price*100);
                formData.append("classification", this.formNewItem.classification);
                formData.append("stock", this.formNewItem.stock);
                if(fileList2.length == 1) {
                    formData.append("file1", fileList2[0].raw);
                    _this.$upload.post("file/upload1", formData)
                        .then(res => {
                            _this.$message("success！");
                        }).catch(data=>{
                        _this.$message("failed!");
                    })
                }
                else if(fileList2.length == 2) {
                    formData.append("file1", fileList2[0].raw);
                    formData.append("file2", fileList2[1].raw);
                    _this.$upload.post("file/upload2", formData)
                        .then(res => {
                            _this.$message("success！");
                        }).catch(data=>{
                        _this.$message("failed!");
                    })
                }

                else if(fileList2.length == 3) {
                    formData.append("file1", fileList2[0].raw);
                    formData.append("file2", fileList2[1].raw);
                    formData.append("file3", fileList2[2].raw);
                    _this.$upload.post("file/upload3", formData)
                        .then(res => {
                            _this.$message("success！");
                        }).catch(data=>{
                        _this.$message("failed!");
                    })
                }
                else if(fileList2.length == 4) {
                    formData.append("file1", fileList2[0].raw);
                    formData.append("file2", fileList2[1].raw);
                    formData.append("file3", fileList2[2].raw);
                    formData.append("file4", fileList2[3].raw);
                    _this.$upload.post("file/upload4", formData)
                        .then(res => {
                            _this.$message("success！");
                        }).catch(data=>{
                        _this.$message("failed!");
                    })
                }
                else if(fileList2.length == 5) {
                    formData.append("file1", fileList2[0].raw);
                    formData.append("file2", fileList2[1].raw);
                    formData.append("file3", fileList2[2].raw);
                    formData.append("file4", fileList2[3].raw);
                    formData.append("file5", fileList2[4].raw);
                    _this.$upload.post("file/upload5", formData)
                        .then(res => {
                            _this.$message("success！");
                        }).catch(data=>{
                        _this.$message("failed!");
                    })
                }
                else {
                    _this.$message("failed! sth is wrong!");
                }

            },



            // 选中行
            handleSelectionChange(val) {
            }
        }
    };
</script>
