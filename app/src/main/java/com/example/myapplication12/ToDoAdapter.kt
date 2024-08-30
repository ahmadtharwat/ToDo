package com.example.myapplication12

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(
    private val toDoList: MutableList<ToDoItem>,
    private val onDeleteClick: (ToDoItem) -> Unit,
    private val onEditClick: (ToDoItem) -> Unit
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.tv_title)
        val timeTextView: TextView = view.findViewById(R.id.tv_time)
        val descriptionTextView: TextView = view.findViewById(R.id.tv_description)
        val deleteButton: Button = view.findViewById(R.id.btn_delete)
        val editButton: Button = view.findViewById(R.id.btn_edit) // New edit button reference
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val toDoItem = toDoList[position]
        holder.titleTextView.text = toDoItem.title
        holder.timeTextView.text = toDoItem.time
        holder.descriptionTextView.text = toDoItem.description

        holder.deleteButton.setOnClickListener {
            onDeleteClick(toDoItem)
        }

        holder.editButton.setOnClickListener {
            onEditClick(toDoItem)  // Trigger the edit callback
        }
    }

    override fun getItemCount(): Int = toDoList.size
}
