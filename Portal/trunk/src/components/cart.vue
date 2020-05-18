<template>
    <div>
<!--        // 购物车表格行-->
        <el-row>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
            <el-col :span="14">
                <div class="grid-content-goods bg-purple-dark-goods">
                    <el-table
                            element-loading-text="loading"
                            element-loading-spinner="el-icon-loading"
                            element-loading-background="rgba(0, 0, 0, 0.8)"
                            tooltip-effect="dark"
                            fit
                            :data="goodsList"
                            style="width: 100%"
                            @selection-change="handleSelectionChange">
                        <div style='display: inline-block;' v-if=false>
                            <el-table-column prop="resourceId" label="ID" width="30"></el-table-column>
                        </div>
<!--                        //隐藏选中，因为我懒得写部分选中-->
                        <div style='display: inline-block;' v-if=false>
                        <el-table-column type="selection" width="30"></el-table-column>
                        </div>
                        <el-table-column prop="image" label="" width="130" >
                            <!-- 图片的显示 -->
                            <template   slot-scope="scope">
                                <img :src="'data:image/jpeg;base64,'+ scope.row.photoDetail"
                                     style="width: 110px; height: 110px" class="image coverImg"/>
                            </template>
                        </el-table-column>
                        <el-table-column prop="resourceName" label="Goods Info" width="280"></el-table-column>
<!--                        <el-table-column prop="price" label="单价" width="50" sortable></el-table-column>-->
                        <el-table-column prop="price" label="Price" width="80"></el-table-column>
                        <el-table-column prop="num" label="Num" width="60"></el-table-column>
                        <el-table-column prop="sumPrice" label="Sum Price" width="110"></el-table-column>
                        <el-table-column label="Operate" width="150">
                            <template slot-scope="scope">
                                <el-button
                                        icon="el-icon-remove"
                                        size="mini"
                                        @click.prevent="handleDeleteOne(scope.row.resourceId)">
                                </el-button>
                                <el-button
                                        icon="el-icon-circle-plus"
                                        size="mini"
                                        @click.prevent="handleAddOne(scope.row.resourceId)">
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </el-col>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
        </el-row>
<!--        合计行-->
        <el-row style="margin-top: 10px">
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>

            <el-col :span="14">
                <div class="grid-content-goods bg-purple-dark-goods">
                    <el-form :model="form">
                        <el-row>

                            <el-col :span="3">
                                <el-button
                                        type="primary"
                                        style="padding:13px; float:left; font-size:16px; margin-left: 30px"
                                        @click.prevent="cleanCart()">Clear Cart
                                </el-button>
                            </el-col>

                            <el-col :span="7">
                                <el-form-item label="Items :" :label-width="formLabelWidth">
                                    <el-input :disabled="true" v-model="goodsListLength" autocomplete="off"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="7">
                                <el-form-item label="Sum price :" :label-width="formLabelWidth">
                                    <el-input :disabled="true" v-model="sumPrice" autocomplete="off"></el-input>
                                </el-form-item>
                            </el-col>

                            <el-col :span="6">
                                <el-button
                                        type="primary"
                                        style="padding:13px; float:left; font-size:16px; margin-left: 80px"
                                        @click.prevent="checkOut()">Check Out
                                </el-button>
                            </el-col>
                        </el-row>
                    </el-form>
                </div>
            </el-col>

            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
        </el-row>

