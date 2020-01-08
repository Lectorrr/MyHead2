package com.example.myhead.second.controller;

import com.example.myhead.second.common.entity.Result;
import com.example.myhead.second.entity.sys.SysUser;
import com.example.myhead.second.service.sys.SysUserService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.MapSession;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping(value = "/api")
public class LoginController {

    @Autowired
    private Producer producer;

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setHeader("Cache-Control", "no-store");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();

        //生成图片验证码
        BufferedImage image = producer.createImage(text);

        //保存到验证码到 session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody SysUser requestUser) {

        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        String password = requestUser.getPassword();
        Result result = new Result();

        if (!sysUserService.isExist(username)){
            result.setCode(400);
            return result;
        }
        SysUser sysUser = sysUserService.findByUsernameAndPassword(username, password);
        if (null == sysUser) {
            result.setCode(400);
            return result;
        } else {
            Session session = new MapSession();
            session.setAttribute("user", sysUser);
            result.setCode(200);
            return result;
        }
    }
}
