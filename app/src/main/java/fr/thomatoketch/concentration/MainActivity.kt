package fr.thomatoketch.concentration

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fr.thomatoketch.concentration.fragments.HomeFragment
import android.widget.Button
import android.view.View
import android.animation.ObjectAnimator
import android.content.Intent
import android.widget.ImageView
import fr.thomatoketch.concentration.fragment.monRepertoireFragment

class MainActivity : AppCompatActivity() {

    private lateinit var countdownTextView: TextView
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var startButton: Button
    private lateinit var pauseButton: Button
    private lateinit var restartButton: Button
    private lateinit var giveUpButton: Button
    private lateinit var repertoireButton: Button
    private var tempsInitial: Long = 3600 // Exemple : 600 secondes (10 minutes)
    private var tempsRestant: Long = tempsInitial



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
