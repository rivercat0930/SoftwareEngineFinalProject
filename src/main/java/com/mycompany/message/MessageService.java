package com.mycompany.message;

import com.mycompany.article.Article;
import com.mycompany.article.ArticleNotFoundException;
import com.mycompany.message.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository repo;

    public List<Message> listAll() {
        return (List<Message>) repo.findAll();
    }

    public void save(Message message) {
        repo.save(message);
    }

    public Message get(Integer id) throws MessageNotFoundException {
        Optional<Message> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new MessageNotFoundException("Could not find any message with ID" + id);
    }

    public void delete(Integer id) throws MessageNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new MessageNotFoundException("Could not find any message with ID" + id);
        }
        repo.deleteById(id);
    }
}
