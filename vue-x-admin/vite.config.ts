import { defineConfig, loadEnv } from "vite";
import vue from "@vitejs/plugin-vue";
import path from "node:path";
import AutoImport from "unplugin-auto-import/vite";
import Components from "unplugin-vue-components/vite";
import { ElementPlusResolver } from "unplugin-vue-components/resolvers";
import Icons from "unplugin-icons/vite";
import IconsResolver from "unplugin-icons/resolver";
import { createSvgIconsPlugin } from "vite-plugin-svg-icons";
import { viteMockServe } from "vite-plugin-mock";
import unocss from "unocss/vite";
// https://vitejs.dev/config/
export default defineConfig(({ mode }) => {
  const config = loadEnv(mode, "./");
  return {
    plugins: [
      unocss(),
      createSvgIconsPlugin({
        // 指定需要缓存的图标文件夹
        iconDirs: [path.resolve(process.cwd(), "src/assets/icons/svg")],
        // 指定symbolId格式
        symbolId: "icon-[dir]-[name]",
        svgoOptions: {
          // 移除svg默认颜色
          plugins: [
            {
              name: "removeAttrs",
              params: { attrs: ["class", "data-name", "fill", "stroke"] },
            },
          ],
        },
      }),
      vue(),
      //自动导入
      AutoImport({
        dts: "types/auto-imports.d.ts",
        resolvers: [
          ElementPlusResolver(),
          IconsResolver({
            prefix: "Icon",
          }),
        ],
        imports: ["vue", "vue-router"],
      }),
      // 自动注册
      Components({
        dts: "types/components.d.ts",
        resolvers: [
          ElementPlusResolver(),
          IconsResolver({ enabledCollections: ["ep"] }),
        ],
      }),
      Icons({
        autoInstall: true,
      }),
      viteMockServe({
        mockPath: "mock",
        localEnabled: true,
        logger: false,
      }),
    ],
    resolve: {
      alias: {
        "@": path.resolve(__dirname, "src"),
      },
    },
    server: {
      port: 5001,
      proxy: {
        "/api/v2": {
          target: "http://127.0.0.1:8080",
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/api\/v2/, ""),
        },
      },
    },
  };
});
