package example.com.finance;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import example.com.finance.model.blogModel;

public class DetailedActivity extends AppCompatActivity {
    private ImageView det_img;
    private TextView det_topic;
    private TextView det_blogger;
    private TextView det_subject;
    private TextView det_text;
    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
                final RatingBar button = (RatingBar) findViewById(R.id.like);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Perform action on click


                    }
                });

                // Showing and Enabling clicks on the Home/Up button
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setDisplayShowHomeEnabled(true);
                }

                // setting up text views and stuff
                setUpUIViews();

                // recovering data from MainActivity, sent via intent
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    String json = bundle.getString("blog");
                    blogModel movieModel = new Gson().fromJson(json, blogModel.class);

                    // Then later, when you want to display image
                    ImageLoader.getInstance().displayImage(movieModel.getImage(), det_img, new ImageLoadingListener() {

                        @Override
                        public void onLoadingStarted(String imageUri, View view) {

                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                        }

                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {

                        }
                    });

                    det_topic.setText(movieModel.getTopic());
                    det_blogger.setText(movieModel.getBlog_name());
                    det_subject.setText(movieModel.getSubject());
                    det_text.setText(movieModel.getMain_text());


                }

                // ATTENTION: This was auto-generated to implement the App Indexing API.
                // See https://g.co/AppIndexing/AndroidStudio for more information.
                client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
            }

            private void setUpUIViews() {
                det_img = (ImageView) findViewById(R.id.det_image);
                det_topic = (TextView) findViewById(R.id.det_topic);
                det_subject = (TextView) findViewById(R.id.det_sub);
                det_text = (TextView) findViewById(R.id.det_maintext);
                det_blogger = (TextView) findViewById(R.id.det_by);

            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                if (id == android.R.id.home) {
                    finish();
                }

                return super.onOptionsItemSelected(item);
            }

            /**
             * ATTENTION: This was auto-generated to implement the App Indexing API.
             * See https://g.co/AppIndexing/AndroidStudio for more information.
             */
            public Action getIndexApiAction() {
                Thing object = new Thing.Builder()
                        .setName("Detail Page") // TODO: Define a title for the content shown.
                        // TODO: Make sure this auto-generated URL is correct.
                        .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                        .build();
                return new Action.Builder(Action.TYPE_VIEW)
                        .setObject(object)
                        .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                        .build();
            }

            @Override
            public void onStart() {
                super.onStart();

                // ATTENTION: This was auto-generated to implement the App Indexing API.
                // See https://g.co/AppIndexing/AndroidStudio for more information.
                client.connect();
                AppIndex.AppIndexApi.start(client, getIndexApiAction());
            }

            @Override
            public void onStop() {
                super.onStop();

                // ATTENTION: This was auto-generated to implement the App Indexing API.
                // See https://g.co/AppIndexing/AndroidStudio for more information.
                AppIndex.AppIndexApi.end(client, getIndexApiAction());
                client.disconnect();
            }
        }

