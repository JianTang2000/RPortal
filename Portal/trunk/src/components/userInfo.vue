<template>
    <div>
        <el-row>
            <el-col :span="5">
                <div class="grid-content-userInfo bg-purple-dark-userInfo"></div>
            </el-col>
            <el-col :span="14">
                <div class="grid-content-userInfo bg-purple-dark-userInfo">
                    <!--左边-->
                    <el-col :span="9" class="grid">
                        <div class="demo-img-userPhoto">
                            <img :src="'data:image/jpeg;base64,'+ demoUserImage"
                                 style="width: 150px;height: 150px; margin-right: 200px"></img>
                        </div>

                        <div class="demo-input-userName" style="margin-top: 10px;">
                            <el-row :gutter="2">
                                <el-col :span="10">Name 用户名:
                                </el-col>
                                <el-col :span="12">
                                    <el-input
                                            :disabled="true"
                                            style="margin-top: -6px;"
                                            type="textarea"
                                            autosize
                                            placeholder="Gust"
                                            v-model="textAreaUserName">
                                    </el-input>
                                </el-col>
                            </el-row>
                        </div>
                        <div class="demo-button-signInOut" style="margin-top: 10px;">
                            <el-button type="primary" style="margin: 5px" @click.prevent="openSignUp()">Sign up 注册</el-button>
                            <el-button type="primary" style="margin: 5px" @click.prevent="handleSignOut()">Sign Out 登出</el-button>
                            <el-button type="primary" style="margin: 5px" @click="dialogFormVisible = true">Sing In 登录/切换账户</el-button>
                            <!-- 登录的弹出界面 -->
                            <el-dialog title="登录" :visible.sync="dialogFormVisible">
                                <el-form :model="form">
                                    <el-form-item label="Name 用户名" :label-width="formLabelWidth">
                                        <el-input v-model="form.userName" placeholder="可选"
                                                  autocomplete="off"></el-input>
                                    </el-form-item>
                                    <el-form-item label="Email 邮箱" :label-width="formLabelWidth">
                                        <el-input v-model="form.email" placeholder="可选" autocomplete="off"></el-input>
                                    </el-form-item>
                                    <el-form-item label="Password 密码" :label-width="formLabelWidth">
                                        <el-input v-model="form.password" placeholder="必填"
                                                  autocomplete="off"></el-input>
                                    </el-form-item>
                                </el-form>
                                <div slot="footer" class="dialog-footer">
                                    <el-button type="primary" @click="dialogFormVisible = false">Cancel</el-button>
                                    <el-button type="primary" @click="dialogFormVisible = false"
                                               @click.prevent="handleSignIn()">OK
                                    </el-button>
                                    <el-button type="primary" @click="dialogForm2Visible = true">Forget your info? 忘记登录信息？
                                    </el-button>
                                </div>
                            </el-dialog>
                            <!-- 找回用户名密码等信息的弹出界面 -->
                            <el-dialog title="Regain your information" :visible.sync="dialogForm2Visible">
                                <el-form :model="form2">
                                    <el-form-item label="用户名" :label-width="formLabelWidth">
                                        <el-input v-model="form2.userName" placeholder="可选"
                                                  autocomplete="off"></el-input>
                                    </el-form-item>
                                    <el-form-item label="邮箱" :label-width="formLabelWidth">
                                        <el-input v-model="form2.email" placeholder="可选" autocomplete="off"></el-input>
                                    </el-form-item>
                                    <el-form-item label="lost&found code" :label-width="formLabelWidth">
                                        <el-input v-model="form2.lostFoundKey" placeholder="必填"
                                                  autocomplete="off"></el-input>
                                    </el-form-item>
                                </el-form>
                                <div slot="footer" class="dialog-footer">
                                    <el-button type="primary" @click="dialogForm2Visible = false">Cancel</el-button>
                                    <el-button type="primary" @click="dialogForm2Visible = false"
                                               @click.prevent="handleFindUserInfo()">找回登录信息
                                    </el-button>
                                </div>
                            </el-dialog>

                        </div>
                        <div class="demo-button-editProfile" style="margin-top: 10px;">
                            <el-button type="primary" style="margin: 5px" @click.prevent="beforeEditProfile()">Edit Password 修改密码</el-button>
                            <el-button type="primary" style="margin: 5px" @click.prevent="editAddress()">Edit Address 修改地址</el-button>
                            <!-- editProfile的弹出界面 -->
                            <el-dialog title="修改资料" :visible.sync="dialogForm3Visible">
                                <el-form :model="form3">
                                    <el-form-item label="当前密码" :label-width="formLabelWidth">
                                        <el-input v-model="form3.password" autocomplete="off"></el-input>
                                    </el-form-item>
                                    <el-form-item label="新密码" :label-width="formLabelWidth">
                                        <el-input v-model="form3.newPassword" autocomplete="off"></el-input>
                                    </el-form-item>
                                </el-form>
                                <div slot="footer" class="dialog-footer">
                                    <el-button type="primary" @click="dialogForm3Visible = false">Cancel</el-button>
                                    <el-button type="primary" @click="dialogForm3Visible = false"
                                               @click.prevent="handleEditProfile()">OK
                                    </el-button>
                                </div>
                            </el-dialog>
                        </div>
                    </el-col>
                    <!--右边-->
                    <el-col :span="14" class="grid">
                        <div class="demo-input-email" style="margin-top: 10px;">
                            <el-row :gutter="2">
                                <el-col :span="7">
                                    <div style="text-align: right">
                                        Email :
                                    </div>
                                </el-col>
                                <el-col :span="15">
                                    <el-input
                                            :disabled="true"
                                            style="margin-top: -6px;"
                                            type="textarea"
                                            autosize
                                            v-model="textAreaEmail">
                                    </el-input>
                                </el-col>
                            </el-row>
                        </div>

                        <div class="demo-input-phoneNum" style="margin-top: 10px;">
                            <el-row :gutter="2">
                                <el-col :span="7">
                                    <div style="text-align: right">
                                        Phone Num :
                                    </div>
                                </el-col>
                                <el-col :span="15">
                                    <el-input
                                            :disabled="true"
                                            style="margin-top: -6px;"
                                            type="textarea"
                                            autosize
                                            v-model="textAreaPhoneNum">
                                    </el-input>
                                </el-col>
                            </el-row>
                        </div>

                        <div class="demo-input-joinTime" style="margin-top: 10px;">
                            <el-row :gutter="2">
                                <el-col :span="7">
                                    <div style="text-align: right">
                                        Join Time :
                                    </div>
                                </el-col>
                                <el-col :span="15">
                                    <el-input
                                            :disabled="true"
                                            style="margin-top: -6px;"
                                            type="textarea"
                                            autosize
                                            v-model="textAreaJoinTime">
                                    </el-input>
                                </el-col>
                            </el-row>
                        </div>

