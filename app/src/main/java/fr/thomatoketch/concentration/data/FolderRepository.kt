package fr.thomatoketch.concentration.data

import androidx.lifecycle.LiveData

//Intermediaire entre la base de donnee et les autres parties de l'application
class FolderRepository(private val folderDao: FolderDao) {

    val readAllData: LiveData<List<Folder>> = folderDao.readAllData()

    suspend fun addFolder(folder: Folder){
        folderDao.addFolder(folder)
    }

    suspend fun updateFolder(folder: Folder){
        folderDao.updateFolder(folder)
    }

    suspend fun deleteFolder(folder: Folder){
        folderDao.deleteFolder(folder)
    }

    fun getFolderInfoByID(folderId: Int): LiveData<Folder> {
        return folderDao.getFolderInfoById(folderId)
    }
}