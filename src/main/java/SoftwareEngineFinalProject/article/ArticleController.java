package SoftwareEngineFinalProject.article;

import SoftwareEngineFinalProject.user.User;
import SoftwareEngineFinalProject.user.UserController;
import SoftwareEngineFinalProject.user.UserNotFoundException;
import SoftwareEngineFinalProject.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    private ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String showArticleList(Model model) {
        String member = UserController.getMemberName();
        Integer memberId = UserController.getMemberId();
        List<Article> articleList = articleService.listAll();

        model.addAttribute("listArticle", articleList);
        model.addAttribute("member", member);
        model.addAttribute("memberid", memberId);

        return "articles";
    }

    @GetMapping("article/new")
    public String showNewFrom(Model model) {
        String member = UserController.getMemberName();

        model.addAttribute("article", new Article());
        model.addAttribute("member", member);
        model.addAttribute("pageTitle", "Add New Article");

        return "article_from";
    }

    @PostMapping("/article/save")
    public String saveArticle(Article article, RedirectAttributes redirectAttributes) {
        String member = UserController.getMemberName();

        article.setAuthor(member);
        articleService.save(article);
        redirectAttributes.addFlashAttribute("message", "The article has been saved successfully.");

        return "redirect:/articles";
    }

    @GetMapping("/article/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Article article = articleService.get(id);
            model.addAttribute("article", article);
            model.addAttribute("pageTitle", "Edit Article (ID: " + id + ")");

            return "article_from";
        } catch (ArticleNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/articles";
        }
    }

    @GetMapping("/article/delete/{id}")
    public String deleteArticle(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            articleService.delete(id);
            redirectAttributes.addFlashAttribute("message", "The article id" + id + "has been deleted");
        } catch (ArticleNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/articles";
    }

    @GetMapping("/article/read/{id}")
    public String ReadArticle(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Article article = articleService.get(id);
            String a = article.getTheme();

            model.addAttribute("article", article);
            model.addAttribute("pageTitle", "Article");

            return "article_read";
        } catch (ArticleNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

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
            check = userService.getnum(id);

            if (check == 1) {
                member = userService.get(id);
                a = member.getFirstName();
                b = name;

                if (Objects.equals(a, b))
                    break;
            }

            id++;
        }

        List<Article> listArticle = articleService.listAll();
        String memberName = UserController.getMemberName();

        model.addAttribute("listArticle", listArticle);
        model.addAttribute("user", member);
        model.addAttribute("author", name);
        model.addAttribute("member", memberName);

        return "user_read";
    }
}
