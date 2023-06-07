<script setup lang="ts">
import { useAppStore } from '@/store/modules/app';
import { useUserStore } from '@/store/modules/user';
import {intersection} from '@/utils'
import appMain from './appMain.vue';
const route = useRoute()
const router = useRouter()
const appStore = useAppStore()
const userStore = useUserStore()
const menus = appStore.routes
const currentMenu = ref<any>(menus[0])
watch(() => route.path, () => {
    currentMenu.value = menus.filter((value: any) => {
        if (value.children) {
            for (let i = 0; i < value.children.length; i++) {
                const item = value.children[i]
                if (item.name == route.matched[1].name) {
                    return true
                }
            }
            return false
        } else {
            return false
        }
    })[0];

})
function addTab(path: string, name: string) {
    router.push(path)
    document.title = name
}
function getMenus() {
    return menus.filter(
        (value) => value.meta.showLink !== false
    )
}
onMounted(() => {
    currentMenu.value = menus.filter((value: any) => {
        if (value.children) {
            for (let i = 0; i < value.children.length; i++) {
                const item = value.children[i]
                if (item.name == route.matched[1].name) {
                    return true
                }
            }
            return false
        } else {
            return false
        }
    })[0];
})
function hasMenuPermission(arr1:any[]){
    if (arr1==undefined) {
        return true
    }
    const arr2=userStore.roles
    const arr=intersection(arr1,arr2)
    console.log(arr1);
    console.log(arr2);
    
    
    return arr.length!=0
}
</script>
<template>
    <div class="sidebar">
        <div class="tab-nav">
            <span class="logo">
            </span>
            <div class="tab-list">
                <div v-for="(item, index) in getMenus()" :key="index" 
                    @click="() => addTab(item.path, item.name)">

                    <span class="tab-item" v-if="hasMenuPermission(item.meta.roles)">
                        {{ item.meta.title }}
                    </span>

                </div>
            </div>
        </div>
        <div class="menubar">
            <span class="title">学生成绩管理系统</span>
            <div class="divider">首页</div>
            <div class="menu">
                <div  v-for="(item, index) in currentMenu.children" :key="index"
                    >
                    <div class="menu-item" v-if="hasMenuPermission(item.meta.roles)" @click="() => addTab(item.path, item.name)">
                        {{ item.meta.title }}
                    </div>
                    

                </div>
            </div>
        </div>
    </div>
    <div class="main">
        <div class="header">
            <div class="nav flex justify-between items-stretch px-[20px]">
                <div class="left flex h-full"></div>
                <div class="right flex h-full">
                    <el-dropdown>
                        <span class="flex items-center cursor-pointer">{{userStore.username}}</span>
                        <template #dropdown>
                            <el-dropdown-menu>
                                <el-dropdown-item>个人中心</el-dropdown-item>
                                <el-dropdown-item divided @click="()=>userStore.logout()">退出登录</el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </div>
            </div>
            <div class="tab-list">
                <div class="tab-item is-active">
                    <span>首页</span>
                </div>

            </div>
        </div>

        <app-main></app-main>

    </div>
</template>


<style scoped>
.sidebar {
    position: relative;
    width: 300px;
    background: rgb(35, 35, 36);
    display: flex;
    flex-direction: row;
}

.sidebar .tab-nav {
    position: relative;
    width: 80px;
    background: #303030;
    color: #fff;
}

.sidebar .tab-nav .logo {
    display: flex;
    width: 100%;
    height: 60px;
}

.sidebar .tab-nav .logo img {
    width: 100%;
    height: 100%;
}

.sidebar .tab-item {
    height: 50px;
    display: flex;
    justify-content: center;
    align-items: center;
    white-space: nowrap;
    cursor: pointer;
}

.sidebar .tab-item.active {
    background: #1f1f1f;
}

.sidebar .tab-item:hover,
.sidebar .tab-item:focus {
    background: #1f1f1f;
}

