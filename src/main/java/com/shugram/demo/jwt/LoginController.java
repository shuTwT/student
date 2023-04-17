package com.shugram.demo.jwt;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shugram.demo.entity.UserEntity;
import com.shugram.demo.pojo.User;
import com.shugram.demo.service.UserService;
import com.shugram.demo.utils.PassToken;
import com.shugram.demo.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 登录Controller
 */
@Slf4j
@RestController
public class LoginController {
    private final UserService userService;
    //static Map<Integer, UserEntity> userMap = new HashMap<>();

//    static {
//        //模拟数据库
//        UserEntity user1 = new UserEntity(1, "张三", "123456");
//        userMap.put(1, user1);
//        UserEntity user2 = new UserEntity(2, "李四", "123123");
//        userMap.put(2, user2);
//    }

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 模拟用户 登录
     */
    @PassToken
    @PostMapping("/login")
    public Response<HashMap<String, Object>> login(@RequestBody User user) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("SELECT 2")
                .eq("user_name",user.getUserName())
                .last("LIMIT 1");
        int count= userService.count(queryWrapper);
        if (count==0){
            return Response.fail(400,"不存在该用户");
        }else{
            QueryWrapper<UserEntity> allQueryWrapper=new QueryWrapper<>();
            allQueryWrapper
                    .select("SELECT 2,3")
                    .allEq(new HashMap<String,Object>(){
                        {
                            put("user_name","张三");
                        }
                    })
                    .last("LIMIT 1");
            count=userService.count(allQueryWrapper);
            if (count==0){
                return Response.fail(400,"密码错误");
            }
            else{
                QueryWrapper<UserEntity> oneQueryWrapper=new QueryWrapper<>();
                oneQueryWrapper.eq("user_name",user.getUserName());
                log.info("登录成功！生成token！");
                UserEntity user1=userService.getOne(oneQueryWrapper);
                String token = JwtUtil.createToken(user1);
                return Response.success(new HashMap<String,Object>(){
                    {
                        put("token",token);
                    }
                });
            }
            //userService.
            //return Response.success("");
        }



//        for (UserEntity dbUser : userMap.values()) {
//            if (dbUser.getUserName().equals(user.getUserName()) && dbUser.getPassword().equals(user.getPassword())) {
//                log.info("登录成功！生成token！");
//                String token = JwtUtil.createToken(dbUser);
//                return Response.success(token);
//            }
//        }

    }
}