<!--        &lt;!&ndash;        提示信息行&ndash;&gt;-->
<!--        <el-row>-->
<!--            <el-col :span="5">-->
<!--                <div class="grid-content-goods bg-purple-dark-goods"></div>-->
<!--            </el-col>-->
<!--            <el-col :span="14">-->
<!--                <div class="grid-content-goods bg-purple-dark-goods">-->
<!--                    <h4>-->
<!--                        Hints: how to use this web portal?<br/>-->
<!--                        1 注册登录:首先你需要注册一个账号，填写名称，电话，密码，派送地址等信息，然后登录~~<br/>-->
<!--                        2 浏览商品: 搜索浏览你所需要的商品，并将它们添加进购物车~~<br/>-->
<!--                        3 下订单: 在购物车界面check out,你会在这里选择取货方式和时间地址等，这将形成一个预订单~~<br/>-->
<!--                        4 等待确认: 你需要等待商家检查并确认你的账单，你可以在处理界面看到进度~~<br/>-->
<!--                        5 确认付款: 当你的账单被商家确认接受,你会在待付款栏看到它，请在付款后点击已付款告知商家~~<br/>-->
<!--                        6 完成交易: 商家确认你已付款后，会将商品送给你~~<br/>-->
<!--                        7 查看历史: 你可以在订单界面查看你的历史订单，如果你有预订单被商家拒绝，也可以在这里看到~~<br/>-->
<!--                    </h4>-->
<!--                </div>-->
<!--            </el-col>-->
<!--            <el-col :span="5">-->
<!--                <div class="grid-content-goods bg-purple-dark-goods"></div>-->
<!--            </el-col>-->
<!--        </el-row>-->

        <!-- 弹出结算 -->
        <el-dialog title="check out" :visible.sync="goodsPictureDetailVisible">
            <el-row :gutter="2" style = "padding: 5px">
                <el-col :span="8">
                    <div style="text-align: right; margin-top: 8px">how to collect item 取货方式 :</div>
                </el-col>
                <el-col :span="12">
                    <el-select v-model="selectDeliver" placeholder="请选择">
                        <el-option label="自取 by yourself" value="1"></el-option>
                        <el-option label="派送 deliver" value="2"></el-option>
                    </el-select>
                </el-col>
            </el-row>

            <el-row :gutter="2" style = "padding: 5px">
                <el-col :span="8">
                    <div style="text-align: right; margin-top: 8px">time 时间 :</div>
                </el-col>
                <el-col :span="12">