<!--                        <div class="demo-input-state" style=" margin-top: 10px;" :style="[this.seeState?'show':'hidden']">-->
                        <div class="demo-input-state" style=" margin-top: 10px;" v-if="seeState">
                            <el-row :gutter="2">
                                <el-col :span="7">
                                    <div style="text-align: right">
                                        State :
                                    </div>
                                </el-col>
                                <el-col :span="15">
                                    <el-input
                                            :disabled="true"
                                            style="margin-top: -6px;"
                                            type="textarea"
                                            autosize
                                            v-model="textAreaState">
                                    </el-input>
                                </el-col>
                            </el-row>
                        </div>

                        <div class="demo-input-lostFoundKey" style="margin-top: 10px;">
                            <el-row :gutter="2">
                                <el-col :span="7">
                                    <div style="text-align: right">
                                        Lost Found Key :
                                    </div>
                                </el-col>
                                <el-col :span="15">
                                    <el-input
                                            :disabled="true"
                                            style="margin-top: -6px;"
                                            type="textarea"
                                            autosize
                                            v-model="textAreaLostFoundKey">
                                    </el-input>
                                </el-col>
                            </el-row>
                        </div>

                        <div class="demo-input-uploadCount" style="margin-top: 10px;">
                            <el-row :gutter="2">
                                <el-col :span="7">
                                    <div style="text-align: right">
                                        Address1 地址 1 :
                                    </div>
                                </el-col>
                                <el-col :span="15">
                                    <el-input
                                            :disabled="true"
                                            style="margin-top: -6px;"
                                            type="textarea"
                                            autosize
                                            v-model="textAreaUploadCount">
                                    </el-input>
                                </el-col>
                            </el-row>
                        </div>

                        <div class="demo-input-downloadCount" style="margin-top: 10px;">
                            <el-row :gutter="2">
                                <el-col :span="7">
                                    <div style="text-align: right">
                                        Address2 地址 2 :
                                    </div>
                                </el-col>
                                <el-col :span="15">
                                    <el-input
                                            :disabled="true"
                                            style="margin-top: -6px;"
                                            type="textarea"
                                            autosize
                                            v-model="textAreaDownloadCount">
                                    </el-input>
                                </el-col>
                            </el-row>
                        </div>

                        <div class="demo-input-divideCount" style="margin-top: 10px;">
                            <el-row :gutter="2">
                                <el-col :span="7">
                                    <div style="text-align: right">
                                        Address3 地址 3 :
                                    </div>
                                </el-col>
                                <el-col :span="15">
                                    <el-input
                                            :disabled="true"
                                            style="margin-top: -6px;"
                                            type="textarea"
                                            autosize
                                            v-model="textAreaDivideCount">
                                    </el-input>
                                </el-col>
                            </el-row>
                        </div>
                    </el-col>
                </div>
            </el-col>
            <el-col :span="5">
                <div class="grid-content-userInfo bg-purple-dark-userInfo"></div>
            </el-col>
        </el-row>

        <!--        提示信息行-->
        <el-row>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
            <el-col :span="14">
                <div class="grid-content-goods bg-purple-dark-goods">
                    <h3 style="margin: 3px">
                        注意！本网站仅用作商品展示和下单，不涉及任何金钱付款操作！你将绝不会在本网站上付款！
                    </h3>
                    <h5>
                        选购好商品后，联系店主(phone: 7712345678微信:vx12345)进行单独付款(付款后别忘了去"处理-待付款"处点击确认)。<br/>
                    </h5>
                    <h4 style="margin: 2px">
                        Hints: how to use this web portal? 如何使用本网站?<br/>
                    </h4>
                    <h4>
                        1 注册登录:首先你需要注册一个账号，填写名称，电话，密码，派送地址等信息，然后登录~~<br/>
                        2 浏览商品: 搜索浏览你所需要的商品，并将它们添加进购物车~~<br/>
                        3 下订单: 在购物车界面check out,你会在这里选择取货方式和时间地址等，这将形成一个预订单~~<br/>
                        4 等待确认: 你需要等待商家检查并确认你的订单，你可以在处理界面看到进度~~<br/>
                        5 确认付款: 当你的订单被商家确认接受,你会在待付款栏看到它(你也可以在这里取消它),请在付款后点击已付款告知商家~~<br/>
                        6 完成交易: 商家确认你已付款后，会将商品送给你~~<br/>
                        7 查看历史: 你可以在订单界面查看你的历史订单，如果你有预订单被商家/自己取消，也可以在这里看到~~<br/>
                    </h4>

                    <h3 style="margin: 2px" v-if="seeState">
                        Hints: 管理员如何使用本网站?(这段话仅管理员可见)<br/>
                    </h3>
                    <h4 v-if="seeState">
                        1 注册登录:首先你需要注册一个账号，填写名称，电话，密码，派送地址等信息，然后登录~~<br/>
                        --plus:注册的电话号码请使用纯数字，不要带字母--<br/>
                        --plus:同一台电脑（手机,ipad也看作是电脑）在三小时内只能注册一次--<br/>
                        --plus:同样的名称/邮箱/电话只能注册一次--<br/>
                        --plus:管理员可以看到用户状态，A为正常--<br/>
                        2 浏览商品: 搜索浏览你所需要的商品，并将它们添加进购物车~~<br/>
                        --plus:点击某个商品，管理员可修改商品价格，库存和细节描述--<br/>
                        --plus:点击某个商品，管理员可删除该商品--<br/>
                        --plus:商品库存为0时，用户无法将该商品加入购物车！--<br/>
                        3 下订单: 在购物车界面check out,你会在这里选择取货方式和时间地址等，这将形成一个预订单~~<br/>
                        4 等待确认: 你需要等待商家检查并确认你的账单，你可以在处理界面看到进度~~<br/>
                        --plus:“处理界--待审核”处可以处理预订单，这个待审核仅管理员可见--<br/>
                        --plus:管理员在“处理界--待审核”处可看到所有预订单，确认是否有货，是否能在被要求的时间内送货--<br/>
                        --plus:管理员可以在预订单中添加包装运输费，写评论注明原因，这些额外费用和备注会被用户看到--<br/>
                        --plus:管理员觉得用户买的某个商品数量太多，需要减少一些，可在预订单中注明原因并减少数量--<br/>
                        --plus:管理员因为时间，派送能力，库存等原因不满足，可以注明原因并取消预订单--<br/>
                        <br/>
                        --plus:“处理界”界面左下方有个按钮，，仅管理员可用，可以添加新商品--<br/>
                        --plus:添加新商品必须填写商品名称，价格，描述--<br/>
                        --plus:添加新商品可以指定商品分类，共1-12类，不指定默认为6(美食/生鲜)--<br/>
                        --plus:添加新商品可以指定商品库存数量，不指定默认为100--<br/>
                        --plus:添加新商品可以指定商品展示图，最多五张，不指定会使用默认图片--<br/>
                        5 确认付款: 当你的账单被商家确认接受,你会在待付款栏看到它，请在付款后点击已付款告知商家~~<br/>
                        6 完成交易: 商家确认你已付款后，会将商品送给你~~<br/>
                        --plus:管理员可刷新“处理”界面，查看有没有用户下了单需要你接收--<br/>
                        --plus:管理员可刷新“订单”界面，查看有没有用户已经确认付款需要你送货或准备--<br/>
                        7 查看历史: 你可以在订单界面查看你的历史订单，如果你有预订单被商家拒绝，也可以在这里看到~~<br/>
                        --plus:管理员可看到所有的人历史账单，包括成交的，被取消的等--<br/>
                    </h4>

                    <h3 style="margin: 2px">
                        Hints: 网站似乎出问题了？请联系 jiantang@163.com<br/>
                    </h3>

                </div>
            </el-col>
            <el-col :span="5">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
        </el-row>

        <!-- 弹出sign up 界面-->
        <el-dialog title="Sign Up/注册" :visible.sync="signUpAble">
            <el-row>
                <el-form :model="formSignUp">
                    <el-form-item label="Email/邮箱:" :label-width="formLabelWidth">
                        <el-input  v-model="formSignUp.email" placeholder="not null/必填"
                                  autocomplete="off" ></el-input>
                    </el-form-item>
                    <el-form-item  label="name/用户名:" :label-width="formLabelWidth">
                        <el-input  v-model="formSignUp.userName" placeholder="not null/必填"
                                  autocomplete="off" ></el-input>
                    </el-form-item>
                    <el-form-item label="phone number/电话" :label-width="formLabelWidth">
                        <el-input  v-model="formSignUp.phoneNumber" placeholder="not null/必填"
                                  autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="passWord/密码:" :label-width="formLabelWidth">
                        <el-input  v-model="formSignUp.passWord" placeholder="not null/必填"
                                  autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="address1 line 1/地址1 第1行" :label-width="formLabelWidth">
                        <el-input  v-model="formSignUp.address1Line1" placeholder="at least one address info please/三个地址请最少填写一个"
                                   autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="address1 line 2/地址1 第2行" :label-width="formLabelWidth">
                        <el-input  v-model="formSignUp.address1Line2" placeholder="at least one address info please/三个地址请最少填写一个"
                                   autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="address1 postcode/地址1 邮编" :label-width="formLabelWidth">
                        <el-input  v-model="formSignUp.address1PostCode" placeholder="at least one address info please/三个地址请最少填写一个"
                                   autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="address2 line 1/地址2 第1行" :label-width="formLabelWidth">
                        <el-input  v-model="formSignUp.address2Line1" placeholder="not necessary/选填"
                                   autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="address2 line 2/地址2 第2行" :label-width="formLabelWidth">
                        <el-input  v-model="formSignUp.address2Line2" placeholder="not necessary/选填"
                                   autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="address2 postcode/地址2 邮编" :label-width="formLabelWidth">
                        <el-input  v-model="formSignUp.address2PostCode" placeholder="not necessary/选填"
                                   autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="address3 line 1/地址3 第1行" :label-width="formLabelWidth">
                        <el-input  v-model="formSignUp.address3Line1" placeholder="not necessary/选填"
                                   autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="address3 line 2/地址3 第2行" :label-width="formLabelWidth">
                        <el-input  v-model="formSignUp.address3Line2" placeholder="not necessary/选填"
                                   autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="address3 postcode/地址3 邮编" :label-width="formLabelWidth">
                        <el-input  v-model="formSignUp.address3PostCode" placeholder="not necessary/选填"
                                   autocomplete="off"></el-input>
                    </el-form-item>
                </el-form>
            </el-row>
            <el-row>
                <el-button
                        type="primary"
                        style="float:left; font-size:16px; margin-left: 145px"
                        @click.prevent="signUp()">Sign Up 注册
                </el-button>
            </el-row>
            <el-row>
                <h4>Hints/一些提示<br/>
                    1:同样的邮箱/电话/用户名只能注册1次<br/>
                    2:密码没有最短长度/大小写/特殊字符限制，但推荐写简单一点<br/>
                    3:不需要邮箱/短信验证码，但有IP检测，请不要恶意刷号注册<br/>
                    4:地址最少要填一个，最多可以填三个，你可以帮朋友购物~<br/>
                </h4>
            </el-row>
        </el-dialog>

        <br>
    </div>
