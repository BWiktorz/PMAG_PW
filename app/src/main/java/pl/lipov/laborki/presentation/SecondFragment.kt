package pl.lipov.laborki.presentation

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_second.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.lipov.laborki.R

class SecondFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private var passPin = listOf<String>()
    private var PIN = ArrayList<String>()
    private var step_PIN = 0
    private var tries = 0
    private var locked = false

    companion object {
        @JvmStatic
        fun newInstance(userPin: Array<String>?) = SecondFragment().apply {
            arguments = Bundle().apply {
                putStringArray("pin", userPin)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getStringArray("pin")?.let {
            passPin = it.toList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_second, container, false)

        inflate.setOnTouchListener { _, event ->
            viewModel.onTouchEvent(event)
            true
        }
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.let { viewModel.initializeGestureDetector(it) }
        activity?.let { viewModel.initializeSensorEvents(it) }

        viewModel.run {
            onAccelerometerNotDetected.observe(::getLifecycle) {
            }
            onGestureEvent.observe(::getLifecycle) {
                PIN.add(it.name)
                checkPIN(it.name)
            }
            onSensorEvent.observe(::getLifecycle) {
                PIN.add(it.name)
                checkPIN(it.name)
            }
        }
    }

    fun checkPIN(event: String) {
        if (event == passPin[step_PIN]) {
            when (step_PIN) {
                0 -> {
                    showOrHide(ic_check1)
                }
                1 -> {
                    showOrHide(ic_check2)
                }
                2 -> {
                    showOrHide(ic_check3)
                }
                3 -> {
                    showOrHide(ic_check4)
                    info_text.text = "Odblokowano"
                    lockImage.setImageResource(R.drawable.ic_lock_open)
                    lockImage.setColorFilter(
                        lockImage.context.resources.getColor(R.color.green2),
                        PorterDuff.Mode.SRC_ATOP
                    )
                    Handler().postDelayed({
                        ViewRouter().fragmentChange(ThirdFragment.newInstance(), activity, R.id.fragmentContainer)
                    }, 1000)

                }
            }
            if (step_PIN < 3) {
                step_PIN += 1
            }
        } else if (event != passPin[step_PIN] && locked == false) {
            step_PIN = 0
            if (tries == 2) {
                info_text.text = "Źle wpisano PIN, zablokowano Interfejs"
                lockImage.setColorFilter(
                    lockImage.context.resources.getColor(R.color.error),
                    PorterDuff.Mode.SRC_ATOP
                )
                blockInterface()
            } else {
                tries += 1
                info_text.text = "Źle wpisano PIN, masz jeszcze prób:${(3 - tries)}"
                lockImage.setImageResource(R.drawable.ic_lock)
            }
            ic_check1.visibility = View.INVISIBLE
            ic_check2.visibility = View.INVISIBLE
            ic_check3.visibility = View.INVISIBLE
            ic_check4.visibility = View.INVISIBLE
            PIN.clear()
        }
    }

    private fun blockInterface() {
        viewModel.unregisterSensorManager()
        viewModel.unregisterGestureDetector()
    }

    override fun onResume() {
        viewModel.registerSensorManager()
        super.onResume()
    }

    override fun onPause() {
        viewModel.unregisterSensorManager()
        super.onPause()
    }

    fun showOrHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
    }
}

