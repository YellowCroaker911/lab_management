/* 实验排课  根据“教师”填报的“实验课申请登记”，将实验课安排到实验室。
根据“实验课申请登记”的“实验室类型”确定可以选择的实验室范围
根据“实验课申请登记”的“学生人数”和实验室设备数决定分配哪几间实验室
根据“实验课申请登记”的“起始周”、“结束周”和已经排课的情况避免实验室冲突
提示：可以创建一个“实验室排课”表来记录实验室排课的情况，该 表的主要数据包括：
排课学期：是哪个学期的课  实验室名称：  实验室编号：  周次：第几周
节次：第几节课  课程名：  任课教师：  学生班级： */
<template>
  <div>
    <h3>实验课申请</h3>
    <!-- 申请登记表格 -->
    <a-table :columns="experimentColumns" :data="experimentData">
      <template #action="{ record }">
        <a-button @click="arrangeExperiment(record)">安排实验课</a-button>
      </template>
    </a-table>

    <!-- 实验室排课表格 -->
    <h3>实验室排课表</h3>
    <a-table
      column-resizable
      :columns="labScheduleColumns"
      :data="labScheduleData"
    ></a-table>
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  name: "ExperimentScheduling",
  setup() {
    // 实验课申请登记数据
    const experimentData = ref([]);

    // 实验室排课数据
    const labScheduleData = ref([]);

    // 实验课申请登记表格列配置
    const experimentColumns = [
      {
        title: "排课学期",
        dataIndex: "courseSem",
      },
      {
        title: "实验室名称",
        dataIndex: "labName",
      },
      {
        title: "实验室编号",
        dataIndex: "labId",
      },
      {
        title: "周次",
        dataIndex: "week",
      },
      {
        title: "节次",
        dataIndex: "course",
      },
      {
        title: "课程名",
        dataIndex: "startWeek",
      },
      {
        title: "任课教师",
        dataIndex: "endWeek",
      },
      {
        title: "学生班级",
        dataIndex: "stuClass",
      },
      {
        title: "操作",
        dataIndex: "action",
        slots: { customRender: "action" },
      },
    ];

    // 实验室排课表格列配置
    const labScheduleColumns = [
      {
        title: "排课学期",
        dataIndex: "semester",
      },
      {
        title: "实验室名称",
        dataIndex: "labName",
      },
      {
        title: "实验室编号",
        dataIndex: "labId",
      },
      {
        title: "周次",
        dataIndex: "week",
      },
      {
        title: "节次",
        dataIndex: "section",
      },
      {
        title: "课程名",
        dataIndex: "courseName",
      },
      {
        title: "任课教师",
        dataIndex: "teacher",
      },
      {
        title: "学生班级",
        dataIndex: "className",
      },
    ];

    // 安排实验课
    const arrangeExperiment = (record) => {
      // 根据实验室类型选择实验室
      // 根据学生人数和实验室设备数确定分配的实验室
      // 根据起始周、结束周和已排课情况避免实验室冲突
      // 更新实验室排课数据
      labScheduleData.value.push({
        semester: "2024春季学期",
        labName: record.labType,
        labId: "Lab001",
        week: `${record.startWeek}-${record.endWeek}`,
        section: "第一节",
        courseName: record.courseName,
        teacher: record.teacher,
        className: "实验班",
      });
    };

    return {
      experimentData,
      experimentColumns,
      labScheduleData,
      labScheduleColumns,
      arrangeExperiment,
    };
  },
};
</script>

<style scoped></style>
