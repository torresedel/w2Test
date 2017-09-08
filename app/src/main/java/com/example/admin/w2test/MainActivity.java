package com.example.admin.w2test;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText etEmail;
    TextView tvRandomNum;
    TextView tvOddList;
    List<Integer> Numbers = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRandomNum = (TextView) findViewById(R.id.tvRandomNum);
        tvOddList = (TextView) findViewById(R.id.tvOddList);
        etEmail = (EditText) findViewById(R.id.etEmail);
    }

    public void generateNumber(View view) {

        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                tvRandomNum.setText(message.getData().getString("keyData"));
                return false;
            }
        });

        com.example.admin.w2test.TestThreadHandlerMessage testThreadHandlerMessage = new com.example.admin.w2test.TestThreadHandlerMessage(handler);
        testThreadHandlerMessage.start();

    }

    public void addOdd(View view) {

        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {

                addList(message.getData().getString("keyData"));

                return false;
            }
        });

        com.example.admin.w2test.TestThreadHandlerMessage testThreadHandlerMessage = new com.example.admin.w2test.TestThreadHandlerMessage(handler);
        testThreadHandlerMessage.start();

    }

    public void addList(String message) {

        String textview = "";
        if ((Integer.parseInt(message)) % 2 != 0) {

            Numbers.add(Integer.parseInt(message));

            for (Integer Number : Numbers) {
                textview += Number + " ";
            }

            tvOddList.setText(textview);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void validateEmail(View view) {

        boolean flag = false;
        int pos = 1000;
        String email = etEmail.getText().toString();

        for (int i = 0; i > email.length(); i++){
            if(email.charAt(i) == '@' && i > 0){
                pos = i;
                flag = true;
            }

            if (flag && pos+1 < i && email.charAt(i) == '.' && i < email.length()-1){
                etEmail.setText(email + " - Is an email");
                break;
            }
        }

        if(!Objects.equals(etEmail.getText().toString(), email + " - Is an email")){
            etEmail.setText(email + " - Is not an email");
        }

    }
}
