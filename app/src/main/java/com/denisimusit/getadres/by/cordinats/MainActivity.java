package com.denisimusit.getadres.by.cordinats;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText myLatitude;
    private EditText myLongitude;
    private EditText myAddress;

    private Button getAddressButton;
    private Geocoder geocoder;

    double lat;
    double lng;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        geocoder = new Geocoder(this, Locale.getDefault());

        myLatitude = findViewById(R.id.latitude);
        myLongitude = findViewById(R.id.longitude);
        myAddress =  findViewById(R.id.address);
        getAddressButton = findViewById(R.id.get_address_button);
        getAddressButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {


        try {
            lat = Double.parseDouble(myLatitude.getText().toString());
            lng = Double.parseDouble(myLongitude.getText().toString());

            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);

            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("Адрес:\n");
                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    String addressLine = returnedAddress.getAddressLine(i);
                    Log.d("myLog ", addressLine);
                    strReturnedAddress.append(addressLine).append("\n");
                }
                myAddress.setText(strReturnedAddress.toString());
            } else {
                myAddress.setText("Нет адресов!");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            myAddress.setText("Не могу получить адрес!");
        }
    }
}
