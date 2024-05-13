/* 3.2 教师的功能 (1) 实验课申请登记 
列出当前学期的自己的所有申请(申请状态有：已排课、未排课) 
新增申请，填写新的申请，新申请的状态为“未排课” 
修改申请，对处于“未排课”状态的申请可以修改内容 
申请内容包括：上课学期(默认当前学期)、申请教师(默认登录教师)、课程名称、需要的实验室类型、学生班级、学生人数、起始周、结束周、节次。
(2) 实验室设备报修  列出教师的所有设备报修(报修状态有：已维修、未维修、维修中)
 新增报修，填写新的报修，新报修的状态为“未维修” 
报修的内容包括：报修教师、实验室编号、故障描述(文本)、报修日期 */
<template>
  <a-layout>
    <a-layout-sider height="auto" width="auto">
      <div class="menu-demo">
        <a-menu
          class="menu"
          mode="pop"
          show-collapse-button
          theme="dark"
          :default-selected-keys="['/teacher/course']"
          @click="handleMenuClick"
        >
          <a-menu-item key="/teacher/course">
            <template #icon>
              <icon-apps></icon-apps>
            </template>
            <router-link to="/teacher/course">实验课申请</router-link>
          </a-menu-item>
          <a-menu-item key="/teacher/maintain">
            <template #icon>
              <icon-at></icon-at>
            </template>
            <router-link to="/teacher/maintain">设备报修</router-link>
          </a-menu-item>
        </a-menu>
      </div>
    </a-layout-sider>
    <a-layout-content :style="{ marginLeft: '100px', padding: '24px' }">
      <h1>教师页面</h1>
      <router-view></router-view>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import { defineComponent, ref, Ref } from "vue";
import { IconApps, IconAt } from "@arco-design/web-vue/es/icon";

export default defineComponent({
  name: "TeacherView",
  components: {
    IconApps,
    IconAt,
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
