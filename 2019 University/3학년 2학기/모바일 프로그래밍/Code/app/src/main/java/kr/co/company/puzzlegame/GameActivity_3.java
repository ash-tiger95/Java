package kr.co.company.puzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class GameActivity_3 extends AppCompatActivity {
    Button backButton, rankButton;
    TextView textView;
    long MillisecondTime, StartTime, EndTime, TimeBuff, UpdateTime = 0L;
    Handler handler;
    int Seconds, Minutes, MilliSeconds;
    int j = 0;
    int timerNum = 0;
    int temp = 0;
    boolean mbClicked = false;
    int mClickedFirstButton = 0;
    int[] numBtnIDs = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
    int[] car = {R.drawable.car_1, R.drawable.car_2, R.drawable.car_3, R.drawable.car_4, R.drawable.car_5, R.drawable.car_6,
            R.drawable.car_7, R.drawable.car_8, R.drawable.car_9};
    int[] car1 ={R.drawable.car_1, R.drawable.car_2, R.drawable.car_3, R.drawable.car_4, R.drawable.car_5, R.drawable.car_6,
            R.drawable.car_7, R.drawable.car_8, R.drawable.car_9};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textView = (TextView)findViewById(R.id.TextView);
        handler = new Handler();
        Random rand = new Random();
        backButton  = (Button)findViewById(R.id.backButton);
        rankButton = (Button)findViewById(R.id.rankButton);
        for (int i = 0; i < 100; ++i) {
            int firstIdx = rand.nextInt(9);
            int secondIdx = rand.nextInt(9);
            int temp = car[firstIdx];

            car[firstIdx] = car[secondIdx];
            car[secondIdx] = temp;
        }

        for (int i = 0 ; i < 9 ; i++) {
            ImageButton btn = (ImageButton) findViewById(numBtnIDs[i]);
            btn.setOnClickListener(click);
            btn.setBackgroundResource(car[i]);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity_3.this, StartActivity_3.class);
                startActivity(intent);
            }
        });

        rankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity_3.this, RankActivity.class);
                intent.putExtra("UpdateTime",UpdateTime);
                intent.putExtra("timer", textView.getText().toString());
                startActivity(intent);
            }
        });

    }
    public View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int t=0;
            int i;
            int temp=0;
            int x=0,y=0;
            int a=0;
            int correct =0;


            if ( timerNum == 0) {
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                timerNum = 1;
            }

            if(!mbClicked){
                mClickedFirstButton = v.getId();
//                Toast.makeText(getApplicationContext(),Integer.toString(mClickedFirstButton),Toast.LENGTH_SHORT).show();

            }
            else {
                if(temp==0) {
                    ImageButton clickedFirstButton = (ImageButton) findViewById(mClickedFirstButton);
                    int Id = v.getId();
                    ImageButton clickedSecondButton = (ImageButton) findViewById(Id);
                    int secondId = Id;

                    for (i = 0; i < 9; i++) {
                        if (numBtnIDs[i] == mClickedFirstButton)
                            x = i;
                    }

                    for (i = 0; i < 9; i++) {
                        if (numBtnIDs[i] == secondId)
                            y = i;
                    }
                    if ( Math.abs(x-y) == 1 || Math.abs(x-y) == 3) {
                        if(!(( x== 2 && y == 3 ) ||( x== 3 && y == 2 )|| ( x== 5 && y == 6 ) || ( x== 6 && y == 5 )) ) {
                            clickedFirstButton.setBackgroundResource(car[y]);
                            clickedSecondButton.setBackgroundResource(car[x]);

                            a = car[x];
                            car[x]=car[y];
                            car[y]=a;

                            for(i =0; i<9; i++) {
                                if (car[i] == car1[i]) correct++;

                            }


                        }

                        else
                            Toast.makeText(getApplicationContext(), "Replay", Toast.LENGTH_SHORT).show();
                    }


                    //�젙�떟�씤媛�? 留욎쑝硫� toast
                    if(correct == 9 ) {
                        Toast.makeText(getApplicationContext(), "success!!!!!!!!", Toast.LENGTH_SHORT).show();
                        // Timer Stop
                        TimeBuff += MillisecondTime;

                        handler.removeCallbacks(runnable);
//                        backButton.setText("INSERT RANKING");
                        rankButton.setVisibility(View.VISIBLE);
//                        backButton.setVisibility(View.INVISIBLE);


                    }

                    temp = 1;
                }
            }
            mbClicked = !mbClicked;
        }


    };

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 100);

            textView.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%02d", MilliSeconds));

            handler.postDelayed(this, 0);

        }
    };



}