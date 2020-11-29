package kr.co.company.kakaonavi;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button navibutton = (Button)findViewById(R.id.navibtn);
        Button phonebtn = (Button)findViewById(R.id.phonebtn);
    }

    public void myListener(View target){
        Intent intent = new Intent(MainActivity.this, NaviInputActivity.class);
        startActivity(intent);
    }
    public void myListener2(View target){
        Intent intent = new Intent(MainActivity.this, PhoneActivity.class);
        startActivity(intent);
    }
}
