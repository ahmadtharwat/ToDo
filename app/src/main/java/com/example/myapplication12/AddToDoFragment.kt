package com.example.myapplication12

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import androidx.fragment.app.Fragment

class AddToDoFragment : Fragment() {

    private var existingItem: ToDoItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_todo, container, false)

        val titleEditText = view.findViewById<EditText>(R.id.edit_title)
        val descriptionEditText = view.findViewById<EditText>(R.id.edit_description)
        val timePicker = view.findViewById<TimePicker>(R.id.time_picker)
        val saveButton = view.findViewById<Button>(R.id.btn_save)

        existingItem?.let {
            titleEditText.setText(it.title)
            descriptionEditText.setText(it.description)
            val timeParts = it.time.split(":")
            timePicker.hour = timeParts[0].toInt()
            timePicker.minute = timeParts[1].toInt()
        }

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val hour = timePicker.hour
            val minute = timePicker.minute
            val time = String.format("%02d:%02d", hour, minute)

            val toDoItem = ToDoItem(title, time, description)

            if (existingItem == null) {
                (activity as MainActivity).addToDoItem(toDoItem)
            } else {
                (activity as MainActivity).updateToDoItem(existingItem!!, toDoItem)
            }

            parentFragmentManager.popBackStack()
        }

        return view
    }

    companion object {
        fun newInstance(toDoItem: ToDoItem): AddToDoFragment {
            val fragment = AddToDoFragment()
            fragment.existingItem = toDoItem
            return fragment
        }
    }
}