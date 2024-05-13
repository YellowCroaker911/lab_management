import ACCESS_ENUM from "@/access/accessEnum";

/**
 * 检查权限（判断当前登录用户是否具有某个权限）
 * @param loginUser 当前用户
 * @param needAcess 所需权限
 * @return boolean 有无权限
 */
const checkAccess = (loginUser: any, needAcess = ACCESS_ENUM.NOT_LOGIN) => {
  // 获取当前用户具有的权限,找不到user就说明没登录
  const loginUserAccess = loginUser?.role ?? ACCESS_ENUM.NOT_LOGIN;
  if (needAcess === ACCESS_ENUM.NOT_LOGIN) {
    return true;
  }

  //登录才能访问的
  if (
    needAcess === ACCESS_ENUM.USER ||
    needAcess === ACCESS_ENUM.ADMIN ||
    needAcess === ACCESS_ENUM.STUDENT ||
    needAcess === ACCESS_ENUM.TEACHER ||
    needAcess === ACCESS_ENUM.EXECERCISOR
  ) {
    //如果用户没登录，表示没有权限
    if (loginUserAccess === ACCESS_ENUM.NOT_LOGIN) {
      return false;
    }

    // 仅管理员可见，判断当前用户是否有权限
    if (needAcess === ACCESS_ENUM.ADMIN) {
      if (loginUserAccess !== ACCESS_ENUM.ADMIN) {
        return false;
      }
    }
    // 仅教师可见
    if (needAcess === ACCESS_ENUM.TEACHER) {
      if (loginUserAccess !== ACCESS_ENUM.TEACHER) {
        return false;
      }
    }

    // 实验员可见
    if (needAcess === ACCESS_ENUM.EXECERCISOR) {
      if (loginUserAccess !== ACCESS_ENUM.EXECERCISOR) {
        return false;
      }
    }

    // 学生可见
    if (needAcess === ACCESS_ENUM.STUDENT) {
      if (loginUserAccess !== ACCESS_ENUM.STUDENT) {
        return false;
      }
    }
    return true;
  }
};

export default checkAccess;

// if (to.meta?.access === "canAdmin") {
//     if (store.state.user.loginUser?.role !== "admin") {
//       next("/noAuth");
//       return;
//     }
//   }
// next();
