package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_username,et_name;
    Button btn_save,btn_viewdata,btn_delete;
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper databaseHelper;

    public static final String DATABASE_NAME="userdatabase.db";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_username=findViewById(R.id.et_username);
        et_name=findViewById(R.id.et_name);
        btn_save=findViewById(R.id.btn_save);
        btn_viewdata=findViewById(R.id.btn_viewdata);
        btn_delete=findViewById(R.id.btn_delete);
        databaseHelper=new DatabaseHelper(getApplicationContext());

       // sqLiteDatabase=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);

btn_save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        if (et_username.getText().toString().trim().length()==0)
        {
            et_username.setError("");
            et_username.requestFocus();
        }
        if (et_name.getText().toString().trim().length()==0)
        {
            et_name.setError("");
            et_name.requestFocus();
        }
       else
        {
            AddData();
        }
    }
});

btn_viewdata.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Cursor cursor=databaseHelper.getViewData();
        if (cursor.getCount()==1)
        {
            showData("Error","Data Not Found");
            Toast.makeText(MainActivity.this, " Data Not Found ", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer stringBuffer=new StringBuffer();
        while (cursor.moveToNext()) {
            stringBuffer.append("ID:"+cursor.getString(0)+"\n");
            stringBuffer.append("USERNAME :" + cursor.getString(1) + "\n");
            stringBuffer.append("NAME :" + cursor.getString(2) + "\n");
        }
        showData("Data",stringBuffer.toString());
    }


});
btn_delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Integer deleteData1=databaseHelper.dataDelete(et_username.getText().toString().trim());
        if (deleteData1>0)
        {
            Toast.makeText(MainActivity.this, "Delete Data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity.this, "Data Not Delete", Toast.LENGTH_SHORT).show();
        }
    }
});

     }
     public void showData(String title,String Message)
     {
         AlertDialog.Builder builder=new AlertDialog.Builder(this);
         builder.setCancelable(true);
         builder.setTitle(title);
         builder.setMessage(Message);
         builder.show();
     }
     public void AddData()
     {
         boolean insetData=databaseHelper.insertData(et_username.getText().toString(),et_name.getText().toString());
         if (insetData == true)
         {
             Toast.makeText(MainActivity.this, "Data Insert", Toast.LENGTH_SHORT).show();
         }
         else
         {
             Toast.makeText(MainActivity.this, "D", Toast.LENGTH_SHORT).show();
         }

     }
    }