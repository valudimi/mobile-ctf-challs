package com.example.hermes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {


    TextView ipPrompt;
    Button listenButton;
    Button connectButton;
    TextInputEditText ipText;
    TextInputEditText nickText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE},
                101
        );

        ipPrompt = findViewById(R.id.ip_prompt);
        listenButton = findViewById(R.id.listen_button);
        connectButton = findViewById(R.id.connect_button);
        ipText = findViewById(R.id.ip_field);
        nickText = findViewById(R.id.nick_edit);

        Context context = getApplicationContext();
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        ipPrompt.append(ip);

        listenButton.setOnClickListener((l) -> {
            Intent intent = new Intent(MainActivity.this, ServerListenter.class);
            intent.putExtra("nick", String.valueOf(nickText.getText()));
            intent.putExtra("type", "listen");
            startActivity(intent);
        });

        connectButton = findViewById(R.id.connect_button);
        connectButton.setOnClickListener((l) -> {
            Intent intent = new Intent(MainActivity.this, ServerListenter.class);
            intent.putExtra("type", "connect");
            intent.putExtra("ip", String.valueOf(ipText.getText()));
            intent.putExtra("nick", String.valueOf(nickText.getText()));
            Log.d("aaa", String.valueOf(ipText.getText()));
            startActivity(intent);
        });
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String permissions[],
            int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

}