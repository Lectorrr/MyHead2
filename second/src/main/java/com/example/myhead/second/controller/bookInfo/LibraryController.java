package com.example.myhead.second.controller.bookInfo;

import com.example.myhead.second.common.entity.ResultData;
import com.example.myhead.second.core.base.BaseController;
import com.example.myhead.second.entity.bookInfo.BookInfo;
import com.example.myhead.second.service.bookInfo.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/bookInfo")
public class LibraryController extends BaseController<BookInfo, String> {

    @Autowired
    private BookInfoService bookInfoService;

    /**
     * showList界面的数据
     *
     * @param request http请求
     */
    @RequestMapping(value = "/listData", method = RequestMethod.GET)
    @ResponseBody
    public Object listData(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        ResultData<BookInfo> bookInfoResultData = bookInfoService.findWithPage(parameterMap, BookInfo.class);
        return bookInfoResultData;
    }
}
