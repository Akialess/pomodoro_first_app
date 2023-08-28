package fr.thomatoketch.concentration.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Fait le lien entre le repository et l'UI et donne les donnees pour l'UI
class ViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Folder>>
    val readAllTask: LiveData<List<Task>>
    var readAllTaskByFolder: LiveData<List<TaskWithFolder>>?
    private val repositoryFolder: NewFolderRepository
    private val repositoryTask: NewTaskRepository

    init {
        val folderDao = MyDatabase.getDatabase(application).folderDao()
        val taskDao = MyDatabase.getDatabase(application).taskDao()
        repositoryFolder = NewFolderRepository(folderDao)
        repositoryTask = NewTaskRepository(taskDao)
        readAllData = repositoryFolder.readAllData
        readAllTask = repositoryTask.readAllData
        //readAllTaskByFolder = repositoryTask.readAllDataByFolder
        readAllTaskByFolder = null
    }

    fun addFolder(folder: Folder) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryFolder.addFolder(folder)
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryTask.addTask(task)
        }
    }

    fun getTaskByFolder(folderId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            readAllTaskByFolder = repositoryTask.getTaskByFolder(folderId)
        }
    }
}