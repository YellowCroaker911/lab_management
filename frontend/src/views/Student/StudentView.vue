/* 实验室借用申请 
列出自己所有填报过的实验室借用申请(申请状态有：未审核、通过、驳回、使用完毕)。 
新增申请，填写新的申请，新申请的状态为“未审核”。 
修改申请，对处于“未审核”状态的申请可以修改内容 
申请的内容包括：申请学期、申请学生、申请周次、申请节次、申
请实验室编号(应根据学期、周次、节次检查是否可用)、申请原因、填报日期。 
对于通过的申请，在使用完成后进行确认(将申请状态设置为“使用完毕”) */
<template>
  <a-layout>
    <a-layout-sider height="auto" width="auto">
      <div class="menu-demo">
        <a-menu
          class="menu"
          mode="pop"
          show-collapse-button
          theme="dark"
          :default-selected-keys="['/student/apply']"
          @click="handleMenuClick"
        >
          <a-menu-item key="/student/apply">
            <template #icon>
              <icon-apps></icon-apps>
            </template>
            <router-link to="/student/apply">实验室借用申请</router-link>
          </a-menu-item>
          <a-menu-item key="/student/practice">
            <template #icon>
              <icon-user></icon-user>
            </template>
            <router-link to="/student/practice">练习天地</router-link>
          </a-menu-item>
        </a-menu>
      </div>
    </a-layout-sider>
    <a-layout-content :style="{ marginLeft: '100px', padding: '24px' }">
      <h1>学生界面</h1>
      <router-view></router-view>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import { defineComponent, ref, Ref } from "vue";
import { IconApps, IconUser } from "@arco-design/web-vue/es/icon";

export default defineComponent({
  name: "StudentView",
  components: {
    IconApps,
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
