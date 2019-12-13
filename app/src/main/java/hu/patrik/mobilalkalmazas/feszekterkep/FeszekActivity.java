package hu.patrik.mobilalkalmazas.feszekterkep;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import hu.patrik.mobilalkalmazas.R;

public class FeszekActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 5, locationListener);
                }
            }
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feszek_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;





        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // Toast.makeText(MapsActivity.this,location.toString(),Toast.LENGTH_SHORT).show();
                mMap.clear();



                LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
                mMap.addMarker(new MarkerOptions()
                        .position(userLocation)

                        .title("Marker in Sydney")

                );
                mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));

                addfeszeksz(userLocation);








            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {


            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if(Build.VERSION.SDK_INT<23){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        }else{
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }else{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }




    }


    public void addfeszeksz(LatLng helyzet){
        List<Feszek> feszeks=new ArrayList<>();



        feszeks.add(new Feszek(48.4304743448,21.4747911634,"Feszek1"));
        feszeks.add(new Feszek(48.4318958541,21.4608130786,"Feszek2"));
        feszeks.add(new Feszek(48.4839018699,21.4060893140,"Feszek3"));
        feszeks.add(new Feszek(48.2598379446,21.3012683692,"Feszek4"));
        feszeks.add(new Feszek(48.4302854485,21.4121868436,"Feszek5"));
        feszeks.add(new Feszek(48.3602930935,21.5764076160,"Feszek6"));

        feszeks.add(new Feszek(48.3859137354,21.4737536773,"Feszek7"));







        for (Feszek feszek : feszeks) {

            LatLng feszekLocation = new LatLng(feszek.getLatitude(),feszek.getLongitude());


            Location pozicio=new Location("helyzetem");
            pozicio.setLongitude(helyzet.longitude);
            pozicio.setLatitude(helyzet.latitude);


            Location loc = new Location("dummyprovider");
            loc.setLatitude(feszek.getLatitude());
            loc.setLongitude(feszek.getLongitude());


            if(pozicio.distanceTo(loc)<5000)
                mMap.addMarker(new MarkerOptions()
                        .position(feszekLocation)
                        .title(feszek.getAzonosito())
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.eggs)));

        }

    }


}
