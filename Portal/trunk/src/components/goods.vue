<template>
    <div>
        <!--模板-->
        <el-row>
            <el-col :span="3">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
            <el-col :span="18">
                <div class="grid-content-goods bg-purple-dark-goods">
                </div>
            </el-col>
            <el-col :span="3">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
        </el-row>

        <el-row>
            <el-col :span="3">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
            <el-col :span="18">
                <div class="grid-content-goods bg-purple-dark-goods">
                    hello
                </div>
            </el-col>
            <el-col :span="3">
                <div class="grid-content-goods bg-purple-dark-goods"></div>
            </el-col>
        </el-row>
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
    .coverImg {
        object-fit: contain;
    }
</style>
<script>
    export default {
        created() {
        },
        data() {
            return {};
        },
        methods: {}
    };
</script>