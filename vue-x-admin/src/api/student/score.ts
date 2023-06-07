import { get, post } from "@/utils/request";
export const getScoreList = (data?: object) => {
  return get("/v2/score/list", data);
};
export const addScore = (data?: object) => {
  return post("/v2/score/add", data);
};
export const updateScore = (data?: object) => {
  return post("/v2/score/update", data);
};
export const increaseScore = (data?: object) => {
  return post("/v2/score/increase", data);
};
export const reduceScore = (data?: object) => {
  return post("/v2/score/reduce", data);
};
export const importScores = (data?: any) => {
    return post("/v2/score/importScores", data);
  };
  