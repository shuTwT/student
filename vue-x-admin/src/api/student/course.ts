import { get, post } from "@/utils/request";
export const getCourseList = (data?: object) => {
  return get("/v2/course/list", data);
};
export const addCourse = (data?: object) => {
  return post("/v2/course/add", data);
};
export const updateCourse = (data?: object) => {
  return post("/v2/course/update", data);
};
