package com.example.ToDo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class addTodoActivity extends AppCompatActivity {

    public static final String CONTENT = "content";
    public static final String DONE = "isDone";
    public static final String TODO = "todo";
    private EditText contentEditText;
    private CheckBox doneCB;
    private Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        final SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        contentEditText = findViewById(R.id.content_et);
        doneCB = findViewById(R.id.done_cb);
        saveButton = findViewById(R.id.add_btn);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = contentEditText.getText().toString();
                boolean isDone = doneCB.isChecked();
                Todo _todo = new Todo();
                _todo.content = data;
                _todo.done = isDone;
                ContentValues values = new ContentValues();
                values.put("texto", data);
                if (isDone == true) {
                    values.put("completado", "si");
                }else if(isDone == false){
                    values.put("completado", "no");
                }
                db.insert("todo", null, values);
                Intent intent = new Intent(getApplicationContext(), ToDoListActivity.class);

                intent.putExtra(TODO, _todo);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
