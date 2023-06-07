<script setup lang="ts">
import { zhCn } from 'element-plus/lib/locale/index.js';

</script>

<template>
    <el-config-provider :locale="zhCn">
        <router-view></router-view>
    </el-config-provider>
</template>

<style scoped></style>
