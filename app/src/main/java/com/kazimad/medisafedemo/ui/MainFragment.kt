package com.kazimad.medisafedemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kazimad.medisafedemo.R
import com.kazimad.medisafedemo.adapters.MainListAdapter
import com.kazimad.medisafedemo.interfaces.CustomClickListener
import com.kazimad.medisafedemo.models.error.ConnectivityError
import com.kazimad.medisafedemo.models.response.Country
import com.kazimad.medisafedemo.utils.ActivityUtils
import com.kazimad.medisafedemo.utils.Logger
import com.kazimad.medisafedemo.utils.NetworkUtils
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    private lateinit var viewModel: MainFragmentViewModel
    private lateinit var mainView: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView =
                inflater.inflate(com.kazimad.medisafedemo.R.layout.fragment_main, container, false)
        return mainView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)
        viewModel.resultLiveData.observe(this, Observer<ArrayList<Country>> {
            fillList(it)
        })
        viewModel.errorLiveData.observe(this, Observer<Throwable> {
            handleError(it)
        })
        if (NetworkUtils.isNetworkAvailable(view.context)) {
            viewModel.getAllCountries()
        } else {
            handleError(ConnectivityError(getString(R.string.error_no_connection)))
        }
    }

    private fun handleError(throwable: Throwable) {
        Logger.log("handleError ${throwable.message}")
        list.visibility = View.INVISIBLE
        val snackbar = throwable.message?.let {
            Snackbar.make(
                fragmentViewContainer,
                it,
                Snackbar.LENGTH_LONG
            )
        }
        snackbar?.show()
    }

    private fun fillList(countries: ArrayList<Country>) {
        Logger.log("fillList ${countries.size}")
        list.visibility = View.VISIBLE
        val mainListAdapter = MainListAdapter(countries)
        list.layoutManager = LinearLayoutManager(list.context, RecyclerView.VERTICAL, false)
        list.adapter = mainListAdapter
        mainListAdapter.setCustomClick(object : CustomClickListener {
            override fun onCustomClick(countryName: String) {
                ActivityUtils.addFragmentToActivity(
                    activity!!,
                    DetailCountryFragment.newInstance(countryName),
                    true
                )
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeAll()
    }
}