package pl.lipov.laborki.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_building.view.*

class BuildingViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    val icon: ImageView = view.icon_image_view
    val name: TextView = view.name_text_view
}