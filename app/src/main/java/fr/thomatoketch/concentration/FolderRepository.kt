package fr.thomatoketch.concentration

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.thomatoketch.concentration.FolderRepository.Singleton.databaseRef
import fr.thomatoketch.concentration.FolderRepository.Singleton.folderList
import android.util.Log
import javax.security.auth.callback.Callback

class FolderRepository {
    object Singleton {
        //se connecter a la reference "dossier" de la database
        val databaseRef = FirebaseDatabase.getInstance().getReference("dossier") //ref dans la firebase

        //creer une liste qui va contenir les dossiers
        val folderList = arrayListOf<TaskFolderModel>()
    }

    fun updateData(callback: () -> Unit) {
        //absorber les donnees depuis la databaseRef -> liste des dossiers
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //retirer les anciennes et mettre a jour
                folderList.clear()
                //recolter la liste
                for (ds in snapshot.children) {
                    //construire un objet dossier
                    Log.d("TAG", "hereeeee")
                    val folder = ds.getValue(TaskFolderModel::class.java) // en quel objet doit etre transformer
                    //verifier que le dossier n'est pas null
                    if (folder != null){
                        //ajouter le dossier a notre liste

                        folderList.add(folder)
                    }
                }
                //actionner le callback donc peut permettre a l appli de lancer le code quand on a la database
                callback()
            }

            override fun onCancelled(error: DatabaseError) {
                //quand on trouve pas
            }

        })
    }
}