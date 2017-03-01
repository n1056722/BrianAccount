package com.example.asuper.myapplication;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Super on 2017/2/23.
 */

public class AccountDAO {
    // 表格名稱
    public static final String TABLE_NAME = "Account";
    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "AccountID";
    // 其它表格欄位名稱
    public static final String COL_MONEY = "money";
    public static final String COL_DESC = "desc";
    public static final String COL_CREATED_DATE = "createddate";

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_MONEY + " INTEGER NOT NULL, " +
                    COL_DESC + " TEXT NOT NULL, " +
                    COL_CREATED_DATE + " TEXT NOT NULL)";
    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public AccountDAO(Context context) {
        db = MyDBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    // 新增參數指定的物件
    public boolean insert(Account item) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(COL_MONEY, item.getMoney());
        cv.put(COL_DESC, item.getDesc());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String date=sdf.format(new java.util.Date());
        cv.put(COL_CREATED_DATE, date);

        // 新增一筆資料並取得編號
        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件

         return  db.insert(TABLE_NAME, null, cv)>0;

    }
    public boolean update(Account item) {
        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(COL_MONEY, item.getMoney());
        cv.put(COL_DESC, item.getDesc());
        cv.put(COL_CREATED_DATE, item.getCreateDate());

        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = KEY_ID + "=" + item.getId();

        // 執行修改資料並回傳修改的資料數量是否成功
        return db.update(TABLE_NAME, cv, where, null) > 0;
    }
    // 刪除參數指定編號的資料
    public boolean delete(long id){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME, where , null) > 0;
    }
    // 讀取所有記事資料
    public ArrayList<Account> getAll() {
        ArrayList<Account> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    // 取得指定編號的資料物件
    public Account get(long id) {
        // 準備回傳結果用的物件
        Account item = null;
        // 使用編號為查詢條件
        String where = KEY_ID + "=" + id;
        // 執行查詢
        Cursor result = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);

        // 如果有查詢結果
        if (result.moveToFirst()) {
            // 讀取包裝一筆資料的物件
            item = getRecord(result);
        }

        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return item;
    }

    // 把Cursor目前的資料包裝為物件
    public Account getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Account result = new Account();

        result.setId((int) cursor.getLong(0));

        result.setMoney( cursor.getInt(1));
        result.setDesc(cursor.getString(2));
        result.setCreateDate(cursor.getString(3));

        // 回傳結果
        return result;

    }

    // 取得資料數量
    public int getCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

    // 建立範例資料
    public void sample() {
        Account a1 = new Account(1,200,"看電影","2017-02-20");
        Account a2 = new Account(2,200,"看電影2","2017-02-23");
        Account a3 = new Account(3,200,"看電影3","2017-02-22");
        Account a4 = new Account(4,200,"看電影4","2017-02-21");
        Account a5 = new Account(5,200,"看電影5","2017-02-28");


        insert(a1);
        insert(a2);
        insert(a3);
        insert(a4);
        insert(a5);

    }

}


