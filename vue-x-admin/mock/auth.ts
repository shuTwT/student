import { MockMethod } from "vite-plugin-mock";
export default [
  {
    url: "/api/login",
    method: "post",
    response: ({ body }) => {
      if (body.username === "admin") {
        return {
          code: 200,
          success: true,
          data: {
            username: "admin",
            avatar: "https://avatars.githubusercontent.com/u/95295076?v=4",
            // 一个用户可能有多个角色
            roles: ["admin"],
            accessToken: "dsfajywgeriuyga",
            expires: "2023/10/30 00:00:00",
            uuid: "1234",
          },
        };
      } else {
        return {
          code: 200,
          success: true,
          data: {
            username: "common",
            avatar: "https://avatars.githubusercontent.com/u/95295076?v=4",
            // 一个用户可能有多个角色
            roles: ["common"],
            accessToken: "dsfajywgeriuyga",
            expires: "2023/10/30 00:00:00",
            uuid: "1122",
          },
        };
      }
    },
  },
  {
    url: "/api/userInfo",
    method: "post",
    response: ({ body }) => {
      if (body.token) {
        return {
          code: 200,
          success: true,
          data: {
            username: "admin",
            avatar: "https://avatars.githubusercontent.com/u/95295076?v=4",
            // 一个用户可能有多个角色
            roles: ["admin"],
            accessToken: "dsfajywgeriuyga",
            expires: "2023/10/30 00:00:00",
            uuid: "1234",
          },
        };
      }
    },
  },
] as MockMethod[];
