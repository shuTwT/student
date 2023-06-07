import { defineStore } from "pinia";
import { store } from "@/store";
export const usePermissionStore = defineStore({
  id: "permission",
  state:()=>({
    menuList:[],//用户的路由权限
    routes:[]//前端的静态+动态路由
  }),
  actions:{

  }
});
export function usePermissionStoreHook() {
  return usePermissionStore(store);
}
