package SoftwareEngineFinalProject.user;

import SoftwareEngineFinalProject.article.Article;
import SoftwareEngineFinalProject.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@Service
public class UserController {
    static String memberName;
    static Integer memberId = 0;

    public static String getMemberName() {
        return memberName;
    }

    public static Integer getMemberId() {
        return memberId;
    }

    public static void setMemberName(String memberName) {
        UserController.memberName = memberName;
    }

    public static void setMemberId(Integer memberId) {
        UserController.memberId = memberId;
    }

    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");

        return "login";
    }

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/users/new")
    public String showNewFrom(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");

        return "register";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");

        return "redirect:/articles";
    }

    /**
     * Immplementation of updated and delete
     */

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            if (id == 0)
                return "index";

            User user = userService.get(id);
            List<Article> listArticle = articleService.listAll();
            model.addAttribute("user", user);
            model.addAttribute("memberid", memberId);
            model.addAttribute("listArticle", listArticle);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

            if (memberId != 0)
                model.addAttribute("membername", memberName);
            else
                model.addAttribute("membername", user.getFirstName());

            return "user_from";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            userService.delete(id);
            redirectAttributes.addFlashAttribute("message", "The user id" + id + "has been deleted");
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/users";
    }

    @GetMapping("/dologin")
    public String dologin(@ModelAttribute User user, RedirectAttributes redirectAttributes) throws UserNotFoundException {
        Integer id = 1;
        Integer check;
        Integer check_2 = 1;
        User member;
        String a;
        String b;

        while (true) {
            check = userService.getnum(id);
            if (check == 1) {
                member = userService.get(id);
                check_2 = 0;
                a = member.getEmail();
                b = user.getEmail();

                if (Objects.equals(a, b)) {
                    memberName = member.getFirstName();
                    memberId = id;
                    break;
                }
            }

            if (check == 0 && check_2 == 0)
                return "redirect:/login";

            id++;
        }

        a = member.getPassword();
        b = user.getPassword();

        if (Objects.equals(a, b))
            return "redirect:/articles";

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout() {
        setMemberName("");
        setMemberId(0);

        return "index";
    }
}
