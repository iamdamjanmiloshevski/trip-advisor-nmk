package com.iamdamjanmiloshevski.makedoniko.fragments.main

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.fragments.bases.BaseFragment
import com.iamdamjanmiloshevski.makedoniko.fragments.pointsofInterest.SkopjePOIFragment

import kotlinx.android.synthetic.main.tab_poi.view.*


/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
class PointsOfInterestFragment : BaseFragment() {
    private lateinit var fragmentView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = getFragmentView(context, container, false, savedInstanceState)
        return fragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(com.iamdamjanmiloshevski.makedoniko.R.menu.points_of_interest_menu, menu)
        menu!!.findItem(com.iamdamjanmiloshevski.makedoniko.R.id.action_settings).setVisible(false)
        val menuItem = menu!!.findItem(com.iamdamjanmiloshevski.makedoniko.R.id.sp_city)
        val spinner = menuItem.actionView as AppCompatSpinner
        ArrayAdapter.createFromResource(
            context!!,
            com.iamdamjanmiloshevski.makedoniko.R.array.cities,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
            spinner.setSelection(0)
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (parent?.getItemAtPosition(position)) {
                    "Skopje" -> {
                        switchView(R.id.fl_page, SkopjePOIFragment.getInstance())
                    }
                }
            }
        }
    }

    private fun switchView(view: Int, fragment: androidx.fragment.app.Fragment) {
        childFragmentManager.beginTransaction().replace(view, fragment).commit()
    }


    override fun getLayoutId(): Int {
        return R.layout.tab_poi
    }

    companion object {
        fun getInstance(): PointsOfInterestFragment {
            return PointsOfInterestFragment()
        }
    }

}