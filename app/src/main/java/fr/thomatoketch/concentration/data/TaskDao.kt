package fr.thomatoketch.concentration.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update


@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //si on a 2 fois le meme, on ignore
    fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    /*
    @Transaction
    @Query("SELECT * FROM task_table")
    fun getAllTaskWithFolder(): LiveData<List<TaskWithFolderEntity>>
     */

    @Transaction
    @Query("SELECT * FROM folder_table WHERE id = :folderId")
    fun getTaskByFolder(folderId: Int): LiveData<List<TaskWithFolder>>

    @Query("SELECT * FROM task_table ORDER BY id ASC") //Query -> ecrire des instructions SQL (prendre toute la table)
    fun readAllData(): LiveData<List<Task>>

}