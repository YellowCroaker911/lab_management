<template>
  <div>
    <h2>学期管理</h2>
    <a-button type="primary" @click="addSem">
      <template #icon>
        <IconApps />
      </template>
      <template #default>添加学期</template>
    </a-button>
    <a-table column-resizable :columns="semColumns" :data="semData">
      <template #action="{ record }">
        <a-popconfirm
          content="确定将本学期设置为当前学期吗?"
          @ok="setSem(record)"
        >
          <a-button id="action" status="success"> 设置为当前学期 </a-button>
        </a-popconfirm>
        <a-button id="action" status="normal" @click="editSem(record)"
          >修改学期
        </a-button>
        <a-button id="action" status="danger" @click="deleteSem(record)"
          >删除学期
        </a-button>
      </template>
    </a-table>

    <a-modal
      title="添加新学期"
      v-model:visible="addSemModalVisible"
      @ok="handleAddSem"
      @cancel="cancelAddSem"
    >
      <a-form ref="addSemForm" :model="newSemester">
        <a-form-item
          label="学期"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="newSemester.semester" />
        </a-form-item>
        <a-form-item
          label="教学周数"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input-number v-model="newSemester.week" :min="17" :max="20" />
        </a-form-item>
      </a-form>
    </a-modal>
    <a-modal
      title="修改学期"
      v-model:visible="editSemModalVisible"
      @ok="handleEditSem"
      @cancel="cancelEditSem"
    >
      <a-form ref="editSemForm" :model="editSemester">
        <a-form-item
          label="学期"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="editSemester.semester" />
        </a-form-item>
        <a-form-item
          label="教学周数"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="editSemester.week" :min="17" :max="20" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script>
import { onMounted, ref } from "vue";
import { SemesterControllerService } from "../../../generated";
import { IconApps } from "@arco-design/web-vue/es/icon";

export default {
  name: "SemesterAdmin",

  setup() {
    let semData = ref([]);
    const addSemModalVisible = ref(false);
    const editSemModalVisible = ref(false);

    const newSemester = ref({
      semester: "",
      week: 0,
    });

    const editSemester = ref({
      semester: "",
      week: 0,
      status: 0,
    });

    const getSem = async () => {
      try {
        const res = await SemesterControllerService.getAllSemesters(
          localStorage.getItem("token")
        );
        semData.value = res.data;
        console.log("allSem:", res);
      } catch (error) {
        console.error("Error fetching semesters:", error);
      }
    };

    const semColumns = [
      {
        title: "学期",
        dataIndex: "semester",
        key: "semester",
      },
      {
        title: "教学周数",
        dataIndex: "week",
        key: "week",
      },
      {
        title: "是否为当前学期",
        dataIndex: "status",
        key: "status",
      },
      {
        title: "操作",
        slotName: "action",
      },
    ];
    const addSem = () => {
      addSemModalVisible.value = true;
    };

    const handleAddSem = async () => {
      try {
        const res = await SemesterControllerService.importSemester(
          newSemester.value,
          localStorage.getItem("token")
        );
        if (res.status === 100) {
          alert("设置成功!");
        } else {
          alert(res.description);
        }
        await getSem();
        addSemModalVisible.value = false;
      } catch (error) {
        console.error("Error adding semester:", error);
      }
    };

    const cancelAddSem = () => {
      addSemModalVisible.value = false;
      resetAddSemForm();
    };

    const resetAddSemForm = () => {
      newSemester.value.semester = "";
      newSemester.value.week = 0;
    };
    const setSem = (record) => {
      record.status = 1;
      editSemester.value = record;
      console.log(record);
      handleEditSem();
      editSemester.value = "";
    };
    const editSem = (record) => {
      editSemester.value = record;
      editSemModalVisible.value = true;
      console.log(editSemester.value);
    };

    const handleEditSem = async () => {
      try {
        const res = await SemesterControllerService.alterSemester(
          editSemester.value,
          localStorage.getItem("token")
        );
        if (res.status === 100) {
          alert("修改成功!");
        } else {
          alert(res.description);
        }
        await getSem();
        editSemModalVisible.value = false;
      } catch (error) {
        console.error("Error editing semester:", error);
      }
    };

    const cancelEditSem = () => {
      editSemModalVisible.value = false;
      editSemester.value.semester = "";
      editSemester.value.week = 0;
    };

    const deleteSem = async (record) => {
      try {
        const res = await SemesterControllerService.removeSemester(
          record.id,
          localStorage.getItem("token")
        );
        if (res.status === 100) {
          alert("删除成功!");
        } else {
          alert(res.description);
        }
        await getSem();
      } catch (error) {
        console.error("Error deleting semester:", error);
      }
    };

    const handleSetCurrentSem = async (record) => {
      // 实现设置为当前学期操作
      console.log("Set Current Semester:", record);
    };

    onMounted(() => {
      getSem();
    });

    return {
      IconApps,
      semData,
      semColumns,
      addSem,
      setSem,
      editSem,
      addSemModalVisible,
      editSemModalVisible,
      newSemester,
      editSemester,
      handleAddSem,
      handleEditSem,
      cancelEditSem,
      deleteSem,
      handleSetCurrentSem,
      cancelAddSem,
    };
  },
};
</script>

<style scoped>
a-table {
  max-width: 1700px;
}

#action {
  margin-left: 10px;
  margin-right: 10px;
}
</style>
