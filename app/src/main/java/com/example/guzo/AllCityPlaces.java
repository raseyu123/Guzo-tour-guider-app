package com.example.guzo;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guzo.Model.Museums;
import com.example.guzo.Model.places;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllCityPlaces extends Fragment implements OnMapReadyCallback, LocationListener,GoogleMap.OnMarkerClickListener{
    RecyclerView recyclerView;
    Context context;
    private List<places> place;
    private static AllCityPlaces  instance;
    private ChildEventListener mChildEventListener;
    private DatabaseReference mContent;
    Marker marker;
    LatLng location;
    public static AllCityPlaces  getInstance(){

        if (instance == null)
            instance= new AllCityPlaces ();

        return instance;

    }
    public AllCityPlaces() {
        // Required empty public constructor
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.why);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager= new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        //   recyclerView.setLayoutManager(new LinearLayoutManager(context));
        PlaceViewAdapter adapter=new PlaceViewAdapter(place);
        initalizeData();
        initalizeAdapter();
//        ChildEventListener mChildEventListener;
//        mContent= FirebaseDatabase.getInstance().getReference("Museums");
//        mContent.push().setValue(marker);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_all_city_places, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




        return rootView;
    }
    @Override
    public void onMapReady(GoogleMap mMap) {

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//        mContent.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot s : dataSnapshot.getChildren()) {
//                    Museums m = s.getValue(Museums.class);
//                  location = new LatLng(m.getLat(), m.getLon());
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });
        mMap.clear(); //clear old markers

        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(9.016320, 38.761204))
                .zoom(10)
                .bearing(0)
                .tilt(45)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);


        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(9.016320, 38.761204))
                .title("National Palace")
               );
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(9.036333188,38.757163638))
                .title("National Museum"));
           ;

    }



private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }





    private void initalizeData() {
        place = new ArrayList<>();

        place.add(new places("vhddis","hotel",R.drawable.guzo));
        place.add(new places("Addnvs","Museum",R.drawable.ic_launcher_background));

    }

    private  void initalizeAdapter() {
        PlaceViewAdapter adapter=new PlaceViewAdapter(place);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {

    }



    }

