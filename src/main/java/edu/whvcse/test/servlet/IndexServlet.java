package edu.whvcse.test.servlet;

import edu.whvcse.test.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns ={"/index","/login"})
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String m=request.getParameter("m");
        if(m==null){
            index(request, response);
        }else if("login".equals(m)){
            login(request, response);
        }
    }
    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        response.setCharacterEncoding("utf-8");
        context.setVariable("msg","请提交姓名");
        engine.process("index.html", context, response.getWriter());
    }
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("name")!=null&&!request.getParameter("name").equals("")){
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            WebContext context = new WebContext(request, response, request.getServletContext());
            response.setCharacterEncoding("utf-8");
           // context.setVariable("name",request.getParameter("name"));
            context.setVariable("msg","欢迎"+request.getParameter("name"));
            engine.process("index.html", context, response.getWriter());
        }else{
            index(request,response);
        }

    }
}
