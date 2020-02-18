package com.rado.lectorqr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity  {
    public ZXingScannerView mScannerView;
    public TextView txtScanner;
    private Button btnScaner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

            String[] parts = resultString.split(";");
            String part1 = parts[0];
            setContentView(R.layout.activity_main);
            mScannerView.stopCamera();
            txtScanner=(TextView) findViewById(R.id.txtRespuesta);

            txtScanner.setText(part1);

        }
    }




}
