/* 设备报修处理  列出属于自己管理的实验室的报修
开始维修时，把报修申请的状态设置为“维修中”
维修结束后，把报修申请的状态设置为“已维修”，并填写维修情况 说明 */
<template>
  <div>
    <h3>设备报修处理：</h3>
    <a-table
      :columns="maintainColumns"
      :data-source="maintainData"
      row-key="id"
      bordered
    >
      <template #action="{ record }">
        <a-button type="primary" @click="startMaintenance(record)"
          >开始维修
        </a-button>
        <a-button type="danger" @click="finishMaintenance(record)"
          >维修完成
        </a-button>
      </template>
    </a-table>
    <h4>负责实验室信息：</h4>
    <a-table :columns="labColumns" :data="labData" />
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import {
  LabControllerService,
  MaintainControllerService,
} from "../../../generated";
import store from "@/store";

export default {
  name: "FixMaintain",
  setup() {
    const maintainColumns = [
      {
        title: "报修设备",
        dataIndex: "equipment",
      },
      {
        title: "报修人",
        dataIndex: "applicant",
      },
      {
        title: "报修时间",
        dataIndex: "applyTime",
      },
      {
        title: "维修状态",
        dataIndex: "status",
      },
      {
        title: "操作",
        dataIndex: "action",
        slots: { customRender: "action" },
      },
    ];
    const maintainData = ref([]);

    const labColumns = [
      {
        title: "实验室编号",
        dataIndex: "number",
      },
      {
        title: "实验室名称",
        dataIndex: "name",
      },
      {
        title: "实验室类型",
        dataIndex: "type",
      },
      {
        title: "实验室设备数量",
        dataIndex: "equipmentNum",
      },
    ];
    const labData = ref([]);
    let isFetched = false;
    const getDatas = async () => {
      if (isFetched) return;
      try {
        const res1 = await MaintainControllerService.getMaintainsByLabAdminId(
          store.state.user.loginUser.id,
          localStorage.getItem("token")
        );
        const res2 = await LabControllerService.getLabsByLabAdminId(
          store.state.user.loginUser.id,
          localStorage.getItem("token")
        );
        maintainData.value = res1.data || [];
        labData.value = res2.data || [];
        isFetched = true;
        //console.log("id" + store.state.loginUser.id);
        console.log(labData.value);
      } catch (error) {
        console.error("Error:", error);
      }
    };

    const startMaintenance = async (record) => {
      try {
        await MaintainControllerService.startMaintenance(record.id);
        record.status = "维修中";
      } catch (error) {
        console.error("Error:", error);
      }
    };

    const finishMaintenance = async (record) => {
      try {
        await MaintainControllerService.finishMaintenance(
          record.id,
          "维修完成，设备正常"
        );
        record.status = "已维修";
      } catch (error) {
        console.error("Error:", error);
      }
    };

    onMounted(getDatas);

    return {
      maintainColumns,
      maintainData,
      labColumns,
      labData,
      startMaintenance,
      finishMaintenance,
    };
  },
};
</script>

<style scoped></style>
