import {useUserStore} from "@/store/modules/user"
import type { Directive } from "vue";


export default {
    mounted(el:any,binding:any){

        const userStore=useUserStore()

        if (userStore.roles.includes('superAdmin')) {
            return
        }
        const hasPermission =userStore.roles.includes(binding.value)
        if (!hasPermission) {
            el.remove()
        }
    }
} as  Directive;
