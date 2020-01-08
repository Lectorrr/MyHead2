package com.example.myhead.second.entity.bookInfo;

import com.example.myhead.second.core.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "bookInfo")
public class BookInfo extends BaseEntity<String> {

    /**
     * 书籍名称
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 作者姓名
     */
    @Column(name = "author")
    private String author;

    /**
     * 出版社名称
     */
    @Column(name = "publish")
    private String publish;

    /**
     * 封面图片
     */
    @Column(name = "cover_img")
    private String coverImg;

    /**
     * 书籍简介
     */
    @Column(name = "description")
    private String description;

    /**
     * 书籍分类
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_bookInfo_category",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "UUID")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "UUID")}
    )
    private Category category;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
