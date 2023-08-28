
package fr.thomatoketch.concentration.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.data.Folder
import fr.thomatoketch.concentration.data.ViewModel
import fr.thomatoketch.concentration.fragments.NewTaskFragment
import kotlinx.android.synthetic.main.item_folder_popup.view.textView

class NewTaskFolderAdapter(val context: MainActivity): RecyclerView.Adapter<NewTaskFolderAdapter.MyViewHolder>() {

    private var folderList = emptyList<Folder>()
    private lateinit var viewModel: ViewModel

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_folder, parent, false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentFolder = folderList[position]
        holder.itemView.textView.text = currentFolder.name.toString()

        viewModel = ViewModelProvider(context).get(ViewModel::class.java)
        //holder.itemView.truc.text = currentFolder.color.toString()

        holder.itemView.setOnClickListener{
            Log.d("TAG", "id of folder : ${currentFolder.id}")
            viewModel.getTaskByFolder(currentFolder.id)
            val transaction = context.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, NewTaskFragment(context))
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun getItemCount(): Int {
        return folderList.size
    }

    fun setData(folder: List<Folder>) {
        this.folderList = folder
        notifyDataSetChanged()
    }
}