package fr.thomatoketch.concentration.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String?,
    val folderId: Int,
    val color: String?,
    val time: Int,
    var remainingTask: Int,
    var totalTask: Int
) : Serializable