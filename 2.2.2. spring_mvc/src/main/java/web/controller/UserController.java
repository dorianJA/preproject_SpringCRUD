package web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Role;
import web.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {


    @GetMapping("/user")
    public String userInfo(@AuthenticationPrincipal UserDetails user, Model model) {
        Set<Role> roles = (Set<Role>) user.getAuthorities();
        List<String> list = new ArrayList<>();
        for (Role r : roles) {
            list.add(r.getName());
        }
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("userRole", list);
        return "userInfo";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(name = "error",required = false) Boolean error,Model model) {
        if(Boolean.TRUE.equals(error)){
            model.addAttribute("error","Incorrect  name or password");
        }
        return "login";
    }

    @RequestMapping(value = "accessDenied", method = RequestMethod.GET)
    public String accessDeniedPage(){
        return "accessDenied";
    }


}