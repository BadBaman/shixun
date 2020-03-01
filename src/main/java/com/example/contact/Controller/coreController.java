package com.example.contact.Controller;

//import com.example.contact.Dao.ContactRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class coreController{

    @RequestMapping("/hello")
    public String home(){

        return "hello";
    }
    @RequestMapping("/history")
    public String index(){

        return "history";
    }
    /*@RequestMapping("/file")
    public String file(){

        return "file";
    }*/
    @RequestMapping("/fabricnetwork")
    public String fabricnetwork(){
        return "fabricnetwork";

    }
    @RequestMapping("/transaction")
    public String transaction(){
        return "transaction";

    }
    @RequestMapping("/mypage")
    public String mypage(){
        return "mypage";

    }
}
