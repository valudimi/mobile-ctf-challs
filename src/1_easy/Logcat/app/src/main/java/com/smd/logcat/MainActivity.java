package com.smd.logcat;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String aksjfhkjh2398aksjf = "Z1hXtsFYmh8ZxXvpvNVluMmRgMXNgdFh51_NXUyZEB+Wgo=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.btn);
        button.setOnClickListener(v -> {
            String izxojoijfaof = asflkj2eflkjasflkj(aksjfhkjh2398aksjf);
            Log.i("aosifjasoj", izxojoijfaof);
        });
    }

    private String asflkj2eflkjasflkj(String mkwamfkjanksfjn2fnj) {
        // Decode Base64
        String mkl2rm2lkrm = wejfk23jrkjasflkj(mkwamfkjanksfjn2fnj);
        byte[] lkjl3j24l3k2j4 = Base64.decode(mkl2rm2lkrm, Base64.DEFAULT);
        String aeworjwlkrsj = new String(lkjl3j24l3k2j4);
        String obfuscated = moi3mrlkwqrlkm(aeworjwlkrsj);
        StringBuilder knfknwfekn = new StringBuilder();
        for (char c : obfuscated.toCharArray()) {
            knfknwfekn.append((char) (c - 1));
        }
        return knfknwfekn.toString();
    }
    private String wejfk23jrkjasflkj(String mkwamfkjanksfjn2fnj) {
        StringBuilder rearskjndkasnd = new StringBuilder();
        char[] chars = mkwamfkjanksfjn2fnj.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i == 3 || i == 5 || i == 6 || i == 12 || i == 14 || i == 32 || i == 34) {
                continue;
            }
            rearskjndkasnd.append(chars[i]);
        }
        return rearskjndkasnd.toString();
    }
    private String moi3mrlkwqrlkm(String aeworjwlkrsj) {
        return aeworjwlkrsj.replaceAll("[XYZ]", "");
    }
}