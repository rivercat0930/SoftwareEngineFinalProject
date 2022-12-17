package com.mycompany.message;

import com.mycompany.article.*;
import com.mycompany.user.UserController;
import com.mycompany.user.UserService;
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
public class MessageController {

    @Autowired
    private MessageService service;

    private ArticleRepository repo;

    @GetMapping("/message/delete/{id}")
    public String deleteArticle(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The message id" + id + "has been deleted");
        } catch (MessageNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/articles";

    }

    @PostMapping("/message/save")
    public String saveMessage(Message message, RedirectAttributes ra) {
        String member = UserController.getMemeberName();
        message.setAuthor(member);
        String theme = ArticleController.gettheme();
        message.setTheme(theme);
        service.save(message);
        ra.addFlashAttribute("message", "The message has been saved successfully.");
        return "redirect:/articles";
    }
}
