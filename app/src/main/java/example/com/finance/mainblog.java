package example.com.finance;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import example.com.finance.model.blogModel;

public class mainblog extends AppCompatActivity {
    public ListView lv;
    public blogModel lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainblog);
// Create default options which will be used for every
//  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()

                .cacheInMemory(true)
                .cacheOnDisk(true)

                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())

                .defaultDisplayImageOptions(defaultOptions)

                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start


        //Declare the button
        //Button Retrieve = (Button)findViewById(R.id.hit);


        //Declare the list view
        lv = (ListView)findViewById(R.id.lv);

        //Set what ha sto be done when we click on the button

        //Retrieve.setOnClickListener(new View.OnClickListener() {
        //@Override
        //public void onClick(View view) {

        new JSONTask().execute("http://financeplusgo.esy.es/blog.php");

        //}
        // });



    }

    //Now we declare the task that has the task of getting the data frrom our hostinger remote server
    //password
    //pallavi-08

    class JSONTask extends AsyncTask<String, String, List<blogModel>> {


        @Override
        protected List<blogModel> doInBackground(String... param) {


            HttpURLConnection conn = null;


            BufferedReader reader = null;


            try {

                URL url = new URL(param[0]);
                conn = (HttpURLConnection) url.openConnection();

                conn.connect();


                InputStream stream = conn.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null) {

                    buffer.append(line);
                }

                String FinalBuffer = buffer.toString();



                try {
                    JSONObject ParentObject = new JSONObject(FinalBuffer);

                    JSONArray Arr = ParentObject.getJSONArray("blog");

                    List<blogModel> BlogModelList = new ArrayList<>();

                    Gson gson = new Gson();




                    for (int i=0; i<Arr.length();i++)
                    {


                        JSONObject FinalObject = Arr.getJSONObject(i);

                        lm = gson.fromJson(FinalObject.toString(), blogModel.class);




                       /* lm.setTopic(FinalObject.getString("topic"));

                        lm.setBlog_name(FinalObject.getString("blog_name"));

                        lm.setSub(FinalObject.getString("subject"));

                        lm.setLikes_count(FinalObject.getInt("likes"));

                        lm.setText(FinalObject.getString("main_text"));

                        lm.setImage(FinalObject.getString("image"));*/







                        BlogModelList.add(lm);


                    }


                    return BlogModelList;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                catch(Resources.NotFoundException e)
                {
                    e.printStackTrace();
                }





            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }







            finally {
                if (conn != null) {

                    conn.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(final List<blogModel> result) {
            super.onPostExecute(result);

            // Basically we have to set the display paramaeters here

            //Blog_Adapter adapter = new Blog_Adapter(getApplicationContext(),R.layout.row,result);
            //lv.setAdapter(adapter);

            if(result != null) {
                Blog_Adapter adapter = new Blog_Adapter(getApplicationContext(), R.layout.row, result);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void  onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        lm = result.get(position);
                        Intent intent = new Intent(mainblog.this, DetailedActivity.class);
                        intent.putExtra("blog", new Gson().toJson(lm));
                        startActivity(intent);
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }
        }
    }



    public class Blog_Adapter extends ArrayAdapter {

        private List<blogModel> BlogModelList;
        private int resource;
        private LayoutInflater inflater;

        public Blog_Adapter(Context context, int resource, List<blogModel> objects) {
            super(context, resource, objects);
            BlogModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
            {
                convertView  = inflater.inflate(resource,null);
            }

            TextView ta;
            TextView tb;
            TextView tc;
            TextView td;
            TextView tl;
            ImageView iv;


            ta=(TextView) convertView.findViewById(R.id.topic);
            tb=(TextView) convertView.findViewById(R.id.blog_name);
            tl=(TextView) convertView.findViewById(R.id.like_count);
            tc=(TextView) convertView.findViewById(R.id.sub);
            td=(TextView) convertView.findViewById(R.id.blog_text);
            iv=(ImageView)convertView.findViewById(R.id.imageView3) ;





            ImageLoader.getInstance().displayImage(BlogModelList.get(position).getImage(), iv);
            ta.setText(BlogModelList.get(position).getTopic());
            tb.setText(BlogModelList.get(position).getBlog_name());
            tc.setText(BlogModelList.get(position).getSubject());
            td.setText(BlogModelList.get(position).getMain_text());
            tl.setText(Integer.toString(BlogModelList.get(position).getLikes()));




            return convertView;
        }
    }

}
