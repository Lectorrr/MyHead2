package com.example.myhead.second.dao.bookInfo;

import com.example.myhead.second.core.base.BaseDao;
import com.example.myhead.second.entity.bookInfo.BookInfo;

import java.awt.print.Book;
import java.util.List;

public interface BookInfoDao extends BaseDao<BookInfo, String> {

    /**
     * 根据书名查找书籍信息
     *
     * @param bookName 书名
     */
    List<BookInfo> findByBookName(String bookName);

    /**
     * 根据出版社查找书籍信息
     *
     * @param publish 出版社名称
     */
    List<BookInfo> findByPublish(String publish);

    /**
     * 根据书名或者作者名，模糊查找所有信息
     *
     * @param bookName 书名
     * @param author   作者名
     */
    List<BookInfo> findAllByBookAndBookNameOrAndAuthorLike(String bookName, String author);

    /**
     * 根据作者名查找书籍信息
     *
     * @param author 作者名
     */
    List<BookInfo> findByAuthor(String author);
}
