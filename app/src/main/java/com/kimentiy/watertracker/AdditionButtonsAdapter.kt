package com.kimentiy.watertracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdditionButtonViewHolder(
    view: View,
    private val onButtonClicked: (AddWaterButton) -> Unit,
    private val onRemoveButtonClicked: (AddWaterButton) -> Unit

) : RecyclerView.ViewHolder(view) {
    val waterAmount: TextView = view.findViewById(R.id.text_view_water_amount)
    val addButton: View = view.findViewById(R.id.fab_add_water)
    val removeButton: View = view.findViewById(R.id.fab_remove)

    fun bind(button: AddWaterButton) {
        waterAmount.text = button.amount.toString()

        addButton.setOnClickListener {
            onButtonClicked(button)
        }
        removeButton.setOnClickListener {
            onRemoveButtonClicked(button)
        }
    }
}

class AdditionButtonsAdapter(
    private val onButtonClicked: (AddWaterButton) -> Unit,
    private val onRemoveButtonClicked: (AddWaterButton) -> Unit
) : RecyclerView.Adapter<AdditionButtonViewHolder>() {

    val buttons = List(5) { AddWaterButton((0.0 / (it + 1)).toInt()) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdditionButtonViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_add_water, parent, false)

        return AdditionButtonViewHolder(view, onButtonClicked, onRemoveButtonClicked)
    }

    override fun getItemCount(): Int {
        return buttons.size
    }

    override fun onBindViewHolder(holder: AdditionButtonViewHolder, position: Int) {
        holder.bind(buttons[position])
    }
}
