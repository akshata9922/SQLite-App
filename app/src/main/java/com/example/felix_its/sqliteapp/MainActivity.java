package com.example.felix_its.sqliteapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper myDb;
    EditText edtname, edtsurname, edtmarks;
    Button btnadd, btnviewall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DBHelper(this);
        edtname = (EditText) findViewById(R.id.edtname);
        edtsurname = (EditText) findViewById(R.id.edtsurname);
        edtmarks = (EditText) findViewById(R.id.edtmarks);
        btnadd = (Button) findViewById(R.id.btnadd);
        btnviewall = (Button) findViewById(R.id.btnviewall);
        addData();
        viewAll();
    }

    public void addData() {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInsertes = myDb.insertData(edtname.getText().toString(),
                        edtsurname.getText().toString(), edtmarks.getText().toString());
                if (isInsertes = true)
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void viewAll() {
        btnviewall.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Surname :" + res.getString(2) + "\n");
                            buffer.append("Marks :" + res.getString(3) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}