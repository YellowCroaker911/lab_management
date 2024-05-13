<template>
  <a-row id="globalHeader" class="grid-demo" align="center" :wrap="false">
    <a-col flex="100px"></a-col>
    <a-col flex="auto">
      <a-menu
        mode="horizontal"
        :selected-keys="selectedKeys"
        @menu-item-click="doMenuClick"
      >
        <a-menu-item
          key="0"
          :style="{ padding: 0, marginRight: '38px' }"
          disabled
        >
          <div class="title-bar">
            <img
              class="logo"
              src="../assets/school_logo.png"
              alt="school_logo"
            />
            <div class="title">计算机系实验管理平台</div>
          </div>
        </a-menu-item>
        <a-menu-item v-for="item in visibleRoutes" :key="item.path">
          {{ item.name }}
        </a-menu-item>
      </a-menu>
    </a-col>
    <a-col flex="100px">
      <div @click="showToken">
        {{ $store.state.user?.loginUser?.username ?? "未登录" }}
      </div>
      <div>
        <a-button v-if="$store.state.user?.loginUser" @click="logout($event)"
          >退出登录
        </a-button>
      </div>
    </a-col>
  </a-row>

  <div></div>
</template>

<script setup lang="ts">
import { routes } from "@/router/routes";
import { useRouter } from "vue-router";
import { ref } from "vue";
import { useStore } from "vuex";
import checkAccess from "@/access/checkAccess";

const router = useRouter();
const store = useStore();
const loginUser = store.state.user.loginUser;

// 展示在菜单的路由数组
const visibleRoutes = routes.filter((item, index) => {
  if (item.meta?.hideInMenu) {
    return false;
  }
  if (!checkAccess(loginUser, item.meta?.access as string)) {
    return false;
  }
  return true;
});

//路由跳转后更新选中的菜单项
router.afterEach((to, from, failure) => {
  selectedKeys.value = [to.path];
});

//默认主页
const selectedKeys = ref(["/"]);
const doMenuClick = (key: string) => {
  router.push({
    path: key,
  });
};
const showToken = () => {
  console.log(localStorage.getItem("token"));
};
//退出登录
const logout = async (event: MouseEvent) => {
  try {
    store.state.loginStatus = false;
    store.state.token = null;
    store.state.userInfo = {};
    localStorage.removeItem("token");
    console.log("退出登录：" + store.state.user.loginUser);
    await router.push({
      path: "/user/login",
      replace: true,
    });
  } catch (error) {
    console.error(error);
  }
};

// setTimeout(() => {
//   //dispatch调用，写路径名+方法名
//   store.dispatch("user/getLoginUser", {
//     userName: "123456",
//     role: 'admin',
//   });
// }, 3000);
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.title-bar {
  display: flex;
  align-items: center;
}

.title {
  color: #444;
  margin-left: 16px;
}

.logo {
  height: 48px;
}
</style>
