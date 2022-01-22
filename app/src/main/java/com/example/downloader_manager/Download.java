package com.example.downloader_manager;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Download extends AppCompatActivity {
    public View view;
    public Context context;
    public Activity activity;

    public String URL_DESCARGA;
    public String NOMBRE_ARCHIVO;
    private static final short REQUEST_CODE = 6545;

    public Download(View view, Context context, Activity activity, String URL_DESCARGA, String NOMBRE_ARCHIVO) {
        this.view = view;
        this.context = context;
        this.activity = activity;
        this.URL_DESCARGA = URL_DESCARGA;
        this.NOMBRE_ARCHIVO = NOMBRE_ARCHIVO;
    }

    public void descargar(){
        if(isAdministradorDescargaDisponible()){
            checkSelfPermission();
        }else{
            Toast.makeText(context, "Administrador de descarga no disponible", Toast.LENGTH_SHORT).show();
        }
    }

    private static boolean isAdministradorDescargaDisponible(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD){
            return true;
        }
        return false;
    }

    private void checkSelfPermission(){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }else{
            ejecutarDescarga();
        }
    }

    private void ejecutarDescarga(){

        context.registerReceiver(new DownloadCompleteReceiver(), new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(URL_DESCARGA));
        request.setDescription("Descargando Archivo" + NOMBRE_ARCHIVO);
        request.setTitle(NOMBRE_ARCHIVO);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }

        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, NOMBRE_ARCHIVO);
        DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:{
                if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    ejecutarDescarga();
                }else{
                    Toast.makeText(context, "Por favor, aceptar los permisos", Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
