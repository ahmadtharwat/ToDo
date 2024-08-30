package com.example.myapplication12

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var toDoAdapter: ToDoAdapter
    private val toDoList = mutableListOf<ToDoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toDoAdapter = ToDoAdapter(toDoList, { toDoItem ->
            toDoList.remove(toDoItem)
            toDoAdapter.notifyDataSetChanged()
        }, { toDoItem ->
            // Handle edit action
            val fragment = AddToDoFragment.newInstance(toDoItem)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        })

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = toDoAdapter

        val addButton = findViewById<FloatingActionButton>(R.id.fab_add)
        addButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AddToDoFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    fun addToDoItem(item: ToDoItem) {
        toDoList.add(item)
        toDoAdapter.notifyDataSetChanged()
    }

    fun updateToDoItem(oldItem: ToDoItem, newItem: ToDoItem) {
        val index = toDoList.indexOf(oldItem)
        if (index != -1) {
            toDoList[index] = newItem
            toDoAdapter.notifyDataSetChanged()
        }
    }
}