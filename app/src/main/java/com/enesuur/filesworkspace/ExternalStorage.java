package com.enesuur.filesworkspace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class ExternalStorage extends AppCompatActivity {

    private final static int REQUEST_EXTERNAL_STORAGE = 1;
    private final static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final String LOG_TAG = "Directory is not founded !";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        // Save a file on external storage.


        // External storage public directory
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        if(!dir.exists()){
            dir.mkdir();
        }
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"filex.txt");

        int  permission = ActivityCompat.checkSelfPermission(ExternalStorage.this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permission != PackageManager.PERMISSION_GRANTED ){
            // We won't have permission  so prompt the user
            ActivityCompat.requestPermissions(ExternalStorage.this,PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
        };
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_EXTERNAL_STORAGE){
            if(grantResults.length == 2 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED  &&
            grantResults[1] == PackageManager.PERMISSION_GRANTED){
                // do whatever you want
            }
        }
    }

    // Checks if external storage is availabe for read and write.
    public boolean isExternalStorageWriteable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    // Checks if external storage is available to at least read.
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    // Saving file on external storage as public.

    public File getAlbumStorageDir(String AlbumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), AlbumName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created!");
        }
        return file;

    }



}
