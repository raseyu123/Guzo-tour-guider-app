package com.example.guzo;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.guzo.Model.city;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class CitySelected extends AppCompatActivity implements OnFragmentInteractionListener
         {

String title="";
    private FragmentManager fragmentManager;
    private Fragment fragment = null;

TextView city_name;
ImageView cityPic;
String cityId="";
FirebaseDatabase database;
DatabaseReference city;
Toolbar toolbar;
NavigationView navigationView;
private FrameLayout frameLayout;
OnFragmentInteractionListener monFragmentInteractionListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = findViewById(R.id.toolbar);
       // toolbar.getNavigationIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
        setSupportActionBar(toolbar);
        //Firebase
        database = FirebaseDatabase.getInstance();
        city = database.getReference("City");
        //init view


      if (getIntent() != null)
          cityId = getIntent().getStringExtra("CityId");
      if (!cityId.isEmpty()){
         getDetailCity(cityId);
         // cityIds.setCityId(cityId);
      }

    //  String title=getIntent().getStringExtra("city");
        //getSupportActionBar().setTitle(title);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        final Bundle bundle = new Bundle();



        drawer.addDrawerListener(toggle);
        toggle.syncState();
        fragmentManager=getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new CityHome();

        final Bundle data = new Bundle();
        data.putString("Id",cityId);
        fragment.setArguments(data);

        navigationView.setCheckedItem(R.id.nav_discover);
        fragmentTransaction.replace(R.id.fragmentContain, fragment);
        fragmentTransaction.commit();
        View headerView = navigationView.getHeaderView(0);
        city_name=(TextView)headerView.findViewById(R.id.nav_city_name);
        cityPic=(ImageView)headerView.findViewById(R.id.city_pic);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id==R.id.nav_discover){
                    fragment=new CityHome();
                   // fragment.setArguments(data);
                    getSupportActionBar().setTitle(title);


                }else if (id==R.id.nav_need_to){
                    fragment = new CityDetailFragment();
                 //   fragment.setArguments(data);
                    getSupportActionBar().setTitle("Need to know");


                }else if (id==R.id.nav_fav){}
                else if (id==R.id.nav_view_city){
                }
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentContain,fragment);
                transaction.commit();
                DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
                assert  drawerLayout !=null;
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });




    }

    private void getDetailCity(String cityId) {
        city.child(cityId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                city cityies=dataSnapshot.getValue(com.example.guzo.Model.city.class);
                assert cityies != null;
                title=cityies.getName();
                getSupportActionBar().setTitle(cityies.getName());
                Picasso.get().load(cityies.getImage()).into(cityPic);
                 city_name.setText(cityies.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.city, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


             @Override
             public void onFragmentInteraction(String id) {
    //   monFragmentInteractionListener.onFragmentInteraction(this.cityId);
             }
         }


