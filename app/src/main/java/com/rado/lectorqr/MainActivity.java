package com.rado.lectorqr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity  {
    public ZXingScannerView mScannerView;
    public Button btnescanner;
    public EditText editRuc1,v1,igv,tpeaje,nroticket,tipeaje,fecha,tipovehiculo,ruc2,cod,subtotal;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnescanner = (Button) findViewById(R.id.btnEscanear);


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);

            }
        }




    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
        }
    }



    public void btnEscanear(View view){
        mScannerView = new ZXingScannerView(this);

        mScannerView.setResultHandler(new zxingscanner());
        setContentView(mScannerView);
        mScannerView.startCamera();
    }

    class zxingscanner implements ZXingScannerView.ResultHandler{
        @Override
        public void handleResult(Result rawResult) {
            String resultString=rawResult.getText();

            String[] parts = resultString.split("\\|");
            String part1 = parts[0];
            String part2 = parts[2];
            String part3 = parts[3];

            String part4 = parts[4];
            String part5 = parts[5];
            Double doubleigv=Double.parseDouble(part4);
            Double doubletpeaje=Double.parseDouble(part5);

            Double doublesubtotal=doubletpeaje-doubleigv;
            String Subtotal=String.valueOf(doublesubtotal);
            String part6 = parts[6];
            String part7 = parts[7];
            String part8 = parts[8];
            String part9 = parts[9];
            setContentView(R.layout.activity_main);
            mScannerView.stopCamera();
            editRuc1=(EditText)findViewById(R.id.EditRuc1);
            nroticket=(EditText)findViewById(R.id.EditNro);
            tipeaje=(EditText)findViewById(R.id.EditTpeaje);
            igv=(EditText)findViewById(R.id.EditIgv);
            fecha=(EditText)findViewById(R.id.EditFecha);
            tipovehiculo=(EditText)findViewById(R.id.EditTVehiculo);
            subtotal=(EditText)findViewById(R.id.EditSubtotal);
            ruc2=(EditText)findViewById(R.id.EditRuc2);
            cod=(EditText)findViewById(R.id.EditCod);
            nroticket.setText(part2+"-"+part3);
            editRuc1.setText(part1);


            igv.setText(part4);
            tipeaje.setText(part5);

            subtotal.setText(Subtotal);
            fecha.setText(part6);
            tipovehiculo.setText("Pesado "+part7+" ejes");
            ruc2.setText(part8);
            cod.setText(part9);



        }
    }




}
