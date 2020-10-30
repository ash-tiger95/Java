package kr.co.company.puzzlegame;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RankActivity extends AppCompatActivity {
    String[] array;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        final TextView textResult = (TextView) findViewById(R.id.text);



        //--------------------대화상자 생성
        final Dialog rankDialog = new Dialog(this);
        rankDialog.setContentView(R.layout.custom_dialog);
        rankDialog.setTitle("랭킹 화면");

        Button save = (Button) rankDialog.findViewById(R.id.save);
        Button cancel = (Button) rankDialog.findViewById(R.id.cancel);
        final EditText username = (EditText) rankDialog.findViewById(R.id.username);

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(username.getText().toString().trim().length()>0) {
                    String susername = username.getText().toString();
                    textResult.append(" 이름: " + susername);
                    rankDialog.dismiss();
                }
                else{
                    Toast.makeText(getApplicationContext(), "다시 입력하시오.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                rankDialog.dismiss();
            }
        });

        rankDialog.show();
        //-----------------------------대화상자 종료


        //-------------------GameActivity에서 intent 받기
        Intent intent = getIntent();
        //long UpdateTime = intent.getExtras().getLong("UpdateTime");
        //textResult.setText(String.valueOf(UpdateTime));
        String timer = intent.getExtras().getString("timer");
        textResult.append(" "+timer);
        //-------------------GameActivity에서 intent 받기 끝



        //replay 버튼
        Button replay = (Button) findViewById(R.id.replayButton);

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
