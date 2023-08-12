<template>
    <div>
        <div>欢迎进入后台管理平台</div>
        <!-- 退出登录 -->
        <div>
            <el-button type="danger" plain @click="logout">
                退出登录
            </el-button>
        </div>
        <!-- 退出登录 -->
        <div>
            <el-button type="success" plain @click="test">
                测试按钮
            </el-button>
        </div>
    </div>
</template>

<script>
export default {
    components: {},
    props: {},
    data() {
        return {
            token: '',
        };
    },
    // 渲染试图前
    created() {
        this.token = window.localStorage.getItem('token');   //获取token
    },
    // 渲染视图后
    mounted() {

    },
    methods: {
        /**
         * 退出登录
         * 1、删除本地token
         * 2、跳转到登录界面
         */
        logout() {
            //删除本地token
            localStorage.removeItem('token');
            //删除vuex存储的userInfo信息
            this.$store.state.userInfo = undefined;
            // console.log(window.localStorage.getItem('token'));
            //跳转到登录界面
            this.$router.push('/');
        },
        test() {
            console.log("=====测试按钮=====");
            this.$axios.get('/login/verifyToken', {
                headers: { token: this.token }
            }).then(value => {
                if (value.data.status === '1') {
                    this.reloginDialog();  //弹出提示框
                }
            }).catch(() => {
                this.$message({
                    message: "登录过期请重新登录",
                    type: "error"
                });
            });
        },
        /**
         * 重新登录提示框
         */
        reloginDialog() {
            this.$confirm('登录已过期，请重新登录', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //跳转到登录界面
                this.$router.push('/');
            }).catch(() => { });
        },
    }
};
</script>

<style lang="scss" scoped></style>