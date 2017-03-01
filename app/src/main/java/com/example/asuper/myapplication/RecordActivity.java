package com.example.asuper.myapplication;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecordActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<Account> accounts;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
         lv = (ListView) findViewById(R.id.lv1);

        lv.setOnItemClickListener(this);

     //操控Account表的物件
        AccountDAO accountDAO = new AccountDAO(this);
//如果Account表裡面沒東西，就建一個範例
        if (accountDAO.getCount() == 0) {
            accountDAO.sample();
        }
        accounts = new ArrayList<>();//把資料塞到Adapter
        accounts.addAll(accountDAO.getAll());
        lv.setAdapter(new RecordListAdapter(this, accounts));

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        new AlertDialog.Builder(RecordActivity.this)
                .setTitle("刪除或修改這一筆資料?")
                .setMessage("按確認刪除OR按下修改")
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //從資料庫刪除所選位置的ID
                        AccountDAO accountDAO =new AccountDAO(RecordActivity.this);
                        accountDAO.delete(accounts.get(position).getId());
                        //轉型轉成自訂Adapter，最後在作畫面更新
                        accounts.remove(position);
                        ((RecordListAdapter)lv.getAdapter()).notifyDataSetChanged();



                        Toast.makeText(RecordActivity.this, "成功刪除", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", null)
                .setNeutralButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
