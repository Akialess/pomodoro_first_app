package fr.thomatoketch.concentration

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fr.thomatoketch.concentration.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //charger notre FolderRepository
        val repo = FolderRepository()

        //mettre a jour la listre de plantes
        repo.updateData {
            //le code ici sera execute apres avoir recuperer le call back
            //injecter le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, HomeFragment(this)) //modifier le dernier param pour afficher une autre page
            transaction.addToBackStack(null)
            transaction.commit()
            Log.d("TAG", "here 1")
        }
    }
}
