package com.iamdamjanmiloshevski.makedoniko.fragments.bases

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.iamdamjanmiloshevski.makedoniko.viewmodels.HomeChildViewModel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders


/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
abstract class BaseHomeFragment : Fragment() {

   fun getFragmentView(
        context: Context?,
        viewGroup: ViewGroup?,
        attachToRoot: Boolean,
        savedInstanceState: Bundle?
    ): View {
        return LayoutInflater.from(context).inflate(com.iamdamjanmiloshevski.makedoniko.R.layout.tab_home_child, viewGroup, attachToRoot)
    }
    fun getViewModel(activity: FragmentActivity):HomeChildViewModel{
       return ViewModelProviders.of(activity).get(HomeChildViewModel::class.java)
    }
}