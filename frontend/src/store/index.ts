import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";
import user from "@/store/user";

export default createStore({
  state: () => ({
    //全局状态
    all: [],
  }),
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    user,
  },
  plugins: [createPersistedState()],
});
