package com.example.ban_ban_taxi.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.text.SimpleDateFormat;

@Component
@Slf4j
public class LoggerFilter implements Filter {
    // req, res 는 한번 읽게 된다면 해당 객체를 다시 읽을 수 없기 때문에 캐싱 동작이 필요함
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);
        var method = req.getMethod();
        var uri = req.getRequestURI();

        var dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss.SSS");
        var requestTime = System.currentTimeMillis();
        var reqTime = dateFormat.format(requestTime);

        chain.doFilter(req, res);// 캐싱 되어진 객체들을 넘겨준다.

        //request info
        var headerNames = req.getHeaderNames(); //key
        var headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey ->{
            var headerValue = req.getHeader(headerKey); // key 에 해당하는 값
            headerValues
                    .append("{")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append("}\n"); // append
        });

        var requestBody = new String(req.getContentAsByteArray());
        log.info(">>>>>>>>>> request \n time : {} \n method : {} \n uri : {} \n header : {} body : {}",
                reqTime , method, uri, headerValues, requestBody);


        //response info
        var resHeaderNames = res.getHeaderNames();
        var resHeaderValues = new StringBuilder();

        resHeaderNames.forEach(headerKey ->{
            var headerValue = res.getHeader(headerKey);
            resHeaderValues
                    .append("{")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append("}\n");
        });
        var turnAroundTime = getTurnAroundTimeMils(requestTime,System.currentTimeMillis());
        var responseBody = new String(res.getContentAsByteArray());
        log.info(">>>>>>>>>>> response \n time : {} \n turn-around-time : {} \n method : {} \n uri : {} \n header : {}  body : {}",
                reqTime, turnAroundTime , method, uri, headerValues, responseBody);

        res.copyBodyToResponse(); // 해당 구문을 반드시 실행해야지만 클라이언트에서 정보를 받을 수 있음 실행하지 않는다면 빈값이 반환된다.
    }


    public StringBuilder getTurnAroundTimeMils(Long reqTime, Long resTime){
        StringBuilder sb = new StringBuilder();
        sb.append(Long.toString(resTime - reqTime)).append(" ms");
        return sb;
    }


    /*
    request logging을 위해 request 내부의 Stream에서 데이터를 읽는 순간 실제
     request 내용 가지고 처리를 해야하는 Controller단에서 request 데이터를 가져올 수 없게 됩니다.
     response도 마찬가지로 client에 보낼 최종 응답 데이터를 보낼 수 없게 됩니다.

    이를 해결하기 위해 나온 것이 CotentCachingRequestWrapper, CotentCachingResponseWrapper 입니다.
    Stream에서 데이터를 읽을 때 내부에서 해당 데이터들을 임시 저장(caching 처리)해놓았다가
     실제 request, response 데이터를 가져올 때 caching해두었던 데이터들을 반환해주는 처리를 알아서 해줍니다.
    */
}
