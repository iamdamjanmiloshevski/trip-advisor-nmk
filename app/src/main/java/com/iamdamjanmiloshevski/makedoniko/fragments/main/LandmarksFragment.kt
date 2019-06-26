package com.iamdamjanmiloshevski.makedoniko.fragments.main

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.fragments.bases.BaseFragment
import com.iamdamjanmiloshevski.makedoniko.fragments.pointsofInterest.SkopjePOIFragment


/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
class LandmarksFragment : BaseFragment() {
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
        inflater!!.inflate(R.menu.landmarks_menu, menu)
        menu!!.findItem(R.id.action_settings).isVisible = false
        val menuItem = menu!!.findItem(R.id.sp_city)
        val spinner = menuItem.actionView as AppCompatSpinner
        ArrayAdapter.createFromResource(
            context!!,
         R.array.cities,
            R.layout.landmarks_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.landmarks_spinner_dropdown_item)
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

    private fun switchView(view: Int, fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(view, fragment)
            .commit()
    }


    override fun getLayoutId(): Int {
        return R.layout.tab_poi
    }

    companion object {
        fun getInstance(): LandmarksFragment {
            return LandmarksFragment()
        }
    }

}