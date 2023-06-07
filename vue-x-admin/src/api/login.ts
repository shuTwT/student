import { post } from "@/utils/request";
export const getLogin = (data?: object) => {
  return post("/login", data);
};
export const getUserInfo = (data?: object) => {
  return post("/userInfo", data);
};
