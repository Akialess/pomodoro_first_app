package fr.thomatoketch.concentration.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Fait le lien entre le repository et l'UI et donne les donnees pour l'UI
class FolderViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Folder>>
    val readAllTask: LiveData<List<Task>>
    private val repositoryFolder: NewFolderRepository
    private val repositoryTask: NewTaskRepository

    init {
        val folderDao = FolderDatabase.getDatabase(application).folderDao()
        val taskDao = FolderDatabase.getDatabase(application).taskDao()
        repositoryFolder = NewFolderRepository(folderDao)
        repositoryTask = NewTaskRepository(taskDao)
        readAllData = repositoryFolder.readAllData
        readAllTask = repositoryTask.readAllData
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
}