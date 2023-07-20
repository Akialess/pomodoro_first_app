package fr.thomatoketch.concentration.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.Button
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import fr.thomatoketch.concentration.R

class HomeFragment : Fragment() {
    //Contient toutes les fonctions qui gèrent les fragments

    // injecter mon layout fragment_home sur la page

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }


}