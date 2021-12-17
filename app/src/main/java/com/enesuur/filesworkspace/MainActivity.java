package com.enesuur.filesworkspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private String internalFile;
    private String fileName = "myFile";
    private String myString = "Salut!";
    String url;
    FileOutputStream fileOutputStream;
    private Object String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // getContext() = this == Activity + XML (It lives on Activity LifeCycle,"use in activities")
        //
        // Creating new file
        File file = new File(context.getFilesDir(),internalFile);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("Salut!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Read File

        try {
            StringBuffer content = new StringBuffer();
            BufferedReader bis = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = bis.readLine()) != null){
                content.append(line).append("/n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileOutputStream = openFileOutput(fileName,Context.MODE_PRIVATE);
            fileOutputStream.write(myString.getBytes());
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            }
        /* Following method extracts the file name from a URL and creates a file with that name
        In your app's internal cache directory
        * */

    }
    public File getTempFile(Context context,String url){
        File file = null;
        try {
            String fileName = Uri.parse(url).getLastPathSegment();
            file = File.createTempFile(fileName,null,context.getCacheDir());

        }catch (IOException e){
            e.printStackTrace();
        }
        return file;
    }
}