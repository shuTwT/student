import { get } from "@/utils/request";
export const getClazzList = (data?: object) => {
  return get("/v2/clazz/list", data);
};
