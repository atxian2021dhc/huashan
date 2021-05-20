package com.itheima.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
//@RequestMapping("/user")
public class Usercontroller {

    /**
     *
     * 1、页面跳转：直接返回字符串、通过modelandview对象
     * 2、回写数据：直接返回字符串、返回对象/集合
     */

    //1、返回字符串
    //@RequestMapping:请求地址 http://localhost:8080/xxx/quick
                                              //默认get请求，如果这里这里设置成post请求，访问会报错
                                                                    //param：限制请求参数条件
//    @RequestMapping(value = "/quick",method = RequestMethod.GET,params = {"username=233"})
    @RequestMapping("/quick")
    public String save(){
        System.out.println("Controller save running");
        return "success";//注意：如果不加 / ，那么success.jsp就是相对资源所在地址，那就是我们请求的地址http://localhost:8080/xxx
    }


    //2、返回 modelandview
    @RequestMapping("/quick2")
    public ModelAndView save2(ModelAndView model){
        /**
         * Model:模型 封装数据
         * view: 页面 展示数据
         */
        //设置模型数据
        model.addObject("username","itcast2");
        //设置视图名称
        model.setViewName("success");
        return model;
    }

    //3、
    @RequestMapping("/quick3")
    public String save3(Model model){
        /**
         * Model:模型 封装数据
         */
        //设置模型数据
        model.addAttribute("username","itcast3");
        return "success";
    }

    //4、不常用
    @RequestMapping("/quick4")
    public String save4(HttpServletRequest request){
        //给域中存对象
        request.setAttribute("username","itcast4");
        return "success";
    }

    /*
    2、回写数据：直接返回字符串、返回对象/集合
    */
    //response.getWrite().print("xxxx")

    @RequestMapping("/quick5")
    public void save5(HttpServletResponse response) throws IOException {
        response.getWriter().print("hello quick5");
    }

    // @ResponseBody将方法的返回值，以特定的格式写入到response的body区域，进而将数据返回给客户端
    //在使用 @RequestMapping后，返回值通常解析为跳转路径，但是加上 @ResponseBody 后返回结果不会被解析为跳转路径，
    // 而是直接写入 HTTP response body 中。 比如异步获取 json 数据，加上 @ResponseBody 后，会直接返回 json 数据。
    // @RequestBody 将 HTTP 请求正文插入方法中，使用适合的 HttpMessageConverter 将请求体写入某个对象。

    @RequestMapping("/quick6")
    @ResponseBody
    public String save6(){
        return "hello quick6";
    }

    //返回json字符串
    @RequestMapping("/quick7")
    @ResponseBody
    public String save7() throws JsonProcessingException {
        User user = new User();
        user.setUsername("lisi");
        user.setAge("18");
        //使用json转换工具将对象转换成json字符串返回
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        return json;
    }

    //返回json字符串
    @RequestMapping("/quick8")
    @ResponseBody
    public User save8(){
        User user = new User();
        user.setUsername("lisi");
        user.setAge("18");
        return user;
    }
}
