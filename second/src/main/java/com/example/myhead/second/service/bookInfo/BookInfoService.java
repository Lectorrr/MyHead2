package com.example.myhead.second.service.bookInfo;

import com.example.myhead.second.core.base.BaseService;
import com.example.myhead.second.dao.bookInfo.BookInfoDao;
import com.example.myhead.second.entity.bookInfo.BookInfo;
import com.example.myhead.second.entity.bookInfo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookInfoService extends BaseService<BookInfo> {

    @Autowired
    private BookInfoDao bookInfoDao;


    public List<BookInfo> findListByBookName(String bookName) {
        return bookInfoDao.findByBookName(bookName);
    }

    public List<BookInfo> findByPublish(String publish) {
        return bookInfoDao.findByPublish(publish);
    }

    public List<BookInfo> findByAuthor(String author) {
        return bookInfoDao.findByAuthor(author);
    }

    public List<BookInfo> findAllByCategory(Category category){
        return bookInfoDao.findAllByCategory(category);
    }

    public List<BookInfo> findAllByBookNameLikeOrAuthorLike(String bookName, String author){
        return bookInfoDao.findAllByBookNameLikeOrAuthorLike(bookName, author);
    }
}
