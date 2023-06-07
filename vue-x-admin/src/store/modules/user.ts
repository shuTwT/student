import { defineStore } from "pinia";
import { getLogin, getUserInfo } from "@/api/login";
import { removeToken, setToken } from "@/utils/auth";
import router from "@/router";

type userType = {
  username: string;
  avatar: string;
  roles: Array<string>;
  uuid: string;
};
export const useUserStore = defineStore({
  id: "user",
  persist:true,
  state: (): userType => ({
    // 用户名
    username: "",
    // 用户头像
    avatar: "",
    // 角色
    roles: [],
    // uuid
    uuid: "",
  }),
  actions: {
    SET_USERNAME(username: string) {
      this.username = username;
    },
    SET_AVATAR(avatar: string) {
      this.avatar = avatar;
    },
    SET_ROLE(roles: Array<string>) {
      this.roles.length = 0;
      roles.forEach((value) => {
        this.roles.push(value);
      });
    },
    SET_UUID(uuid: string) {
      this.uuid = uuid;
    },
    /**
     * 登入
     * @param data
     * @returns
     */
    async loginByUsername(data: object) {
      return new Promise((resolve, reject) => {
        getLogin(data)
          .then((res: any) => {
            const { data } = res;
            console.log(res);
            
            setToken(data.accessToken);
            this.username = data.username;
            this.roles = data.roles;
            resolve(res);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    async loginByToken(data: any) {
      return new Promise((resolve, reject) => {
        getUserInfo(data)
          .then((data: any) => {
            setToken(data.token);
            resolve(data);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    /**
     * 登出
     */
    logout() {
      this.username = "";
      this.roles = [];
      removeToken();
      router.push("/login");
    },
  },
});
