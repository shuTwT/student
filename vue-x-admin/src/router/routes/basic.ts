export default [
  {
    path: "/login",
    name: "Login",
    component: () => import("@/views/login/login.vue"),
    meta: {
      title: "login",
      showLink: false,
      rank: 101,
    },
  },
  {
    path: "/redirect",
    component: () => import("@/layout/index.vue"),
    meta: {
      title: "home",
      showLink: false,
      rank: 102,
    },
    children: [
      {
        path: "/redirect/:path(.*)",
        name: "Redirect",
        component: () => import("@/layout/redirect.vue"),
      },
    ],
  },
  {
    path: "/empty",
    name: "Empty",
    component: () => import("@/views/empty/empty.vue"),
    meta: {
      title: "empty",
      showLink: false,
      rank: 103,
    },
  },
];
