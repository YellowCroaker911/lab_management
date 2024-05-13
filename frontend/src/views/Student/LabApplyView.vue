/* 实验室借用申请 
列出自己所有填报过的实验室借用申请(申请状态有：未审核、通过、驳回、使用完毕)。
新增申请，填写新的申请，新申请的状态为“未审核”。
修改申请，对处于“未审核”状态的申请可以修改内容
申请的内容包括：申请学期、申请学生、申请周次、申请节次、
申请实验室编号(应根据学期、周次、节次检查是否可用)、申请原因、填报日期。
对于通过的申请，在使用完成后进行确认(将申请状态设置为“使用完毕”) */
<template>
  <div>
    <h2>实验室借用申请</h2>
    <a-button type="primary" @click="addApply">新增申请</a-button>
    <h3>已有的个人申请：</h3>
    <a-table id="apply" :columns="applyColumns" :data="applyData">
      <template #action="{ record }">
        <a-button @click="editApply(record)">修改申请</a-button>
      </template>
    </a-table>
    <a-modal
      title="新增申请"
      v-model:visible="addApplyModalVisible"
      @ok="handleAddApply"
      @cancel="cancelAddApply"
    >
      <a-form ref="addApplyForm" :model="newApply">
        <a-form-item
          label="申请周次"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="newApply.week" />
        </a-form-item>
        <a-form-item
          label="申请节次"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="newApply.session" />
        </a-form-item>
        <a-form-item
          label="实验室编号"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-select v-model="newApply.labId">
            <a-option value="001">软件实验室1</a-option>
            <a-option value="002">软件实验室2</a-option>
            <a-option value="003">硬件实验室1</a-option>
            <a-option value="004">硬件实验室1</a-option>
            <a-option value="005">网络实验室1</a-option>
          </a-select>
        </a-form-item>
        <a-form-item
          label="申请原因"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-textarea v-model="newApply.reason" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script>
import { onMounted, ref } from "vue";
import { BorrowControllerService } from "../../../generated";
import store from "@/store";

export default {
  name: "LabApplyView",
  setup() {
    let applyData = ref();
    const addApplyModalVisible = ref(false);
    const newApply = ref({
      studentId: store.state.user.loginUser.id,
      labId: "",
      reason: "",
      semester: "",
      week: "",
      session: "",
    });
    const getApply = async () => {
      try {
        const res = await BorrowControllerService.getBorrowById(
          store.state.user.loginUser.id,
          localStorage.getItem("token")
        );
        applyData.value = res.data;
        console.log("applys:", res);
      } catch (error) {
        console.error("Error fetching users:", error);
      }
    };
    const applyColumns = [
      {
        title: "申请学期",
        dataIndex: "applySemester",
      },
      {
        title: "申请学生",
        dataIndex: "applyStudent",
      },
      {
        title: "申请周次",
        dataIndex: "applyWeek",
      },
      {
        title: "申请节次",
        dataIndex: "applyClass",
      },
      {
        title: "申请实验室编号",
        dataIndex: "labNum",
      },
      {
        title: "申请原因",
        dataIndex: "applyReason",
      },
      {
        title: "填报日期",
        dataIndex: "applyDate",
      },
      {
        title: "申请状态",
        dataIndex: "applyState",
      },
      {
        title: "操作",
        dataIndex: "action",
        slotName: "action",
      },
    ];
    const addApply = () => {
      addApplyModalVisible.value = true;
      console.log(localStorage.getItem("token"));
    };
    const handleAddApply = async () => {
      try {
        await BorrowControllerService.importBorrow(
          newApply.value,
          localStorage.getItem("token")
        );
        console.log("newApply" + newApply.value);
        await getApply();
        addApplyModalVisible.value = false;
      } catch (error) {
        console.error("Error adding Apply:", error);
      }
    };

    const cancelAddApply = () => {
      addApplyModalVisible.value = false;
      resetAddApplyForm();
    };

    const resetAddApplyForm = () => {
      newApply.value.name = "";
      newApply.value.role = "";
      newApply.value.password = "";
    };
    const editApply = () => {
      return 1;
    };
    onMounted(() => {
      getApply();
    });
    return {
      applyColumns,
      applyData,
      addApplyModalVisible,
      newApply,
      addApply,
      handleAddApply,
      cancelAddApply,
      editApply,
    };
  },
};
</script>

<style scoped>
#apply {
  max-width: 1200px;
}
</style>
