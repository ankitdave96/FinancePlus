package example.com.finance;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonClass1 {
    String result;
    public String getData(String url)
    {

        try {
            URL Url=new URL(url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)Url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int id=httpURLConnection.getResponseCode();
            if(id==200)
            {
                InputStream in=new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader br=new BufferedReader(new InputStreamReader(in));
                StringBuilder sb=new StringBuilder();
                String line;

                while((line=br.readLine())!=null)
                {
                    sb.append(line);
                }
                result=sb.toString();

                Log.d("data is coming",result);

            }
            httpURLConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  result;
    }
}
