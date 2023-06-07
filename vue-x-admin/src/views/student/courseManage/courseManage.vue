<template>
    <div class="layout">
        <el-row style="padding:10px">
            <el-input style="width: 200px;margin-right: 20px;" placeholder="请输入搜索的内容" v-model="formData.input"></el-input>
            <el-button type="primary">搜索</el-button>
            <el-button  v-permission="'admin'" type="info">添加用户</el-button>
            <el-button v-permission="'admin'" type="danger">批量删除</el-button>
            <el-button v-permission="'admin'" type="success">批量导入</el-button>
        </el-row>
        <el-row style="padding:10px">
            <el-table :data="courseList" style="height:700px;width: 100%;">
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column prop="courseId" label="课程ID"></el-table-column>
                <el-table-column prop="courseName" label="课程名"></el-table-column>
                <el-table-column prop="teacherName" label="授课教师"></el-table-column>
                <el-table-column prop="courseHour" label="学时"></el-table-column>
                <el-table-column prop="courseScore" label="学分"></el-table-column>
            </el-table>
        </el-row>
        <el-pagination style="align-self: center;" layout="prev,pager,next" :total="100"></el-pagination>
    </div>
</template>

<script setup lang="ts">
import { getCourseList } from "@/api/student/course"
const formData = ref({
    input: ""
})
const courseList = ref([])
onBeforeMount(() => {
    getCourseList({
        limit: 10,
        page: 1
    }).then(({ data }: any) => {
        courseList.value = data
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