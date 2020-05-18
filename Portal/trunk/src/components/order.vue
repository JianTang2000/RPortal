<template>
    <div>

        <!-- 搜索-->
        <el-row>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
            <el-col :span="14">
                <div class="grid-content-goods bg-purple-dark-goods">
                    <el-row>
                        <el-col :span="14" class="grid">
                            <el-input placeholder="请输入订单号/用户全名" v-model="input" class="input-with-select">
                                <el-select v-model="select" slot="prepend" placeholder="选择">
                                    <el-option label="订单号" value="1"></el-option>
                                    <el-option label="用户名" value="2"></el-option>
                                </el-select>
                                <el-button slot="append" icon="el-icon-search"
                                           @click.prevent="searchOrder()"></el-button>
                            </el-input>
                        </el-col>
                        <el-col :span="5">
                            <div class="grid-content-goods bg-purple-dark-goods">
                                <el-button
                                        type="primary"
                                        style="float:left; font-size:16px; margin-left: 100px"
                                        @click.prevent="refresh()">刷新
                                </el-button>
                            </div>
                        </el-col>
                    </el-row>
                </div>
            </el-col>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
        </el-row>

<!--        勾选-->
        <el-row style="margin-top: 10px">
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
            <el-col :span="14">
                <el-radio-group v-model="radio" @change="radioChange">
                    <el-radio :label="5">全部</el-radio>
                    <el-radio :label="1">已付款</el-radio>
                    <el-radio :label="2">买家取消</el-radio>
                    <el-radio :label="3">卖家取消</el-radio>
                    <el-radio :label="4">已完成</el-radio>
                </el-radio-group>
            </el-col>

            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
        </el-row>

