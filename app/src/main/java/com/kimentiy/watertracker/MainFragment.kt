package com.kimentiy.watertracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {

    private val buttonsAdapter =
        AdditionButtonsAdapter(
            onButtonClicked = ::onButtonClicked,
            onRemoveButtonClicked = ::onRemoveButtonClicked
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonsRecycler = view.findViewById<RecyclerView>(R.id.recycler_view_addition_buttons)

        buttonsRecycler.adapter = buttonsAdapter
        buttonsRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val editAdditionButtonsButton =
            view.findViewById<TextView>(R.id.text_view_edit_addition_buttons)

        editAdditionButtonsButton.setOnClickListener {
            openEditAdditionButtonsScreen()
        }
    }

    private fun openEditAdditionButtonsScreen() {
        fragmentManager?.beginTransaction()
            ?.replace(android.R.id.content, EditAdditionButtonsFragment())
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun onButtonClicked(button: AddWaterButton) {

    }

    private fun onRemoveButtonClicked(button: AddWaterButton) {

    }
}