declare module "*.vue" {
  import { DefineComponent } from "vue";
  const component: DefineComponent<{}, {}, any>;
  export default component;
}

declare module "*.scss" {
  const scss: Record<string, string>;
  export default scss;
}
type routerConfig = {
  path: string;
  name: string;
  component?: any;
  meta: any;
  redirect?: string;
  children?: routerConfig[];
};