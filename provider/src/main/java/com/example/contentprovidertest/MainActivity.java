package com.example.contentprovidertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView dataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {

        dataText = (TextView) findViewById(R.id.database_data_tv);
        //在数据库添加数据
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this,"persons.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("person", null, null, null, null, null,null);

        if(cursor.getCount()==0){
            Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
        ContentValues values = new ContentValues();
        values.put("name", "Hetingwei");
        values.put("age",21);
        db.insert("person", null, values);
        values.clear();
        values.put("name", "Caiqingguo");
        values.put("age",20);
        db.insert("person", null, values);}
    }

    public void click(View v){
        String result="";
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this,"persons.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("person", null, null, null, null, null,null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                result = result + "name:" + name + " age:" + age+"\n";
            } while (cursor.moveToNext());
        }
        dataText.setText(result);
    }




}
