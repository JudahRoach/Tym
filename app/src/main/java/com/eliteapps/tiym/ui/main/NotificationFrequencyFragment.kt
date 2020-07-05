package com.eliteapps.tiym.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eliteapps.tiym.R

class NotificationFrequencyFragment : Fragment() {

    companion object {
        fun newInstance() = NotificationFrequencyFragment()
    }

    private lateinit var viewModel: NotificationFrequencyViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.notification_frequency_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NotificationFrequencyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}