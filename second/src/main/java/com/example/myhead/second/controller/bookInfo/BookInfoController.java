package com.example.myhead.second.controller.bookInfo;

import com.example.myhead.second.core.base.BaseController;
import com.example.myhead.second.entity.bookInfo.BookInfo;
import com.example.myhead.second.service.bookInfo.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/bookInfo")
public class BookInfoController extends BaseController<BookInfo, String> {

    @Autowired
    private BookInfoService bookInfoService;

    @Override
    public String getPathPrefix() {
        return "/bookInfo";
    }
}
