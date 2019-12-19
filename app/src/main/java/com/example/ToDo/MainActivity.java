package com.example.ToDo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private int i;
    private Button button;
    public EditText password;
    public EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.login_btn);
        button.setOnClickListener(this);
        password = findViewById(R.id.password_et);
        username = findViewById(R.id.username_et);
    }

    @Override
    public void onClick(View v) {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        boolean isError = false;

        if (TextUtils.isEmpty(user)) {
            username.setError(getString(R.string.this_field_is_required));
            isError = true;
        }
        if (TextUtils.isEmpty(pass)) {
            password.setError(getString(R.string.this_field_is_required));
            isError = true;
        }


        if (!isError) {
            login(user, pass);
        }
    }

    private void login(String user, String pass) {
         {
            AsyncTask<String, Integer, Boolean> asyncTask = new AsyncTask<String, Integer, Boolean>() {
                @Override
                protected Boolean doInBackground(String... strings) {
                    String user = strings[0];
                    String pass = strings[1];
                    for (i=0;i<100;i++){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        publishProgress(i);
                    }

                    return user.equals("test") && pass.equals("test");
                }

                @Override
                protected void onProgressUpdate(Integer... values) {
                    super.onProgressUpdate(values);
                    Log.d(TAG, "Progress: "+ i);
                    button.setText(Integer.toString(i));
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    button.setEnabled(false);
                }

                @Override
                protected void onPostExecute(Boolean logged) {
                    super.onPostExecute(logged);
                    button.setEnabled(true);
                    if (logged) {
                        Toast.makeText(getApplicationContext(), "Login OK", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ToDoListActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            };
            asyncTask.execute(user, pass);

        }
    }
}
