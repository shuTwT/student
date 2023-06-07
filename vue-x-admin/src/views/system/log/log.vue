<template>
    <div>
        <el-row>
            <el-select v-model="formData.type" placeholder="请选择类型">
                <el-option label="正常" :value="0">
                </el-option>
                <el-option label="异常" :value="1">
                </el-option>
            </el-select>
            <el-button type="primary">查询</el-button>
            <el-button type="default">重置</el-button>
            <el-button type="warning">删除</el-button>
        </el-row>
        <el-row style="margin-top: 10px;">
            <el-table :border="true" :data="tableData">
                <el-table-column type="selection" width="55" />
                <el-table-column label="#" prop="logId" width="55"></el-table-column>
                <el-table-column label="类型" prop="logType" width="120">
                    <template #default="scope">
                        <span>{{ scope.row.logType==0?'正常':'异常' }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="标题" prop="logTitle" width="120"></el-table-column>
                <el-table-column label="IP地址" prop="logIp" width="120"></el-table-column>
                <el-table-column label="请求方式" prop="logMethod" width="120"></el-table-column>
                <el-table-column label="客户端" width="120"></el-table-column>
                <el-table-column label="创建时间" prop="createTime" width="120"></el-table-column>
                <el-table-column label="创建人" prop="userName" width="120"></el-table-column>
                <el-table-column label="操作"></el-table-column>
            </el-table>
        </el-row>
        <el-pagination v-model:current-page="currentPage" @current-change="handleCurrentChange" style="align-self: center;"
            layout="prev,pager,next" :total="total"></el-pagination>
    </div>
</template>
<script setup lang="ts">
const tableData=ref([])
const currentPage=ref(1)
const total=ref(0)
const formData=reactive({
    type:""
})
import {getLogList} from "@/api/system/log"
function handleCurrentChange(){

}
function getData(){
    getLogList({
        limit: 10,
        page: currentPage.value - 1
    }).then(({data}:any)=>{
        console.log(data);
        tableData.value=data.data
    })
}
onBeforeMount(()=>{
    getData()
})
</script>
<style>
</style>