<template>
  <div class="home">
	
    <el-container>
		<el-header>
			<el-button @click="logout">退出登录</el-button>
		</el-header>
      <!-- 侧边栏 -->
      <el-aside width="200px" class="sidebar">
        <el-menu default-active="1" class="el-menu-vertical-demo">
          <el-menu-item index="1">实验室课程表</el-menu-item>
          <!-- 在这里添加其他菜单项 -->
        </el-menu>
      </el-aside>

      <!-- 内容区域 -->
      <el-container>
        <el-header class="header">计算机系实验室管理平台</el-header>
        <el-main class="main">
          <!-- 实验室课程表 -->
          <div v-show="activeIndex === '1'">
            <h2 class="table-title">实验室课程表</h2>
            <el-table :data="tableData" border class="custom-table">
              <el-table-column prop="week" label="周次"></el-table-column>
              <el-table-column prop="time" label="时间"></el-table-column>
              <el-table-column prop="course" label="课程"></el-table-column>
              <el-table-column prop="teacher" label="教师"></el-table-column>
              <el-table-column prop="class" label="班级"></el-table-column>
              <el-table-column prop="lab" label="实验室"></el-table-column>
            </el-table>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>




<script>
import element from 'element-plus'
import router from '../router';


export default {
  data() {
    return {
      activeIndex: '1', // 默认选中的菜单项
      tableData: [ // 示例数据
        { week: '1', time: '1-2', course: '计算机组成原理', teacher: '张三', class: '计算机科学与技术1班', lab: '532' },
        { week: '2', time: '3-5', course: '数据库原理', teacher: '李四', class: '计算机科学与技术2班', lab: '533' },
        // 添加更多课程数据
      ]
    };
  },
  methods: {
	  logout(){
		  router.push('/')
	  }
  }
};
</script>

<style scoped>
.home {
  height: 100vh;
}

.sidebar {
  background-color: #f0f2f5;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.header {
  text-align: right;
  font-size: 18px;
  color: #333;
  padding: 10px;
}

.main {
  background-color: #fff;
}

.table-title {
  font-size: 24px;
  color: #333;
  margin-bottom: 20px;
}

.custom-table {
  font-size: 14px;
}

.custom-table th {
  background-color: #f5f7fa;
  color: #333;
}

.custom-table td {
  color: #666;
}
</style>
