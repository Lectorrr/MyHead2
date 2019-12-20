package com.example.myhead.second.dao.bookInfo;

import com.example.myhead.second.core.base.BaseDao;
import com.example.myhead.second.entity.bookInfo.BookInfo;

import java.util.List;

public interface BookInfoDao extends BaseDao<BookInfo, String> {

    /**
     * 根据书名查找书籍信息
     * @param bookName 书名
     */
    BookInfo findBydBookName(String bookName);

    List<BookInfo> findByBookName(String bookName);

    /**
     * 根据出版社查找书籍信息
     * @param publish 出版社名称
     */
    List<BookInfo> findByPublish(String publish);
}
