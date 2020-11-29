package kr.co.company.kakaonavi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class PhoneActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "172.20.10.4";
    private static String TAG = "phptest";

    private EditText mEditTextName;
    private EditText mEditTextCountry;
    private TextView mTextViewResult;

    private final String fileName = "items.list" ;
    private ListView listview ;
    private ArrayAdapter adapter ;
    private ArrayList<String> items = new ArrayList<String>() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        mEditTextName = (EditText) findViewById(R.id.editText_main_name);
        mEditTextCountry = (EditText) findViewById(R.id.editText_main_country);
        mTextViewResult = (TextView) findViewById(R.id.textView_main_result);

        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());

        listview = (ListView) findViewById(R.id.listview1);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items);
        listview.setAdapter(adapter);
        // 파일에서 데이터를 읽어들여 리스트뷰에 표시.
        loadItemsFromFile();
        adapter.notifyDataSetChanged();


        Button buttonInsert = (Button) findViewById(R.id.button_main_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mEditTextName.getText().toString();
                String phone = mEditTextCountry.getText().toString();

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/test2.php", name, phone);




                String strNew = (String) mEditTextName.getText().toString() ;
                String strNew2 = (String) mEditTextCountry.getText().toString() ;

                if (strNew.length() > 0) {
                    // 리스트에 문자열 추가.
                    items.add(strNew + " " + strNew2);

                    // 에디트텍스트 내용 초기화.
                    mEditTextName.setText("");
                    mEditTextCountry.setText("");

                    // 리스트뷰 갱신
                    adapter.notifyDataSetChanged();

                    // 리스트뷰 아이템들을 파일에 저장.
                    saveItemsToFile();
                }

            }
        });

        Button buttonDel = (Button) findViewById(R.id.button_main_delete) ;

        buttonDel.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos2;
                pos2 = listview.getCheckedItemPosition();
                if (pos2 != ListView.INVALID_POSITION) { // 선택된 항목이 있으면
                    String test1 = items.get(pos2).toString();
                    String name = test1.split("\\s")[0]; //mEditTextName.getText().toString();

                    deleteData task = new deleteData();
                    task.execute("http://" + IP_ADDRESS + "/delete.php", name);
                    mEditTextName.setText("");
                }
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

    }


    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(PhoneActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String name = (String) params[1];
            String phone = (String) params[2];

            String serverURL = (String) params[0];
            String postParameters = "name=" + name + "&phone=" + phone;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }
    class deleteData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(PhoneActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String name = (String) params[1];

            String serverURL = (String) params[0];
            String postParameters = "name=" + name;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "DeleteData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
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
}