/* 平台用户管理  实验员的增加、删除、修改、重置密码 
教师的增加、删除、修改、重置密码  学生的增加、删除、修改、重置密码
每一类用户的管理需要列出全部该类用户；提供按姓名查询的功能。
扩展功能：用户的批量导入。*/

<template>
  <div>
    <h2>平台用户管理</h2>
    <a-input
      placeholder="请输入姓名"
      v-model="searchName"
      style="width: 200px; margin-bottom: 10px"
    />
    <a-button @click="searchUser">搜索</a-button>
    <a-button @click="resetSearch">重置</a-button>
    <a-button type="primary" @click="showAddUserModal">新增用户</a-button>
    <h3>批量导入：(传入格式正确的Excel文档)</h3>
    <a-space direction="vertical" :style="{ width: '100%' }">
      <a-upload action="/" />
    </a-space>
    <a-modal
      title="新增用户"
      column-resizable
      v-model:visible="addUserModalVisible"
      @ok="handleAddUser"
      @cancel="cancelAddUser"
    >
      <a-form ref="addUserForm" :model="newUser">
        <a-form-item
          label="用户名"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="newUser.username" />
        </a-form-item>
        <a-form-item
          label="角色"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-select v-model="newUser.role" style="width: 100%">
            <a-option value="0">管理员</a-option>
            <a-option value="3">实验员</a-option>
            <a-option value="2">教师</a-option>
            <a-option value="1">学生</a-option>
          </a-select>
        </a-form-item>
        <a-form-item
          label="密码"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="newUser.password" />
        </a-form-item>
        <a-form-item
          label="姓名"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="newUser.name" />
        </a-form-item>
        <a-form-item
          label="专业"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="newUser.major" />
        </a-form-item>
        <a-form-item
          label="班级"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="newUser.clazz" />
        </a-form-item>
        <a-form-item
          label="职称"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="newUser.title" />
        </a-form-item>
      </a-form>
    </a-modal>
    <a-table
      :columns="userColumns"
      :data="userData"
      column-resizable
      @change="handleChange"
    >
      <template
        #name-filter="{
          filterValue,
          setFilterValue,
          handleFilterConfirm,
          handleFilterReset,
        }"
      >
        <div class="custom-filter">
          <a-space direction="vertical">
            <a-input
              :model-value="filterValue[0]"
              @input="(value) => setFilterValue([value])"
            />
            <div class="custom-filter-footer">
              <a-button @click="handleFilterConfirm">Confirm</a-button>
              <a-button @click="handleFilterReset">Reset</a-button>
            </div>
          </a-space>
        </div>
      </template>
      <template #action="{ record }">
        <a-button id="action" status="normal" @click="editUser(record)"
          >修改用户
        </a-button>
        <a-button id="action" status="danger" @click="deleteUser(record)"
          >删除用户
        </a-button>
      </template>
    </a-table>
    <a-modal
      title="修改用户"
      v-model:visible="editUserModalVisible"
      @ok="handleEditUser"
      @cancel="canceleditUser"
    >
      <a-form ref="addUserForm" :model="newUser">
        <a-form-item
          label="用户名"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="newUser.username" />
        </a-form-item>
        <a-form-item
          label="角色"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-select v-model="newUser.role" style="width: 100%">
            <a-option value="0">管理员</a-option>
            <a-option value="3">实验员</a-option>
            <a-option value="2">教师</a-option>
            <a-option value="1">学生</a-option>
          </a-select>
        </a-form-item>
        <a-form-item
          label="密码"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="newUser.password" />
        </a-form-item>
        <a-form-item
          label="姓名"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="newUser.name" />
        </a-form-item>
        <a-form-item
          label="专业"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="newUser.major" />
        </a-form-item>
        <a-form-item
          label="班级"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="newUser.clazz" />
        </a-form-item>
        <a-form-item
          label="职称"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 18 }"
        >
          <a-input v-model="newUser.title" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script>
import { h, onMounted, ref } from "vue";
import { UserControllerService } from "../../../generated";
import { IconSearch } from "@arco-design/web-vue/es/icon";

