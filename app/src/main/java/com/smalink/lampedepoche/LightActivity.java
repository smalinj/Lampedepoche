package com.smalink.lampedepoche;

import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class LightActivity extends ActionBarActivity {
    private Camera camera;
    boolean hasFlash;
    boolean on;
    ImageButton fab;
    Toolbar tool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        hasFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        on=false;
        fab=(ImageButton) findViewById(R.id.fab);


        tool=(Toolbar) findViewById(R.id.tool_bar);
        tool.setTitleTextColor(Color.WHITE);
        tool.setTitle("Lampe de Poche");
    }






    public void onClickFAB(View v){

        if(hasFlash){
            if(camera== null ){
                camera = Camera.open();
            }
            on=!on;
            if (on) {

                Camera.Parameters p = camera.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(p);
                camera.startPreview();

                fab.setBackgroundResource(R.drawable.on);

            } else {
                Camera.Parameters p = camera.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(p);
                camera.stopPreview();
                fab.setBackgroundResource(R.drawable.off);



            }

        }
        else{
            AlertDialog alert = new AlertDialog.Builder(this)
                    .create();
            alert.setTitle("Error\nErreur");
            alert.setMessage("No flash on your phone... Please quit the application \n Pas de flash, veuillez quitter l'application");
            alert.show();
            // closing the application

            return;
        }
    }
}
