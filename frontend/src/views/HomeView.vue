<template>
  <div class="home">欢迎来到计算机系实验管理平台</div>

  <a-card>
    <span>专业课表：</span>
    <a-table />
  </a-card>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";

export default defineComponent({
  name: "HomeView",
  methods: { ref },
});
</script>
<style>
.home {
  margin-left: 20px;
  font-size: 30px;
}

a-calendar {
  max-width: 1600px;
}
</style>
