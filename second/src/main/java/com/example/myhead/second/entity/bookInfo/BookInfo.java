package com.example.myhead.second.entity.bookInfo;

import com.example.myhead.second.core.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bookInfo")
public class BookInfo extends BaseEntity<String> {

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_writer")
    private String bookWriter;

    @Column(name = "publish")
    private String publish;

    @Column(name = "cover_img")
    private String coverImg;

    @Column(name = "description")
    private String description;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookWriter() {
        return bookWriter;
    }

    public void setBookWriter(String bookWriter) {
        this.bookWriter = bookWriter;
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
}
