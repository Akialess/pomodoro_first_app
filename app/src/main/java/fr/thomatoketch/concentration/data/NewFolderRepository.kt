package fr.thomatoketch.concentration.data

import androidx.lifecycle.LiveData

//Intermediaire entre la base de donnee et les autres parties de l'application
class NewFolderRepository(private val folderDao: FolderDao) {

    val readAllData: LiveData<List<Folder>> = folderDao.readAllData()

    suspend fun addFolder(folder: Folder){
        folderDao.addFolder(folder)
    }
}