/* 实验室设备报修  列出教师的所有设备报修(报修状态有：已维修、未维修、维修中) 
新增报修，填写新的报修，新报修的状态为“未维修” 
报修的内容包括：报修教师、实验室编号、故障描述(文本)、报修日 期 */
<template>
  <span>已有报修项目</span>
  <a-button>新增报修</a-button>
  <a-table></a-table>
</template>

<script>
import { ref } from "vue";
import store from "@/store";
import { MaintainControllerService } from "../../../generated";

const applyColumns = [
  {
    title: "账号ID",
    dataIndex: "id",
    key: "id",
  },
  {
    title: "用户名",
    dataIndex: "username",
    key: "username",
  },
  {
    title: "角色",
    dataIndex: "role",
    key: "role",
  },
  {
    title: "操作",
    key: "action",
    slots: { customRender: "action" },
  },
];
let applyData = ref([]);

export default {
  name: "MaintainView",
  setup() {
    const getMaintain = async () => {
      try {
        const res = await MaintainControllerService.getMaintainsByTeacherId(
          store.state.user.loginUser.id
        );
      } catch (e) {
        console.log(e);
      }
    };
  },
};
</script>

<style scoped></style>
