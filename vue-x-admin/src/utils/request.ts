import axios from "axios";
import { getToken } from "@/utils/auth";

const service = axios.create({
  headers: {
    Accept: "application/json,text/plain,*/*",
    "Content-Type": "application/json",
  },
  timeout: 20000,
});
service.interceptors.request.use(
  (config) => {
    config.baseURL = import.meta.env["VITE_BASE_PREFIX"];
    if (getToken()) {
      config.headers["x-authorization"] = "Bearer " + getToken();
    }
    return config;
  },
  (error) => {
    console.warn(error);
    return Promise.reject(error);
  }
);
service.interceptors.response.use(
  (response: any) => {
    return response;
  },
  (error) => {
    console.warn("错误");
    return Promise.reject(error);
  }
);
export function get(url: string, params = {}) {
  return new Promise((resolve, reject) => {
    service
      .get(url, { params })
      .then((response) => {
        resolve(response.data);
      })
      .catch((err) => {
        reject(err);
      });
  });
}
export function post(url: string, data = {}) {
  return new Promise((resolve, reject) => {
    service
      .post(url, data)
      .then((response) => {
        resolve(response.data);
      })
      .catch((err) => {
        reject(err);
      });
  });
}
export default service;
