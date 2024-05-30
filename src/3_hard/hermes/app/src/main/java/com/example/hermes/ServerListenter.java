package com.example.hermes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ServerListenter extends AppCompatActivity {

    String nick;
    EditText messageBox;

    TextInputEditText inputBox;
    Button sendButton;

    byte[] key = "deadbeefgeampara".getBytes();
    SecretKey sk = new SecretKeySpec(key, "AES");

    private String magic(String plain) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {


        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, sk);
        byte[] ciphertext = cipher.doFinal(plain.getBytes());
        byte[] iv = cipher.getIV();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(iv);
        outputStream.write(ciphertext);
        byte[] combined = outputStream.toByteArray();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Base64.getEncoder().encodeToString(combined);
        }

        return null;
    }


    private String unmagic(String magic) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            byte[] decodedMsg = Base64.getDecoder().decode(magic);
            byte[] iv = Arrays.copyOfRange(decodedMsg, 0, 16);
            byte[] ciphertext = Arrays.copyOfRange(decodedMsg, 16, decodedMsg.length);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, sk, new IvParameterSpec(iv));
            return new String(cipher.doFinal(ciphertext), StandardCharsets.UTF_8);
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_listenter);

        messageBox = findViewById(R.id.message_box);
        inputBox = findViewById(R.id.input_box);
        sendButton = findViewById(R.id.send_button);

        sendButton.setOnClickListener((l) -> {
                Log.d("sendmsg", "here");
                Log.d("sendmsg", nick);
                Log.d("sendmsg", String.valueOf(inputBox.getText()));

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String newMsg = String.format("<%s>: %s", nick, String.valueOf(inputBox.getText()));
                            out.write(magic(newMsg));
                            out.newLine();
                            out.flush();

                            messageBox.append(newMsg + System.lineSeparator());
                        } catch (Exception e) {
                            Log.e("sendmsg", e.toString());
                        }
                    }
                }).start();
        });

        statusText = findViewById(R.id.status);
        Intent intent = getIntent();

        nick = intent.getStringExtra("nick");

        if (Objects.equals(intent.getStringExtra("type"), "listen")) {
            Thread t = new Thread(this::runTcpServer);
            t.start();
        } else {
            Thread t = new Thread(() -> runTcpClient(intent.getStringExtra("ip")));
            t.start();
        }
    }

    private static final int TCP_SERVER_PORT = 6666;
    TextView statusText;


    BufferedReader in;
    BufferedWriter out;

    Socket s;

    @SuppressLint("DefaultLocale")
    private void runTcpServer() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(TCP_SERVER_PORT);
            //ss.setSoTimeout(10000);
            //accept connections

            statusText.setText(String.format("Waiting for connection on %d", TCP_SERVER_PORT));
            Log.d("tcpserver", "before accept");
            s = ss.accept();
            Log.d("tcpclient", "after accept");
            statusText.setText(String.format("Got connection on %d", s.getPort()));

            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));


            String helloMsg = String.format("<%s>: Hello!", nick);
            out.write(helloMsg);
            out.newLine();
            out.flush();

            String helloRecv = in.readLine();
            messageBox.append(helloRecv + System.lineSeparator());
            while (true) {
                String msg = in.readLine();
                Log.i("TcpServer", "received: " + msg);
                messageBox.append(unmagic(msg) + System.getProperty("line.separator"));
            }
        } catch (InterruptedIOException e) {
            //if timeout occurs
            Log.e("tcpserver", e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("tcpserver", e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            Log.e("tcpserver", e.toString());
        } finally {
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void runTcpClient(String ip) {

        try {
            Log.d("tcpclient", ip);
            s = new Socket(ip, 6000);

            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));


            String helloMsg = String.format("<%s>: Hello!", nick);
            out.write(helloMsg);
            out.newLine();
            out.flush();

            String helloRecv = in.readLine();
            messageBox.append(helloRecv + System.lineSeparator());

            while (true) {
                String msg = in.readLine();
                Log.i("tcpclient", "received: " + msg);
                messageBox.append(unmagic(msg) + System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            Log.e("error", e.toString());
            statusText.setText("Invalid IP address specified, no connection");
        } catch (Exception e) {
            Log.e("tcpclient", e.toString());
        }
    }
}