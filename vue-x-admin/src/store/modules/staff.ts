import { defineStore} from 'pinia';
import { getToken,setToken,removeToken } from '@/utils/auth';
export const useStaffStore=defineStore({
    id:'staff',
    state:()=>({
        token:getToken()||'',
        staff:ref<any>()
    }),
    actions:{

    }
});
