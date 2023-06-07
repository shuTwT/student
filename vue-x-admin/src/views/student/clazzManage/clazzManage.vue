<template>
    <div class="layout">
        <el-row style="padding:10px">
            <el-input style="width: 200px;margin-right: 20px;" placeholder="请输入搜索的内容" v-model="formData.input"></el-input>
            <el-button type="primary">搜索</el-button>
            <el-button v-permission="'admin'" type="info">添加用户</el-button>
            <el-button v-permission="'admin'" type="danger">批量删除</el-button>
            <el-button v-permission="'admin'" type="success">批量导入</el-button>
        </el-row>
        <el-row style="padding:10px">
            <el-table :data="clazzList" style="height:700px">
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column prop="clazzId" label="班级ID"></el-table-column>
                <el-table-column prop="clazzName" label="班级名"></el-table-column>
                <el-table-column prop="majorName" label="专业名"></el-table-column>
                <el-table-column prop="teacherName" label="授课教师"></el-table-column>
            </el-table>
        </el-row>
        <el-pagination style="align-self: center;" layout="prev,pager,next" :total="100"></el-pagination>
    </div>
</template>

<script setup lang="ts">
import { getClazzList } from "@/api/student/clazz"
const formData = ref({
    input: ""
})
const clazzList = ref([])
onBeforeMount(() => {
    getClazzList().then(({ data }: any) => {
        clazzList.value = data
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