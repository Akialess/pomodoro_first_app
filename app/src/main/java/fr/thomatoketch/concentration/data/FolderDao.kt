package fr.thomatoketch.concentration.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


//Contient les methodes pour avoir acces a la databse
@Dao
interface FolderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //si on a 2 fois le meme, on ignore
    fun addFolder(folder: Folder)

    @Query("SELECT * FROM folder_table ORDER BY id ASC") //Query -> ecrire des instructions SQL (prendre toute la table)
    fun readAllData(): LiveData<List<Folder>>

    @Query("SELECT * FROM folder_table WHERE id = :folderId")
    fun getFolderInfoById(folderId: Int): LiveData<Folder>

    @Delete
    fun deleteFolder(folder: Folder)

    @Update
    fun updateFolder(folder: Folder)
}