import { get, post } from "@/utils/request";
export const getTeacherList = (data?: object) => {
  return get("/v2/teacher/list", data);
};
export const addTeacher = (data?: object) => {
  return post("/v2/teacher/add", data);
};
export const updateTeacher = (data?: object) => {
  return post("/v2/teacher/update", data);
};