<!--        //表格-->
        <el-row>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
            <el-col :span="14">
                <div class="grid-content-goods bg-purple-dark-goods">
                    <!--表格-->
                    <el-table
                            element-loading-text="loading"
                            element-loading-spinner="el-icon-loading"
                            element-loading-background="rgba(0, 0, 0, 0.8)"
                            tooltip-effect="dark"
                            fit
                            :data="allOrderList"
                            style="width: 100%"
                            @selection-change="handleSelectionChange">
                        <div style='display: inline-block;' v-if=false>
                            <el-table-column type="userId" width="30"></el-table-column>
                            <el-table-column type="deliverType" width="30"></el-table-column>
                            <el-table-column type="deliverTime" width="30"></el-table-column>
                            <el-table-column type="addressType" width="30"></el-table-column>
                        </div>
                        <el-table-column prop="orderId" label="订单号" width="100"></el-table-column>
                        <el-table-column prop="typeDesc" label="状态" width="100"></el-table-column>
                        <el-table-column prop="sumPay" label="总金额" width="100"></el-table-column>
                        <el-table-column label="操作" width="100">
                            <template slot-scope="scope">
                                <el-button
                                        icon="el-icon-view"
                                        size="mini"
                                        @click.prevent="seeThisOrder(
                                        scope.row.userId,
                                        scope.row.orderId,
                                        scope.row.deliverType,
                                        scope.row.deliverTime,
                                        scope.row.addressType)">
                                </el-button>
                            </template>
                        </el-table-column>
                        <el-table-column prop="userName" label="买家名" width="100"></el-table-column>
                        <el-table-column prop="orderDetail" label="detail" width="340"></el-table-column>


                    </el-table>
                </div>
            </el-col>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
        </el-row>

        <!-- 弹出view-->
        <el-dialog title="单子细节" :visible.sync="orderViewAble">
            <el-row>
                <div class="grid-content-goods bg-purple-dark-goods">
                    <el-table
                            element-loading-text="loading"
                            element-loading-spinner="el-icon-loading"
                            element-loading-background="rgba(0, 0, 0, 0.8)"
                            tooltip-effect="dark"
                            fit
                            :data="orderDetailList"
                            style="width: 100%"
                            @selection-change="handleSelectionChange">
                        <div style='display: inline-block;' v-if=false>
                            <el-table-column prop="userId" label="" width="30"></el-table-column>
                            <el-table-column prop="resourceId" label="ID" width="30"></el-table-column>
                        </div>
                        <el-table-column prop="image" label="" width="130" >
                            <!-- 图片的显示 -->
                            <template   slot-scope="scope">
                                <img :src="'data:image/jpeg;base64,'+ scope.row.photoDetail"
                                     style="width: 110px; height: 110px" class="image coverImg"/>
                            </template>
                        </el-table-column>
                        <el-table-column prop="resourceName" label="商品信息" width="320"></el-table-column>
                        <!--                        <el-table-column prop="price" label="单价" width="50" sortable></el-table-column>-->
                        <el-table-column prop="price" label="单价" width="80"></el-table-column>
                        <el-table-column prop="num" label="数量" width="50"></el-table-column>
                        <el-table-column prop="sumPrice" label="金额" width="90"></el-table-column>
                    </el-table>
                </div>
            </el-row>
            <br>
            <el-row>
                <el-form :model="formPayView">
                    <el-form-item label="包装/运输费:" :label-width="formLabelWidth">
                        <el-input :disabled="true" v-model="formPayView.extraCost" placeholder=""
                                  autocomplete="off" style="width: 80px"></el-input>
                    </el-form-item>
                    <el-form-item  label="额外折扣:" :label-width="formLabelWidth">
                        <el-input :disabled="true" v-model="formPayView.discount" placeholder=""
                                  autocomplete="off" style="width: 80px"></el-input>
                    </el-form-item>
                    <el-form-item label="备注:" :label-width="formLabelWidth">
                        <el-input :disabled="true" v-model="formPayView.comment" placeholder=""
                                  autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="总共需要付:" :label-width="formLabelWidth">
                        <el-input :disabled="true" v-model="formPayView.sumPay" placeholder=""
                                  autocomplete="off"></el-input>
                    </el-form-item>
                </el-form>
            </el-row>
            <el-row>
                    <el-button
                            type="primary"
                            style="float:left; font-size:16px; margin-left: 145px"
                            @click.prevent="orderClose()">这个单子已经完成，我要关掉它
                    </el-button>
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
</style>
<script>
    export default {
        data() {
            return {
                formLabelWidth: '150px',
                select: "1", //1订单号     2name
                input: "", //搜索输入框
                radio: 1,
                allOrderList:[],
                orderDetailList:[],
                orderViewAble:false,

                //当前打开的单子的一些ID
                openOrderId: null,

                formPayView: {
                    extraCost: 0,
                    comment: "",
                    sumPay: 0,
                    discount: 0
                },


                orderViewId:null,
            };
        },
        created() {
            this.initSome();
            this.queryHisOrder();
        },
        methods: {
            initSome() {
                let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                if (currentUserId == 1) {
                    this.radio = 1;
                }
                else {
                    this.radio = 5;
                }
            },

            //查询当前用户的订单
            queryHisOrder() {
                let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                // admin ,全查，否则只查 本人
                if (currentUserId == 1) {
                    this.$http.put("mainView/queryAllHisOrder", {
                        radio : this.radio,
                        userId: currentUserId
                    }).then(result => {
                        var result = result.body;
                        if (result.code === 200) {
                            this.allOrderList = result.data;

                            if('undefined'!= typeof(this.allOrderList) && this.allOrderList !== null) {
                                let retLength = this.allOrderList.length;
                                for (let i = retLength -1; i >= 0; i--) {
                                    this.allOrderList[i].sumPay = this.allOrderList[i].sumPay/100;
                                }
                            }

                        } else {
                            this.$message('获取购数据失败！');
                        }
                    });
                } else {
                    this.$http.put("mainView/queryHisOrder", {
                        radio : this.radio,
                        userId: currentUserId
                    }).then(result => {
                        var result = result.body;
                        if (result.code === 200) {
                            this.allOrderList = result.data;

                            if('undefined'!= typeof(this.allOrderList) && this.allOrderList !== null) {
                                let retLength = this.allOrderList.length;
                                for (let i = retLength -1; i >= 0; i--) {
                                    this.allOrderList[i].sumPay = this.allOrderList[i].sumPay/100;
                                }
                            }

                        } else {
                            this.$message('获取购数据失败！');
                        }
                    });
                }
            },

            radioChange(val){
                this.  queryHisOrder();
            },

            //搜索，搜索会先自动将类型转化为全部，然后搜索这个ID（或者这个名字） 下的所有单子
            searchOrder() {
                this.radio = 5;
                let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                // admin ,没有限制，否则只能查自己一个人
                if (currentUserId == 1) {
                    this.$http.put("mainView/adminSearchOrder", {
                        select : this.select,
                        input : this.input,
                        radio : this.radio,
                        userId: currentUserId
                    }).then(result => {
                        var result = result.body;
                        if (result.code === 200) {
                            this.allOrderList = result.data;
                            if(!('undefined'!= typeof(this.allOrderList) && this.allOrderList !== null)) {
                                this.$message('未找到记录！');
                            }

                            if('undefined'!= typeof(this.allOrderList) && this.allOrderList !== null) {
                                let retLength = this.allOrderList.length;
                                for (let i = retLength -1; i >= 0; i--) {
                                    this.allOrderList[i].sumPay = this.allOrderList[i].sumPay/100;
                                }
                            }


                        } else {

                        }
                    });
                } else {
                    this.$http.put("mainView/searchOrder", {
                        select : this.select,
                        input : this.input,
                        radio : this.radio,
                        userId: currentUserId
                    }).then(result => {
                        var result = result.body;
                        if (result.code === 200) {
                            this.allOrderList = result.data;
                            if(!('undefined'!= typeof(this.allOrderList) && this.allOrderList !== null)) {
                                this.$message('未找到记录！');
                            }

                            if('undefined'!= typeof(this.allOrderList) && this.allOrderList !== null) {
                                let retLength = this.allOrderList.length;
                                for (let i = retLength -1; i >= 0; i--) {
                                    this.allOrderList[i].sumPay = this.allOrderList[i].sumPay/100;
                                }
                            }

                        } else {
                            this.$message('获取购数据失败！');
                        }
                    });
                }
            },

            seeThisOrder(userId, orderId, deliverType, deliverTime, addressType) {
                this.$http.put("mainView/seeThisOrder", {
                    userId: userId,
                    orderId: orderId
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        this.orderDetailList = result.data;

                        if('undefined'!= typeof(this.orderDetailList) && this.orderDetailList !== null) {
                            let retLength = this.orderDetailList.length;
                            for (let i = retLength -1; i >= 0; i--) {
                                this.orderDetailList[i].price = this.orderDetailList[i].price/100;
                                this.orderDetailList[i].sumPrice = this.orderDetailList[i].sumPrice/100;
                            }
                        }

                        //初始化折扣和评论,再加载
                        this.formPayView.extraCost = 0;
                        this.formPayView.discount = 0;
                        this.formPayView.comment = "";
                        this.formPayView.sumPay = 0;
                        this.formPayView.comment = this.orderDetailList[0].comment;
                        this.formPayView.extraCost = this.orderDetailList[0].extraCost/100;
                        this.formPayView.discount = this.orderDetailList[0].discount/100;
                        this.formPayView.sumPay = this.orderDetailList[0].sumPay/100;
                        this.openOrderId = orderId;
                        this.orderViewAble = true;
                    } else {
                        this.$message('获取购数据失败！');
                    }
                });
            },

            orderClose() {
                if(this.orderDetailList.length > 0) {
                    if(this.orderDetailList[0].state == "C") {
                        this.$confirm('请确定这个单子已经完成了吗？', '提示', {
                            confirmButtonText: '已经完成',
                            cancelButtonText: '还未完成',
                            type: 'info '
                        }).then(() => {
                            this.$http.put("mainView/orderClose", {
                                orderId: this.openOrderId
                            }).then(result => {
                                var result = result.body;
                                if (result.code === 200) {
                                    //刷新这个单子()
                                    this.orderDetailList = [];
                                    //初始化折扣和评论
                                    this.formPayView.extraCost = 0;
                                    this.formPayView.discount = 0;
                                    this.formPayView.comment = "";
                                    this.formPayView.sumPay = 0;
                                    this.openOrderId = 0;
                                    //刷新外面的 待审核的单子列表
                                    this.refresh();
                                    this.$message('确认成功！');
                                } else {
                                    this.$message('操作数据失败！');
                                }
                            });
                        }).catch(() => {});
                    }
                    else {
                        this.$message('这个单子已经是关闭/取消状态，不需要关闭！');
                    }
                }
                else {
                    this.$message('无内容，不需要确定！');
                }
            },

            refresh() {
                this.input = "";
                this.initSome();
                this.queryHisOrder();
            },
            // 选中行
            handleSelectionChange(val) {
            }
        }
    };
</script>
