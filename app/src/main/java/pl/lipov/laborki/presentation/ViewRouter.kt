package pl.lipov.laborki.presentation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.ValueEventListener
import pl.lipov.laborki.R

class ViewRouter {

    fun fragmentChange(
        fragment: Fragment,
        activity: FragmentActivity?,
        @IdRes fragmentContainerId: Int = R.id.root_container
    ){
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            setCustomAnimations(R.anim.fade_in,0,0, R.anim.fade_out)
            replace(fragmentContainerId, fragment)

            commit()
        }
    }
}