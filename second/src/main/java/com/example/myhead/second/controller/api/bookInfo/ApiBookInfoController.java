package com.example.myhead.second.controller.api.bookInfo;

import com.example.myhead.second.core.base.BaseController;
import com.example.myhead.second.entity.bookInfo.BookInfo;
import com.example.myhead.second.service.bookInfo.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/api/bookInfo")
public class ApiBookInfoController extends BaseController<BookInfo, String> {

    @Autowired
    private BookInfoService bookInfoService;

    @Override
    public String getPathPrefix() {
        return "/bookInfo";
    }

    @RequestMapping(value = "/showList")
    public List<BookInfo> showList() {
        return bookInfoService.showList();
    }
}
