import axios from "axios";
import watermark from "@/utils/watermark";
const { VITE_BASE_URL } = import.meta.env ?? "";
export async function getServerConfig() {
  return new Promise((resolve, reject) => {
    axios({
      method: "get",
      url: `${VITE_BASE_URL}/serverConfig.json`,
    })
      .then(({ data }) => {
        watermark.set("舒元祺出品");
        resolve(data);
      })
      .catch(() => {
        console.warn("检测不到配置文件");
        reject("检测不到配置文件");
      });
  });
}
