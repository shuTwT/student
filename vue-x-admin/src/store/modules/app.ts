import { defineStore } from "pinia";
import { store } from "@/store";
type appType = {
  routes: Array<{
    path: string;
    name: string;
    meta: { showLink?: boolean; title?: string,roles?:any };
    children?: Array<any>;
  }>;
};
export const useAppStore = defineStore({
  id: "app",
  state: (): appType => ({
    routes: [],
  }),
  actions: {
    SET_ROUTES(routes: Array<any>) {
      this.routes.length = 0;
      routes.forEach((value) => {
        this.routes.push(value);
      });
    },
    ADD_ROUTES(routes: Array<any>) {
      routes.forEach((value) => {
        this.routes.push(value);
      });
    },
  },
  persist:true
});
export function useAppStoreHook() {
  return useAppStore(store);
}
