import { createApp } from "vue";
import { store } from "@/store";
import router from "@/router";
import "element-plus/dist/index.css"
import "@/style/element/index.scss";
import 'element-plus/theme-chalk/dark/css-vars.css'
import "@/style/index.scss";
import { getServerConfig } from "@/utils/config";
import App from "@/App.vue";
import SvgIcon from "@/components/SvgIcon/SvgIcon.vue";
import "virtual:svg-icons-register";
import "virtual:uno.css";
import directives from "./directives";
getServerConfig().then((data) => {
  if (matchMedia("(prefers-color-schmem: dark)")) {
    document.querySelector("html")?.classList.add("dark");
  }
  const app = createApp(App);
  app.config.globalProperties.$config = data;
  app.component("svg-icon", SvgIcon);
  app.use(store).use(router).use(directives);
  app.mount("#app");
});
