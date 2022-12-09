package com.example.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskAdapter = TaskAdapter(mutableListOf())

        rvTodoItems.adapter = taskAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)
        
        etNewTaskText.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                btnAddTask.isEnabled = text.trim().isNotEmpty()
            }
        }

        btnAddTask.setOnClickListener {
            val newTaskText = etNewTaskText.text.toString().trim()
            if (newTaskText.isNotEmpty()) {
                val newTask = Task(newTaskText)
                taskAdapter.addTask(newTask)
                etNewTaskText.text.clear()
            }
        }

        btnRemoveCompletedTasks.setOnClickListener {
            taskAdapter.removeCompletedTasks()
        }
    }
}