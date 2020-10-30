package kr.co.company.puzzlegame;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton imgbtn1 = (ImageButton)findViewById(R.id.imgbtn1);
        ImageButton imgbtn2 = (ImageButton)findViewById(R.id.imgbtn2);
        ImageButton imgbtn3 = (ImageButton)findViewById(R.id.imgbtn3);
        ImageButton imgbtn4 = (ImageButton)findViewById(R.id.imgbtn4);

    }
    public void myListener(View target){
        Intent intent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(intent);
    }
    public void myListener2(View target){
        Intent intent = new Intent(MainActivity.this, StartActivity_2.class);
        startActivity(intent);
    }
    public void myListener3(View target){
        Intent intent = new Intent(MainActivity.this, StartActivity_3.class);
        startActivity(intent);
    }
    public void myListener4(View target){
        Intent intent = new Intent(MainActivity.this, StartActivity_4.class);
        startActivity(intent);
    }

}
