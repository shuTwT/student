<script setup lang="ts">
import { useUserStore } from '@/store/modules/user';
const formData = reactive({
    username: "admin",
    password: "admin123",
    uuid: "",
    verifyCode: "null"
})
const router = useRouter()
async function onLogin() {
    useUserStore().loginByUsername({
        username: formData.username,
        password: formData.password,
        verifyCode: formData.verifyCode,
        uuid: formData.uuid
    }).then((res: any) => {
        if (res.success) {
            router.push("/")

        }

    })
}
function onCommonLogin(){
    useUserStore().loginByUsername({
        username: "common",
        password: "",
        verifyCode: "",
        uuid: ""
    }).then((res: any) => {
        if (res.success) {
            router.push("/")

        }

    })
}
</script>
<template>
    <div class="login-container">
        <div class="login-right">
            <div class="login-wrap">
                <div class="login-main">
                    <div class="login-title">pure-admin-vue</div>
                    <div class="login-form">
                        <div class="form-item username"><input :value="formData.username" type="text" placeholder="用户名"
                                autocapitalize="false"></div>
                        <div class="form-item password"><input type="password" :value="formData.password" placeholder="密码"
                                autocapitalize="false"></div>
                        <div class="form-item code"><input :value="formData.verifyCode" type="text" placeholder="验证码"
                                autocapitalize="false">
                            <canvas id="code"></canvas>
                        </div>
                        <div class="form-item login">
                            <button @click="() => onLogin()">登录</button>
                            <button @click="()=>onCommonLogin()">游客登录</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped>
.login-container {
    display: flex;
    flex-direction: row;
    width: 100%;
    height: 100%;
}

@media screen and (min-width:750px) {
    .login-right {
        position: relative;
        display: flex;
        flex-direction: column;
        width: 500px;
        height: 500px;
        top: 20%;
        left: 40%;
        border-radius: 20px;
        background-color: #333333;

        .login-title {
            font-size: 30px;
            height: 100px;
            line-height: 100px;
        }

        .login-form {
            display: flex;
            flex-direction: column;
            width: 400px;
            height: 400px;
            margin: auto;

            .form-item {
                display: flex;
                position: relative;
                height: 40px;
                margin-top: 30px;

                input {
                    height: 40px;
                }

            }

            .username,
            .password {
                input {
                    width: 100%;
                }
            }

            .code {
                justify-content: space-between;

                #code {
                    width: 120px;
                    background: #fff;
                }
            }

            .form-item.login {
                display: flex;
                justify-content: center;
                align-items: center;
            }
        }
    }
}
</style>