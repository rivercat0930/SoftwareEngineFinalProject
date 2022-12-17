package com.mycompany.article;

import com.mycompany.user.*;
import com.mycompany.message.*;
import org.aspectj.bridge.IMessageContext;
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
import java.util.Optional;

@Controller
@Service
public class ArticleController {

    static String theme;
    public static String gettheme(){return theme;}
    @Autowired
    private ArticleService service;
    @Autowired
    private UserService serviceUser;

    @Autowired
    private MessageService serviceMessage;

    private ArticleRepository repo;

    public ArticleController() {
    }

    @GetMapping("/articles")
    public String showArticleList(Model model) {
        String member = UserController.getMemeberName();
        Integer memberid = UserController.getMemeberid();
        List<Article> listArticle = service.listAll();
        model.addAttribute("listArticle", listArticle);
        model.addAttribute("member", member);
        model.addAttribute("memberid", memberid);
        return "articles";
    }

    @GetMapping("/article/new")
    public String showNewFrom(Model model) {
        String member = UserController.getMemeberName();
        model.addAttribute("article", new Article());
        model.addAttribute("member", member);
        model.addAttribute("pageTitle", "Add New Article");
        return "article_from";
    }

    @PostMapping("/article/save")
    public String saveArticle(Article article, RedirectAttributes ra) {
        String member = UserController.getMemeberName();
        article.setAuthor(member);
        service.save(article);
        ra.addFlashAttribute("message", "The article has been saved successfully.");
        return "redirect:/articles";
    }

    @GetMapping("/article/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Article article = service.get(id);
            model.addAttribute("article", article);
            model.addAttribute("pageTitle", "Edit Article (ID: " + id + ")");

            return "article_from";
        } catch (ArticleNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/articles";
        }
    }

    @GetMapping("/article/delete/{id}")
    public String deleteArticle(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The article id" + id + "has been deleted");
        } catch (ArticleNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/articles";

    }

    @GetMapping("/article/read/{id}")
    public String ReadArticle(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            String member = UserController.getMemeberName();
            Article article = service.get(id);
            String a = article.getTheme();
            theme = a;
            model.addAttribute("article", article);
            model.addAttribute("pageTitle", "Article");
            model.addAttribute("Theme", a);
            model.addAttribute("new_message", new Message());
            List<Message> listMessage = serviceMessage.listAll();
            model.addAttribute("listMessage", listMessage);
            model.addAttribute("member", member);

            return "article_read";
        } catch (ArticleNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/articles";
        }
    }

    @GetMapping("/user/read/{name}")
    public String ReadUser(@PathVariable("name") String name, Model model) throws UserNotFoundException {
        Integer id = 1;
        Integer check;
        User member;
        String a;
        String b;
        while (true) {
            check = serviceUser.getnum(id);
            if (check == 1) {
                member = serviceUser.get(id);
                a = member.getFirstName();
                b = name;
                if (Objects.equals(a, b)) {
                    break;
                }
            }
            id++;
        }
        List<Article> listArticle = service.listAll();
        String membername = UserController.getMemeberName();
        model.addAttribute("listArticle", listArticle);
        model.addAttribute("user", member);
        model.addAttribute("author", name);
        model.addAttribute("member", membername);
        return "user_read";
    }
}
