package com.balyaba.genesisgithubapp.features.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.balyaba.genesisgithubapp.R

class AboutFragment : Fragment() {

    private lateinit var notificationsViewModel: AboutViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(AboutViewModel::class.java)

        return inflater.inflate(R.layout.fragment_about, container, false)
    }
}