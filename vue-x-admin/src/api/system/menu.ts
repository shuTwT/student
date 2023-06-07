import { get } from "@/utils/request";
export const getMenuList = () => {
  return get("/system/menu/list");
};
