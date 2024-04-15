<template>
  <div class="login-container">
    <el-card class="login-card">
      <h2 class="login-title"><EditPen style="width: 1em; height: 1em; margin-right: 8px" /> 欢迎来到计算机实验室管理系统</h2>
      <el-form :model="loginForm" ref="loginForm" :rules="loginRules" label-width="0px" class="login-form">
        <el-form-item prop="username">
		    <Edit style="width: 1em; height: 1em; margin-right: 8px" />
            <el-input v-model="loginForm.username" placeholder="用户名" clearable prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <el-form-item prop="password">
			<Lock style="width: 1em; height: 1em; margin-right: 8px" />
          <el-input v-model="loginForm.password" placeholder="密码" type="password" clearable prefix-icon="el-icon-lock"></el-input>
        </el-form-item>
        <el-form-item prop="identity">
          <el-radio-group class="chooseIdentity" v-model="loginForm.identity" @change="handleChange()" border>
            <el-radio value="admin" border>系统管理员</el-radio>
            <el-radio value="experimenter" border>实验员</el-radio>
            <el-radio value="teacher" border>教师</el-radio>
            <el-radio value="student" border>学生</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login" :loading="loginLoading"><i class="el-icon-login"></i> 登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script >
import { ElMessage, ElForm, ElFormItem, ElInput, ElButton, ElCard, ElRadio, ElRadioGroup } from 'element-plus';
import { ref } from 'vue';
import router from '../router';
import axios from 'axios';

export default {
  name: "Login",
  components: {
    ElForm,
    ElFormItem,
    ElInput,
    ElButton,
    ElCard,
    ElRadio,
    ElRadioGroup
  },
  setup() {
    const loginForm = ref({
      username: '',
      password: '',
      identity: 'student' // 默认选择身份为学生
    });

    const loginRules = {
      username: [
        { required: true, message: '请输入用户名', trigger: 'blur' }
      ],
      password: [
        { required: true, message: '请输入密码', trigger: 'blur' }
      ],
      identity: [
        { required: true, message: '请选择身份', trigger: 'change' }
      ]
    };

    const loginLoading = ref(false);

	const handleChange = () => {
	      // 处理身份选择变化的逻辑
		  
	    };
	
    const login = () => {
      // 这里模拟登录过程，实际场景中需要发送请求到后端验证
	  // axios.post()
	  
      loginLoading.value = true;
      setTimeout(() => {
        // 登录成功
        ElMessage.success('登录成功');
        router.push('/home');
        loginLoading.value = false;
      }, 500);
    };
	

    return { loginForm, loginRules, loginLoading, login, handleChange, };
  },
  
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login-card {
  width: 400px;
  border-radius: 10px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.login-title {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  margin: 20px 0;
}

.login-form {
  padding: 0 30px 20px;
}

/* 自定义radio样式 */
.el-radio__input.is-focus .el-radio__inner {
  border-color: #409EFF;
  box-shadow: none;
}

.el-radio__input.is-checked .el-radio__inner {
  background-color: #409EFF;
  border-color: #409EFF;
}

.el-radio__input.is-checked .el-radio__inner::after {
  background-color: #fff;
}

.el-radio__input.is-disabled .el-radio__inner {
  background-color: #f5f7fa;
  border-color: #d1dbe5;
  cursor: not-allowed;
}

.el-radio__input.is-disabled .el-radio__inner::after {
  background-color: #d1dbe5;
}

/* 调整输入框样式 */
.el-input__inner {
  border-radius: 20px;
  padding-left: 40px;
}

.chooseIdentity {
	
}
</style>
