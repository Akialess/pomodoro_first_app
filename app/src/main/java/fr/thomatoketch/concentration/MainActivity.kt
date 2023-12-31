package fr.thomatoketch.concentration

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.thomatoketch.concentration.fragments.HomeFragment
import fr.thomatoketch.concentration.fragments.FolderFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //barre de navigation
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home_page -> {
                    loadFragment(HomeFragment(this))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.task_page -> {
                    loadFragment(FolderFragment(this))
                    return@setOnNavigationItemSelectedListener true
                }
                //TODO("modifier pour les suivants")
                R.id.stat_page -> {
                    loadFragment(HomeFragment(this))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.setting_page -> {
                    loadFragment(HomeFragment(this))
                    return@setOnNavigationItemSelectedListener true
                }

                else -> false
            }
        }

        loadFragment(HomeFragment(this))
    }


    private fun loadFragment(fragment: Fragment) {

        //injecter le fragment dans notre boite (fragment_container)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        Log.d("TAG", "here 1")

    }


}
