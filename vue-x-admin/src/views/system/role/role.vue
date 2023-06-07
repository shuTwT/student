<script setup lang="ts">
const form = reactive({
    name: ''
})
const data = ref([
    {
        roleId: '1',
        roleName: '管理员',
        roleSymbol: "ROLE_ADMIN",
        roleDescription: "管理员",
        roleLevel: "一级",
        createdTime: "2023-4-29 16:52"
    }
])
const total = 10
function handleCurrentChange() {

}
function handleSizeChange() {

}
</script>
<template>
    <section>
        <div class="role-management-container">
            <el-row>
                <el-form :inline="true" :model="form">
                    <el-form-item label="角色名称">
                        <el-input v-model="form.name" />
                    </el-form-item>
                    <el-form-item>
                        <el-button size="large" type="primary">查询</el-button>
                        <el-button size="large" type="default">重置</el-button>
                    </el-form-item>
                </el-form>
            </el-row>
            <el-row>
                <el-button>新增</el-button>
                <el-button>导入</el-button>
                <el-button>删除</el-button>
            </el-row>
            <el-row>
                <el-table :data="data">
                    <el-table-column type="selection" width="55" />
                    <el-table-column prop="roleId" label="#" width="120" />
                    <el-table-column prop="roleName" label="角色名称" width="120" />
                    <el-table-column prop="roleSymbol" label="角色标识" width="120" />
                    <el-table-column prop="roleDescription" label="角色描述" width="120" />
                    <el-table-column prop="createdTime" label="创建时间" />
                </el-table>
            </el-row>
            <el-pagination background :total="total" :page-size="100" :page-sizes="[10, 25, 50, 100]"
                layout="total, sizes, prev, pager, next, jumper" @current-change="handleCurrentChange"
                @size-change="handleSizeChange"></el-pagination>
        </div>
    </section>
</template>


<style scoped>
.role-management-container {
    padding: 20px;
}
</style>