package com.iamdamjanmiloshevski.makedoniko.fragments.pointsofInterest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.internal.VisibilityAwareImageButton
import com.iamdamjanmiloshevski.makedoniko.adapters.LandmarksRecyclerViewAdapter
import com.iamdamjanmiloshevski.makedoniko.fragments.bases.BasePOIFragment
import com.iamdamjanmiloshevski.makedoniko.models.Landmark
import com.iamdamjanmiloshevski.makedoniko.viewmodels.pointsofinterest.SkopjePointsOfInterestViewModel
import kotlinx.android.synthetic.main.tab_home_child.view.*
import kotlinx.android.synthetic.main.tab_home_child.view.progressbar
import kotlinx.android.synthetic.main.tab_home_child.view.tv_no_data
import kotlinx.android.synthetic.main.tab_poi_child.view.*

/** Created by Damjan on 23.6.2019
Project: trip-advisor-nmk
 **/
class SkopjePOIFragment : BasePOIFragment() {
    override fun registerObservers() {
        mViewModel.getLandmarksForCity("Skopje", fragmentView.rv_landmarks.adapter as LandmarksRecyclerViewAdapter)
            .observe(this, this)
    }

    override fun removeObservers() {
        mViewModel.getLandmarksForCity("Skopje", fragmentView.rv_landmarks.adapter as LandmarksRecyclerViewAdapter)
            .removeObserver(this)
    }

    override fun initComponents() {
        mViewModel = ViewModelProviders.of(activity!!).get(SkopjePointsOfInterestViewModel::class.java)
        fragmentView.rv_landmarks.layoutManager = LinearLayoutManager(context)
        fragmentView.rv_landmarks.adapter = LandmarksRecyclerViewAdapter(context!!, landmarks)
        fragmentView.progressbar.visibility = View.VISIBLE
    }

    /**
     * Called when the data is changed.
     * @param t  The new data
     */
    override fun onChanged(data: List<Landmark>?) {
        fragmentView.progressbar.visibility = View.VISIBLE
        getData(
            fragmentView.rv_landmarks,
            fragmentView.rv_landmarks.adapter as LandmarksRecyclerViewAdapter,
            fragmentView.tv_no_data,
            fragmentView.progressbar,
            data,
            landmarks
        )
    }

    private lateinit var mViewModel: SkopjePointsOfInterestViewModel
    private lateinit var fragmentView: View
    private var landmarks: MutableList<Landmark> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = getFragmentView(context, container, false, savedInstanceState)
        initComponents()
        registerObservers()
        return fragmentView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removeObservers()
    }

    companion object {
        const val TAG = "SkopjePOIFragment"
        fun getInstance(): SkopjePOIFragment {
            return SkopjePOIFragment()
        }
    }
}