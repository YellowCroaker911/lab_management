import { StoreOptions } from "vuex";
import ACCESS_ENUM from "@/access/accessEnum";
import { UserControllerService } from "../../generated";
import store from "@/store/index";

/**
 * 存储用户登录信息的模块
 */

export default {
  namespaced: true,
  state: {
    loginUser: {
      username: "未登录",
    },
  },
  getters: {},
  actions: {
    async getLoginUser({ commit, state }, payload) {
      // 从远程请求获取登录信息
      const token = localStorage?.getItem("token");
      const res = await UserControllerService.getLoginUser(
        token || "123123123"
      );
      console.log(res);
      if (res.status === 100) {
        commit("updateUser", res.data);
        //console.log("登录响应：" + JSON.stringify(res));
        //console.log(store.state.user.loginUser);
        // console.log("token: " + token);
        const role = store.state.user.loginUser.role;
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
        commit("updateUser", {
          ...state.loginUser,
          role: res.data?.role,
        });
        console.log("当前用户权限：" + res.data?.role);
      } else {
        commit("updateUser", {
          ...state.loginUser,
          role: ACCESS_ENUM.NOT_LOGIN,
        });
      }
    },
  },
  mutations: {
    updateUser(state, products) {
      state.loginUser = products;
    },
  },
} as StoreOptions<any>;
