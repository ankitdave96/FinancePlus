package example.com.finance;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import example.com.finance.model.blogModel1;

public class news extends AppCompatActivity {
    ArrayList<blogModel1> BlogModelList = new ArrayList<>();
    public ListView lv;
    public blogModel1 lm;
    Blog_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
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
        lv = (ListView) findViewById(R.id.lv);
        CrawlData cd=new CrawlData();
        cd.execute();
       /* try {
            //processData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/


        //}


        //private void processData() throws IOException, ClassNotFoundException, SQLException {
        /*try {

        Document doc = null;
        try {
            doc = Jsoup.connect("http://in.reuters.com/news/archive/economicNews?view=page&page=2&pageSize=10").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements img = doc.select("div.story-photo lazy-photo ");
                Elements di = doc.select("div.story-content h3");
                for (Element q : di) {
                    lm.setTopic(q.text());
                    BlogModelList.add(lm);


                }


        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    lv.setAdapter(adapter);
*/
    }


    public class CrawlData extends AsyncTask
    {
        /*ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(getApplicationContext());
            pd.setMessage("Loading Images");
            pd.setCancelable(false);
            pd.show();
        }*/

        /*ArrayList<String> al=new ArrayList<>();*/
        @Override
        protected Object doInBackground(Object[] objects) {


            try {


                try {
                    for(int i=3;i<30;i++) {
                        Document doc= Jsoup.connect("http://in.reuters.com/news/archive/economicNews?view=page&page="+i+"&pageSize=10").get();
                        Elements di = doc.select("div.story-content");
                        Elements ki = di.select("h3");
                        Elements gi = di.select("p");
                        Elements ii=doc.select("div.story-photo");
                        ArrayList<String> imgs=new ArrayList<>();
                        for(Element im:ii)
                        {
                            Elements iia=im.select("a");
                            for(Element e:iia)
                            {
                                Elements img=iia.select("img");
                                for(Element t:img)
                                {String src=img.attr("org-src");
                                    imgs.add(src);
                                    Log.d("img " ,src);
                                }
                            }
                        }
                        ArrayList<String> t=new ArrayList<>();
                        ArrayList<String> ta=new ArrayList<>();

                        for(Element q : ki ) {
                            t.add(q.text());
/*                          lm.setTopic(q.text());*/
                            //lm.setMain_text(p.text())

                              /*BlogModelList.add(lm);*/
                        }
                        for(Element y:gi)
                        {
                            ta.add(y.text());

                        }

                        int size;
                        if(t.size()>ta.size())
                        {
                            size=ta.size();
                        }
                        else
                        {
                            size=t.size();
                        }

                        for(int j=0;j<size;j++)
                        {
                            lm=new blogModel1();
                            lm.setTopic(t.get(j));
                            lm.setMain_text(ta.get(j));
                            lm.setImage(imgs.get(j));
                            BlogModelList.add(lm);
                            Log.d("status","blogmodel added : "+j);
                        }

                    }


                }
                catch (Exception ex)
                {
                    ex.printStackTrace();

                }
/*                Elements img = doc.select("div.story-photo lazy-photo ");*/




            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
          /*  if(pd.isShowing())
                pd.hide();*/
            adapter = new Blog_Adapter(getApplicationContext(), R.layout.row1, BlogModelList);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void  onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    lm = BlogModelList.get(position);
                    Intent intent = new Intent(news.this, DetailActivity.class);
                    intent.putExtra("blog",new Gson().toJson(lm));
                    startActivity(intent);
                }
            });

        }
    }






    public class Blog_Adapter extends ArrayAdapter {

        private List<blogModel1> BlogModelList;
        private int resource;
        private LayoutInflater inflater;

        public Blog_Adapter(Context context, int resource, List<blogModel1> objects) {
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
                convertView  =inflater.inflate(resource,null);
            }

            TextView ta;

            TextView td;

            ImageView iv;


            ta=(TextView) convertView.findViewById(R.id.topic);

            td=(TextView) convertView.findViewById(R.id.blog_text);

            iv=(ImageView)convertView.findViewById(R.id.imageView3) ;





            ImageLoader.getInstance().displayImage(BlogModelList.get(position).getImage(), iv);
            ta.setText(BlogModelList.get(position).getTopic());
            td.setText(BlogModelList.get(position).getMain_text());





            return convertView;
        }
    }

}
