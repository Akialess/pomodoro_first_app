package fr.thomatoketch.concentration

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.thomatoketch.concentration.FolderRepository.Singleton.databaseRef

class FolderRepository {
    object Singleton {
        //se connecter a la reference "dossier" de la database
        val databaseRef = FirebaseDatabase.getInstance().getReference("folders")

        //creer une liste qui va contenir les dossiers
        val folderList = arrayListOf<TaskFolderModel>()
    }

    fun updateData() {
        //absorber les donnees depuis la databaseRef -> liste des dossiers
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //recolter la liste
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}