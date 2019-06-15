package com.iamdamjanmiloshevski.makedoniko.fragments.bases

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.iamdamjanmiloshevski.makedoniko.models.Phrase
import java.util.*


/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
abstract class BaseHomeFragment : Fragment(),Observer<List<Phrase>> {

    fun getFragmentView(
        context: Context?,
        viewGroup: ViewGroup?,
        attachToRoot: Boolean,
        savedInstanceState: Bundle?
    ): View {
        return LayoutInflater.from(context)
            .inflate(com.iamdamjanmiloshevski.makedoniko.R.layout.tab_home_child, viewGroup, attachToRoot)
    }
    abstract fun registerObservers()
    abstract fun initComponents()
    abstract fun removeObservers()
    fun showNoData(show:Boolean,rv_data:RecyclerView,tv_msg:AppCompatTextView){
        if(show){
            rv_data.visibility = View.GONE
            tv_msg.visibility = View.VISIBLE
        }else{
            rv_data.visibility = View.VISIBLE
            tv_msg.visibility = View.GONE
        }
    }
}