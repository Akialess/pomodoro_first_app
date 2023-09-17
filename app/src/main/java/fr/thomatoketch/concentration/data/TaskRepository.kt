package fr.thomatoketch.concentration.data

import androidx.lifecycle.LiveData


//Intermediaire entre la base de donnee et les autres parties de l'application
class TaskRepository(private val taskDao: TaskDao) {

    val readAllData: LiveData<List<Task>> = taskDao.readAllData()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    fun getTaskByFolder(folderId: Int): LiveData<List<TaskWithFolder>> {
        return taskDao.getTaskByFolder(folderId)
    }

}