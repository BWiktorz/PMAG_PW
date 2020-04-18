package pl.lipov.laborki.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.channels.consumesAll
import pl.lipov.laborki.data.model.Building
import pl.lipov.laborki.R

class BuildingAdapter(
    private var buildings: List<Building>,
    private var context: Context
) : RecyclerView.Adapter<BuildingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingViewHolder {
        val textView = LayoutInflater.from(context).inflate(R.layout.item_building, parent, false) as TextView
        return BuildingViewHolder(textView)
    }

    override fun getItemCount(): Int = buildings.size

    override fun onBindViewHolder(holder: BuildingViewHolder, position: Int) {
        holder.name.text = buildings[position].name
        //holder.icon.setImageDrawable()
    }
}