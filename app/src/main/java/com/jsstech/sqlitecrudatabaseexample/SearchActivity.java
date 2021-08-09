package com.jsstech.sqlitecrudatabaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    EditText editTextsearch;
    TextView txtaddress,txtContact;
    Button buttonseach,btnDelete;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    String serchName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editTextsearch=findViewById(R.id.searchet);

        txtaddress=findViewById(R.id.txtemail);
        txtContact=findViewById(R.id.txtcontact);

        buttonseach=findViewById(R.id.bttsearch);
        btnDelete=findViewById(R.id.btdelete);

        txtContact.setVisibility(View.GONE);
        txtaddress.setVisibility(View.GONE);

        buttonseach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               serchName=editTextsearch.getText().toString();
               dbHelper=new DBHelper(getApplicationContext());
               sqLiteDatabase=dbHelper.getReadableDatabase();
                Cursor cursor=dbHelper.getContact(serchName,sqLiteDatabase);
                if(cursor.moveToFirst()){
                    String MOBILE = cursor.getString(0);
                    String EMAIL = cursor.getString(1);
                    txtContact.setText(MOBILE);
                    txtaddress.setText(EMAIL);
                    txtContact.setVisibility(View.VISIBLE);
                    txtaddress.setVisibility(View.VISIBLE);
                }
            }
        });

btnDelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        dbHelper = new DBHelper(getApplicationContext());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        dbHelper.deleteInformations(serchName, sqLiteDatabase);
        Toast.makeText(getApplicationContext(), "Data Deleted", Toast.LENGTH_SHORT).show();
    }
});
    }
}