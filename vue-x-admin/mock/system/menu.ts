import { MockMethod } from "vite-plugin-mock";
const systemRouter = {
  path: "/system",
  meta: {
    title: "系统管理",
    rank: 12,
  },
  children: [
    {
      path: "/system/user",
      name: "User",
      meta: {
        title: "用户管理",
        roles: ["admin"],
      },
    },
    {
      path: "/system/role",
      name: "User",
      meta: {
        title: "角色管理",
        roles: ["admin"],
      },
    },
    {
      path: "/system/menu",
      name: "User",
      meta: {
        title: "菜单管理",
        roles: ["admin"],
      },
    },
    {
      path: "/system/dict",
      name: "User",
      meta: {
        title: "字典管理",
        roles: ["admin"],
      },
    },
    {
      path: "/system/dept",
      name: "User",
      meta: {
        title: "部门管理",
        roles: ["admin"],
      },
    },
  ],
};
export default [
  {
    url: "/api/system/menu/list",
    method: "get",
    response: ({ query }) => {
      return [systemRouter];
    },
  },
] as MockMethod[];
