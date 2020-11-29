package kr.co.company.kakaonavi;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kakao.kakaonavi.KakaoNaviParams;
import com.kakao.kakaonavi.KakaoNaviService;
import com.kakao.kakaonavi.Location;
import com.kakao.kakaonavi.NaviOptions;
import com.kakao.kakaonavi.options.CoordType;
import com.kakao.kakaonavi.options.RpOption;
import com.kakao.kakaonavi.options.VehicleType;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class NaviInputActivity extends AppCompatActivity {
    static double reallatitude;
    static double reallongitude;

    private final String fileName = "destinationlist.list" ;

    ListView listview ;
    ArrayAdapter adapter ;
    ArrayList<String> items = new ArrayList<String>() ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naviinput);

        final Geocoder geocoder = new Geocoder(this);

        listview = (ListView) findViewById(R.id.listview1);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items);

        listview.setAdapter(adapter);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE); //하나의 항목만 선택할 수 있다.

        // 파일에서 데이터를 읽어들여 리스트뷰에 표시.
        loadItemsFromFile();
                adapter.notifyDataSetChanged();

        Button buttonMap = (Button) findViewById(R.id.buttonMap) ;

        buttonMap.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextNew = (EditText) findViewById(R.id.editTextNew) ;
                TextView tv = (TextView) findViewById(R.id.test);
                // 주소입력후 지도2버튼 클릭시 해당 위도경도값의 지도화면으로 이동
                List<Address> list = null;

                String str = editTextNew.getText().toString();
                try {
                    list = geocoder.getFromLocationName
                            (str, // 지역 이름
                                    10); // 읽을 개수
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
                }

                if (list != null) {
                    if (list.size() == 0) {
                        tv.setText("해당되는 주소 정보는 없습니다");
                    } else {
                        // 해당되는 주소로 인텐트 날리기
                        Address addr = list.get(0);
                        double lat = addr.getLatitude();
                        double lon = addr.getLongitude();

                        String sss = String.format("geo:%f,%f", lat, lon);
                        reallatitude = lat;
                        reallongitude = lon;

                        Intent intent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(sss));
                        startActivity(intent);
                    }
                }
            }
        });

        Button buttonAdd = (Button) findViewById(R.id.buttonAdd) ;
        buttonAdd.setEnabled(false) ; // 초기 버튼 상태 비활성 상태로 지정.
        buttonAdd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextNew = (EditText) findViewById(R.id.editTextNew) ;
                String strNew = (String) editTextNew.getText().toString() ;
                String strLatitude = String.valueOf(reallatitude);
                String strLongtitude = String.valueOf(reallongitude);

                if (strNew.length() > 0) {
                    // 리스트에 문자열 추가.
                    items.add(strNew+" "+strLatitude + " "+strLongtitude);

                    // 에디트텍스트 내용 초기화.
                    editTextNew.setText("") ;

                    // 리스트뷰 갱신
                    adapter.notifyDataSetChanged();

                    // 리스트뷰 아이템들을 파일에 저장.
                    saveItemsToFile() ;

                    List<Address> list = null;


                    String str = editTextNew.getText().toString();
                    try {
                        list = geocoder.getFromLocationName(
                                str, // 지역 이름
                                10); // 읽을 개수
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
                    }


                }
            }
        });
        Button buttonDel = (Button) findViewById(R.id.buttonDel) ;
        buttonDel.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count ;
                int checkedIndex ;

                count = adapter.getCount() ;

                if (count > 0) {
                    // 리스트뷰에서 선택된 아이템 인덱스 얻어오기.
                    checkedIndex = listview.getCheckedItemPosition();
                    if (checkedIndex > -1 && checkedIndex < count) {
                        // 아이템 삭제
                        items.remove(checkedIndex) ;

                        // 리스트뷰 선택 초기화.
                        listview.clearChoices();

                        // 리스트뷰 갱신
                        adapter.notifyDataSetChanged();

                        // 리스트뷰 아이템들을 파일에 저장.
                        saveItemsToFile() ;
                    }
                }
            }
        });

        Button buttonStart = (Button) findViewById(R.id.startbtn) ;
        buttonStart.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos2;
                pos2 = listview.getCheckedItemPosition();    // int getCheckedItemPosition()으로 현재 선택된 항목의 첨자를 조사
                if (pos2 != ListView.INVALID_POSITION) { // 선택된 항목이 있으면
                    String test1 = items.get(pos2).toString();


                    String str1 = test1.split("\\s")[0];
                    String str3 = test1.split("\\s")[1];
                    String str2 = test1.split("\\s")[2];



                    Location destination = Location.newBuilder(str1, Double.parseDouble(str2), Double.parseDouble(str3)).build();

                    NaviOptions options = NaviOptions.newBuilder().setCoordType(CoordType.WGS84).setVehicleType(VehicleType.FIRST).setRpOption(RpOption.SHORTEST).build();

                    KakaoNaviParams.Builder builder = KakaoNaviParams.newBuilder(destination).setNaviOptions(options);
                    KakaoNaviParams params = builder.build();
                    KakaoNaviService.getInstance().navigate(NaviInputActivity.this, builder.build());
                }
            }
        });


        EditText editTextNew = (EditText) findViewById(R.id.editTextNew) ;
        editTextNew.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                Button buttonAdd = (Button) findViewById(R.id.buttonAdd) ;
                if (edit.toString().length() > 0) {
                    // 버튼 상태 활성화.
                    buttonAdd.setEnabled(true) ;
                } else {
                    // 버튼 상태 비활성화.
                    buttonAdd.setEnabled(false) ;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        }) ;


    }

    private void saveItemsToFile() {
        File file = new File(getFilesDir(), fileName) ;
        FileWriter fw = null ;
        BufferedWriter bufwr = null ;

        try {
            // open file.
            fw = new FileWriter(file) ;
            bufwr = new BufferedWriter(fw) ;

            for (String str : items) {
                bufwr.write(str) ;
                bufwr.newLine() ;
            }

            // write data to the file.
            bufwr.flush() ;

        } catch (Exception e) {
            e.printStackTrace() ;
        }

        try {
            // close file.
            if (bufwr != null) {
                bufwr.close();
            }

            if (fw != null) {
                fw.close();
            }
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }
    private void loadItemsFromFile() {
        File file = new File(getFilesDir(), fileName) ;
        FileReader fr = null ;
        BufferedReader bufrd = null ;
        String str ;

        if (file.exists()) {
            try {
                // open file.
                fr = new FileReader(file) ;
                bufrd = new BufferedReader(fr) ;

                while ((str = bufrd.readLine()) != null) {
                    items.add(str) ;
                }

                bufrd.close() ;
                fr.close() ;
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }
    }

//    public void mOnClick(View v) {
//        TextView test = (TextView)findViewById((R.id.test));
//        int pos2;
//        pos2 = listview.getCheckedItemPosition();    // int getCheckedItemPosition()으로 현재 선택된 항목의 첨자를 조사
//        if (pos2 != ListView.INVALID_POSITION) { // 선택된 항목이 있으면
//            String test1 = items.get(pos2).toString();
//            test.setText(test1);
//
//            String str1 = test1.split("\\s")[0];
//            String str2 = test1.split("\\s")[1];
//            String str3 = test1.split("\\s")[2];
//
//            Location destination = Location.newBuilder(str1, Double.parseDouble(str2), Double.parseDouble(str3)).build();
//            NaviOptions options = NaviOptions.newBuilder().setCoordType(CoordType.WGS84).setVehicleType(VehicleType.FIRST).setRpOption(RpOption.SHORTEST).build();
//
//            KakaoNaviParams.Builder builder = KakaoNaviParams.newBuilder(destination).setNaviOptions(options);
//            KakaoNaviParams params = builder.build();
//            KakaoNaviService.getInstance().navigate(NaviInputActivity.this,builder.build());
//        }
//    }



}
