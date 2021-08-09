package com.jsstech.sqlitecrudatabaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class DataListActivity extends AppCompatActivity {
ListView listView;
    DBHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    DataListAdapter dataListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        listView=findViewById(R.id.listV);
        dataListAdapter = new DataListAdapter(DataListActivity.this, R.layout.fetchlayout);
        listView.setAdapter(dataListAdapter);

        userDbHelper = new DBHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        cursor = userDbHelper.getInfos(sqLiteDatabase);

        if (cursor.moveToFirst()) {
            do {
                String name, contact, address;
                name = cursor.getString(0);
                contact = cursor.getString(1);
                address = cursor.getString(2);
                DataProvider dataProvider = new DataProvider(name, contact, address);
                dataListAdapter.add(dataProvider);

            } while (cursor.moveToNext());
        }

    }
}