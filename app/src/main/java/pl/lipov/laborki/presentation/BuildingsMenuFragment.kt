package pl.lipov.laborki.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import pl.lipov.laborki.R

class BuildingsMenuFragment : BottomSheetDialogFragment() {

    companion object {
        val TAG = BuildingsMenuFragment::class.java.simpleName //zwraca nazwę fragmentu
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var inflate = inflater.inflate(R.layout.buildings_menu, container, false)

        return inflate
    }

}