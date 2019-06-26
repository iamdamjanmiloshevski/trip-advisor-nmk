package com.iamdamjanmiloshevski.makedoniko.fragments.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.activities.LoginActivity
import com.iamdamjanmiloshevski.makedoniko.fragments.bases.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tab_account.view.*


/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
class AccountFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = getFragmentView(context, container, false, savedInstanceState)
        fragmentView.tv_logout.setOnClickListener { v ->
            context?.startActivity(Intent(context,LoginActivity::class.java))
        }
        return fragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_account,menu)
        menu!!.findItem(R.id.action_settings).isVisible = false
    }

    override fun getLayoutId(): Int {
        return R.layout.tab_account
    }

    companion object {
        fun getInstance(): AccountFragment {
            return AccountFragment()
        }
    }
}