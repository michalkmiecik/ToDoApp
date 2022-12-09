package com.example.todoapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(
    private val tasks: MutableList<Task>
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_task,
                parent,
                false
            )
        )
    }

    fun addTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }

    fun removeCompletedTasks() {
        tasks.removeAll { task ->
            task.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTaskText: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTaskText.paintFlags = tvTaskText.paintFlags.or(STRIKE_THRU_TEXT_FLAG)
        } else {
            tvTaskText.paintFlags = tvTaskText.paintFlags.and(STRIKE_THRU_TEXT_FLAG.inv())
        }
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.itemView.apply {
            tvTaskText.text = task.taskText
            cbCompleted.isChecked = task.isChecked
            toggleStrikeThrough(tvTaskText, task.isChecked)

            cbCompleted.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTaskText, isChecked)
                task.isChecked = !task.isChecked
            }
        }
    }

    override fun getItemCount(): Int = tasks.size

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}