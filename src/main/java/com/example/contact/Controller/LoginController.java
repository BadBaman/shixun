package com.example.contact.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.contact.dao.UserRepository;
import com.example.contact.entity.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password, Model model) throws Exception
    {
        List<UserVo> users = userRepository.findByUserName(userName);
        // 如果数据库中未查到该账号:
        if (users == null) {
            log.warn("attempting to log in with the non-existed account");
            return "该用户不存在";
        } else {
            UserVo user = users.get(0);
            if (user.getPassword().equals(password)) {
                // 如果密码与邮箱配对成功:
                model.addAttribute("name", user.getUserName());
                log.warn(user.toString()+ " logged in");
                return "mypage";
            } else {
                // 如果密码与邮箱不匹配:
                model.addAttribute("name", "logging failed");
                log.warn(user.toString()+ " failed to log in");
                return "login";
            }
        }

       /* //注意修改，使函数参数不为空
        List<UserVo> users = userRepository.findByUserName(userName);
        // 如果数据库中未查到该账号:
        if (users == null) {
            log.warn("用户不存在");
            modelAndView.addObject("error","此用户不存在！");
            modelAndView.setViewName("login");
        }
        else {
            UserVo user = users.get(0);
            if (user.getPassword().equals(password)) {
                // 如果密码与邮箱配对成功:
                //model.addAttribute("name", user.getUserName());
                log.warn(user.toString()+ " 成功登陆");
                modelAndView.setViewName("mypage");
                modelAndView.addObject("userName",userName);

            } else {
                // 如果密码与账号不匹配:
                modelAndView.addObject("error","密码不正确，登陆失败！");
                log.warn(userName.toString()+ " 登录失败");
                modelAndView.setViewName("login");
            }

        }*/
/*if(!"admin".equals(userName)){
            modelAndView.addObject("error","无此用户！");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        if(!"123456".equals(password)){
            modelAndView.addObject("error","密码错误！");
            modelAndView.setViewName("login");
            return modelAndView;
        }*/

        //return modelAndView;
    }

}
