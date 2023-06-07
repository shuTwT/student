export default {
  path: "/stu",
  name: "Student",
  meta: {
    title: "学生管理",
    showLink: true,
    rank: 0,
  },
  redirect: "/student",
  children: [
    {
      path: "/student",
      name: "StudentManage",
      component: () => import("@/views/student/studentManage/studentManage.vue"),
      meta: {
        title: "学生管理",
      },
    },
    {
      path: "/clazz",
      name: "Clazz",
      component: () => import("@/views/student/clazzManage/clazzManage.vue"),
      meta: {
        title: "班级管理",
      },
    },
    {
      path: "/course",
      name: "Course",
      component: () => import("@/views/student/courseManage/courseManage.vue"),
      meta: {
        title: "课程管理",
      },
    },
    {
      path: "/major",
      name: "Major",
      component: () => import("@/views/student/majorManage/majorManage.vue"),
      meta: {
        title: "专业管理",
      },
    },
    {
      path: "/score",
      name: "Score",
      component: () => import("@/views/student/scoreManage/scoreManage.vue"),
      meta: {
        title: "分数管理",
      },
    },
    {
      path: "/teacher",
      name: "Teacher",
      component: () => import("@/views/student/teacherManage/teacherManage.vue"),
      meta: {
        title: "教师管理",
      },
    },
  ],
} as routerConfig;
