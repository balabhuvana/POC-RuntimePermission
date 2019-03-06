package application.gdms.com.myapplication;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RunTimePermissionActivity extends AppCompatActivity {

    private String permissionListForContacts[] = {Manifest.permission.READ_CONTACTS};
    private String permissionListForCamera[] = {Manifest.permission.CAMERA};
    private String permissionListForLocation[] = {Manifest.permission.ACCESS_FINE_LOCATION};
    private int requestPermissionCode = 1005;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_time_permission);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Button btnContact = findViewById(R.id.btnContact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRuntimePermissionForReadContacts();
            }
        });

        Button btnCamera = findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRuntimePermissionForCamera();
            }
        });


        Button btnLocation = findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRuntimePermissionForLocation();
            }
        });

    }

    private void checkRuntimePermissionForReadContacts() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissionListForContacts, requestPermissionCode);
        } else {
            Toast.makeText(getApplicationContext(), "All Ready granted", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkRuntimePermissionForCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissionListForCamera, 2000);
        } else {
            Toast.makeText(getApplicationContext(), "All Ready granted", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkRuntimePermissionForLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissionListForLocation, 3000);
        } else {
            Toast.makeText(getApplicationContext(), "All Ready granted", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == requestPermissionCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Permission is granted", Toast.LENGTH_SHORT).show();
            } else {
                /**
                 *  if we select don't ask again and deny from permission dialog showRationale method return true
                 */
                boolean showRationale = shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS);
                if (!showRationale) {
                    openSettingsDialog();
                    Toast.makeText(getApplicationContext(), "Permission is not granted showRationale false", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission is not granted showRationale true", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == 2000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Permission is granted", Toast.LENGTH_SHORT).show();
            } else {
                /**
                 *  if we select don't ask again and deny from permission dialog showRationale method return true
                 */
                boolean rationale = shouldShowRequestPermissionRationale(Manifest.permission.CAMERA);
                if (!rationale) {
                    openSettingsDialog();
                    Toast.makeText(getApplicationContext(), "Permission is not granted showRationale false", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission is not granted showRationale true", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == 3000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Permission is granted", Toast.LENGTH_SHORT).show();
            } else {
                boolean rationale = shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);
                if (!rationale) {
                    openSettingsDialog();
                    Toast.makeText(getApplicationContext(), "Permission is not granted showRationale false", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission is not granted showRationale true", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void openSettingsDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(RunTimePermissionActivity.this);
        builder.setTitle("Required Permissions");
        builder.setMessage("This app require permission to use awesome feature. Grant them in app settings.");
        builder.setPositiveButton("Take Me To SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, 101);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }
}
