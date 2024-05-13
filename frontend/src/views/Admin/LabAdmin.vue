/* 实验室借用审批  查看“学生”填报的“实验室借用申请” 
审批“实验室借用申请”，通过或不通过*/
<template>
  <h2>实验室借用审批</h2>
  <a-table
    id="labTable"
    column-resizable
    :bordered="{ cell: true }"
    :columns="columns"
    :data="dataSource"
  >
    <template #action="{ record }">
      <a-button type="primary" @click="approve(record)">通过</a-button>
      <a-button type="warning" @click="reject(record)">不通过</a-button>
    </template>
  </a-table>
</template>

<script>
import { onMounted, ref } from "vue";
import async from "async";
import { BorrowControllerService } from "../../../generated";

export default {
  name: "LabAdmin",
  setup() {
    const columns = [
      {
        title: "申请学期",
        dataIndex: "semester",
      },
      {
        title: "申请学生",
        dataIndex: "studentId",
      },
      {
        title: "申请周次",
        dataIndex: "startingWeek",
      },
      {
        title: "申请节次",
        dataIndex: "session",
      },
      {
        title: "申请实验室编号",
        dataIndex: "labId",
      },
      {
        title: "申请原因",
        dataIndex: "reason",
      },
      {
        title: "填报日期",
        dataIndex: "updateTime",
      },
      {
        title: "申请状态",
        dataIndex: "status",
      },
      {
        title: "审批操作",
        dataIndex: "action",
        key: "action",
        slotName: "action",
      },
    ];
    const dataSource = ref();
    const getBorrow = async () => {
      try {
        const res = await BorrowControllerService.getAllBorrows(
          localStorage.getItem("token")
        );
        dataSource.value = res.data;
        console.log("allBorrow:" + dataSource.value);
      } catch (e) {
        console.log(e);
      }
    };
    const approve = (record) => {
      return 1;
    };
    const reject = (record) => {
      // 这里可以编写不通过操作的逻辑
    };
    onMounted(getBorrow);
    return {
      columns,
      dataSource,
      approve,
    };
  },
};
</script>

<style scoped>
#labTable {
  max-width: 1700px;
}
</style>
