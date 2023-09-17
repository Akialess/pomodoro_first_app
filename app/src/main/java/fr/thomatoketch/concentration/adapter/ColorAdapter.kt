package fr.thomatoketch.concentration.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.data.Color
import kotlinx.android.synthetic.main.item_color.view.cercle_color
import kotlinx.android.synthetic.main.item_color.view.cercle_select

class ColorAdapter(private val context: MainActivity): RecyclerView.Adapter<ColorAdapter.MyViewHolder>() {
    private var colorList = listOf(Color(0, "#2457C5"), Color(0, "#FF0000"), Color(0, "#FFEB3B"), Color(0, "#03A9F4"), Color(0, "#006000"), Color(0, "#9C27B0"),
        Color(0, "#E91E63"), Color(0, "#FF5722"), Color(0, "#009688"))
    var singleitem_selection_position = 0

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return ColorAdapter.MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false))
    }

    override fun getItemCount(): Int {
        return colorList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentColor = colorList[position]
        val newColor = android.graphics.Color.parseColor(currentColor.color) //convertir la couleur en un entier
        holder.itemView.cercle_color.backgroundTintList = ColorStateList.valueOf(newColor) //change la couleur du fond de l'icone

        val bleuFond = android.graphics.Color.parseColor("#EDF2FC")
        val bleuSelection = android.graphics.Color.parseColor("#2457C5")

        //quand on selectionne l'item, on met un cercle colore autour de la couleur choisie
        if(singleitem_selection_position == position) {
            holder.itemView.cercle_select.backgroundTintList = ColorStateList.valueOf(bleuSelection)
        } else {
            holder.itemView.cercle_select.backgroundTintList = ColorStateList.valueOf(bleuFond)
        }

        holder.itemView.setOnClickListener{setSingleSelection(position)}



    }

    private fun setSingleSelection(adapterPosition: Int) {
        //savoir si item choisi

        if (adapterPosition == RecyclerView.NO_POSITION) return

        notifyItemChanged(singleitem_selection_position)

        singleitem_selection_position = adapterPosition

        notifyItemChanged(singleitem_selection_position)
    }

    fun getSelectedColor() : Color {
        return colorList[singleitem_selection_position]
    }

}