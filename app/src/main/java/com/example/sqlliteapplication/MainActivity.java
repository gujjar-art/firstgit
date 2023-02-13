package com.example.sqlliteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, sabaq, sabqi , manzil;
    Button insert, update, delete, view, git;

    dbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        sabaq = findViewById((R.id.sabaq));
        sabqi = findViewById((R.id.sabqi));
        manzil = findViewById((R.id.manzil));


        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        git = findViewById(R.id.btnGit);

        DB = new dbHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String sabaqTXT = sabaq.getText().toString();
                String sabqiTXT = sabqi.getText().toString();
                String manzilTXT = manzil.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, sabaqTXT, sabqiTXT, manzilTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String sabaqTXT = sabaq.getText().toString();
                String sabqiTXT = sabqi.getText().toString();
                String manzilTXT = manzil.getText().toString();


                Boolean checkupdatedata = DB.updateuserdata(nameTXT, sabaqTXT, sabqiTXT, manzilTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });
        git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("https://github.com/gujjar-art/firstgit/blob/main/sqlliteapplication.rar");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = DB.deletedata(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Sabaq :"+res.getString(1)+"\n");
                    buffer.append("Sabqi :"+res.getString(2)+"\n");
                    buffer.append("Manzil :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });




        }
    private void goLink(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}