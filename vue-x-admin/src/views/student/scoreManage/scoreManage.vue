<template>
    <div class="layout">
        <el-dialog v-model="dialogVisible">
            {{ dialogContent }}
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="dialogConfirmFn()">
                        确定
                    </el-button>
                </span>
            </template>
        </el-dialog>
        <el-dialog v-model="addScoreVisible">
            <add-score @destory="()=>addScoreVisible=false"></add-score>
        </el-dialog>
        <el-row style="padding:10px">
            <el-input style="width: 150px;margin-right: 20px;" placeholder="请输入课程名"
                v-model="formData.courseName"></el-input>
            <el-input style="width: 150px;margin-right: 20px;" placeholder="请输入班级名" v-model="formData.clazzName"></el-input>
            <el-button type="primary" @click="handleClickSearch">搜索</el-button>
            <el-button v-permission="'admin'" type="info" @click="()=>addScoreVisible=true">添加分数</el-button>
            <el-button v-permission="'admin'" type="danger">批量删除</el-button>
            <el-button v-permission="'admin'" type="success"  @click="() => analyseExcelToJson()">批量导入</el-button>
            <el-button v-permission="'admin'" @click="() => downloadXLSX()">下载模板</el-button>
        </el-row>
        <el-row style="padding:10px">
            <el-table :data="scoreList" style="height:700px;width: 100%;">
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column prop="scoreId" label="成绩ID"></el-table-column>
                <el-table-column prop="courseName" label="课程名"></el-table-column>
                <el-table-column prop="stuName" label="学生名"></el-table-column>
                <el-table-column prop="clazzName" label="班级名"></el-table-column>
                <el-table-column prop="scoreNum" label="分数"></el-table-column>
                <el-table-column label="操作">
                    <template #default="scope">
                        <el-button v-permission="'admin'" @click="() => handleClickScore(1, scope)">加分</el-button>
                        <el-button v-permission="'admin'" @click="() => handleClickScore(2, scope)">减分</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-row>
        <el-pagination v-model:current-page="currentPage" @current-change="handleCurrentChange" style="align-self: center;"
            layout="prev,pager,next" :total="total"></el-pagination>
    </div>
</template>

<script setup lang="ts">
import * as XLSX from 'xlsx'
import { ElNotification } from 'element-plus'
import  addScore  from './addScore.vue'
import { getScoreList,increaseScore,reduceScore,importScores } from "@/api/student/score"
const dialogVisible = ref(false)
const addScoreVisible=ref(false)
const formData = ref({
    courseName: "",
    clazzName: "",
})
const dialogContent = ref('')
const dialogConfirmFn = ref(() => { })
const currentPage = ref(1)
const total = ref(0)
const scoreList = ref([])
function getData() {
    console.log( currentPage.value-1);
    
    getScoreList({
        courseName: formData.value.courseName,
        clazzName: formData.value.clazzName,
        limit: 10,
        page: currentPage.value-1
    }).then(({ data }: any) => {
        scoreList.value = data.data
        total.value=data.total
        console.log(data);

    })
}
function handleClickSearch() {
    getData()
}
function handleCurrentChange() { 
    getData()
}
onBeforeMount(() => {
    getData()
})
function handleClickScore(type: number, scope: any) {
    dialogVisible.value = true
    if (type == 1) {
        dialogContent.value = '该操作将增加10分'
        dialogConfirmFn.value = () => {
            if (+scope.row.scoreNum >= 90) {
                ElNotification({
                    title: "警告",
                    message: "最大值100",
                    duration: 1000
                })

            }
            else {
                increaseScore({scoreId:scope.row.scoreId,userId:"3"})
                scope.row.scoreNum = +scope.row.scoreNum + 10 + ""
            }

            dialogVisible.value = false
        }
    } else if (type == 2) {
        dialogContent.value = '该操作将减少10分'
        dialogConfirmFn.value = () => {
            dialogVisible.value = false
            if (+scope.row.scoreNum <= 20) {
                ElNotification({
                    title: "警告",
                    message: "最小值20",
                    duration: 1000
                })

            }
            else {
                reduceScore({scoreId:scope.row.scoreId,userId:"3"})
                scope.row.scoreNum = +scope.row.scoreNum - 10 + ""
            }
        }
    }
    console.log(scope.row);
}
/**
 * 下载XLSX
 */
 function downloadXLSX() {
    const worksheet = XLSX.utils.aoa_to_sheet([
        ['课程名', '学生名', '班级名', '分数']
    ])
    const workbook = XLSX.utils.book_new()
    XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1')
    XLSX.writeFile(workbook, '分数模板.xlsx', { type: 'binary' })
}
function analyseExcelToJson() {
    function readWorkbook(workbook: any) {
        const sheetNames = workbook.SheetNames
        const worksheet = workbook.Sheets[sheetNames[0]]
        const excelData = XLSX.utils.sheet_to_json(worksheet);
        importScores(excelData).then((res: any) => {
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