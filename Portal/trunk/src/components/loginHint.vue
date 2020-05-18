<template>
    <div>
        <el-col :span="10">
        <div class="grid-content-goods bg-purple-dark-goods"></div>
    </el-col>
        <el-col :span="4">
            <div class="grid-content-goods bg-purple-dark-goods">
                <el-row>
                    <div style="text-align: center">
                        <img src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                             style="width: 100px;height: 100px"
                             class="image">
                    </div>
                </el-row>
                <el-row>
                    <div style="text-align: center; padding: 14px;">
                        <router-link to="/userInfo">Click here and login first please! 请先点击此处登录登录！</router-link>
                    </div>
                </el-row>
            </div>
        </el-col>
        <el-col :span="10">
            <div class="grid-content-goods bg-purple-dark-goods"></div>
        </el-col>
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
            return {};
        },
        created() {
        },
        methods: {}
    };
</script>
