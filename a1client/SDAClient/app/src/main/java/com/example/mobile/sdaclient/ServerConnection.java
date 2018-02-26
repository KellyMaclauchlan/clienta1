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
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.api.client.ClientResponse;
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
    String delete="delete";
    String documents="documents";
    String search="search";
    String reset="reset";
    String list="list";
    String pagerank="pagerank";
    String boost="boost";
    String noboost="noboost";
    String update="update";
    private static String BASE_URL = "http://192.168.1.5:8080/COMP4601-SDA"; //<---- change to ip adress desired here
    WebResource service;
    Client client;
    ClientConfig config;

    public ServerConnection() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        config = new DefaultClientConfig();
        client = Client.create(config);
        service = client.resource(getBaseURI());
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

        return service.path(REST).path(SDA)
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String getDocument(String id){
        return service.path(REST).path(SDA+ "/" + id)
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String getDocuments(){
        return service.path(REST).path(SDA).path(documents)
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String getList(){
        return service.path(REST).path(SDA).path(list)
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String deleteDocument(String id){
        return service.path(REST).path(SDA+ "/" + id)
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String newDocument(String id,String name, String url, String text, String tags, String links){

        Form form = new Form();
        form.add("id", Integer.valueOf(id));
        form.add("name", name);
        form.add("url", url);
        form.add("text", text);
        form.add("tags", tags.split(" "));
        form.add("links", links.split(" "));
        form.add("score", 1);

        return String.valueOf(service.path(REST).path(SDA+ "/" + id)
                .type(MediaType.APPLICATION_FORM_URLENCODED)
                .post(ClientResponse.class, form));
    }
    public String deleteDocuments(String tags){

        return service.path(REST).path(SDA).path(delete+"/"+tags)
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String searchDocuments(String tags){

        return service.path(REST).path(SDA).path(search+"/"+tags)
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String reset(){
        return service.path(REST).path(SDA).path(reset)
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String boost(){
        return service.path(REST).path(SDA).path(boost)
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String pagerank(){
        return service.path(REST).path(SDA).path(pagerank)
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String update(String id,String name, String url, String text, String tags, String links){
       //there is also a score
        Form form = new Form();
        form.add("id", Integer.valueOf(id));
        form.add("name", name);
        form.add("url", url);
        form.add("text", text);
        form.add("tags", tags.split(" "));
        form.add("links", links.split(" "));
        form.add("score", 1);

        return String.valueOf(service.path(REST).path(SDA).path(update)
                .type(MediaType.APPLICATION_FORM_URLENCODED)
                .post(ClientResponse.class, form));
//
//        return service.path(REST).path(SDA).path(update)
//                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String noboost(){
        return service.path(REST).path(SDA).path(noboost)
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String register(){
        return service.path(REST).path(SDA).path("register")
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String unregister(){
        return service.path(REST).path(SDA).path("unregister")
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String view(String id){
        return service.path(REST).path(SDA).path("view/"+id)
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String crawl(){
        return service.path(REST).path(SDA).path("crawl")
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String simpleQuerry(String terms){
        return service.path(REST).path(SDA).path("simplequery/"+ terms)
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String querry(String terms){
        return service.path(REST).path(SDA).path("query/"+terms)
                .accept(MediaType.TEXT_HTML).get(String.class);
    }
    public String viewGraph(){
        return service.path(REST).path(SDA).path("viewgraph")
                .accept(MediaType.TEXT_HTML).get(String.class);
    }

    public String deleteQuerry(String query){
        Form form = new Form();
        form.add("query", query);

        return String.valueOf(service.path(REST).path(SDA).path("deletequery")
                .type(MediaType.APPLICATION_FORM_URLENCODED)
                .post(ClientResponse.class, form));
    }


    private static URI getBaseURI() {
        return UriBuilder.fromUri(BASE_URL).build();
    }
}
