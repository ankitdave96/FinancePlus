package example.com.finance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

public class second extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    SliderLayout sliderLayout;
    HashMap<String,String> Hash_file_maps ;
    Button l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        l = (Button)findViewById(R.id.login);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(second.this, login.class);
                startActivity(i);

            }
        });
        Hash_file_maps = new HashMap<String, String>();

        sliderLayout = (SliderLayout)findViewById(R.id.slider);
        Hash_file_maps.put("FINANCIAL NEWS", "http://www.roisinbyrne.co.uk/wp-content/uploads/2016/11/image-5.jpg");
        Hash_file_maps.put("FINANCIAL CALCULATORS", "http://www.calcxml.com/images/business-chart.jpg");

        Hash_file_maps.put("ALL ABOUT FINANCE+", "http://www.datamanager.it/wp-content/uploads/2016/10/KVENTURE.jpg");
        Hash_file_maps.put("FINANCIAL PORTFOLIO", "https://www.newretirement.com/retirement/wp-content/uploads/2015/09/iStock_000016091817_Small.jpg");
        Hash_file_maps.put("FINANCIAL BLOGS", "http://cdn2.business2community.com/wp-content/uploads/2016/04/Finance_blog_22809_11119.jpg-600x420.jpg");

        for(String name : Hash_file_maps.keySet()){

            TextSliderView textSliderView = new TextSliderView(second.this);
            textSliderView
                    .description(name)
                    .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);
    }
    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

}
