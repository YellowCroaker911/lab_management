<template>
  <div id="app">
    <template v-if="router.currentRoute.value.path.startsWith('/user')">
      <router-view />
    </template>
    <!--    <template v-if="router.currentRoute.value.path.startsWith('/')">-->
    <!--      <router-view />-->
    <!--    </template>-->
    <template v-else>
      <BasicLayout />
    </template>
  </div>
</template>

<style>
#app {
}
</style>

<script setup lang="ts">
import BasicLayout from "@/layouts/BasicLayout.vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { onMounted } from "vue";
import { UserControllerService } from "../generated";

const router = useRouter();
const store = useStore();

/**
 * 全局项目入口
 */
const doInit = () => {
  console.log("hello!");
  // UserControllerService.login({
  //   username: "abcdefg",
  //   password: "abcdefg",
  // });
  console.log("111");
  UserControllerService.getLoginUser(
    "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJmMjU0NWIwZDU3YTk0ODE4YmEwOTVmNWIzMDg1MTZhYiIsInN1YiI6IjIiLCJpc3MiOiJzZyIsImlhdCI6MTcxNTYxODM5MCwiZXhwIjoxNzE2ODI3OTkwfQ.YuW01T99V-G6zHBBSf9fTX8g9f0F5Y7IZSYTnOhnmrg"
  );
  console.log("222");
};

onMounted(() => {
  doInit();
});
</script>
<!--openapi &#45;&#45;input http://localhost:3000/v3/api-docs/default &#45;&#45;output ./generated &#45;&#45;client axios-->
