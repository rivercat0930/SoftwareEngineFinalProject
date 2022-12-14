package com.mycompany.user;

import com.mycompany.article.Article;
import com.mycompany.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.PublicKey;
import java.util.List;
import java.util.Objects;

@Controller
@Service
public class UserController {

    static String memeberName;

    static Integer memeberid = 0;

    public static String getMemeberName(){return memeberName;}

    public static void setMemeberName(String id){memeberName = id;}

    public static Integer getMemeberid(){return memeberid;}

    public static void setMemeberid(Integer id){memeberid = id;}
    @Autowired
    private UserService service;
    @Autowired
    private ArticleService serviceArticle;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "login";
    }

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewFrom(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "註冊");
        return "register";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra) {
        service.save(user);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/articles";
    }

    /**
     * Immplementation of updated and delete
     */

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            if (id == 0) {
                return "index";
            }
            User user = service.get(id);
            List<Article> listArticle = serviceArticle.listAll();
            model.addAttribute("user", user);
            model.addAttribute("memberid", memeberid);
            model.addAttribute("listArticle", listArticle);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            if (memeberid != 0) {
                model.addAttribute("membername", memeberName);
            }
            else {
                model.addAttribute("membername", user.getFirstName());
            }
            return "user_from";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The user id" + id + "has been deleted");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";

    }

    @GetMapping("/dologin")
    public String dologin(@ModelAttribute User user, RedirectAttributes ra) throws UserNotFoundException {
        Integer id = 1;
        Integer check;
        Integer check_2 = 1;
        User member;
        String a;
        String b;
        while (true) {
            check = service.getnum(id);
            if (check == 1) {
                member = service.get(id);
                check_2 = 0;
                a = member.getEmail();
                b = user.getEmail();
                if (Objects.equals(a, b)) {
                    memeberName = member.getFirstName();
                    memeberid = id;
                    break;
                }
            }
            if (check == 0 && check_2 == 0) {
                return "redirect:/login";
            }
            id++;
        }
        a = member.getpassword();
        b = user.getpassword();
        if (Objects.equals(a, b)) {
            return "redirect:/articles";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout() {
        setMemeberName("");
        setMemeberid(0);
        return "index";
    }

}
