<template>
    <el-form :model="form" label-width="120px">
        <el-form-item label="学号名">
            <el-input v-model="form.stuId"></el-input>
        </el-form-item>
        <el-form-item label="学生姓名">
            <el-input v-model="form.stuName"></el-input>
        </el-form-item>
        <el-form-item label="班级ID">
            <el-input v-model="form.clazzId"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">提交</el-button>
        </el-form-item>
    </el-form>
</template>

<script setup lang="ts">
import {addStudent} from "@/api/student/student"
const form = reactive({
    stuName: '',
    stuId: '',
    clazzId: '',
})
const emits=defineEmits(['destory'])
function onSubmit() {
    emits('destory')
    addStudent(form)
}

</script>

<style scoped></style>