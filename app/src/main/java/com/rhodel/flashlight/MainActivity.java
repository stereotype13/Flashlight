package com.rhodel.flashlight;

import android.*;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mToggleOnOffButton;
    private Boolean mIsOn = false;
    private Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToggleOnOffButton = (Button) findViewById(R.id.toggleOnOffButton);

        mToggleOnOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleOnOff();
            }
        });

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
        }





    }

    private void toggleOnOff() {




        if (mIsOn) {
            mIsOn = false;
            //mCamera.stopPreview();
            Camera.Parameters p = mCamera.getParameters();
            p.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
            mCamera.setParameters(p);
            mCamera.release();
        }
        else {
            mCamera = Camera.open();
            Camera.Parameters p = mCamera.getParameters();
            p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            //mCamera.startPreview();
            mCamera.setParameters(p);
            mIsOn = true;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
