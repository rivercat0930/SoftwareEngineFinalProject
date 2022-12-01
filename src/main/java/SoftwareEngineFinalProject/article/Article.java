package SoftwareEngineFinalProject.article;

import jakarta.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "article_test")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, name = "theme")
    private String theme;

    @Column(length = 5000, nullable = false, name = "content")
    private String content;

    @Column(length = 45, nullable = false, name = "author")
    private String author;

    public Integer getId() {
        return id;
    }

    public String getTheme() {
        return theme;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + getId() +
                ", theme='" + getTheme() + '\'' +
                ", content='" + getContent() + '\'' +
                ", author='" + getAuthor() + '\'' +
                "}";
    }
}
