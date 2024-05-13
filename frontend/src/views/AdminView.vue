<template>
  <a-layout>
    <a-layout-sider height="auto" width="auto">
      <div class="menu-demo">
        <a-menu
          class="menu"
          mode="pop"
          show-collapse-button
          theme="dark"
          :default-selected-keys="['/admin/semester']"
          @click="handleMenuClick"
        >
          <a-menu-item key="/admin/semester">
            <template #icon>
              <icon-apps></icon-apps>
            </template>
            <router-link to="/admin/semester">学期管理</router-link>
          </a-menu-item>
          <a-menu-item key="/admin/class">
            <template #icon>
              <icon-at></icon-at>
            </template>
            <router-link to="/admin/class">实验排课</router-link>
          </a-menu-item>
          <a-menu-item key="/admin/lab">
            <template #icon>
              <icon-bulb></icon-bulb>
            </template>
            <router-link to="/admin/lab">实验室借用审批</router-link>
          </a-menu-item>
          <a-menu-item key="/admin/user">
            <template #icon>
              <icon-user></icon-user>
            </template>
            <router-link to="/admin/user">用户管理</router-link>
          </a-menu-item>
        </a-menu>
      </div>
    </a-layout-sider>
    <a-layout-content :style="{ marginLeft: '100px', padding: '24px' }">
      <h1>欢迎来到管理员页面</h1>
      <router-view></router-view>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import { defineComponent, ref, Ref } from "vue";
import {
  IconApps,
  IconAt,
  IconBulb,
  IconUser,
} from "@arco-design/web-vue/es/icon";

export default defineComponent({
  name: "AdminView",
  components: {
    IconApps,
    IconAt,
    IconBulb,
    IconUser,
  },
  setup() {
    const collapsed: Ref<boolean> = ref(false);
    const toggleCollapse = () => {
      collapsed.value = !collapsed.value;
    };

    const handleMenuClick = (event: MouseEvent) => {
      event.stopPropagation();
    };

    return {
      collapsed,
      toggleCollapse,
      handleMenuClick,
    };
  },
});
</script>

<style>
.menu-demo {
  width: 100%;
  height: 600px;
  padding: 40px;
  box-sizing: border-box;
  background-color: var(--color-neutral-2);
}

.menu-demo .arco-menu {
  width: 200px;
  height: 100%;
  box-shadow: 0 0 1px rgba(0, 0, 0, 0.3);
}

.menu-demo .arco-menu :deep(.arco-menu-collapse-button) {
  width: 32px;
  height: 32px;
  border-radius: 50%;
}

.menu-demo
  .arco-menu:not(.arco-menu-collapsed)
  :deep(.arco-menu-collapse-button) {
  right: 0;
  bottom: 8px;
  transform: translateX(50%);
}

.menu-demo .arco-menu:not(.arco-menu-collapsed)::before {
  content: "";
  position: absolute;
  right: 0;
  bottom: 0;
  width: 48px;
  height: 48px;
  background-color: inherit;
  border-radius: 50%;
  box-shadow: -4px 0 2px var(--color-bg-2), 0 0 1px rgba(0, 0, 0, 0.3);
  transform: translateX(50%);
}

.menu-demo .arco-menu.arco-menu-collapsed {
  width: 48px;
  height: auto;
  padding-top: 24px;
  padding-bottom: 138px;
  border-radius: 22px;
}

.menu-demo .arco-menu.arco-menu-collapsed :deep(.arco-menu-collapse-button) {
  right: 8px;
  bottom: 8px;
}
</style>
