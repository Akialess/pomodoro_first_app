package fr.thomatoketch.concentration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.thomatoketch.concentration.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //injecter le fragment dans notre boite (fragment_container)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, HomeFragment(this)) //modifier le dernier param pour afficher une autre page
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
