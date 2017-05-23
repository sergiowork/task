package com.dev.task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Authorization of client.
 */
public class ClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        final TextView ed_email = (TextView) findViewById(R.id.textViewEmail);
        final Button bt_exit = (Button) findViewById(R.id.buttonExit);

        Client uUser = Client.getInstance();
        ed_email.setText(uUser.getEmail());

        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(ClientActivity.this, MainActivity.class);
                ClientActivity.this.startActivity(registerIntent);
            }
        });
    }
}
