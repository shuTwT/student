<template>
    <div class="layout">
        <el-row style="padding:10px">
            <el-input style="width: 200px;margin-right: 20px;" placeholder="请输入搜索的内容" v-model="formData.input"></el-input>
            <el-button type="primary">搜索</el-button>
            <el-button v-permission="'admin'" type="info">添加用户</el-button>
            <el-button  v-permission="'admin'" type="danger">批量删除</el-button>
            <el-button v-permission="'admin'" type="success">批量导入</el-button>
        </el-row>
        <el-row style="padding:10px">
            <el-table :data="majorList" style="height:700px">
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column prop="majorId" label="专业ID"></el-table-column>
                <el-table-column prop="majorName" label="专业名"></el-table-column>
                <el-table-column prop="lastUpdate" label="更新时间"></el-table-column>
            </el-table>
        </el-row>
        <el-pagination style="align-self: center;" layout="prev,pager,next" :total="100"></el-pagination>
    </div>
</template>

<script setup lang="ts">
import { getMajorList } from "@/api/student/major"
const formData = ref({
    input: ""
})
const majorList = ref([])
onBeforeMount(() => {
    getMajorList({
        limit: 10,
        page: 1
    }).then(({ data }: any) => {
        majorList.value = data
        console.log(data);

    })
})

</script>

<style scoped>
.layout {
    background: #303030;
    border: 1px solid #e4e4e4;
    border-radius: 4px;
    width: 100%;
    min-height: 100%;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
}
</style>