package com.cristina.cristinagallery.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cristina.cristinagallery.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Главный экран
 */
@AndroidEntryPoint
class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}