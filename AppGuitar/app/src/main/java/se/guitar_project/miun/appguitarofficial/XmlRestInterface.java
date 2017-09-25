package se.guitar_project.miun.appguitarofficial;

import android.content.Intent;
import android.os.AsyncTask;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by linus on 2017-09-25.
 */

public class XmlRestInterface extends AsyncTask<String, Intent, String> {

    public interface AsyncResponse
    {
        void processFinish(String output);
    }
    public AsyncResponse delegate = null;
    public XmlRestInterface(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... urls)
    {
        String content = "";
        try
        {
            int size = urls.length;
            URL url = null;
            for (int i = 0; i < size; i++)
            {
                url = new URL(urls[i]);
                content = getContentFromHttp(url);
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
    private String getContentFromHttp(URL url) throws IOException
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
}
