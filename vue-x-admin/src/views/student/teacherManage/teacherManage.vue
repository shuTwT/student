<template>
    <div class="layout">
        <el-row style="padding:10px">
            <el-input style="width: 200px;margin-right: 20px;" placeholder="请输入搜索的内容"
                v-model="searchFormData.input"></el-input>
            <el-button type="primary">搜索</el-button>
            <el-button v-permission="'admin'" type="info">添加教师</el-button>
            <el-button v-permission="'admin'" type="danger">批量删除</el-button>
            <el-button v-permission="'admin'" type="success">批量导入</el-button>
        </el-row>
        <el-row style="padding:10px">
            <el-table :data="teacherList" style="height:700px">
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column prop="teacherId" label="教师ID"></el-table-column>
                <el-table-column prop="teacherName" label="教师姓名"></el-table-column>
                <el-table-column label="操作">
                    <template #default="scope">
                        <el-button v-permission="'admin'" size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button v-permission="'admin'" size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">禁用</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-row>
        <el-pagination style="align-self: center;" layout="prev,pager,next" :total="100"></el-pagination>
        <el-dialog v-model="dialogFormVisible">
            <el-form ref="postFormRef" :model="postFormData" label-width="80px">
                <el-form-item label="教师号" prop="name">
                    <el-input placeholder="请填写ID" :maxLength="20" v-model="postFormData.teacherId"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="sex">
                    <el-select placeholder="请选择姓名" v-model="postFormData.teacherName"></el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">Create</el-button>
                    <el-button @click="() => dialogFormVisible = false">Cancel</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script setup lang="ts">
import { getTeacherList } from "@/api/student/teacher"
const searchFormData = ref({
    input: ""
})
const postFormData = ref({
    teacherId: "",
    teacherName: ""
})
const postFormRef = ref()
const dialogFormVisible = ref(false)
const teacherList = ref([])
function handleEdit(_: any, row: any) {
    dialogFormVisible.value = true
    postFormData.value = {
        teacherId: row.teacherId,
        teacherName: row.teacherName
    }
}
function handleDelete(_: any, row: any) {
    console.log(row);

}
function onSubmit() {
    dialogFormVisible.value = false
    postFormRef.value.resetFields()
}
onBeforeMount(() => {
    getTeacherList({
        limit: 10,
        page: 1
    }).then(({ data }: any) => {
        teacherList.value = data
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