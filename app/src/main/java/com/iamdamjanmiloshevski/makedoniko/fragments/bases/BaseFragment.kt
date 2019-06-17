package com.iamdamjanmiloshevski.makedoniko.fragments.bases

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

/** Created by Damjan on 09.6.2019
Project: trip-advisor-nmk
 **/
abstract class BaseFragment : Fragment(){

    fun getFragmentView(
        context: Context?,
        viewGroup: ViewGroup?,
        attachToRoot: Boolean,
        savedInstanceState: Bundle?
    ): View {
        return LayoutInflater.from(context).inflate(getLayoutId(), viewGroup, attachToRoot)
    }

    abstract fun getLayoutId(): Int
    fun setToolbar(toolbar: Toolbar, title: String) {
        toolbar.title = title
    }
}