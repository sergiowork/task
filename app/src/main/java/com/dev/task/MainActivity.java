package com.dev.task;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Authentication of client.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText et_email, et_password;
        final Button bt_enter, bt_register;
        et_email = (EditText) findViewById(R.id.editEmail);
        et_password = (EditText) findViewById(R.id.editPassword);
        bt_enter = (Button) findViewById(R.id.buttonEnter);
        bt_register = (Button) findViewById(R.id.buttonRegister);

        bt_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client client = Client.getInstance();
                String emailClient, passwordClient, enteredPassword;
                emailClient = client.getEmail();
                passwordClient = client.getPassword();
                boolean email, paswd;
                email = et_email.getText().toString().equals(emailClient);
                enteredPassword = MD5.encrypt(et_password.getText().toString());
                paswd = enteredPassword.equals(passwordClient);

                if (email && paswd) {
                    Intent registerIntent = new Intent(MainActivity.this, ClientActivity.class);
                    MainActivity.this.startActivity(registerIntent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Неверный логин и/или пароль")
                            .setNegativeButton("Повторить", null)
                            .create()
                            .show();
                }
            }
        });

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });
    }
}
