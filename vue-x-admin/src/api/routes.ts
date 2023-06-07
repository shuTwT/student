import { get } from "@/utils/request";
export const getAsyncRoutes = () => {
  return get("/asyncRoutes");
};
