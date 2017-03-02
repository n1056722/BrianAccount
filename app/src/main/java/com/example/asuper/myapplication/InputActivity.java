package com.example.asuper.myapplication;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;




public class InputActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_number;

    private EditText et_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        findViewById(R.id.tv_record).setOnClickListener(this);
        findViewById(R.id.input).setOnClickListener(this);
        et_number = (EditText) findViewById(R.id.et_number);
         et_desc = (EditText) findViewById(R.id.et_write);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.input:
                int money = Integer.parseInt(et_number.getText().toString());
                String desc = et_desc.getText().toString();


                Account a1 = new Account();
                a1.setMoney(money);
                a1.setDesc(desc);


                AccountDAO accountOAD = new AccountDAO(this);
                Boolean success = accountOAD.insert(a1);
                if (success) {
                    Intent myIntent = new Intent(InputActivity.this, RecordActivity.class);
                    InputActivity.this.startActivity(myIntent);
                }else {
                    Toast.makeText(this, "新增失敗", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.tv_record:
                Intent myIntent = new Intent(InputActivity.this, RecordActivity.class);
                InputActivity.this.startActivity(myIntent);
                break;
        }


    }
}