export default {
  name: "UserAdmin",

  setup() {
    let userData = ref([]);
    const addUserModalVisible = ref(false);
    let editUserModalVisible = ref(false);
    const newUser = ref({
      username: "",
      password: "",
      role: "",
      name: "",
      major: "",
      clazz: "",
      title: "",
    });
    let searchName = ref("");

    const getUser = async () => {
      try {
        const res = await UserControllerService.getAllUsers(
          localStorage.getItem("token")
        );
        userData.value = res.data;
        //console.log("allUsers:", res);
      } catch (error) {
        console.error("Error fetching users:", error);
      }
    };

    const searchUser = async () => {
      try {
        const res = await UserControllerService.getUsersByNamePrefix(
          searchName.value,
          localStorage.getItem("token")
        );
        userData.value = res.data ? [res.data] : [];
        console.log("userData" + userData.value);
      } catch (error) {
        console.error("Error searching user:", error);
      }
    };

    const resetSearch = () => {
      searchName.value = "";
      getUser();
    };

    const userColumns = [
      {
        title: "账号ID",
        dataIndex: "id",
        key: "id",
        sortable: {
          sortDirections: ["ascend", "descend"],
        },
      },
      {
        title: "用户名",
        dataIndex: "username",
        key: "username",
        sortable: {
          sortDirections: ["ascend", "descend"],
        },
        filterable: {
          filter: (value, record) => record.username.includes(value),
          slotName: "name-filter",
          icon: () => h(IconSearch),
        },
      },
      {
        title: "角色",
        dataIndex: "role",
        key: "role",
        filterable: {
          filters: [
            {
              text: "管理员",
              value: 0,
            },
            {
              text: "学生",
              value: 1,
            },
            {
              text: "教师",
              value: 2,
            },
            {
              text: "实验员",
              value: 3,
            },
          ],
          filter: (value, row) => row.role.toString().includes(value),
        },
      },
      {
        title: "操作",
        key: "action",
        slotName: "action",
      },
    ];
    const handleChange = (data, extra, currentDataSource) => {
      console.log("change", data, extra, currentDataSource);
    };
    const handleAddUser = async () => {
      try {
        await UserControllerService.importUser(
          newUser.value,
          localStorage.getItem("token")
        );
        console.log("newUser" + newUser.value);
        await getUser();
        addUserModalVisible.value = false;
      } catch (error) {
        console.error("Error adding user:", error);
      }
    };

    const cancelAddUser = () => {
      addUserModalVisible.value = false;
      resetAddUserForm();
    };

    const resetAddUserForm = () => {
      newUser.value.name = "";
      newUser.value.role = "";
      newUser.value.password = "";
    };
    const editUser = async (record) => {
      editUserModalVisible.value = true;
      newUser.value = record;
      console.log(editUserModalVisible);
      console.log(newUser.value);
    };
    const deleteUser = async (userId) => {
      try {
        await UserControllerService.removeUser(
          userId,
          localStorage.getItem("token")
        );
        await getUser();
      } catch (error) {
        console.error("Error deleting user:", error);
      }
    };

    const resetPassword = async (userId) => {
      try {
        //todo ：alter方法要修
        await UserControllerService.alterPassword(
          userId,
          localStorage.getItem("token")
        );
        await getUser();
      } catch (error) {
        console.error("Error resetting password:", error);
      }
    };

    const showAddUserModal = () => {
      addUserModalVisible.value = true;
    };
    const getToken = () => {
      console.log(localStorage.getItem("token"));
    };
    onMounted(() => {
      getUser();
      getToken();
    });

    return {
      userData,
      userColumns,
      searchName,
      searchUser,
      resetSearch,
      handleChange,
      addUserModalVisible,
      editUserModalVisible,
      newUser,
      editUser,
      handleAddUser,
      cancelAddUser,
      deleteUser,
      resetPassword,
      showAddUserModal,
    };
  },
};
</script>

<style scoped>
.custom-filter {
  padding: 20px;
  background: var(--color-bg-5);
  border: 1px solid var(--color-neutral-3);
  border-radius: var(--border-radius-medium);
  box-shadow: 0 2px 5px rgb(0 0 0 / 10%);
}

.custom-filter-footer {
  display: flex;
  justify-content: space-between;
}
</style>
