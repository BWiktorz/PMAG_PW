package pl.lipov.laborki.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.IndoorBuilding
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_third.*
import pl.lipov.laborki.R

class ThirdFragment : Fragment(), OnMapReadyCallback {

    private var sharedActionsListener: SharedActionsListener? = null

    //lateinit var mapFragment: SupportMapFragment
    lateinit var mMap: GoogleMap

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SharedActionsListener) {
            sharedActionsListener = context
        }
    }

    companion object {
        fun newInstance(): ThirdFragment {
            return ThirdFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var inflate = inflater.inflate(R.layout.fragment_third, container, false)

        sharedActionsListener?.hideBanner()

        //val mapFragment = (R.id.map) as SupportMapFragment
        //mapFragment.getMapAsync(this)

        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.supportFragmentManager?.let {
            BuildingsMenuFragment().show(
                it,
                BuildingsMenuFragment.TAG
            )
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap;

        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}

//    override fun onIndoorBuildingFocused() {
//        buildingHasFocus = !buildingHasFocus
//    }
//
//    override fun onIndoorLevelActivated(
//        indoorBuilding: IndoorBuilding
//    ) {
//        indoorBuilding.levels //lista pięter
//        indoorBuilding.activeLevelIndex //index ktywnego
//        // pobrać info o nazwie aktywnego piętra
//    }