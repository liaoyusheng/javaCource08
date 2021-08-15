package com.lys;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClient {

    public static void main(String [] art) throws IOException {
        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
        HttpGet httpGet=new HttpGet("http://localhost:8801");
        CloseableHttpResponse response =httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if(entity!=null){
            System.out.println(EntityUtils.toString(entity));
        }

    }
}
