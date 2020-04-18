package pl.lipov.laborki.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import pl.lipov.laborki.R


class MainActivity : AppCompatActivity(), SharedActionsListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewRouter().fragmentChange(FirstFragment.newInstance(), this, R.id.fragmentContainer)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideBanner() {
        imageView3.visibility = View.GONE
        imageView4.visibility = View.GONE
    }
}

