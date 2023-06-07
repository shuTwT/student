export default {
  path: "/system",
  name: "System",
  meta: {
    title: "系统管理",
    roles:['admin']
  },
  redirect: "/system/dict",
  children: [
    {
      path: "/system/dept",
      name: "Dept",
      component: () => import("@/views/system/dept/dept.vue"),
      meta: {
        title: "部门管理",
        roles:["admin"]
      },
    },
    {
      path: "/system/dict",
      name: "Dict",
      component: () => import("@/views/system/dict/dict.vue"),
      meta: {
        title: "字典管理",
        roles:["admin"]
      },
    },
    {
      path: "/system/menu",
      name: "Menu",
      component: () => import("@/views/system/menu/menu.vue"),
      meta: {
        title: "菜单管理",
        roles:["admin"]
      },
    },
    {
      path: "/system/role",
      name: "Role",
      component: () => import("@/views/system/role/role.vue"),
      meta: {
        title: "角色管理",
        roles:["admin"]
      },
    },
    {
      path: "/system/user",
      name: "User",
      component: () => import("@/views/system/user/user.vue"),
      meta: {
        title: "用户管理",
        roles:["admin"]
      },
    },
    {
        path:"/system/log",
        name:"log",
        component:()=>import("@/views/system/log/log.vue"),
        meta:{
            title:"系统日志",
            roles:["admin"]
        }
    }
  ],
} as routerConfig;
