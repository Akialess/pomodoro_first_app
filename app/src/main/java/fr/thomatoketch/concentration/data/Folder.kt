package fr.thomatoketch.concentration.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//Modele de la table Folder
@Entity(tableName = "folder_table")
data class Folder (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val color: String
)