<!--                    <el-select v-model="selectDeliverTime" placeholder="请选择" >-->
<!--                        <el-option label="今天" value="1"></el-option>-->
<!--                        <el-option label="明天" value="2"></el-option>-->
<!--                        <el-option label="后天" value="3"></el-option>-->
<!--                        <el-option label="ASAP" value="4"></el-option>-->
<!--                    </el-select>-->

                    <el-select v-model="selectDeliverTime" placeholder="请选择">
                        <el-option
                                v-for="item in selectDeliverTimeOptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>

                </el-col>
            </el-row>

            <el-row :gutter="2" style = "padding: 5px">
                <el-col :span="8">
                    <div style="text-align: right; margin-top: 8px">address 地址 :</div>
                </el-col>
                <el-col :span="12">
                    <el-select v-model="selectAddress" placeholder="请选择" style="width:100%">
                        <el-option
                                v-for="item in addressSet"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-col>
            </el-row>
            <el-row style = "padding: 5px">
                <el-tooltip placement="top" effect="light">
                    <div slot="content">
                        点击确定后，会形成一个预订单<br/>
                        接下来你需要等待商家确认这个预订单<br/>
                        商家可能会去掉一些unavailable的商品，但绝不会为你添加任何额外收费的新商品<br/>
                        商家可能会添加包装/运输的费用<br/>
                    </div>
                    <el-button
                            type="primary"
                            style="float:left; font-size:16px; margin-left: 145px"
                            @click.prevent="sureToCheckOut()">sure 确定
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
                goodsList: [],
                goodsListLength:null,
                sumPrice:null,
                selectDeliver: "2",
                // 运送时间选择
                selectDeliverTime : "4",
                selectDeliverTimeOptions: [{
                value: '1',
                label: 'today'
            }, {
                value: '2',
                label: 'tomorrow'
            }, {
                value: '3',
                label: 'the day after tomorrow'
            }, {
                value: '4',
                label: 'ASAP'
            }],

                selectAddress : "1",
                addressSet: [{
                    value: '1',
                    label: 'A1111111111'
                }, {
                    value: '2',
                    label: 'A22222222222'
                }, {
                    value: '3',
                    label: 'A33333333333'
                }],

                form: {
                    resourceId: null,
                    resourceName: null,
                    uploadBy: null,
                    uploadTime: null,
                    downloadAble: null,
                    resourceLink: null,
                    resourceSize: null,
                    resourceHot: null,
                    resourceDesc: null
                },
                formLabelWidth: '120px',
                demoUserImage: JSON.parse(localStorage.getItem('loginResult')).userDetail,
                goodsPictureDetailVisible: false,
            };
        },
        created() {
            this.queryCart();
            this.setAddressSet();
            this.resetNowDays();
        },
        methods: {
            resetNowDays() {
                let date = new Date();
                let seperator1 = "-";
                let year = date.getFullYear();
                let month = date.getMonth() + 1;
                let strDate = date.getDate();
                let strDate2 = date.getDate() +1 ;
                let strDate3 = date.getDate() +2 ;
                if (month >= 1 && month <= 9) {
                    month = "0" + month;
                }
                if (strDate >= 0 && strDate <= 9) {
                    strDate = "0" + strDate;
                }
                let currentdate = year + seperator1 + month + seperator1 + strDate;
                let currentdate2 = year + seperator1 + month + seperator1 + strDate2;
                let currentdate3 = year + seperator1 + month + seperator1 + strDate3;

                this.selectDeliverTimeOptions[0].value = currentdate;
                this.selectDeliverTimeOptions[0].label = "today " + currentdate;
                this.selectDeliverTimeOptions[1].value = currentdate2;
                this.selectDeliverTimeOptions[1].label = "tomorrow " + currentdate2;
                this.selectDeliverTimeOptions[2].value = currentdate3;
                this.selectDeliverTimeOptions[2].label = "the day after tomorrow " + currentdate3;
                this.selectDeliverTimeOptions[3].value = "4";
                this.selectDeliverTimeOptions[3].label = "ASAP";

                // this.$message(currentdate);
            },



            setAddressSet() {
                this.addressSet[0].label = JSON.parse(localStorage.getItem('loginResult')).address1Line1 + "," +
                    JSON.parse(localStorage.getItem('loginResult')).address1Line2 + "," +
                    JSON.parse(localStorage.getItem('loginResult')).address1PostCode;
                this.addressSet[0].value = "1";
                this.addressSet[1].label = JSON.parse(localStorage.getItem('loginResult')).address2Line1 + "," +
                    JSON.parse(localStorage.getItem('loginResult')).address2Line2 + "," +
                    JSON.parse(localStorage.getItem('loginResult')).address2PostCode;
                this.addressSet[1].value = "2";
                this.addressSet[2].label = JSON.parse(localStorage.getItem('loginResult')).address3Line1 + "," +
                    JSON.parse(localStorage.getItem('loginResult')).address3Line2 + "," +
                    JSON.parse(localStorage.getItem('loginResult')).address3PostCode;
                this.addressSet[2].value = "3";
            },

            // 界面进来就会被调用执行的函数，空条件调用查询接口，返回推荐资源列表
            queryCart() {
                let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                this.$http.put("mainView/queryCart", {
                    userId: currentUserId
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        this.goodsList = result.data;
                        if('undefined'!= typeof(this.goodsList) && this.goodsList !== null) {
                            this.goodsListLength = this.goodsList.length;
                            let sumP = 0;
                            for (let i = this.goodsListLength -1; i >= 0; i--) {
                                sumP += (this.goodsList[i].sumPrice/100);
                            }
                            this.sumPrice = sumP;

                            for (let i = this.goodsListLength -1; i >= 0; i--) {
                                this.goodsList[i].price = this.goodsList[i].price/100;
                                this.goodsList[i].sumPrice = this.goodsList[i].sumPrice/100;
                            }
                        }
                        else {
                            this.goodsListLength = 0;
                            this.sumPrice = 0;
                        }
                    } else {
                        this.$message('获取购物车数据失败！');
                    }
                });
            },

            //删掉一个之后会重新加载整个购物车表格
            handleDeleteOne(resourceId) {
                let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                this.$http.put("mainView/cartDeleteOne", {
                    resourceId: resourceId,
                    userId: currentUserId
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        this.goodsList = result.data;
                        if('undefined'!= typeof(this.goodsList) && this.goodsList !== null) {
                            this.goodsListLength = this.goodsList.length;
                            let sumP = 0;
                            // for (let i = this.goodsListLength -1; i >= 0; i--) {
                            //     sumP += this.goodsList[i].sumPrice;
                            // }
                            // this.sumPrice = sumP;
                            for (let i = this.goodsListLength -1; i >= 0; i--) {
                                sumP += (this.goodsList[i].sumPrice/100);
                            }
                            this.sumPrice = sumP;

                            for (let i = this.goodsListLength -1; i >= 0; i--) {
                                this.goodsList[i].price = this.goodsList[i].price/100;
                                this.goodsList[i].sumPrice = this.goodsList[i].sumPrice/100;
                            }
                        }
                        else {
                            this.goodsListLength = 0;
                            this.sumPrice = 0;
                        }
                    } else {
                        this.$message('操作失败！');
                    }
                });
            },

            handleAddOne(resourceId) {
                let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                this.$http.put("mainView/cartAddOne", {
                    resourceId: resourceId,
                    userId: currentUserId
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        this.goodsList = result.data;
                        if('undefined'!= typeof(this.goodsList) && this.goodsList !== null) {
                            this.goodsListLength = this.goodsList.length;
                            let sumP = 0;
                            // for (let i = this.goodsListLength -1; i >= 0; i--) {
                            //     sumP += this.goodsList[i].sumPrice;
                            // }
                            // this.sumPrice = sumP;
                            for (let i = this.goodsListLength -1; i >= 0; i--) {
                                sumP += (this.goodsList[i].sumPrice/100);
                            }
                            this.sumPrice = sumP;

                            for (let i = this.goodsListLength -1; i >= 0; i--) {
                                this.goodsList[i].price = this.goodsList[i].price/100;
                                this.goodsList[i].sumPrice = this.goodsList[i].sumPrice/100;
                            }
                        }
                        else {
                            this.goodsListLength = 0;
                            this.sumPrice = 0;
                        }
                    } else {
                        this.$message('操作失败！');
                    }
                });
            },

            cleanCart() {
                let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                this.$http.put("mainView/cleanCart", {
                    userId: currentUserId
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        this.goodsList = result.data;
                        if('undefined'!= typeof(this.goodsList) && this.goodsList !== null) {
                            this.goodsListLength = this.goodsList.length;
                            let sumP = 0;
                            // for (let i = this.goodsListLength -1; i >= 0; i--) {
                            //     sumP += this.goodsList[i].sumPrice;
                            // }
                            // this.sumPrice = sumP;
                            for (let i = this.goodsListLength -1; i >= 0; i--) {
                                sumP += (this.goodsList[i].sumPrice/100);
                            }
                            this.sumPrice = sumP;

                            for (let i = this.goodsListLength -1; i >= 0; i--) {
                                this.goodsList[i].price = this.goodsList[i].price/100;
                                this.goodsList[i].sumPrice = this.goodsList[i].sumPrice/100;
                            }
                        }
                        else {
                            this.goodsListLength = 0;
                            this.sumPrice = 0;
                        }
                    } else {
                        this.$message('操作失败！');
                    }
                });
            },

            //
            checkOut() {
                // window.location.reload();
                if (this.goodsListLength != 0) {
                    this.goodsPictureDetailVisible = true;
                }
                else {
                    this.$message('购物车空空如也，先添加点什么吧~');
                }
            },

            sureToCheckOut() {
                let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                let deliverType = this.selectDeliver;// 1 自取/2 派送 （都是 string）
                let deliverTime =  this.selectDeliverTime; // 1 2 3 4
                let addressType =  this.selectAddress; // 1 2 3
                this.$http.put("mainView/sureToCheckOut", {
                    deliverType: deliverType,
                    deliverTime: deliverTime,
                    addressType: addressType,
                    userId: currentUserId
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        this.goodsList = result.data;
                        if('undefined'!= typeof(this.goodsList) && this.goodsList !== null) {
                            this.goodsListLength = this.goodsList.length;
                            let sumP = 0;
                            // for (let i = this.goodsListLength -1; i >= 0; i--) {
                            //     sumP += this.goodsList[i].sumPrice;
                            // }
                            // this.sumPrice = sumP;
                            for (let i = this.goodsListLength -1; i >= 0; i--) {
                                sumP += (this.goodsList[i].sumPrice/100);
                            }
                            this.sumPrice = sumP;

                            for (let i = this.goodsListLength -1; i >= 0; i--) {
                                this.goodsList[i].price = this.goodsList[i].price/100;
                                this.goodsList[i].sumPrice = this.goodsList[i].sumPrice/100;
                            }
                            this.$message('系统检查到在你选定的“取货方式+时间+地址”组合下有未完成的订单，请先完成！');
                        }
                        else {
                            this.goodsListLength = 0;
                            this.sumPrice = 0;

                            this.$message({
                                showClose: true,
                                duration: 10000,
                                message: '成功添加预订单，等待商家的确认中...你可以在"处理"界面查看进度！ 请注意，长时间未被接单,或被接单后长时间未确认付款，该订单可能被系统取消！',
                                type: 'success'
                            });
                            this.goodsPictureDetailVisible = false;

                            // this.$message('成功添加预订单，等待商家的确认中，你可以在"处理"界面查看进度！');
                        }
                    } else {
                        this.$message('操作失败！');
                    }
                });
            },

            // 选中行
            handleSelectionChange(val) {
            }

        }
    };
</script>
