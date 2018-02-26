package com.example.mobile.sdaclient;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;// = (Spinner) findViewById(R.id.spinner);
    EditText text1 ;//= (EditText) findViewById(R.id.editText) ;
    EditText text2;// = (EditText) findViewById(R.id.editText2) ;
    EditText text3 ;//= (EditText) findViewById(R.id.editText3) ;
    EditText text4 ;//= (EditText) findViewById(R.id.editText4) ;
    EditText text5 ;//= (EditText) findViewById(R.id.editText3) ;
    EditText text6 ;
    EditText textResult ;
    Button go;//=(Button)findViewById(R.id.button);
    ServerConnection server;
    int endpoint=0;
    // Create an ArrayAdapter using the string array and a default spinner layout
//    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//            R.array.endpoints_array, android.R.layout.simple_spinner_item);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner);
        text1 = (EditText) findViewById(R.id.editText) ;
         text2 = (EditText) findViewById(R.id.editText2) ;
         text3 = (EditText) findViewById(R.id.editText3) ;
         text4 = (EditText) findViewById(R.id.editText4) ;
         text5=(EditText)findViewById(R.id.editText5);
        text6=(EditText)findViewById(R.id.editText6);
         go=(Button)findViewById(R.id.button);
        WebView webview = (WebView)findViewById(R.id.webView);
        webview.setVisibility(View.INVISIBLE);
//        ServerConnection server;
//        int endpoint=0;
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.endpoints_array, android.R.layout.simple_spinner_item);

        server= new ServerConnection();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // in here use the end point to call the correct function in the server class that will talk to the server
                String response;//=server.getMainPage();


                switch (endpoint) {
                    case 0:
                        response=server.view(text1.getText().toString());
                        break;
                    case 1:  response=server.deleteDocument(text1.getText().toString());
                        break;
                    case 2: response=server.newDocument(text1.getText().toString(),text2.getText().toString(),"","",
                        "","");
                        break;
                    case 3:  response=server.deleteDocuments(text4.getText().toString());
                        break;
                    case 4:  response=server.getDocuments();
                        break;
                    case 5: response=server.searchDocuments(text4.getText().toString());
                        break;
                    case 6:  response=server.reset();
                        break;
                    case 7:  response=server.getList();
                        break;
                    case 8:  response=server.pagerank();
                        break;
                    case 9:  response=server.boost();
                        break;
                    case 10: response=server.update(text1.getText().toString(),text2.getText().toString(),text3.getText().toString(),text6.getText().toString(),
                            text4.getText().toString(),text5.getText().toString());
                        break;
                    case 11:  response=server.register();
                        break;
                    case 12: response=server.unregister();
                        break;
                    case 13:  response=server.crawl();
                        break;
                    case 14:  response=server.simpleQuerry(text4.getText().toString());
                        break;
                    case 15: response=server.querry(text4.getText().toString());
                        break;
                    case 16:  response=server.noboost();
                        break;
                    case 17:  response=server.viewGraph();
                        break;
                    case 18: response=server.deleteQuerry(text4.getText().toString());
                        break;
                    case 19: response = server.getMainPage();
                    default: response=server.getMainPage();

                        break;
                }
                //textResult.setText(response);
                WebView webview = (WebView)findViewById(R.id.webView);
                webview.setVisibility(View.VISIBLE);
                webview.bringToFront();
                webview.getSettings().setJavaScriptEnabled(true);
                webview.loadDataWithBaseURL("", response, "text/html", "UTF-8", "");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
/*<item>View Document</item>
        <item>Delete Document</item>
        <item>New Document</item>
        <item>Delete By Tags</item>
        <item>List All Documents</item>
        <item>Search</item>
        <item>Reset</item>
        <item>List</item>
        <item>PageRank</item>
        <item>Boost</item>
        <item>Update</item>*/
        WebView webview = (WebView)findViewById(R.id.webView);
        webview.setVisibility(View.INVISIBLE);
        endpoint=pos;
        switch (pos) {
            case 0:
                    showIDOnly();
                break;
            case 1:  showIDOnly();
                break;
            case 2: showAll();
                break;
            case 3:  showTagsOnly();
                break;
            case 4:  showNone();
                break;
            case 5: showTagsOnly();
                break;
            case 6:  showNone();
                break;
            case 7:  showNone();
                break;
            case 8:  showNone();
                break;
            case 9:  showNone();
                break;
            case 10: showNone();
                break;
            case 11:  showAll();
                break;
            case 12: showNone();
                break;
            case 13:  showNone();
                break;
            case 14:  showTagsOnly();
                break;
            case 15: showTagsOnly();
                break;
            case 16:  showNone();
                break;
            case 17:  showNone();
                break;
            case 18:  showTagsOnly();
                break;
            case 19:  showNone();
                break;
            default: showTagsOnly();
                break;
        }
    }
    public void showIDOnly(){
        text1.setVisibility(View.VISIBLE);
        text2.setVisibility(View.INVISIBLE);
        text3.setVisibility(View.INVISIBLE);
        text4.setVisibility(View.INVISIBLE);
        text6.setVisibility(View.INVISIBLE);
        text5.setVisibility(View.INVISIBLE);
    }
    public void showTagsOnly(){
        text1.setVisibility(View.INVISIBLE);
        text2.setVisibility(View.INVISIBLE);
        text3.setVisibility(View.INVISIBLE);
        text4.setVisibility(View.VISIBLE);
        text5.setVisibility(View.INVISIBLE);
        text6.setVisibility(View.INVISIBLE);
    }
    public void showAll(){
        text1.setVisibility(View.VISIBLE);
        text2.setVisibility(View.VISIBLE);
        text3.setVisibility(View.VISIBLE);
        text4.setVisibility(View.VISIBLE);
        text5.setVisibility(View.VISIBLE);
        text6.setVisibility(View.VISIBLE);
    }
    public void showNone(){
        text1.setVisibility(View.INVISIBLE);
        text2.setVisibility(View.INVISIBLE);
        text3.setVisibility(View.INVISIBLE);
        text4.setVisibility(View.INVISIBLE);
        text5.setVisibility(View.INVISIBLE);
        text6.setVisibility(View.INVISIBLE);

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
