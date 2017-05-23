package com.dev.task;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Registration of client.
 */
public class RegisterActivity extends AppCompatActivity {

    private boolean isValidEmailID(String email) {
        String PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

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
                int paswd_length;
                paswd_length = ed_password.getText().toString().length();
                boolean check_paswd, check_client, email, paswd;
                check_paswd = ed_password.getText().toString().equals(ed_password2.getText().toString());
                Client client = Client.getInstance();
                check_client = ed_email.getText().toString().equals(client.getEmail());

                if (isValidEmailID(ed_email.getText().toString())){
                    email = true;
                } else {
                    ed_email.setError("Введите правильный email.");
                    email = false;
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
                    client.setEmail(ed_email.getText().toString());
                    String md5Password = MD5.encrypt(ed_password.getText().toString());
                    client.setPassword(md5Password);

                    Intent registerIntent = new Intent(RegisterActivity.this, MainActivity.class);
                    RegisterActivity.this.startActivity(registerIntent);
                }
            }
        });
    }
}
