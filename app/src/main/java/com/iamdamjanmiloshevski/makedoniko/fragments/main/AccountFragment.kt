package com.iamdamjanmiloshevski.makedoniko.fragments.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.FragmentActivity
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.fragments.bases.BaseFragment
import com.iamdamjanmiloshevski.makedoniko.managers.FirebaseLoginManager
import com.iamdamjanmiloshevski.makedoniko.managers.SharedPreferencesManager
import kotlinx.android.synthetic.main.tab_account.view.*


/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
class AccountFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_logout -> {
                val isLoggedInWithGoogle = SharedPreferencesManager.getInstance(context!!).isLoggedInWithGoogle()
                FirebaseLoginManager.getInstance(context!!)
                    .logout(activity as FragmentActivity, isLoggedInWithGoogle)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = getFragmentView(context, container, false, savedInstanceState)
        fragmentView.tv_logout.setOnClickListener(this)
        return fragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_account, menu)
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