.menubar {
    position: relative;
    flex: 1;
    overflow: hidden;
}

.menubar .title {
    display: flex;
    justify-content: center;
    align-items: center;
    color: #515a6e;
    font-size: 20px;
    line-height: 55px;
    text-overflow: ellipsis;
    white-space: nowrap;
    vertical-align: middle;
}

.menubar .divider {
    display: flex;
    justify-content: center;
    color: #515a6e;
    font-size: 14px;
}

.menubar .divider::before,
.menubar .divider::after {
    content: "";
    position: relative;
    display: inline-block;
    width: 80px;
    height: 1px;
    margin-top: 10px;
    border-bottom: 1px solid #E2E9EE;
}

.menubar .divider::before {
    right: 10px;
}

.menubar .divider::after {
    left: 10px;
}

.menubar .menu {
    color: #6e6e6e;
}

.menubar .menu .menu-item {
    height: 60px;
    line-height: 60px;
    font-size: 20px;
    cursor: pointer;
}

.menubar .menu .menu-item.active {
    background: #515a6e;
}

.menubar .menu .menu-item:not(.active):hover {
    background: #515a6e;
}

.main {
    position: relative;
    flex: 1;
    display: flex;
    flex-direction: column;
    width: calc(100% - 300px);
}

.header {
    position: relative;
    background: #303030;
    height: 110px;
    box-shadow: 0 1px 4px rgba(0, 21, 41, .08);
    display: flex;
    width: 100%;
    flex-direction: column;
}

.header .nav {
    position: relative;
    height: 60px;
}

.header .tab-list {
    border-top: 1px solid #f6f6f6;
    height: 50px;
    display: flex;
    flex-direction: row;
    align-items: flex-end;
    padding-left: 30px;
    position: relative;
    overflow: hidden;
}


.header .tab-item {
    position: relative;
    display: flex;
    align-items: center;
    justify-content: space-between;
    bottom: 0;
    outline: none;
    margin-top: 5.59px;
    margin-right: 5px;
    line-height: 38px;
    height: 38px;
    text-align: center;
    background: #fff;
    font-size: 14px;
    padding-left: 30px;
    padding-right: 30px;
    cursor: pointer;
    transition: padding .3s cubic-bezier(.645, .045, .355, 1) !important;
}

.header .tab-item span {
    user-select: none;
}

.header .tab-item.is-active {
    background: #6e6e6e;
    padding-right: 40px;
}

.header .tab-item:not(.is-active):hover {
    padding-right: 40px;
    background: #6e6e6e;
}

.header .tab-item.is-active::before {
    content: "";
    position: absolute;
    border-bottom: 10px solid #6e6e6e;
    border-left: 10px solid transparent;
    border-right: 10px solid transparent;
    left: -10px;
    bottom: -2px;
}

.header .tab-item.is-active::after {
    content: "";
    position: absolute;
    border-bottom: 10px solid #6e6e6e;
    border-left: 10px solid transparent;
    border-right: 10px solid transparent;
    right: -10px;
    bottom: -2px;
}

.header .tab-item:not(.is-active):hover::before {
    content: "";
    position: absolute;
    border-bottom: 10px solid #6e6e6e;
    border-left: 10px solid transparent;
    border-right: 10px solid transparent;
    left: -10px;
    bottom: -2px;
}

.header .tab-item:not(.is-active):hover::after {
    content: "";
    position: absolute;
    border-bottom: 10px solid #6e6e6e;
    border-left: 10px solid transparent;
    border-right: 10px solid transparent;
    right: -10px;
    bottom: -2px;
}



.content .iframe {
    border: 0;
    width: 100%;
    height: 100%;
}

.icon {
    display: inline-block;
    width: 20px;
    height: 20px;
    background-position: center center;
    background-size: 20px 20px;
    background-repeat: no-repeat;
}

.icon.close {
    background-image: url(/close.svg);
}



.header .tab-item.is-active .icon.close {
    background-image: url(/close-active.svg);
}
</style>