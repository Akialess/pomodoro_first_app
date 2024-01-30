package fr.thomatoketch.concentration

interface FolderItemClickListener {
    fun onFolderItemClick(folderId: Int)
    fun onLongFolderItemClick(folderId: Int)
}