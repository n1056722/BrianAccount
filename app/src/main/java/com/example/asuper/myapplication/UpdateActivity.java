package com.example.asuper.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_number_update;
    private EditText et_write_update;
    Account account1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        findViewById(R.id.update).setOnClickListener(this);
        et_number_update = (EditText) findViewById(R.id.et_number_update);
        et_write_update = (EditText) findViewById(R.id.et_write_update);


        Intent myIntent = this.getIntent();


        TextView textView = (TextView) findViewById(R.id.update);

        int account = myIntent.getIntExtra("AccountID", -1); //輸出Bundle內容

        AccountDAO accountOAD = new AccountDAO(this);
        account1 = accountOAD.get(account);

        et_number_update.setText(account1.getMoney() + "");
        et_write_update.setText(account1.getDesc() + "");


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.update:
                int money = Integer.parseInt(et_number_update.getText().toString());
                String desc = et_write_update.getText().toString();


                account1.setMoney(money);
                account1.setDesc(desc);


                AccountDAO accountOAD = new AccountDAO(this);
                Boolean success = accountOAD.update(account1);
                if (success) {

                    finish();

                } else {
                    Toast.makeText(this, "修改失敗", Toast.LENGTH_SHORT).show();
                }
                break;

        }


    }
}
