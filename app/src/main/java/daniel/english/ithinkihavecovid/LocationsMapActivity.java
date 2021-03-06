/*
  Copyright 2017 Google Inc. All Rights Reserved.
  <p>
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  <p>
  http://www.apache.org/licenses/LICENSE-2.0
  <p>
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package daniel.english.ithinkihavecovid;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class LocationsMapActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private ArrayList<TestingLocation> locations;

    private Double lat;
    private Double lng;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_map);

        Intent intent = getIntent();
        locations = (ArrayList<TestingLocation>)intent.getSerializableExtra("list");

        Bundle bundle = intent.getExtras();
        lat = bundle.getDouble("lat");
        lng = bundle.getDouble("lng");

        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync( this);

    }

    public void goToLocationsListActivity() {
        Intent intent = new Intent(this, LocationsListActivity.class);
        intent.putExtra("list", locations);
        startActivity(intent);
    }

    public void goToLocationInfoActivity(TestingLocation location) {
        Intent intent = new Intent(this, LocationInfoActivity.class);
        intent.putExtra("location", location);
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

//        LatLng fh = new LatLng(48.465014, -122.962771);
        LatLng current = new LatLng(lat, lng);

        googleMap.addMarker(new MarkerOptions()
                .position(current)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title("Your location"));

        getMarkers(googleMap);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 9));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnMarkerClickListener(this);
    }

    private void getMarkers(GoogleMap googleMap) {

        for (TestingLocation location : locations) {
            Double lat = location.getPosition().getLatitude();
            Double lng = location.getPosition().getLongitude();

            LatLng position = new LatLng(lat, lng);

            googleMap.addMarker(new MarkerOptions()
                    .position(position)
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title(location.getTitle()))
                    .setTag(location);

            googleMap.setOnInfoWindowClickListener(this);
        }
    }


    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }


    @Override
    public void onInfoWindowClick(final Marker marker) {
        TestingLocation location = (TestingLocation) marker.getTag();

        goToLocationInfoActivity(location);
    }
}
