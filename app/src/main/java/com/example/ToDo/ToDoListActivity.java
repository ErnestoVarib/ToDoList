package com.example.ToDo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

public class ToDoListActivity extends AppCompatActivity {

    private TextView txtResult;
    private SQLiteDatabase db;
    SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        txtResult = findViewById(R.id.txtResults);
        db = sqLiteHelper.getWritableDatabase();
        Cursor c = db.rawQuery("Select texto, completado from todo", null
        );
        if (c.moveToFirst()){
            do
                {
                    String texto = c.getString(0);
                    String done = c.getString(1);

                    txtResult.append(" " + texto + "\n Terminado: " + done + "\n");
                }while (c.moveToNext());

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.todo_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_logout){
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }else if(id==R.id.action_add){
            Intent intent = new Intent(getApplicationContext(), addTodoActivity.class);
            startActivityForResult(intent, 123);
            return true;
        }else if (id==R.id.action_refresh){
            Cursor c = db.rawQuery("Select texto, completado from todo", null
            );
            if (c.moveToFirst()){
                do
                {
                    txtResult.setText("");
                    String texto = c.getString(0);
                    String done = c.getString(1);

                    txtResult.append(" " + texto + "\n Terminado: " + done + "\n");
                }while (c.moveToNext());

            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123){
            if (resultCode == RESULT_OK){
                Todo _todo = (Todo)data.getSerializableExtra(addTodoActivity.TODO);
                //Object obj = data.getType();
               Toast.makeText(getApplicationContext(), "Result OK content: "+_todo.content, Toast.LENGTH_SHORT).show();

            }else if (resultCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "Result canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
