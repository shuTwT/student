<template>
    <div class="layout">
        <el-row>
            <el-col :span="24" :lg="8">
                <el-card>
                    <div class="header">
                        <el-button type="primary">新增</el-button>
                        <el-button type="info">刷新</el-button>
                    </div>
                    <el-scrollbar height="500">
                        <p v-for="item in 20" :key="item" class="scrollbar-demo-item">{{ item }}</p>
                    </el-scrollbar>
                </el-card>
            </el-col>
            <el-col :span="24" :lg="16">
                <el-card>
                    <div class="header">
                        <el-button type="primary">新增</el-button>
                        <el-button type="default">刷新</el-button>
                    </div>
                    <el-table stripe :border="true"></el-table>
                    <el-pagination layout="prev,pager,next" :total="10"></el-pagination>
                </el-card></el-col>
        </el-row>
    </div>
</template>

<script setup lang="ts">

</script>

<style scoped>
.layout {
    background: #303030;
    border: 1px solid #e4e4e4;
    border-radius: 4px;
    width: 100%;
    min-height: 100%;
    box-sizing: border-box;
}


:deep(.el-card) {
    height: 600px;
    margin: 5px;
}

:deep(.el-card .header) {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin-bottom: 10px;
}
</style>