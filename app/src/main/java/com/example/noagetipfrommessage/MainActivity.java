package com.example.noagetipfrommessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected String space = "\u200B";     //space
    protected String zero = "\u200C";     //0
    protected String one = "\u200D";      //1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getIp = (Button) findViewById(R.id.getIpBtn);
        EditText copiedTxt = (EditText) findViewById(R.id.copiedTxt);
        TextView ipTxt = (TextView) findViewById(R.id.ipTxt);

        getIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stangIpAddress = copiedTxt.getText().toString();
                Log.d("debug", "onClick: stangIpAddress: " + stangIpAddress);
                String binaryAfter = steganographyToBinary(stangIpAddress);
                Log.d("debug", "onClick: binaryAfter: " + binaryAfter);
                String ipAfter = binaryToString(binaryAfter);
                Log.d("debug", "onClick: ipAfter: " + ipAfter);

                ipTxt.setText(ipAfter);
            }
        });
    }

    public String steganographyToBinary(@NonNull String steganographyCode) {
        String binary = "";
        Log.d("debug", "steganographyToBinary: steganographyCode: " + steganographyCode);
        binary = steganographyCode.replaceAll("((\\''')*\\**~*_*[a-zA-Z]+\\**~*_*(\\''')*)", "");
        Log.d("debug", "steganographyToBinary after regex: binary: " + binary);
        binary = binary.replaceAll(zero, "0");
        binary = binary.replaceAll(one, "1");
        binary = binary.replaceAll(space, " ");
        return binary;
    }

    public String binaryToString(@NonNull String binaryCode) {
        String[] code = binaryCode.split(" ");
        String word = "";
        for (int i = 0; i < code.length; i++) {
            word += (char) Integer.parseInt(code[i], 2);
        }
        Log.d("debug", word);
        return word;
    }
}