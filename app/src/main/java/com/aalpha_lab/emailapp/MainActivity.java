/*
* The MIT License (MIT)
*
* Copyright (c) 2016 Isha Hmmam <isha.hammam.7@gmail.com>
*
* Permission is hereby granted, free of charge, to any person
* obtaining a copy of this software and associated documentation
* files (the "Software"), to deal in the Software without restriction,
* including without limitation the rights to use, copy, modify, merge,
* publish, distribute, sublicense, and/or sell copies of the Software,
* and to permit persons to whom the Software is furnished to do so,
* subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included
* in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
* INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
* PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
* FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
* ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*
* */

package com.aalpha_lab.emailapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText textTo;
    EditText textSubject;
    EditText textMessage;
    Button buttonSend;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSend = (Button) findViewById(R.id.buttonSend);

        textTo = (EditText) findViewById(R.id.editTextTo);
        textSubject = (EditText) findViewById(R.id.editTextSubject);
        textMessage = (EditText) findViewById(R.id.editTextMessage);

        buttonSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String to = textTo.getText().toString();
                String subject = textSubject.getText().toString();
                String message = textMessage.getText().toString();

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("plain/text");
                File data = null;
                try {
                    Date dateVal = new Date();
                    String filename = dateVal.toString();
                    data = File.createTempFile("Report", ".csv");
                    FileWriter out = (FileWriter) GenerateCsv.generateCsvFile(
                            data, "Name,Data1");
                    i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(data));
                    i.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                    i.putExtra(Intent.EXTRA_SUBJECT, subject);
                    i.putExtra(Intent.EXTRA_TEXT, message);
                    startActivity(Intent.createChooser(i, "E-mail"));

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
