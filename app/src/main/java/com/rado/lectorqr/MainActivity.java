package com.rado.lectorqr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    public TextView txtScanner;
    public EditText editRuc1,v1,igv,tpeaje,nroticket,tipeaje,fecha,tipovehiculo,ruc2,cod;
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

            String[] parts = resultString.split("\\|");
            String part1 = parts[0];
            String part2 = parts[1];
            String part4 = parts[4];
            String part5 = parts[5];
            String part6 = parts[6];
            String part7 = parts[7];
            String part8 = parts[8];
            String part9 = parts[9];
            setContentView(R.layout.activity_main);
            mScannerView.stopCamera();
            txtScanner=(TextView) findViewById(R.id.txtRespuesta);
            editRuc1=(EditText)findViewById(R.id.EditRuc1);
            v1=(EditText)findViewById(R.id.Editv1);
            nroticket=(EditText)findViewById(R.id.EditNro);
            tipeaje=(EditText)findViewById(R.id.EditTpeaje);
            igv=(EditText)findViewById(R.id.EditIgv);
            fecha=(EditText)findViewById(R.id.EditFecha);
            tipovehiculo=(EditText)findViewById(R.id.EditTVehiculo);
            ruc2=(EditText)findViewById(R.id.EditRuc2);
            cod=(EditText)findViewById(R.id.EditCod);

            editRuc1.setText(part1);
            v1.setText(part2);
            igv.setText(part4);
            tipeaje.setText(part5);
            fecha.setText(part6);
            tipovehiculo.setText(part7);
            ruc2.setText(part8);
            cod.setText(part9);



        }
    }




}
