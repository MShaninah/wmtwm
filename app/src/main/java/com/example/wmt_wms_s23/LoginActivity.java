package com.example.wmt_wms_s23;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
//import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (authenticate(username, password)) {
                // Authentication successful, proceed to the main menu
                Intent intent = new Intent(LoginActivity.this, ManageSalespersonsActivity.class);
                startActivity(intent);
                finish(); // Finish this activity to prevent going back to it
            } else {
                // Authentication failed, show an error message
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean authenticate(String username, String password) {
        // Check if admin credentials
        if (username.equals("admin") && password.equals("admin")) {
            return true;
        } else {
            // Check if username and password exist in the database
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(
                    SalespersonContract.SalespersonEntry.TABLE_NAME,
                    null,
                    SalespersonContract.SalespersonEntry.COLUMN_NAME_USERNAME + " = ? AND " +
                            SalespersonContract.SalespersonEntry.COLUMN_NAME_PASSWORD + " = ?",
                    new String[]{username, password},
                    null,
                    null,
                    null
            );

            boolean isAuthenticated = cursor.getCount() > 0;

            cursor.close();
            db.close();

            return isAuthenticated;
        }
    }
}
