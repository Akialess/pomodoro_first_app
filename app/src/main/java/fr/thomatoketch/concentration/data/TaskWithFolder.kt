package fr.thomatoketch.concentration.data

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithFolder (
    @Embedded
    val folder: Folder,

    @Relation(
        parentColumn = "id",
        entityColumn = "folderId"
    )
    val task: List<Task>
)