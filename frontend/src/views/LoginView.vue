<template>
  <div id="userLoginView">
    <a-card>
      <div id="welcomeText">欢迎来到计算机系实验管理平台！</div>
      <a-form
        style="max-width: 480px; margin: 0 auto"
        label-align="left"
        auto-label-width
        :model="loginForm"
        @submit="handleSubmit"
      >
        <a-form-item field="userAccount" label="账号">
          <a-input v-model="loginForm.username" placeholder="请输入账号" />
        </a-form-item>
        <a-form-item
          field="userPassword"
          tooltip="密码在6到20位之间"
          label="密码"
        >
          <a-input-password
            v-model="loginForm.password"
            placeholder="请输入密码"
          />
        </a-form-item>
        <a-radio-group v-model="userRole">
          <a-radio value="0">管理员</a-radio>
          <a-radio value="2">教师</a-radio>
          <a-radio value="3">实验员</a-radio>
          <a-radio value="1">学生</a-radio>
        </a-radio-group>
        <a-form-item>
          <a-button type="primary" html-type="submit" style="width: 120px"
            >登录
          </a-button>
        </a-form-item>
      </a-form>
    </a-card>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from "vue";
import message from "@arco-design/web-vue/es/message";
import { UserControllerService, UserLoginDTO } from "../../generated";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import ACCESS_ENUM from "@/access/accessEnum";

const router = useRouter();
const store = useStore();

const loginForm = reactive<UserLoginDTO>({
  username: "",
  password: "",
});
const userRole = ref();
/**
 * 提交表单实现登录
 * @param data
 */
const handleSubmit = async () => {
  console.log(loginForm);
  const res = await UserControllerService.login(loginForm);
  console.log(res);
  if (res.status === 100) {
    alert("登录成功!");
    localStorage.setItem("token", res.data?.jwtToken || " ");
    console.log(localStorage.getItem("token"));
    await store.dispatch("/user/getLoginUser");
    const role = store.state.user.loginUser.role;

    console.log(role);

    switch (role) {
      case 0:
        store.state.user.loginUser.role = ACCESS_ENUM.ADMIN;
        break;
      case 1:
        store.state.user.loginUser.role = ACCESS_ENUM.STUDENT;
        break;
      case 2:
        store.state.user.loginUser.role = ACCESS_ENUM.TEACHER;
        break;
      case 3:
        store.state.user.loginUser.role = ACCESS_ENUM.EXECERCISOR;
        break;
      default:
        store.state.user.loginUser.role = ACCESS_ENUM.USER;
    }
    await router.push({
      path: "/",
      replace: true,
    });
  } else {
    message.error("登录失败!  " + res.message + "  " + res.description);
  }
};
</script>

<style scoped>
#welcomeText {
  max-width: fit-content;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 30px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe WPC", "Segoe UI",
    "HelveticaNeue-Light", system-ui, "Ubuntu", "Droid Sans", sans-serif;
  font-size: 20px;
}
</style>
