<template>
    <div style="width:100vw; height: 100vh; overflow: hidden; display: flex;">
        <!-- 加入图片 -->
        <div style="flex:1;">
            <img src="@/assets/login.jpg" style="width: 100%; height: 100%; " fit="cover" />
        </div>
        <div style="position: absolute; bottom: 400px; left: 300px;">
        </div>
        <!-- 登录注册组件 -->
        <div style=" width: 400px; background-color: white; text-align: center;">
            <router-view />
        </div>
    </div>
</template>

<script>

export default {
    data() {
        return {

        };
    },
    // 监听
    watch: {},
    // 页面结构加载完成后
    mounted() { },
    // 方法
    methods: {},
};
</script>

<style lang="scss" ></style>