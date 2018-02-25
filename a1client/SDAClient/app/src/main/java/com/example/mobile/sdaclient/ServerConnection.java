package com.example.mobile.sdaclient;

import android.os.StrictMode;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.MediaType;
/**
 * Created by kellymaclauchlan on 2018-02-23.
 */

public class ServerConnection {
    //https://developer.android.com/training/volley/simple.html
    //that resource should help we should be able to use most of what we did with the bank client i think
    String ipadd="http://localhost:8080/";
    String baseUrl="COMP4601-SDA/rest/sda";
    private static String REST = "rest";
    private static String SDA = "sda";
    String compleatURL=ipadd+baseUrl;
    String delete="/delete";
    String search="/search";
    String reset="/reset";
    String list="/list";
    String pagerank="/pagerank";
    String boost="/boost";
    private static String BASE_URL = "http://192.168.1.5:8080/COMP4601-SDA";

    public ServerConnection() {

    }

    //    String url ="http://www.google.com";
//
//    public String getURLPAGE(){
//        // Request a string response from the provided URL.
//        // Instantiate the RequestQueue.
//        String result="";
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url ="http://www.google.com";
//         stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        result="Response is: "+ response.substring(0,500);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                result="That didn't work!";
//            }
//        });
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);
//
//    }
    public String getMainPage(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        System.out.print("here");
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(getBaseURI());

        return service.path(REST).path(SDA)
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    private static URI getBaseURI() {
        return UriBuilder.fromUri(BASE_URL).build();
    }
}
