package br.fxd.com.fxd;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import br.fxd.com.fxd.helper.GPSTracker;
import br.fxd.com.fxd.model.Occurrence;
import br.fxd.com.fxd.utils.AddActivityPermissionsDispatcher;
import br.fxd.com.fxd.webservice.APIClient;
import br.fxd.com.fxd.webservice.APIInterface;
import permissions.dispatcher.NeedsPermission;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private ViewPager mViewPager;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AddActivityPermissionsDispatcher
                .getLatLngWithCheck(MainActivity.this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSpeechToText();
            }
        });

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.rapel) {
//            return true;
//        } else  if (id == R.id.surfar) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.happn) {
            Intent intent = new Intent(MainActivity.this, HappnActivity.class);
            startActivity(intent);
        } else if (id == R.id.maintenance) {

        } else if (id == R.id.nav_3) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    GoogleMap googleMapExt;


    public Bitmap resizeMapIcons(String iconName, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }


    private final int SPEECH_RECOGNITION_CODE = 1;
    private GoogleMap mMap;
    private GPSTracker gps;
    private double latitude = 0f, longitude = 0f;

    protected void startSpeechToText() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                R.string.action);
        try {
            startActivityForResult(intent, SPEECH_RECOGNITION_CODE);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(this, R.string.sorry,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    public void getLatLng(){
        gps = new GPSTracker(MainActivity.this);
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SPEECH_RECOGNITION_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String text = result.get(0);

                    getLatLng();

                    setOccurrence(text.toUpperCase(), latitude, longitude);
                }
                break;
            }
        }
    }

    private Call<ArrayList<Occurrence>> callOccurrences;
    private Call<Object> callInsertOccurrences;
    private APIInterface apiService;

    public void setOccurrence(String occurrence, double latitude, double longitude) {

        apiService = APIClient.getService().create(APIInterface.class);

        callInsertOccurrences = apiService.insertOccurrence(occurrence, String.valueOf(latitude), String.valueOf(longitude));

        Log.e("request", "" + callInsertOccurrences.request().toString());
        Log.e("insertOccurrence", "" + occurrence);

        callInsertOccurrences.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                boolean resp = (boolean) response.body();

                if(resp) {
                    Log.i("response", "OK");
                } else {
                    Log.e("response", "ERRO");
                }

                updateMaps();

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("Networking", t.toString());
            }
        });
    }

    public void updateMaps() {
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        googleMapExt = googleMap;

        AddActivityPermissionsDispatcher.getLatLngWithCheck(MainActivity.this);

        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        apiService = APIClient.getService().create(APIInterface.class);
        callOccurrences = apiService.getOccurrence();

        Log.e("request", "" + callOccurrences.request().toString());

        callOccurrences.enqueue(new Callback<ArrayList<Occurrence>>() {
            @Override
            public void onResponse(Call<ArrayList<Occurrence>> call, Response<ArrayList<Occurrence>> response) {

                ArrayList<Occurrence> occurrences = response.body();

                for (Occurrence occurrence : occurrences) {
                    if(!occurrence.getLng().equals("") && !occurrence.getLat().equals("")) {
                        LatLng latLng = new LatLng(Double.parseDouble(occurrence.getLat()), Double.parseDouble(occurrence.getLng()));


                        if(occurrence.getType().matches(".*BURACO.*")){
                            mMap.addMarker(new MarkerOptions().position(latLng).title(String.valueOf(occurrence.getId())).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("hole",90,120))));
                        } else if(occurrence.getType().matches(".*SINALIZA.*")){
                            mMap.addMarker(new MarkerOptions().position(latLng).title(String.valueOf(occurrence.getId())).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("signaling",90,120))));
                        }
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                        googleMapExt.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                        googleMapExt.animateCamera(CameraUpdateFactory.zoomIn());
                        googleMapExt.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

                        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
                        {

                            @Override
                            public boolean onMarkerClick(Marker arg0) {
    //                            Intent intent = new Intent(MainActivity.this, InfoServiceActivity.class);
    //                            intent.putExtra("id", "" + arg0.getTitle());
    //                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //                            startActivity(intent);

                                return true;
                            }

                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Occurrence>> call, Throwable t) {
                Log.e("Networking", t.toString());

            }
        });
    }
}


