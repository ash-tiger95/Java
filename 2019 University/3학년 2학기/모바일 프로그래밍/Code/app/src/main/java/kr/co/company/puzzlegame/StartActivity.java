package kr.co.company.puzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {
    ImageView imgv = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button startbutton = (Button)findViewById(R.id.startbutton);
        imgv = (ImageView)findViewById(R.id.imgv);
    }
    public void myListener5(View target){
        Intent intent = new Intent(StartActivity.this, GameActivity.class);
        startActivity(intent);
    }
}