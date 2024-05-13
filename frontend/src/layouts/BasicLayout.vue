<template>
  <div id="basicLayout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header">
        <global-header></global-header>
      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">计算机系实验管理系统</a-layout-footer>
    </a-layout>
  </div>
</template>

<style>
#basicLayout {
}

#basicLayout .header {
  background: dodgerblue;
  margin-bottom: 10px;
  box-shadow: #eee 1px 1px 5px;
}

#basicLayout .content {
  background: linear-gradient(to right, #aaa, #fff);
  margin-bottom: 10px;
  padding: 20px;
}

#basicLayout .footer {
  background: #efefef;
  margin-bottom: 10px;
  padding: 16px;
  position: sticky;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}
</style>

<script setup lang="ts">
import GlobalHeader from "@/components/GlobalHeader.vue";
</script>
