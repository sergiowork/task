package com.dev.task;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.*;
/**
 * Registration of client.
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText ed_email, ed_password, ed_password2;
        final TextView tv_email, tv_password;
        final Button bt_register;
        ed_email = (EditText) findViewById(R.id.editEmail);
        ed_password = (EditText) findViewById(R.id.editPassword);
        ed_password2 = (EditText) findViewById(R.id.editPassword2);
        tv_email = (TextView) findViewById(R.id.textViewEmail);
        tv_password = (TextView) findViewById(R.id.textViewPassword);
        bt_register = (Button) findViewById(R.id.buttonRegister);

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_email.setText("");
                tv_password.setText("");
                int email_length, paswd_length;
                boolean check_paswd, email, paswd, check_client;
                Map<String, String> hashMap = new HashMap<>();

                email_length = ed_email.getText().toString().length();
                paswd_length = ed_password.getText().toString().length();
                check_paswd = ed_password.getText().toString().equals(ed_password2.getText().toString());

                SharedPreferences pref = getSharedPreferences("prefer", MODE_PRIVATE);
                String clientemail = pref.getString("words", "");
                if (!hashMap.isEmpty()) {
                    hashMap = ClientSaveLoad.strToMap(clientemail);
                    check_client = hashMap.containsKey(ed_email.getText().toString());
                } else {
                    check_client = false;
                }

                if (email_length < 6) {
                    ed_email.setError("Email < 6 символов.");
                    email = false;
                } else {
                    email = true;
                }

                if (paswd_length < 4) {
                    ed_password.setError("Пароль < 4 символов.");
                    paswd = false;
                } else {
                    paswd = true;
                }

                if (!check_paswd ) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("Пароли не совпадают.")
                            .setNegativeButton("Повторить", null)
                            .create()
                            .show();
                    return;
                }

                if (check_client) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("Аккаунт с таким email уже существует.")
                            .setNegativeButton("Повторить", null)
                            .create()
                            .show();
                    return;
                }

                if (email && paswd) {
                    String eMail = ed_email.getText().toString();
                    String md5Password = MD5.encrypt(ed_password.getText().toString());
                    hashMap.put(eMail, md5Password);

                    pref = getSharedPreferences("prefer", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("words", hashMap.toString());
                    editor.commit();
                }

                Intent registerIntent = new Intent(RegisterActivity.this, MainActivity.class);
                RegisterActivity.this.startActivity(registerIntent);
            }
        });
    }
}
