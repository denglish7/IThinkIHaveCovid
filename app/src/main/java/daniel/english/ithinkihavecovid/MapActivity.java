package daniel.english.ithinkihavecovid;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MapActivity extends AppCompatActivity implements LocationListener {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    LocationManager locationManager;
    String mainProvider;

    private ArrayList<TestingLocation> locations;

    final String baseUrl = "https://discover.search.hereapi.com/v1/discover?apikey=" + "key" + "&q=Covid&at=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        locations = new ArrayList<>();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        getLastKnownLocation();

//        provider = locationManager.getBestProvider(new Criteria(), false);
//        Log.e("provider", provider);
//
//
//        Location location = locationManager.getLastKnownLocation(provider);
//

//        if (location != null) {
//            Log.i("Location Info", "Location achieved!");
//            Log.i("location", String.valueOf(location));
//        } else {
//            Log.i("Location Info", "No location :(");
//        }
    }

    private void getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            checkLocationPermission();
        }
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                mainProvider = provider;
                bestLocation = l;
            }
        }

        Log.e("location here", String.valueOf(bestLocation));

        Double lat = bestLocation.getLatitude();
        Double lng = bestLocation.getLongitude();

        Log.i("Location info: Lat", lat.toString());
        Log.i("Location info: Lng", lng.toString());

        search(lat, lng);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(mainProvider, 400, 1, this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            locationManager.removeUpdates(this);
        }
    }


    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MapActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        locationManager.requestLocationUpdates(mainProvider, 400, 1, this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }


    @Override
    public void onLocationChanged(android.location.Location location) {

        Double lat = location.getLatitude();
        Double lng = location.getLongitude();

        Log.i("Location info: Lat", lat.toString());
        Log.i("Location info: Lng", lng.toString());

//        search(lat, lng);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {
        Log.e("provider is disabled", s);

    }

    public void goToLocationsListActivity() {
        Intent intent = new Intent(this, LocationsListActivity.class);

        intent.putParcelableArrayListExtra("key", (ArrayList<? extends Parcelable>) locations);

        startActivity(intent);
    }

    public void search(Double lat, Double lng) {

        final String url = baseUrl + lat + "," + lng + "&limit=5";
        locations = new ArrayList<>();
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response);
                            JSONArray items = jsonObject.getJSONArray("items");

                            for (int i = 0; i < items.length(); i++) {
                                JSONObject obj = items.getJSONObject(i);
                                JSONObject addressObj = obj.getJSONObject("address");

                                String houseNumber;

                                if (addressObj.has("houseNumber")) {
                                    houseNumber = addressObj.getString("houseNumber");
                                } else {
                                    houseNumber = null;
                                }

                                Address address = new Address(
                                        addressObj.getString("label"),
                                        addressObj.getString("countryCode"),
                                        addressObj.getString("countryName"),
                                        addressObj.getString("stateCode"),
                                        addressObj.getString("state"),
                                        addressObj.getString("county"),
                                        addressObj.getString("city"),
                                        addressObj.getString("street"),
                                        addressObj.getString("postalCode"),
                                        houseNumber
                                );
                                JSONObject positionObj = obj.getJSONObject("position");


                                Position position = new Position(
                                        positionObj.getDouble("lat"),
                                        positionObj.getDouble("lng")
                                );

                                JSONArray accessArray = obj.getJSONArray("access");
                                JSONObject accessObj = accessArray.getJSONObject(0);

                                Position accessPoint = new Position(
                                        accessObj.getDouble("lat"),
                                        accessObj.getDouble("lng")
                                );

                                JSONArray contactsArray = obj.getJSONArray("contacts");
                                JSONObject contactsObj = contactsArray.getJSONObject(0);

                                JSONArray phoneArray = contactsObj.getJSONArray("phone");
                                JSONObject phoneObj = phoneArray.getJSONObject(0);

                                JSONArray websiteArray = contactsObj.getJSONArray("www");
                                JSONObject websiteObj = websiteArray.getJSONObject(0);

                                TestingLocation location = new TestingLocation(
                                        obj.getString("title"),
                                        obj.getString("id"),
                                        obj.getString("resultType"),
                                        address,
                                        position,
                                        accessPoint,
                                        obj.getInt("distance"),
                                        phoneObj.getString("value"),
                                        websiteObj.getString("value")
                                );

                                locations.add(location);
                            }

                            goToLocationsListActivity();
                        } catch (JSONException err){
                            Log.d("Error", err.toString());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", String.valueOf(error));
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


}
