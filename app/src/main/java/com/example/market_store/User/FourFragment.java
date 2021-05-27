package com.example.market_store.User;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.market_store.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FourFragment extends Fragment {

    public FourFragment(){
    }
    GoogleMap gmap;
    MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_layout, container, false);
        Init(view);
        mapView.onCreate(savedInstanceState);
        Event();
        return view;
    }

    public void Init(View view){
        mapView = (MapView) view.findViewById(R.id.mapView);

    }
    public void Event() {
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gmap = googleMap;
                LatLng cs1 = new LatLng(20.939798, 106.058970);
                LatLng cs2 = new LatLng(20.925411, 106.057802);
                LatLng cs3 = new LatLng(20.940529, 106.033079);

                gmap.getUiSettings().setMyLocationButtonEnabled(false);
                gmap.addMarker(new MarkerOptions().position(cs1).title("Cửa hàng Mobile-Store 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.button1)));//pin
                gmap.addMarker(new MarkerOptions().position(cs2).title("Cửa hàng Mobile-Store 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.button1)));
                gmap.addMarker(new MarkerOptions().position(cs3).title("Cửa hàng Mobile-Store 3").icon(BitmapDescriptorFactory.fromResource(R.drawable.button1)));
                gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(cs1,15));
                gmap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
