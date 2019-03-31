package com.kazimad.medisafedemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.kazimad.medisafedemo.R
import com.kazimad.medisafedemo.models.error.ConnectivityError
import com.kazimad.medisafedemo.models.error.ResponseException
import com.kazimad.medisafedemo.models.response.CountryDetailed
import com.kazimad.medisafedemo.utils.Constants
import com.kazimad.medisafedemo.utils.NetworkUtils
import com.kazimad.medisafedemo.utils.Utils
import kotlinx.android.synthetic.main.fragment_detail_country.*

class DetailCountryFragment : Fragment() {
    private lateinit var mainView: View
    private lateinit var viewModel: DetailCountryFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = inflater.inflate(R.layout.fragment_detail_country, container, false)
        return mainView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailCountryFragmentViewModel::class.java)
        viewModel.resultLiveData.observe(this, Observer<ArrayList<CountryDetailed>> {
            showCountyDetails(it[0])
        })
        viewModel.errorLiveData.observe(this, Observer<Throwable> {
            handleError(it)
        })
        arguments?.let {
            if (NetworkUtils.isNetworkAvailable(view.context)) {
                viewModel.getCountryDetails(arguments!!.getString(Constants.bundleParam)!!)
            } else {
                handleError(ConnectivityError(getString(R.string.error_no_connection)))
            }

        }
    }

    private fun showCountyDetails(countryDetailed: CountryDetailed) {
        dataContainer.visibility = View.VISIBLE
        Utils.fetchSvg(countryDetailed.flag, flagView)
        nameText.text = countryDetailed.name
        populationText.text = countryDetailed.population.toString()
        subRegion.text = countryDetailed.region
        capital.text = countryDetailed.capital

    }

    private fun handleError(throwable: Throwable) {
        dataContainer.visibility = View.INVISIBLE
        val message: String? = if (throwable is ResponseException) {
            throwable.errorMessage
        } else {
            throwable.message
        }
        val snackbar = message?.let {
            Snackbar.make(
                fragmentViewContainer,
                it,
                Snackbar.LENGTH_LONG
            )
        }
        snackbar?.show()
    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeAll()
    }

    companion object {
        fun newInstance(countryName: String): DetailCountryFragment {
            val bundle = Bundle(1)
            val fragment = DetailCountryFragment()
            bundle.putString(Constants.bundleParam, countryName)
            fragment.arguments = bundle
            return fragment
        }
    }
}