package com.example.administrator.filedemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    private File fPhonedirectry;
    private File fExternalStoragePublicDirectory;
    private File fExternalStorageDirectory;
   private File fDataStorage;
    private File fDownloadCacheDirectory;
    private File fRootDirectory;
    private String name,path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.result);

        fPhonedirectry = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        fExternalStorageDirectory = Environment.getExternalStorageDirectory();
        fDataStorage = Environment.getDataDirectory();
        fDownloadCacheDirectory = Environment.getDownloadCacheDirectory();
        fRootDirectory = Environment.getRootDirectory();

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED)){
            Button btn = (Button) findViewById(R.id.externalStorageDirectory);
            btn.setEnabled(false);


        }
    }
    private boolean listFile(String path){
        name = "路径"+path+"\n文件清单:\n";
        File file = new File(path);
        if(file.listFiles()!=null&&file.listFiles().length>0){
            for(File f:file.listFiles()){
                path = f.getAbsolutePath();
                name= name+f.getName()+"\n";
            }
        }
        tv1.setText(name);
        return  true;

    }
    public void phoneDirectory(View v){
        path = fPhonedirectry.getPath();
        try{
            FileOutputStream fos = openFileOutput("test.txt",MODE_PRIVATE);
            fos.write("hello".getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        listFile(path);
    }
    public void externalStoragePublicDirectory(View v){
        path = fExternalStoragePublicDirectory.getAbsolutePath();
        listFile(path);
    }

    public void externalStorageDirectory(View v){
        path = fExternalStorageDirectory.getAbsolutePath();
        listFile(path);
    }
    public void dataStorage(View v){
        path = fDataStorage.getAbsolutePath();
        listFile(path);
    }
    public void downloadCacheDirectory(View v){
        path = fDownloadCacheDirectory.getAbsolutePath();
        listFile(path);
    }
    private void rootDirectory(View v){
        path = fRootDirectory.getAbsolutePath();
        listFile(path);
    }
}
