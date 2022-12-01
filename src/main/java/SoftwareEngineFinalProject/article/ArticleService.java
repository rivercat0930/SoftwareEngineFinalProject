package SoftwareEngineFinalProject.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> listAll() {
        return (List<Article>) articleRepository.findAll();
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public Article get(Integer id) throws ArticleNotFoundException {
        Optional<Article> result = articleRepository.findById(id);

        if (result.isPresent())
            return result.get();

        throw new ArticleNotFoundException("Could not find any article with ID: " + id);
    }

    public void delete(Integer id) throws ArticleNotFoundException {
        Long count = articleRepository.countById(id);

        if (count == null || count == 0)
            throw new ArticleNotFoundException("Could not find any article with ID: " + id);

        articleRepository.deleteById(id);
    }
}
