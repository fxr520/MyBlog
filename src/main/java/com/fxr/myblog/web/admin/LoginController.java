package com.fxr.myblog.web.admin;

import com.fxr.myblog.po.User;
import com.fxr.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author fanXiaoRui
 * @date 2020/10/6 12:04
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName,
                        @RequestParam String passWord,
                        HttpSession httpSession,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(userName,passWord);
        if(user != null) {
            user.setPassWord(null);
            httpSession.setAttribute("user", user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "redirect:/admin";
    }
}
