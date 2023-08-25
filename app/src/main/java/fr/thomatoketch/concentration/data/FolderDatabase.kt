package fr.thomatoketch.concentration.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Contient la database et sert à faire les relations entre l'app et la database
@Database(entities = [Folder::class, Task::class], version = 2, exportSchema = false) // /:\ a chaque modif de la db (table, colonne), changer la version
abstract class FolderDatabase: RoomDatabase() {

    abstract fun folderDao(): FolderDao
    abstract fun taskDao(): TaskDao

    companion object {
        //Represente un singleton, on cree une instance unique pour notre database ou on la recupere
        @Volatile
        private var INSTANCE: FolderDatabase? = null

        fun getDatabase(context: Context): FolderDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FolderDatabase::class.java,
                    "folder_database"
                ).fallbackToDestructiveMigration().build()  //fallbackToDestructiveMigration sert a supprimer l'ancienne version de la db si on la modifie
                INSTANCE = instance
                return instance
            }
        }
    }
}