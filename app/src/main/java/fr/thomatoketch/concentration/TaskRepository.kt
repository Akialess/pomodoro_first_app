package fr.thomatoketch.concentration

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import fr.thomatoketch.concentration.FolderRepository.Singleton.databaseRef
import javax.security.auth.callback.Callback

class TaskRepository {

    fun getTaskList(reference: String, callback: (List<TaskModel>) -> Unit){

        val taskList = arrayListOf<TaskModel>()

        databaseRef.child(reference).child("activityfolder").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                FolderRepository.Singleton.taskList.clear()
                Log.d("TAG", "here in TaskRepository")
                for (ds in snapshot.children) {
                    val task = ds.getValue(TaskModel::class.java) // en quel objet doit etre transformer folder
                    if (task != null){
                        Log.d("TAG", "Il y a dans la liste ${task.name}")
                        taskList.add(task)
                    }
                }
                callback(taskList)
            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }
}