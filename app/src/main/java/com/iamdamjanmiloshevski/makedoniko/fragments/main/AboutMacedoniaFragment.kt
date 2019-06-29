package com.iamdamjanmiloshevski.makedoniko.fragments.main

import android.os.Bundle
import android.view.*
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.fragments.bases.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tab_macedonia.view.*


/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
class AboutMacedoniaFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = getFragmentView(context, container, false, savedInstanceState)
        fragmentView.web_view.webChromeClient = WebChromeClient()
        fragmentView.web_view.settings.loadsImagesAutomatically = true
        //fragmentView.web_view.settings.javaScriptEnabled = true
        fragmentView.web_view.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        fragmentView.web_view.loadUrl("https://www.lonelyplanet.com/macedonia")
        return fragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.findItem(R.id.action_settings)?.isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun getLayoutId(): Int {
        return R.layout.tab_macedonia
    }

    companion object {
        fun getInstance(): AboutMacedoniaFragment {
            return AboutMacedoniaFragment()
        }
    }
}
