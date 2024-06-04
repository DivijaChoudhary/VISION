package com.example.vishal.dvds;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    int vd=0;
    Button b;EditText e;
    TextToSpeech t1;
    private GoogleMap mMap;
    Button mback;
    Button mconfirm;
static String work=null;
    LocationManager locationManager;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if(t1!=null)
        {t1.stop();
            t1.shutdown();
        }

        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR)
                { t1.setLanguage(Locale.US);}
                String o="Here You Can Search And Can View Their Location";

                t1.speak(o,TextToSpeech.QUEUE_FLUSH,null);
            }
        });




                Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);


        mconfirm = (Button) findViewById(R.id.btnconfirm);

        confirm();

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MapsActivity.this, android.R.layout.
                simple_list_item_1, getResources().getStringArray(R.array.names));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position)
        {
            case 0:work="Not Specified";break;
            case 1:work="Plumbrer";break;
            case 2:work="Electrician";break;
            case 3:work="Other";break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        work="No Work Selected";
    }


});

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;

        }

       /* if(locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)){



            locationManager.requestLocationUpdates
                    (LocationManager.NETWORK_PROVIDER, 0,
                            0, new LocationListener() {
                                @Override
                                public void onLocationChanged(Location location) {

                                    double latitude=location.getLatitude();
                                    double longitude=location.getLongitude();
                                    LatLng latLng=new LatLng(latitude,longitude);
                                    Geocoder geocoder=new Geocoder(getApplicationContext());
                                    try {
                                        List<Address>addressList= geocoder.getFromLocation(latitude , longitude, 1);
                                        String string=addressList.get(0).getLocality()+",";
                                        string +=addressList.get(0).getPostalCode();
                                        mMap.addMarker(new MarkerOptions().position(latLng).title(string).snippet("and snippet").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng ,20.5f));



                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onStatusChanged(String provider, int status, Bundle extras) {

                                }

                                @Override
                                public void onProviderEnabled(String provider) {

                                }

                                @Override
                                public void onProviderDisabled(String provider) {

                                }
                            });



        }*/

        //else
            if (locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)){

            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,
                    0, 0, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {

                            double latitude=location.getLatitude();
                            double longitude=location.getLongitude();
                            LatLng latLng=new LatLng(latitude,longitude);
                            Geocoder geocoder=new Geocoder(getApplicationContext());
                            try {
                                List<Address>addressList= geocoder.getFromLocation(latitude , longitude, 1);
                                String string=addressList.get(0).getLocality()+",";
                                string +=addressList.get(0).getCountryName();
                                mMap.addMarker(new MarkerOptions().position(latLng).title(string));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng ,10.5f));



                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    });
        }


    }






    private void confirm() {
        mconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onpause();
                Intent intent=new Intent(MapsActivity.this,PopGetId.class);
                startActivity(intent);
//onpause();


            }


        });
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ratibad= new LatLng(23.1661214, 77.328128);
        mMap.addMarker(new MarkerOptions().position(ratibad).title("Ratibad").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ratibad ,12.9f));



        LatLng neelbad= new LatLng(23.2014, 77.3443);
        mMap.addMarker(new MarkerOptions().position(neelbad).title("Neelbad").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(neelbad ,12.9f));


        LatLng anandnagar= new LatLng(23.35995, 77.3998);
        mMap.addMarker(new MarkerOptions().position(anandnagar).title("AnandNagar").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(anandnagar ,12.9f));
    }
    public void onpause()
    {
        if(t1!=null)
        {t1.stop();t1.shutdown();
        }
        //super.onPause();
    }

    public static String getwork() {

        String id= work;
        return id;


    }
}
