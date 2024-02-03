package fr.thomatoketch.concentration.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Fait le lien entre le repository et l'UI et donne les donnees pour l'UI
class ViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Folder>>
    val readAllTask: LiveData<List<Task>>
    var readAllTaskByFolder: LiveData<List<TaskWithFolder>>?
    private val repositoryFolder: FolderRepository
    private val repositoryTask: TaskRepository

    // Save state fragment home
    var homeFragmentSaveState: HomeFragmentSaveState? // Stocke notre info
    val currentHomeFragmentSaveState: MutableLiveData<HomeFragmentSaveState> by lazy {
        MutableLiveData<HomeFragmentSaveState>()
    } // notifie un changement


    init {
        val folderDao = MyDatabase.getDatabase(application).folderDao()
        val taskDao = MyDatabase.getDatabase(application).taskDao()
        repositoryFolder = FolderRepository(folderDao)
        repositoryTask = TaskRepository(taskDao)
        readAllData = repositoryFolder.readAllData
        readAllTask = repositoryTask.readAllData

        readAllTaskByFolder = null

        homeFragmentSaveState = null

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

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryTask.updateTask(task)
        }
    }

    fun getTaskByFolder(folderId: Int) {
        //voir s'il faut mettre une coroutine
        readAllTaskByFolder = repositoryTask.getTaskByFolder(folderId)

    }

    fun getFolderInfoById(folderId: Int): LiveData<Folder> {
        return repositoryFolder.getFolderInfoByID(folderId)
    }

    fun updateFolder(folder: Folder) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryFolder.updateFolder(folder)
        }
    }

    fun deleteFolder(folder: Folder) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryFolder.deleteFolder(folder)
        }
    }

}