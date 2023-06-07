import { get, post } from "@/utils/request";
export const getStudentList = (data?: object) => {
  return get("/v2/student/list", data);
};
export const addStudent = (data?: object) => {
  return post("/v2/student/add", data);
};
export const updateStudent = (data?: object) => {
  return post("/v2/student/update", data);
};
export const removeStudent = (data?: object) => {
  return post("/v2/student/delete", data);
};
export const importStudents = (data?: any) => {
  return post("/v2/student/importStudents", data);
};
