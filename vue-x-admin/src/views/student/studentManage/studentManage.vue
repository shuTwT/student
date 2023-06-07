<script setup lang="ts">
import { getStudentList, importStudents } from "@/api/student/student"
import addStudent from "./addStudent.vue"
import * as XLSX from 'xlsx'
const formData = ref({
    input: ""
})
const currentPage = ref(1)
const total = ref(0)
const clazzList = ref([])
const addStudentVisible=ref(false)
function getData() {
    getStudentList({
        clazzName:formData.value.input,
        limit: 10,
        page: currentPage.value - 1
    }).then(({ data }: any) => {
        total.value = data.total
        clazzList.value = data.data
    })
}
function handleCurrentChange() {
    getData()
}
onBeforeMount(() => {
    getData()
})
/**
 * 下载XLSX
 */
function downloadXLSX() {
    const worksheet = XLSX.utils.aoa_to_sheet([
        ['学号', '姓名', '专业', '班级', '平均分']
    ])
    const workbook = XLSX.utils.book_new()
    XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1')
    XLSX.writeFile(workbook, '学生模板.xlsx', { type: 'binary' })
}
function analyseExcelToJson() {
    function readWorkbook(workbook: any) {
        const sheetNames = workbook.SheetNames
        const worksheet = workbook.Sheets[sheetNames[0]]
        const excelData = XLSX.utils.sheet_to_json(worksheet);
        importStudents(excelData).then((res: any) => {
            console.log(res);
            getData()
        }).catch((res: any) => {
            console.log(res);

        })

    }
    function readWorkbookFromLocalFile(file: any, callback?: any) {
        const reader = new FileReader();
        reader.onload = function (e: any) {
            const data = e.target.result
            const workbook = XLSX.read(data, { type: 'binary' })
            if (callback) callback(workbook)
        }
        reader.readAsBinaryString(file);
    }
    const inputEl = document.createElement('input')
    inputEl.setAttribute('type', 'file')
    inputEl.setAttribute('accept', '.xlsx,.xls')
    inputEl.click()
    inputEl.addEventListener('change', (e: any) => {
        const files = e.target.files;
        if (files.length == 0) {
            return;
        }
        const f = files[0];
        readWorkbookFromLocalFile(f, (workbook: any) => {
            readWorkbook(workbook)


        })
    })
    inputEl.remove()

}

</script>

<template>
    <div class="layout">
        <el-dialog v-model="addStudentVisible">
            <add-student @destory="()=>addStudentVisible=false"></add-student>
        </el-dialog>
        <el-row style="padding:10px">
            <el-input style="width: 200px;margin-right: 20px;" placeholder="请输入班级名" v-model="formData.input"></el-input>
            <el-button type="primary" @click="()=>getData()">搜索</el-button>
            <el-button v-permission="'admin'" type="info" @click="()=>addStudentVisible=true">添加学生</el-button>
            <el-button v-permission="'admin'" type="danger">批量删除</el-button>
            <el-button v-permission="'admin'" type="success" @click="() => analyseExcelToJson()">批量导入</el-button>
            <el-button type="default" @click="() => downloadXLSX()">下载模板</el-button>
        </el-row>
        <el-row style="padding:10px">
            <el-table :data="clazzList" style="height:700px">
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column prop="stuId" label="学号"></el-table-column>
                <el-table-column prop="stuName" label="学生姓名"></el-table-column>
                <el-table-column prop="clazzName" label="班级"></el-table-column>
                <el-table-column prop="stuSex" label="学生性别"></el-table-column>
            </el-table>
        </el-row>
        <el-pagination v-model:current-page="currentPage" @current-change="handleCurrentChange" style="align-self: center;"
            layout="prev,pager,next" :total="total"></el-pagination>
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
    display: flex;
    flex-direction: column;
}
</style>