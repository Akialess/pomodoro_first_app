package fr.thomatoketch.concentration

import fr.thomatoketch.concentration.data.Task

interface TaskItemClickListener {
    fun onTaskItemClick(task: Task) {
    }
}