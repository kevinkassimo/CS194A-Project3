package edu.stanford.kassimo.simpleyelp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

private const val TAG = "MapsActivity"
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var restaurant: YelpRestaurant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        restaurant = intent.getSerializableExtra(EXTRA_RESTAURANT) as YelpRestaurant
        supportActionBar?.title = "Restaurant Location"

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        Log.i(TAG, "Restaurant to render: ${restaurant.name}")

        // Add a marker in Sydney and move the camera
        val restaurantLocation = LatLng(restaurant.coordinates.latitude, restaurant.coordinates.longitude)
        mMap.addMarker(MarkerOptions().position(restaurantLocation).title(restaurant.name).snippet(restaurant.location.address))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(restaurantLocation))
    }
}