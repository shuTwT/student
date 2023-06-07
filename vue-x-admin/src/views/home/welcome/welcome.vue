<script setup lang="ts">
import { getClazzList } from "@/api/student/clazz"
const canlendarValue = ref(new Date())
onMounted(() => {
    getClazzList().then(res => {
        console.log(res);

    })
})
</script>

<template>
    <div class="layout">
        <el-row>
            <el-col :span="24" :lg="14">
                <el-card>
                    <template #header>我的工作台</template>
                    <div>早上好，Admin</div>
                </el-card>
            </el-col>
            <el-col :span="24" :lg="10">
                <el-card>
                    <el-calendar v-model="canlendarValue" />
                </el-card>
            </el-col>
            <el-col :span="24" :lg="8">
                <el-card>
                    <template #header>
                        系统日志
                    </template>
                </el-card>

            </el-col>
            <el-col :span="24" :lg="8">
                <el-card>
                    <template #header>
                        公告
                    </template>
                </el-card>

            </el-col>
        </el-row>
    </div>
</template>

<style scoped>
.layout {
    background: #303030;
    border: 1px solid #e4e4e4;
    border-radius: 4px;
    width: 100%;
    min-height: 100%;
    box-sizing: border-box;
}

:deep(.el-calendar-table .el-calendar-day) {
    height: 40px;
}

:deep(.el-card) {
    height: 400px;
    margin: 5px;
}
</style>