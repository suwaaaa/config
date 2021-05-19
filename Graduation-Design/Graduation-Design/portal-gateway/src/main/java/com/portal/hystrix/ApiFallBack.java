package com.portal.hystrix;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ApiFallBack implements FallbackProvider {
    @Override
    public String getRoute() {
        //api服务id，如果需要所有调用都支持回退，则return "*"或return null
        return null;
    }



    /**
     * 如果请求用户服务失败，返回什么信息给消费者客户端
     **/
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
    return new ClientHttpResponse() {

      /**
       * 网关向api服务请求是失败了，但是消费者客户端向网关发起的请求是OK的，
       * 不应该把api的404,500等问题抛给客户端 网关和api服务集群对于客户端来说是黑盒子
       *
       * @return
       * @throws IOException
       */
      @Override
      public HttpStatus getStatusCode() throws IOException {
        return HttpStatus.OK;
      }

      @Override
      public int getRawStatusCode() throws IOException {
        return HttpStatus.OK.value();
      }

      @Override
      public String getStatusText() throws IOException {
        return HttpStatus.OK.getReasonPhrase();
      }

      @Override
      public void close() {}

      @Override
      public InputStream getBody() throws IOException {
        JSONObject r = new JSONObject();
          try {
              r.put("state", "9999");
          } catch (JSONException e) {
              e.printStackTrace();
          }
          try {
              r.put("msg", "系统忙");
          } catch (JSONException e) {
              e.printStackTrace();
          }
          return new ByteArrayInputStream(r.toString().getBytes("UTF-8"));
      }

      @Override
      public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        // 和body中的内容编码一致，否则容易乱码
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
      }
    };
    }
}
