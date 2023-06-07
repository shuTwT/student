import basicRoute from "./basic";
import {
  ascending,
  buildHierarchyTree,
  formatTwoStageRoutes,
  formatFlatteningRoutes,
} from "@/utils";
import { useAppStoreHook } from "@/store/modules/app";
import type { RouteRecordRaw, RouteComponent } from "vue-router";
/**
 * 自动导入静态路由
 */
const modules: Record<string, any> = import.meta.glob(["./modules/**/*.ts"], {
  eager: true,
});
const routerModuleList: any[] = [];

Object.keys(modules).forEach((key) => {
  const mod = modules[key].default || {};
  const modList = Array.isArray(mod) ? [...mod] : [mod];
  routerModuleList.push(...modList);
});
export const constantRoutes: Array<RouteRecordRaw> = formatTwoStageRoutes(
  formatFlatteningRoutes(buildHierarchyTree(ascending(routerModuleList)))
);
/** 用于渲染菜单，保持原始层级 */
export const constantMenus: Array<RouteComponent> = ascending(
  routerModuleList
).concat(...basicRoute);
useAppStoreHook().SET_ROUTES(constantMenus);
export const asyncRoutes = [...constantRoutes, ...basicRoute];
