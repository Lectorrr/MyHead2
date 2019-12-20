package com.example.myhead.second.dao.bookInfo;

import com.example.myhead.second.core.base.BaseDao;
import com.example.myhead.second.entity.bookInfo.BookInfo;

import java.util.List;

public interface BookInfoDao extends BaseDao<BookInfo, String> {

    BookInfo findBydBookName(String bookName);
    List<BookInfo> findByBookName(String bookName);

    List<BookInfo> findByPublish(String publish);
}
