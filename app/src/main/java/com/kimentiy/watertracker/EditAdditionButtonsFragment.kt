package com.kimentiy.watertracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EditAdditionButtonsFragment : Fragment() {

    private val buttonsAdapter =
        AdditionButtonsAdapter(
            onButtonClicked = ::onButtonClicked,
            onRemoveButtonClicked = ::onRemoveButtonClicked
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_edit_addition_buttons, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonsRecycler = view.findViewById<RecyclerView>(R.id.recycler_view_addition_buttons)

        buttonsRecycler.adapter = buttonsAdapter
        buttonsRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun onButtonClicked(button: AddWaterButton) {

    }

    private fun onRemoveButtonClicked(button: AddWaterButton) {

    }
}
