import { RouteRecordRaw } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import NoAuthView from "@/views/NoAuthView.vue";
import ACCESS_ENUM from "@/access/accessEnum";
import AdminView from "@/views/AdminView.vue";
import LoginView from "@/views/LoginView.vue";
import UserLayout from "@/layouts/UserLayout.vue";
import SemesterAdmin from "@/views/Admin/SemesterAdmin.vue";
import ClassAdmin from "@/views/Admin/ClassAdmin.vue";
import LabAdmin from "@/views/Admin/LabAdmin.vue";
import UserAdmin from "@/views/Admin/UserAdmin.vue";
import ExercisorView from "@/views/Exercisor/ExercisorView.vue";
import FixMaintain from "@/views/Exercisor/FixMaintain.vue";
import AboutView from "@/views/AboutView.vue";
import CourseApply from "@/views/Teacher/CourseApply.vue";
import Maintain from "@/views/Teacher/Maintain.vue";
import LabApplyView from "@/views/Student/LabApplyView.vue";
import PracticeView from "@/views/Student/PracticeView.vue";
import TeacherView from "@/views/Teacher/TeacherView.vue";
import StudentView from "@/views/Student/StudentView.vue";

export const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "首页",
    component: HomeView,
  },
  {
    path: "/user",
    name: "用户",
    component: UserLayout,
    children: [
      {
        path: "/user/login",
        name: "登录页面",
        component: LoginView,
      },
    ],
    meta: {
      hideInMenu: true,
    },
  },

  {
    path: "/noAuth",
    name: "无权限",
    component: NoAuthView,
    meta: {
      hideInMenu: true,
    },
  },

  {
    path: "/admin",
    name: "管理员页面",
    component: AdminView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
    children: [
      {
        path: "/admin/semester",
        name: "学期管理页面",
        component: SemesterAdmin,
        meta: {
          access: ACCESS_ENUM.ADMIN,
        },
      },
      {
        path: "/admin/class",
        name: "课程管理页面",
        component: ClassAdmin,
        meta: {
          access: ACCESS_ENUM.ADMIN,
        },
      },
      {
        path: "/admin/lab",
        name: "实验室管理页面",
        component: LabAdmin,
        meta: {
          access: ACCESS_ENUM.ADMIN,
        },
      },
      {
        path: "/admin/user",
        name: "用户管理页面",
        component: UserAdmin,
        meta: {
          access: ACCESS_ENUM.ADMIN,
        },
      },
    ],
  },

  {
    path: "/exerciser",
    name: "实验员界面",
    component: ExercisorView,
    meta: {
      access: ACCESS_ENUM.EXECERCISOR,
    },
    children: [
      {
        path: "/exerciser/fixmaintain",
        name: "设备报修处理",
        component: FixMaintain,
        meta: {
          access: ACCESS_ENUM.EXECERCISOR,
        },
      },
    ],
  },

  {
    path: "/teacher",
    name: "教师界面",
    component: TeacherView,
    meta: {
      access: ACCESS_ENUM.TEACHER,
    },
    children: [
      {
        path: "/teacher/course",
        name: "课程申请",
        component: CourseApply,
        meta: {
          access: ACCESS_ENUM.TEACHER,
        },
      },
      {
        path: "/teacher/maintain",
        name: "设备报修",
        component: Maintain,
        meta: {
          access: ACCESS_ENUM.TEACHER,
        },
      },
    ],
  },

  {
    path: "/student",
    name: "学生界面",
    component: StudentView,
    meta: {
      access: ACCESS_ENUM.STUDENT,
    },
    children: [
      {
        path: "/student/apply",
        name: "实验室申请借用",
        component: LabApplyView,
        meta: {
          access: ACCESS_ENUM.STUDENT,
        },
      },
      {
        path: "/student/practice",
        name: "练习天地",
        component: PracticeView,
        meta: {
          access: ACCESS_ENUM.STUDENT,
        },
      },
    ],
  },

  {
    path: "/about",
    name: "关于",
    component: AboutView,
  },
];
