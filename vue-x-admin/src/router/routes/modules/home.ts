import Home from "@/layout/index.vue";
export default {
  path: "/",
  name: "Home",
  component: Home,
  meta: {
    title: "首页",
    showLink: true,
    rank: 0,
  },
  redirect: "/welcome",
  children: [
    {
      path: "/welcome",
      name: "Welcome",
      component: () => import("@/views/home/welcome/welcome.vue"),
      meta: {
        title: "首页",
      },
    },
    {
      path: "/dashboard",
      name: "Dashboard",
      component: () => import("@/views/home/dashboard/dashboard.vue"),
      meta: {
        title: "看板",
      },
    },
    {
      path: "/workbench",
      name: "Workbench",
      component: () => import("@/views/home/workbench/workbench.vue"),
      meta: {
        title: "工作台",
      },
    },
  ],
} as routerConfig;
