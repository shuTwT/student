package com.shugram.demo.controller;

import com.shugram.demo.entity.RouterEntity;
import com.shugram.demo.jwt.JwtUtil;
import com.shugram.demo.service.RouterService;
import com.shugram.demo.utils.PassToken;
import com.shugram.demo.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ViewController {

    private HttpServletRequest request;
    private RouterService routerService;

    public String testCookieValue(@CookieValue("token") String token) {
        System.out.println("testCookieValue,token=" + token);
        return "SUCCESS";
    }

    @Autowired
    public ViewController(HttpServletRequest request, RouterService routerService) {

        this.request = request;
        this.routerService = routerService;
    }

    @PassToken
    @GetMapping("/admin/index")
    public String admin(Model model) {
        Cookie[] cookies = request.getCookies();
        List<RouterEntity> temp = routerService.list();
        model.addAttribute("router", Util.list2Tree(temp));
        return verify(cookies, "admin/index");
    }

    @PassToken
    @GetMapping("admin/login")
    public String login1() {
        return "admin/login";
    }

    @PassToken
    @GetMapping("admin/welcome")
    public String welcome() {
        Cookie[] cookies = request.getCookies();
        return verify(cookies, "admin/welcome");
    }

    @PassToken
    @GetMapping("admin/clazz_list")
    public String clazzList() {
        Cookie[] cookies = request.getCookies();
        return verify(cookies,"admin/clazz/clazz-list");
    }

    @PassToken
    @GetMapping("admin/score_list")
    public String scoreList() {
        Cookie[] cookies = request.getCookies();
        return verify(cookies,"admin/score/score-list");
    }

    @PassToken
    @GetMapping("admin/score_add")
    public String scoreAdd() {
        Cookie[] cookies = request.getCookies();
        return verify(cookies,"admin/score/score-add");
    }

    @PassToken
    @GetMapping("admin/score_edit")
    public String scoreEdit() {

        Cookie[] cookies = request.getCookies();
        return verify(cookies,"admin/score/score-edit");
    }

    @PassToken
    @GetMapping("admin/student_list")
    public String studentList() {

        Cookie[] cookies = request.getCookies();
        return verify(cookies,"admin/student/student-list");
    }

    @PassToken
    @GetMapping("admin/student_add")
    public String studentAdd() {
        Cookie[] cookies = request.getCookies();
        return verify(cookies,"admin/student/student-add");
    }

    @PassToken
    @GetMapping("admin/student_edit")
    public String studentEdit() {
        Cookie[] cookies = request.getCookies();
        return verify(cookies,"admin/major/student-edit");
    }

    @PassToken
    @GetMapping("admin/major_list")
    public String majorList() {
        Cookie[] cookies = request.getCookies();
        return verify(cookies,"admin/major/major-list");
    }

    @PassToken
    @GetMapping("admin/major_add")
    public String majorAdd() {
        Cookie[] cookies = request.getCookies();
        return verify(cookies,"admin/major/major-add");
    }

    @PassToken
    @GetMapping("admin/major_edit")
    public String majorEdit() {
        Cookie[] cookies = request.getCookies();
        return verify(cookies,"admin/major/major-edit");
    }

    @PassToken
    @GetMapping("admin/course_list")
    public String courseList() {
        Cookie[] cookies = request.getCookies();
        return verify(cookies,"admin/course/course-list");
    }

    @PassToken
    @GetMapping("admin/course_add")
    public String courseAdd() {
        Cookie[] cookies = request.getCookies();
        return verify(cookies,"admin/course/course-add");
    }

    @PassToken
    @GetMapping("admin/course_edit")
    public String courseEdit() {
        Cookie[] cookies = request.getCookies();
        return verify(cookies,"admin/course/course-edit");
    }

    @PassToken
    @GetMapping("admin/teacher_list")
    public String teacherList() {
        Cookie[] cookies = request.getCookies();
        return verify(cookies,"admin/teacher/teacher-list");
    }

    @PassToken
    @GetMapping("admin/teacher-add")
    public String teacherAdd() {
        Cookie[] cookies = request.getCookies();
        return verify(cookies,"admin/teacher/teacher-add");
    }

    @PassToken
    @GetMapping("admin/teacher_edit")
    public String teacherEdit() {
        Cookie[] cookies = request.getCookies();
        return verify(cookies,"admin/teacher/teacher-edit");
    }

    private static String verify(Cookie[] cookies, String success) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String cookieValue = cookie.getValue();
                    // 处理Cookie值
                    if (JwtUtil.verify(cookieValue)) {
                        return success;
                    }
                }
            }
        }
        return "redirect:/admin/login";
    }

}
