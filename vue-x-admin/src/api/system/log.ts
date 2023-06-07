import { get } from "@/utils/request";
export const getLogList = (data?:object) => {
    return get("/v2/system/log/list",data);
};