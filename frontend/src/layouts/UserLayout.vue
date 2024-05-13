<template>
  <div id="userLayout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header">
        <a-space
          ><img src="../assets/school_logo.png" class="logo" />
          <div>计算机系实验管理平台</div>
        </a-space>
      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">计算机系实验管理系统</a-layout-footer>
    </a-layout>
  </div>
</template>

<style>
#userLayout {
}

#userLayout .header {
  margin-top: 16px;
  background: dodgerblue;
  box-shadow: #eee 1px 1px 5px;
}

#userLayout .logo {
}

#userLayout .content {
  background: linear-gradient(to right, #aaa, #fff);
  margin-bottom: 10px;
  padding: 20px;
}

#userLayout .footer {
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

<script setup lang="ts"></script>
