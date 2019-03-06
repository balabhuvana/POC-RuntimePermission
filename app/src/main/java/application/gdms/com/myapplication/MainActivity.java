package application.gdms.com.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String[] INITIAL_PERMS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_CONTACTS};
    private static final String[] CONTACT_PERMS = {Manifest.permission.READ_CONTACTS};
    private static final String[] CAMERA_PERMS = {Manifest.permission.CAMERA};
    private static final String[] LOCATION_PERMS = {Manifest.permission.ACCESS_FINE_LOCATION};

    private static final int INITIAL_REQS = 1300;
    private static final int CONTACT_REQS = INITIAL_REQS + 1;
    private static final int CAMERA_REQ = INITIAL_REQS + 2;
    private static final int LOCATION_REQ = INITIAL_REQS + 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!canAccessLocation() || !canAccessCamera()) {
            requestPermissions(INITIAL_PERMS, INITIAL_REQS);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.camera:
                if (canAccessCamera()) {
                    showPermissionMessage("Permission : True");
                } else {
                    requestPermissions(CAMERA_PERMS, CAMERA_REQ);
                }
                return true;
            case R.id.location:
                if (canAccessLocation()) {
                    showPermissionMessage("Permission : True");
                } else {
                    requestPermissions(LOCATION_PERMS, LOCATION_REQ);
                }
                return true;
            case R.id.contacts:
                if (canReadContacts()) {
                    showPermissionMessage("Permission : True");
                } else {
                    requestPermissions(CONTACT_PERMS, CAMERA_REQ);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean canAccessCamera() {
        return hasPermission(Manifest.permission.CAMERA);
    }

    private boolean canReadContacts() {
        return hasPermission(Manifest.permission.READ_CONTACTS);
    }

    private boolean canAccessLocation() {
        return hasPermission(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    private boolean hasPermission(String perms) {
        return (PackageManager.PERMISSION_GRANTED == checkSelfPermission(perms));
    }

    private void showPermissionMessage(String message) {
        Toast.makeText(getApplicationContext(), "" + message, Toast.LENGTH_SHORT).show();
    }

    private void showGrantPermissionMessage(String message) {
        Toast.makeText(getApplicationContext(), "Permission Granted for : " + message, Toast.LENGTH_SHORT).show();
    }

    private void showNotGrantPermissionMessage(String message) {
        Toast.makeText(getApplicationContext(), "Permission Granted for : " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case CAMERA_REQ:
                if (canAccessCamera()) {
                    showGrantPermissionMessage("Camera");
                } else {
                    showNotGrantPermissionMessage("Camera");
                }
            case LOCATION_REQ:
                if (canAccessCamera()) {
                    showGrantPermissionMessage("Location");
                } else {
                    showNotGrantPermissionMessage("Location");
                }
            case CONTACT_REQS:
                if (canAccessCamera()) {
                    showGrantPermissionMessage("Contacts");
                } else {
                    showNotGrantPermissionMessage("Contacts");
                }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
