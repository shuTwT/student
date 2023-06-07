<template>
    <div class="app-main">
        <router-view></router-view>
    </div>
</template>

<script setup lang="ts">

</script>

<style scoped>
.app-main {
    flex: 1;
    overflow: hidden;
    padding: 20px;
    overflow-y: auto;
    overflow-x: hidden;
    width: 100%;
}
</style>