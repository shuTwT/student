import { get, post } from "@/utils/request";
export const getMajorList = (data?: object) => {
  return get("/v2/major/list", data);
};
export const addManjor = (data?: object) => {
  return post("/v2/major/add", data);
};
export const updateManjor = (data?: object) => {
  return post("/v2/major/update", data);
};
