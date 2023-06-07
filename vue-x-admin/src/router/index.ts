import { createWebHashHistory, createWebHistory } from "vue-router";
import { createRouter } from "vue-router";

import { asyncRoutes } from "./routes";
import { getToken } from "@/utils/auth";

const whiteList = ["/login"];
const routes = asyncRoutes;

const router = createRouter({
  history:
    import.meta.env.HISTORY_MODE === "hash"
      ? createWebHashHistory()
      : createWebHistory(),
  routes,
  strict: true,
});
/**
 * 导航守卫
 */
router.beforeEach(async (to, _, next) => {
  if (to.meta?.keepAlive) {
  }

  // 检查是否登录
  if (!getToken()) {
    // 未登录
    // 如果是白名单
    if (whiteList.indexOf(to.path) !== -1) {
      // 正常跳转
      next();
    } else {
      // 否则跳转登录页
      next("/login");
    }
  } else {
    // 已登录
    // 如果是登录页
    if (to.name === "Login") {
      next("/");
    } else {
      next();
    }
  }
});
export default router;
