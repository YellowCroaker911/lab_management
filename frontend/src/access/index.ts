import router from "@/router";
import store from "@/store";
import ACCESS_ENUM from "@/access/accessEnum";
import checkAccess from "@/access/checkAccess";

router.beforeEach(async (to, from, next) => {
  console.log("登录用户信息", store.state.user.loginUser);
  const loginUser = store.state.user.loginUser;
  // 如果之前没登录，自动登录
  if (!loginUser || !loginUser.userRole) {
    // 等用户成功登录之后再执行后续代码
    await store.dispatch("user/getLoginUser");
  }
  const needAccess = (to.meta?.access as string) ?? ACCESS_ENUM.NOT_LOGIN;
  // 要跳转的页面要登录
  if (needAccess !== ACCESS_ENUM.NOT_LOGIN) {
    // 如果没登录直接跳到登录页
    if (!loginUser || !loginUser.role) {
      console.log();
      next(`/user/login?redirect=${to.fullPath}`);
      return;
    }
    // 如果已经登录还是权限不足，跳转到无权限页面
    if (!checkAccess(loginUser, needAccess)) {
      next("/noAuth");
      return;
    }
  }
  next();
});
