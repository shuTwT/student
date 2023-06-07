<template>
    <el-form :model="form" label-width="120px">
        <el-form-item label="课程名">
            <el-input v-model="form.courseName"></el-input>
        </el-form-item>
        <el-form-item label="学生名">
            <el-input v-model="form.stuId"></el-input>
        </el-form-item>
        <el-form-item label="班级名">
            <el-input v-model="form.clazzId"></el-input>
        </el-form-item>
        <el-form-item label="分数">
            <el-input v-model="form.scoreNum"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">提交</el-button>
        </el-form-item>
    </el-form>
</template>

<script setup lang="ts">
import {addScore} from "@/api/student/score"
const form = reactive({
    courseName: '',
    stuId: '',
    clazzId: '',
    scoreNum:''
})
const emits=defineEmits(['destory'])
function onSubmit() {
    emits('destory')
    addScore(form)
}

</script>

<style scoped></style>