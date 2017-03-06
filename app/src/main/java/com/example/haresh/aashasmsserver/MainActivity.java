package com.example.haresh.aashasmsserver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int PERMISSIONS_REQUEST_CODE = 1;
    static TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions();

        statusText = (TextView) findViewById(R.id.status);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean allGranted = true;

        if (grantResults.length > 0) {
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
        }
        if (allGranted) {
            // Nothing
        } else {
            Toast.makeText(this, "Please grant the requested permissions.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void requestPermissions() {
        String[] permissions = {
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.SEND_SMS
        };
        ActivityCompat.requestPermissions(this, permissions, PERMISSIONS_REQUEST_CODE);
    }

    public static void setStatus(String phNo, String msg) {
        int requestCode = Integer.parseInt(String.valueOf(msg.charAt(0)));
        switch (requestCode) {
            case 1: {
                statusText.setText("Hospital fetch request from : " + phNo);
                break;
            }
            case 2: {
                statusText.setText("Appointemt fetch request from : " + phNo);
                break;
            }
            case 3: {
                statusText.setText("Doctor fetch request from : " + phNo);
                break;
            }
            case 4: {
                statusText.setText("VIsits fetch request from : " + phNo);
                break;
            }
            case 5: {
                statusText.setText("Login request from : " + phNo);
                break;
            }
            case 6: {
                statusText.setText("Sign Up request from : " + phNo);
                break;
            }
            case 7: {
                statusText.setText("Set hospital request from : " + phNo);
                break;
            }
            case 8: {
                statusText.setText("Appointment book request from : " + phNo);
                break;
            }
        }
    }
}
