package com.kazimad.medisafedemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kazimad.medisafedemo.R
import com.kazimad.medisafedemo.interfaces.CustomClickListener
import com.kazimad.medisafedemo.models.response.Country
import com.kazimad.medisafedemo.utils.Utils

class MainListAdapter(private var listCountries: ArrayList<Country>) :
    RecyclerView.Adapter<MainListAdapter.ViewHolderItem>() {

    private var customClickListener: CustomClickListener? = null

    fun setCustomClick(customClickListener: CustomClickListener) {
        this.customClickListener = customClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {
        return ViewHolderItem(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_country,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listCountries.size
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        holder.itemView.setOnClickListener { customClickListener?.onCustomClick(listCountries[position].name) }
        holder.countryName.text = listCountries[position].name
        holder.countryNameLocal.text = listCountries[position].nativeName
        holder.countryPopulation.text = listCountries[position].population.toString()
        Utils.fetchSvg(listCountries[position].flag, holder.flagView)
    }

    class ViewHolderItem(view: View) : RecyclerView.ViewHolder(view) {
        val flagView = view.findViewById<ImageView>(R.id.countryFlag)!!
        val countryName = view.findViewById<TextView>(R.id.countryName)!!
        val countryNameLocal = view.findViewById<TextView>(R.id.countryNameLocal)!!
        val countryPopulation = view.findViewById<TextView>(R.id.countryPopulation)!!
    }
}