<template>
    <div id="app">

        <el-row>
            <!--一个占位为5的空的content，自定义其背景色样式-->
            <el-col :span="5">
                <div class="grid-content bg-purple-dark"></div>
            </el-col>
            <el-col :span="14">
                <el-menu class="el-menu-demo" mode="horizontal" background-color="#1E89E0" text-color="#fff"
                         active-text-color="#ffd04b">
                    <el-menu-item index="1">
                        <router-link to="/userInfo">
                            <el-button type="text">
                                <img :src="'data:image/jpeg;base64,'+ demoUserImage" style="width: 42px;height: 42px">
                            </el-button>
                        </router-link>
                    </el-menu-item>
                    <el-menu-item index="2">
                        <router-link to="/goods">Goods 首页</router-link>
                    </el-menu-item>
                    <el-menu-item index="3">
                        <router-link to="/message">Processing 处理</router-link>
                    </el-menu-item>
                    <el-menu-item index="4">
                        <router-link to="/cart">Cart 购物车</router-link>
                    </el-menu-item>
                    <el-menu-item index="5">
                        <router-link to="/order">Order 订单</router-link>
                    </el-menu-item>
<!--                    <el-menu-item index="5">-->
<!--                        <router-link to="/cooperation">合作加盟</router-link>-->
<!--                    </el-menu-item>-->
                </el-menu>
            </el-col>
            <el-col :span="2">
                <el-menu class="el-menu-demo" mode="horizontal" background-color="#1E89E0" text-color="#fff"
                         active-text-color="#ffd04b">
<!--                    <div style="visibility:hidden">-->
                        <el-menu-item index="7" >
                            <router-link to="/userInfo">登录/注册</router-link>
                        </el-menu-item>
<!--                    </div>-->
                </el-menu>
            </el-col>
            <el-col :span="3">
                <div class="grid-content bg-purple-dark"></div>
            </el-col>
        </el-row>


        <br>
        <router-view></router-view>

    </div>
</template>

<style>
    a {
        /*去掉导航栏链接文字的下划线*/
        text-decoration: none;
        text-align: center;
    }

    /*样式的使用方式 .+标签 指到对应的标签*/
    .el-menu {
        min-height: 60px;
        max-height: 60px;
    }

    .el-menu-item {
        min-height: 60px;
        max-height: 60px;
        /* 修改默认字体的大小 */
        font-size: 16px;
    }

    /* 两边填充位置的底色和el-menu-item el-menu保持一致 */
    .bg-purple-dark {
        background: #1E89E0;
    }

    .grid-content {
        /* 圆角边框*/
        border-radius: 0px;
        /* 长度和el-menu-item el-menu在缩放的情况下保持一致*/
        min-height: 60px;
    }
</style>

<script>
    export default {
        created() {
            this.init();
        },
        data() {
            return {
                router7Visible: false,
            };
        },
        methods: {
            // root init function
            init() {
                if (localStorage.getItem('loginResult') === null) {
                    this.initUserInfo();
                } else {
                    this.demoUserImage = JSON.parse(localStorage.getItem('loginResult')).userDetail;
                }
            },

            // init user info
            // 先清空localStorage.loginResult，再请求服务器，把gust用户的信息写入localStorage.loginResult
            initUserInfo() {
                localStorage.removeItem('loginResult');
                this.$http.put("mainView/getUserInfo", {
                    userId: 2 // 1是管理员admin,2是gust
                }).then(result => {
                    let ret = result.body;
                    if (ret.code === 200) {
                        localStorage.setItem('loginResult', JSON.stringify(ret.data));
                        this.demoUserImage = JSON.parse(localStorage.getItem('loginResult')).userDetail;
                    } else {
                        this.$message('服务器异常');
                    }
                });
            }
        }
    };
</script>