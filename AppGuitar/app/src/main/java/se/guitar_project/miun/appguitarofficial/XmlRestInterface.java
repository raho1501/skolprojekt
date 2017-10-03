package se.guitar_project.miun.appguitarofficial;

import android.content.Intent;
import android.os.AsyncTask;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.net.Uri.encode;

/**
 * Created by linus on 2017-09-25.
 */

public class XmlRestInterface extends AsyncTask<String, Intent, String> {

    private String method = "GET";
    private XmlDocument param = new XmlDocument();
    public interface AsyncResponse
    {
        void processFinish(String output);
    }
    public AsyncResponse delegate = null;
    public XmlRestInterface(String httpMethod, AsyncResponse delegate)
    {
        method = httpMethod;
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... urls)
    {
        String content = "";
        int test = urls.length;
        String arr[] = new String[test]; //TODO returnera en sånhär istället.
        try
        {
            if(method == "GET")
            {
                int size = urls.length;
                URL url = null;
                for (int i = 0; i < size; i++) {
                    url = new URL(urls[i]);
                    content = getContentWithHttp(url); //TODO verkar kunna vara en array av in argument. fixa så den ger tillbaks en lista.
                }
            }
            else if(method == "POST")
            {
                int size = urls.length;
                URL url = null;
                for (int i = 0; i < size; i++) {
                    url = new URL(urls[i]);
                    content = setContentWithHttp(url); //TODO verkar kunna vara en array av in argument. fixa så den ger tillbaks en lista.
                }
            }
        }
        catch(Exception e)
        {
            content = e.toString();
        }
        return content;
    }
    @Override
    protected void onPostExecute(String string)
    {
        delegate.processFinish(string);
    }
    private String getContentWithHttp(URL url) throws IOException
    {
        String content = "";
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        InputStream input = connection.getInputStream();
        InputStreamReader inputReader = new InputStreamReader(input);

        int temp = 0;
        while((temp = inputReader.read()) != -1)
        {
            content += (char)temp;
        }
        return content;
    }
    private String setContentWithHttp(URL url) throws IOException
    {
        String content = "";
        String paramString = param.toString();
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/xml");
        connection.setRequestProperty("Content-Length", String.valueOf(paramString.length()));
        OutputStream output = connection.getOutputStream();
        output.write(paramString.getBytes());
        content = connection.getResponseMessage();
        return content;
    }
    public void setParam(XmlDocument xml) //TODO ta in ett helt gäng med sånhär med "..." notationen.
    {
        param = xml;
    }
}