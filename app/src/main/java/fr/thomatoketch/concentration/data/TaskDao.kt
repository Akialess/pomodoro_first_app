package fr.thomatoketch.concentration.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //si on a 2 fois le meme, on ignore
    fun addTask(task: Task)

    @Query("SELECT * FROM task_table ORDER BY id ASC") //Query -> ecrire des instructions SQL (prendre toute la table)
    fun readAllData(): LiveData<List<Task>>

}