package fr.thomatoketch.concentration

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.adapter.TaskAdapter

class TaskPopup(private val adapter: TaskAdapter
) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_task)
        val recyclerView = findViewById<RecyclerView>(R.id.vertical_recycler_view)
        recyclerView.adapter = adapter
    }
}