</template>
<style>
    /*搜索行，样式全局影响，要特殊命名*/
    .bg-purple-dark-userInfo {
        background: Transparent;
    }

    .grid-content-userInfo {
        border-radius: 0px;
        min-height: 30px;
    }
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
        created() {
            this.getIp();
            this.initUserInfo();
            this.adminView();
        },
        data() {
            return {
                textAreaUserName: '',
                textAreaEmail: '',
                textAreaPhoneNum: '',
                textAreaJoinTime: '',
                textAreaState: '',
                textAreaLostFoundKey: '',
                textAreaUploadCount: '',
                textAreaDownloadCount: '',
                textAreaDivideCount: '',
                demoUserImage: '',
                form3: {
                    password: null,
                    newPassword: null
                },
                form2: {
                    userName: null,
                    email: null,
                    lostFoundKey: null
                },
                form: {
                    userName: null,
                    email: null,
                    password: null
                },
                dialogFormVisible: false,
                dialogForm2Visible: false,
                dialogForm3Visible: false,
                formLabelWidth: '220px',

                signUpAble:false,
                formSignUp: {
                    email: null,
                    userName: null,
                    phoneNumber: null,
                    passWord: null,
                    address1Line1: null,
                    address1Line2: null,
                    address1PostCode: null,
                    address2Line1: null,
                    address2Line2: null,
                    address2PostCode: null,
                    address3Line1: null,
                    address3Line2: null,
                    address3PostCode: null
                },
                yourIp:null,

                //adminView 选择是否需要隐藏（对非 admin隐藏）
                seeState : false,
            };
        },
        methods: {

            //每个界面都有这个，即判断当前登录用户是否是管理员，是的话展示的可操作内容会多i一些
            adminView() {
                if (localStorage.getItem('loginResult') === null) {
                    this.seeState = false;
                }
                else {
                    let currentUserId = JSON.parse(localStorage.getItem('loginResult')).userId;
                    if(currentUserId <= 100 && currentUserId != 2) {
                        this.seeState = true;
                    }
                    else {
                        this.seeState = false;
                    }
                }
            },

            getIp() {
                fetch('https://api.ipify.org?format=json').then(x => x.json())
                    .then(({ ip }) => {
                        this.yourIp = ip;
                        // this.$message(this.yourIp);
                    });
            },

            //时间转换函数，后台那串数字解析成可视的时间格式
            getLocalTime(jsondate) {
                jsondate = "" + jsondate + "";//因为jsonDate是number型的indexOf会报错
                if (jsondate.indexOf("+") > 0) {
                    jsondate = jsondate.substring(0, jsondate.indexOf("+"));
                } else if (jsondate.indexOf("-") > 0) {
                    jsondate = jsondate.substring(0, jsondate.indexOf("-"));
                }
                var date = new Date(parseInt(jsondate, 10));
                var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
                var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
                var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
                return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
            },

            // 界面进来就会根据localStorage存储的当前用户信息，初始化用户详情
            initUserInfo() {
                if (localStorage.getItem('loginResult') === null) {
                    this.initGustLocalStorage();
                } else {
                    this.textAreaUserName = JSON.parse(localStorage.getItem('loginResult')).userName;
                    this.textAreaEmail = JSON.parse(localStorage.getItem('loginResult')).email;
                    this.textAreaPhoneNum = JSON.parse(localStorage.getItem('loginResult')).phoneNumber;
                    this.textAreaJoinTime = this.getLocalTime(JSON.parse(localStorage.getItem('loginResult')).createDate);
                    this.textAreaState = JSON.parse(localStorage.getItem('loginResult')).state;
                    this.textAreaLostFoundKey = JSON.parse(localStorage.getItem('loginResult')).lostFoundKey;
                    this.demoUserImage = JSON.parse(localStorage.getItem('loginResult')).userDetail;
                    this.setAddress();
                }
            },

            setAddress() {
                this.textAreaUploadCount = JSON.parse(localStorage.getItem('loginResult')).address1Line1 + "," +
                    JSON.parse(localStorage.getItem('loginResult')).address1Line2 + "," +
                    JSON.parse(localStorage.getItem('loginResult')).address1PostCode;
                this.textAreaDownloadCount = JSON.parse(localStorage.getItem('loginResult')).address2Line1 + "," +
                    JSON.parse(localStorage.getItem('loginResult')).address2Line2 + "," +
                    JSON.parse(localStorage.getItem('loginResult')).address2PostCode;
                this.textAreaDivideCount = JSON.parse(localStorage.getItem('loginResult')).address3Line1 + "," +
                    JSON.parse(localStorage.getItem('loginResult')).address3Line2 + "," +
                    JSON.parse(localStorage.getItem('loginResult')).address3PostCode;
            },

            //
            // 再请求服务器，得到gust用户的信息
            // 清空localStorage.loginResult；
            // 写入localStorage.loginResult;
            // 再初始化界面的组建为gust的信息
            initGustLocalStorage() {
                this.$http.put("mainView/getUserInfo", {
                    userId: 2 // 1是管理员admin,2是gust
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        localStorage.removeItem('loginResult');
                        localStorage.setItem('loginResult', JSON.stringify(result.data))
                        this.textAreaUserName = JSON.parse(localStorage.getItem('loginResult')).userName;
                        this.textAreaEmail = JSON.parse(localStorage.getItem('loginResult')).email;
                        this.textAreaPhoneNum = JSON.parse(localStorage.getItem('loginResult')).phoneNumber;
                        this.textAreaJoinTime = this.getLocalTime(JSON.parse(localStorage.getItem('loginResult')).createDate);
                        this.textAreaState = JSON.parse(localStorage.getItem('loginResult')).state;
                        this.textAreaLostFoundKey = JSON.parse(localStorage.getItem('loginResult')).lostFoundKey;
                        this.demoUserImage = JSON.parse(localStorage.getItem('loginResult')).userDetail;
                        this.setAddress();
                    } else {
                        this.$message('服务器异常');
                    }
                });
            },

            openSignUp() {
                this.signUpAble = true;
            },
            signUp() {
                //检测输入是否合适
                if (!(this.formSignUp.email) || !(this.formSignUp.userName) || !(this.formSignUp.phoneNumber)
                    || !(this.formSignUp.passWord) || !(this.formSignUp.address1Line1)
                    || !(this.formSignUp.address1Line2) || !(this.formSignUp.address1PostCode)) {
                    this.$message('sth must not be empty!/有些内容不能为空！！');
                    return;
                }
                //后台注册
                this.$http.put("mainView/signUp", {
                    ip: this.yourIp,
                    email: this.formSignUp.email,
                    userName: this.formSignUp.userName,
                    phoneNumber: this.formSignUp.phoneNumber,
                    passWord: this.formSignUp.passWord,
                    address1Line1: this.formSignUp.address1Line1,
                    address1Line2: this.formSignUp.address1Line2,
                    address1PostCode: this.formSignUp.address1PostCode,
                    address2Line1: this.formSignUp.address2Line1,
                    address2Line2: this.formSignUp.address2Line2,
                    address2PostCode: this.formSignUp.address2PostCode,
                    address3Line1: this.formSignUp.address3Line1,
                    address3Line2: this.formSignUp.address3Line2,
                    address3PostCode: this.formSignUp.address3PostCode
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        let ret = result.data;
                        let retInfo = ret.retInfo;
                        if(ret.successOrNot == "Y") {
                            this.$message(retInfo);
                            this.form.email = this.formSignUp.email;
                            this.form.password = this.formSignUp.passWord;
                            this.handleSignIn();
                        }
                        else {
                            this.$message(retInfo);
                        }

                    } else {
                        this.$message('失败！');
                    }
                });


            },

            beforeEditProfile() {
                if (2 == JSON.parse(localStorage.getItem('loginResult')).userId) {
                    this.$message('游客身份，尚未登录!!');
                    // alert("");
                    return;
                } else {
                    this.dialogForm3Visible = true;
                }
            },

            editAddress() {
                this.$message("尚不支持！");
            },

            // handleEditProfile
            handleEditProfile() {
                if (!(this.form3.password) || !(this.form3.newPassword)) {
                    this.$message('当前密码或新密码不能为空!');
                    return;
                }
                if (this.form3.password != JSON.parse(localStorage.getItem('loginResult')).passWord) {
                    this.$message('当前密码输入不对!');
                    return;
                }
                this.$http.put("mainView/handleEditProfile", {
                    userId: JSON.parse(localStorage.getItem('loginResult')).userId,
                    newPassWord: this.form3.newPassword
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        this.$message('你的密码修改成功，请重新登录！');
                        this.initGustLocalStorage();
                    } else {
                        this.$message('服务器异常');
                    }
                });
            },
            // 检索界面输入，不合法时返回
            // 合法时请求服务器，返回passWord在界面显式
            handleFindUserInfo() {
                if (!(this.form2.userName) && !(this.form2.email)) {
                    this.$message('用户名和邮箱不能同时为空！');
                    // alert("用户名和邮箱不能同时为空！");
                    return;
                }
                if (!(this.form2.lostFoundKey)) {
                    this.$message('lostFoundKey 不能为空!');
                    // alert("lostFoundKey 不能为空!");
                    return;
                }
                this.$http.put("mainView/handleFindUserInfo", {
                    userName: this.form2.userName,
                    email: this.form2.email,
                    lostFoundKey: this.form2.lostFoundKey
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        alert("your password is " + result.data.passWord);
                    } else if (result.code === 499) {
                        alert(result.data.handleFindUserInfoErrRet);
                    } else {
                        this.$message('服务器异常');
                    }
                });
            },
            // 不管当前是什么用户，都可以登陆新用户；
            // 当发现新用户就是本用户时，提示无需登陆
            // 当登录gust用户时，提示登出即可
            handleSignIn() {
                if (!(this.form.userName) && !(this.form.email)) {
                    alert("用户名和邮箱不能同时为空！");
                    return;
                }
                if (!(this.form.password)) {
                    alert("password 不能为空!");
                    return;
                }
                if (this.form.userName == 'gust') {
                    alert("gust用户无需登录!");
                    return;
                }
                this.$http.put("mainView/getUserInfo", {
                    userName: this.form.userName,
                    email: this.form.email,
                    passWord: this.form.password
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        localStorage.removeItem('loginResult');
                        localStorage.setItem('loginResult', JSON.stringify(result.data))
                        this.textAreaUserName = JSON.parse(localStorage.getItem('loginResult')).userName;
                        this.textAreaEmail = JSON.parse(localStorage.getItem('loginResult')).email;
                        this.textAreaPhoneNum = JSON.parse(localStorage.getItem('loginResult')).phoneNumber;
                        this.textAreaJoinTime = this.getLocalTime(JSON.parse(localStorage.getItem('loginResult')).createDate);
                        this.textAreaState = JSON.parse(localStorage.getItem('loginResult')).state;
                        this.textAreaLostFoundKey = JSON.parse(localStorage.getItem('loginResult')).lostFoundKey;
                        this.demoUserImage = JSON.parse(localStorage.getItem('loginResult')).userDetail;
                        this.setAddress();
                        //刷新当前页面
                        window.location.reload();
                        alert("登入成功");
                    } else if (result.code === 499) {
                        alert(result.data.handleSignInErrRet);
                    } else {
                        this.$message('服务器异常');
                    }
                });
            },
            // 登出操作.先判断localStorage，如果为gust,提示无需登出;否则清空localStorage,然后将gust信息导入.
            handleSignOut() {
                if (JSON.parse(localStorage.getItem('loginResult')).userId == 2) {
                    alert("gust用户无需登出");
                }
                else {
                    this.$http.put("mainView/getUserInfo", {
                        userId: 2 // 1是管理员admin,2是gust
                    }).then(result => {
                        var result = result.body;
                        if (result.code === 200) {
                            localStorage.removeItem('loginResult');
                            localStorage.setItem('loginResult', JSON.stringify(result.data))
                            this.textAreaUserName = JSON.parse(localStorage.getItem('loginResult')).userName;
                            this.textAreaEmail = JSON.parse(localStorage.getItem('loginResult')).email;
                            this.textAreaPhoneNum = JSON.parse(localStorage.getItem('loginResult')).phoneNumber;
                            this.textAreaJoinTime = this.getLocalTime(JSON.parse(localStorage.getItem('loginResult')).createDate);
                            this.textAreaState = JSON.parse(localStorage.getItem('loginResult')).state;
                            this.textAreaLostFoundKey = JSON.parse(localStorage.getItem('loginResult')).lostFoundKey;
                            this.demoUserImage = JSON.parse(localStorage.getItem('loginResult')).userDetail;
                            this.setAddress();
                            //刷新当前页面
                            window.location.reload();
                            alert("登出成功，现在是gust身份");
                        } else {
                            this.$message('服务器异常');
                        }
                    });
                }
            }
        }
    };
</script>