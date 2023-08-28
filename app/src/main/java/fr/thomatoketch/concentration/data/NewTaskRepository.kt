package fr.thomatoketch.concentration.data

import androidx.lifecycle.LiveData


//Intermediaire entre la base de donnee et les autres parties de l'application
class NewTaskRepository(private val taskDao: TaskDao) {

    val readAllData: LiveData<List<Task>> = taskDao.readAllData()
    //var readAllDataByFolder: LiveData<List<TaskWithFolderEntity>> = taskDao.getTaskByFolder(2)

    //val readAllDataWithFolderEntity: LiveData<List<TaskWithFolderEntity>> = taskDao.getAllTaskWithFolder()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

    fun getTaskByFolder(folderId: Int): LiveData<List<TaskWithFolder>> {
        return taskDao.getTaskByFolder(folderId)
    }

}