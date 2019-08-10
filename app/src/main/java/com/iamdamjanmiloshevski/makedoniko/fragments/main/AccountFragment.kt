package com.iamdamjanmiloshevski.makedoniko.fragments.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.activities.LoginActivity
import com.iamdamjanmiloshevski.makedoniko.fragments.bases.BaseFragment
import com.iamdamjanmiloshevski.makedoniko.listeners.ScreenListener
import com.iamdamjanmiloshevski.makedoniko.managers.FirebaseLoginManager
import com.iamdamjanmiloshevski.makedoniko.managers.SharedPreferencesManager
import com.iamdamjanmiloshevski.makedoniko.models.User
import com.iamdamjanmiloshevski.makedoniko.viewmodels.AccountViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tab_account.view.*


/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
class AccountFragment : BaseFragment(), View.OnClickListener, Observer<User>, ScreenListener {
    override fun openMain() {
       //not used
    }

    override fun openLogin() {
        startActivity(Intent(context!!, LoginActivity::class.java))
        activity?.finish()
    }

    private fun registerObservers() {
        mViewModel.getUser(uid!!).observe(this, this)
    }

    private fun removeObservers() {
        mViewModel.getUser(uid!!).removeObserver(this)
    }

    private var fragmentView: View? = null
    private var uid: String? = null

    override fun onChanged(data: User?) {
        if (data != null) {
            loadData(data)
        }
    }

    private lateinit var mViewModel: AccountViewModel
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_logout -> {
                val isLoggedInWithGoogle = SharedPreferencesManager.getInstance(context!!).isLoggedInWithGoogle()
                FirebaseLoginManager.getInstance(context!!)
                    .logout(context!!, isLoggedInWithGoogle, this)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = getFragmentView(context, container, false, savedInstanceState)
        mViewModel = ViewModelProviders.of(activity!!).get(AccountViewModel::class.java)
        uid = SharedPreferencesManager.getInstance(context!!).getUID()
        registerObservers()
        fragmentView?.tv_logout?.setOnClickListener(this)
        return fragmentView
    }

    private fun loadData(user: User) {
        with(user) {
            fragmentView?.tv_user_name?.text = this.displayName
            fragmentView?.tv_user_email?.text = this.email
            if (this.photoUrl != "") {
                Picasso.with(fragmentView?.context)
                    .load(this.photoUrl)
                    .placeholder(R.drawable.image_loader)
                    .error(R.drawable.ic_account)
                    .into(fragmentView?.iv_user_avatar)
            } else {
                Picasso.with(fragmentView?.context)
                    .load(R.drawable.ic_account)
                    .placeholder(R.drawable.image_loader)
                    .error(R.drawable.ic_account)
                    .into(fragmentView?.iv_user_avatar)
            }
        }
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


    override fun onDestroyView() {
        super.onDestroyView()
        removeObservers()
        mViewModel.detachListener()
    }

    companion object {
        fun getInstance(): AccountFragment {
            return AccountFragment()
        }
    }
}