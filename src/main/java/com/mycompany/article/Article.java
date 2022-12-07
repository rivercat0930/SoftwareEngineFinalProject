package com.mycompany.article;

import javax.persistence.*;

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

    public Integer getId(){return this.id;}

    public void setId(Integer id){this.id = id;}

    public String getTheme(){return this.theme;}

    public void setTheme(String theme){this.theme = theme;}

    public String getContent(){return this.content;}

    public void setContent(String content){this.content = content;}

    public String getAuthor(){return this.author;}

    public void setAuthor(String author){this.author = author;}

    @Override
    public String toString() {
        return "Article{" +
                "id=" + getId() +
                ", theme=" + getTheme() + + '\'' +
                ", content=" + getContent() + '\'' +
                ", author=" + getAuthor() + '\'' +
                '}';
    }

}
