package com.swagger.handler;

import com.swagger.config.BaseCont;
import com.swagger.http.HttpAPIService;
import com.swagger.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.springframework.http.HttpMethod.resolve;

/**
 * @Description: 请求handler
 * @Author 袁伟倩
 * @Date 2018-6-710:48
 * @Version 1.0
 */
@Component
public class RequestHandler implements HandlerInterceptor {
//
//    @Resource
//    private HttpAPIService httpAPIService;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    BaseCont baseCont;
    /*
     * 视图渲染之后的操作
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }

    /*
     * 处理请求完成后视图渲染之前的处理操作
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {

    }

    /*
     * 进入controller层之前拦截请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        //获取请求方式
//        System.out.println(request.getMethod());
//
//        //获取请求参数
//        System.out.println(request.getQueryString());
//        //获取项目名称
//        System.out.println("getContextPath:" + request.getContextPath());
//        System.out.println("getServletPath:" + request.getServletPath());
//        //获取除了域名外的请求数据
//        System.out.println("getRequestURI:" + request.getRequestURI());
//        //获取完整请求路径
//        System.out.println("getRequestURL:" + request.getRequestURL());
//        System.out.println("getRealPath:" + request.getSession().getServletContext().getRealPath("image"));
        boolean flg = baseCont.getFlg(); // 模拟数据标识
        if(flg){
            String easy_mock_url = "http://127.0.0.1:7300/mock/5b165e823977dc3043df4072";
//            String str = "";
//            HttpResult httpResult = new HttpResult();

            // 如果为模拟数据，则跳转到easy-mock 获取数据。
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            String url = easy_mock_url+request.getRequestURI();
            RestTemplate client = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
          //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
              String key = (String) headerNames.nextElement();
              String value = request.getHeader(key);
              headers.add(key, value);
            }


           //  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
            MultiValueMap<String, String> params= getParameterMap(request);

            //  也支持中文
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
            //  执行HTTP请求
            ResponseEntity<String> res = client.exchange(url, resolve(request.getMethod()), requestEntity, String.class);
            //  输出结果

            out.print(res.getBody());

            out.flush();
            out.close();

            return false;
        }
            return true;
        }



    /**
     * 从request中获得参数Map，并返回可读的Map
     *
     * @param request
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static MultiValueMap<String, String> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        MultiValueMap<String, String> returnMap = new LinkedMultiValueMap<String, String>();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.add(name, value);
        }
        return returnMap;
    }
}