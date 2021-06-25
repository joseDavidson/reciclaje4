package com.example.recycleit;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.math.BigInteger;

public class MainFragment extends Fragment implements OnMapReadyCallback {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    //Mapa
    GoogleMap map;
    MapView miMapa;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        miMapa = view.findViewById(R.id.mapView);
        miMapa.onCreate(null);
        getLocalizacion();
        miMapa.onResume();
        miMapa.getMapAsync(this);

    }

    private void getLocalizacion() {
        int permiso = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)){
            }else{
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        map = googleMap;


        LatLng recoclajePlazaDA = new LatLng(-33.4381297,-70.6487444);
        map.addMarker(new MarkerOptions().position(recoclajePlazaDA).title("Centro de reciclaje Plaza de Armas Merced 821 Santiago").snippet("Plástico/Carton/Vidrio/aluminio/.").icon(BitmapDescriptorFactory.fromResource(R.drawable.puntoreciclajeextraextrachico)));
        LatLng reciclajeReyes = new LatLng(-33.428412526129215,-70.66830728225443);
        map.addMarker(new MarkerOptions().position(reciclajeReyes).title("Punto Limpio Parque de Los Reyes").snippet("Plástico/Carton/Vidrio/Aceite/.").icon(BitmapDescriptorFactory.fromResource(R.drawable.puntoreciclajeextraextrachico)));
        LatLng reciclajeCerrillos = new LatLng(-33.5051505,-70.728599);
        map.addMarker(new MarkerOptions().position(reciclajeCerrillos).title("Punto Limpio TriCiclos - Sodimac Cerrillos").snippet("Plástico/Carton/Vidrio/Aceite/Electrico/.").icon(BitmapDescriptorFactory.fromResource(R.drawable.puntoreciclajeextraextrachico)));
        LatLng reciclajeElBosque = new LatLng(-33.5623518,-70.6768486);
        map.addMarker(new MarkerOptions().position(reciclajeElBosque).title("Punto Limpio TriCiclos - Sodimac El Bosque").snippet("Plástico/Carton/Vidrio/Aceite/Electrónica/Orgánico/.").icon(BitmapDescriptorFactory.fromResource(R.drawable.puntoreciclajeextraextrachico)));
        LatLng reciclajeSanJoaquin = new LatLng(-33.5,-70.61667);
        map.addMarker(new MarkerOptions().position(reciclajeSanJoaquin).title("Punto de Reciclaje - Uno de Septiembre 308, San Joaquín").snippet("Plástico/Carton/Vidrio/Aceite/Electrónica/.").icon(BitmapDescriptorFactory.fromResource(R.drawable.puntoreciclajeextraextrachico)));
        //Centrar Mapa
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurantSoju, 11));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());

                //map.addMarker(new MarkerOptions().position(miUbicacion).title("ubicacion actual"));
                //map.moveCamera(CameraUpdateFactory.newLatLngZoom(reciclajeReyes, 11));

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
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0, locationListener);


    }
}