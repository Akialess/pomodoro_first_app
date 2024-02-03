package fr.thomatoketch.concentration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.thomatoketch.concentration.data.Task
import fr.thomatoketch.concentration.fragments.HomeFragment
import fr.thomatoketch.concentration.fragments.FolderFragment


class MainActivity : AppCompatActivity(), Communicator {

    private val fragmentHome = HomeFragment(this)
    private val fragmentFolder = FolderFragment(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //barre de navigation
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
            fragmentHome.saveData()
            when (it.itemId) {
                R.id.home_page -> {
                    loadFragment(fragmentHome)
                    fragmentHome.loadData()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.task_page -> {
                    loadFragment(fragmentFolder)
                    return@setOnNavigationItemSelectedListener true
                }
                //TODO("modifier pour les suivants")
                R.id.stat_page -> {
                    loadFragment(fragmentHome)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.setting_page -> {
                    loadFragment(fragmentHome)
                    return@setOnNavigationItemSelectedListener true
                }

                else -> false
            }
        }

        loadFragment(fragmentHome)
    }


    private fun loadFragment(fragment: Fragment) {

        //injecter le fragment dans notre boite (fragment_container)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun passData(task: Task) {
        // Sert à communiquer entre le TaskFragment et le HomeFragment pour lancer une tache depuis
        // le TaskFragment. On donne la tache dans le TaskFragment au mainActivity qui a acces aux
        // deux fragments et on l'envoie ensuite au HomeFragment pour lancer la tache

        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.selectedItemId = R.id.home_page

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragmentHome)
        transaction.commitNow()

        fragmentHome.runTask(task)

    }
}

