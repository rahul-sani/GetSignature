package com.r4hu1.getsignature;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this,"Grep 'TAG' while logging to get the signature",Toast.LENGTH_LONG).show();

    }

    public void logMe(View v) {
        EditText text = (EditText) findViewById(R.id.text);
        String packageName = text.getText().toString();
        PackageManager pm = getPackageManager();
        boolean isapkInstalled=isInstalled(packageName);
        if(isapkInstalled!=true) {
            Toast.makeText(MainActivity.this,"The given Package Does not exist",Toast.LENGTH_SHORT).show();
        } else {
            try {
            Toast.makeText(MainActivity.this,"Package exist! Logcat to get Signature ",Toast.LENGTH_LONG).show();
            Signature sig = pm.getPackageInfo(packageName,PackageManager.GET_SIGNATURES).signatures[0];
            Log.d("TAG", "Signature(in hex)=  "+ sig);
            Log.d("TAG", "Signature(in hashCode)=  "+ sig.hashCode());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    }

    public  boolean isInstalled(String packageName) {
        List<ApplicationInfo> packages ;
        PackageManager pm = getPackageManager();
        packages= pm.getInstalledApplications(0);
        for(ApplicationInfo info : packages) {
            if(info.packageName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }
}
