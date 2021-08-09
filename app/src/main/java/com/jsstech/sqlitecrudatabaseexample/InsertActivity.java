package com.jsstech.sqlitecrudatabaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
EditText etName,etContact,etAddress;
Button btSave;
    Context context = this;
    DBHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        etName=findViewById(R.id.name);
        etContact=findViewById(R.id.contact);
        etAddress=findViewById(R.id.address);
        btSave=findViewById(R.id.saveInfo);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String NAME=etName.getText().toString().trim();
                String CONTACT=etContact.getText().toString().trim();
                String ADDRESS=etAddress.getText().toString().trim();

                userDbHelper=new DBHelper(context);
                sqLiteDatabase = userDbHelper.getWritableDatabase();
                userDbHelper.addInformation(NAME, CONTACT, ADDRESS, sqLiteDatabase);
                userDbHelper.close();
                Toast.makeText(getApplicationContext(), "Data inseretd", Toast.LENGTH_LONG).show();

            }
        });
    }
}