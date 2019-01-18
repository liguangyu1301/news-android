package com.li.yu.mvc.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @class name：com.zhang.chao.mvc.utils
 * @class describe:
 * @anthor zhang
 * @time 2019/1/2
 */
public class HttpStatusInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if(response!=null){
            if(response.code()==403){
               response.sentRequestAtMillis();
            }
        }
        return response;
    }
}
