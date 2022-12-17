package com.mycompany.message;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "message_test")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 5000, nullable = false, name = "content")
    private String content;

    @Column(length = 45, nullable = false, name = "author")
    private String author;

    @Column(length = 45, nullable = false, name = "theme")
    private String theme;

    public Integer getId(){return this.id;}

    public void setId(Integer id){this.id = id;}

    public String getContent(){return this.content;}

    public void setContent(String content){this.content = content;}

    public String getAuthor(){return this.author;}

    public void setAuthor(String author){this.author = author;}

    public String getTheme(){return this.theme;}

    public void setTheme(String theme){this.theme = theme;}

    @Override
    public String toString() {
        return "Message{" +
                "id=" + getId() +
                ", content=" + getContent() + '\'' +
                ", author=" + getAuthor() + '\'' +
                ", theme=" + getTheme() + '\'' +
                '}';
    }
}
