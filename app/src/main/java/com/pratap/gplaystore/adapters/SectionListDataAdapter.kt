package com.pratap.gplaystore.adapters

/**
 * Created by pratap.kesaboyina on 24-12-2014.
 */

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pratap.gplaystore.R
import com.pratap.gplaystore.models.Section

class SectionListDataAdapter(private val mContext: Context, private val itemsList: List<Section>?) :
        RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SingleItemRowHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_single_card, null)
        return SingleItemRowHolder(v)
    }

    override fun onBindViewHolder(holder: SingleItemRowHolder, i: Int) {

        val singleItem = itemsList!![i]

        holder.tvTitle.text = singleItem.name


        Glide.with(mContext)
                .load(singleItem.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.android)
                .into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return itemsList?.size ?: 0
    }

    inner class SingleItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvTitle: TextView

        var itemImage: ImageView


        init {

            this.tvTitle = view.findViewById(R.id.tvTitle) as TextView
            this.itemImage = view.findViewById(R.id.itemImage) as ImageView


            view.setOnClickListener { v -> Toast.makeText(v.context, tvTitle.text, Toast.LENGTH_SHORT).show() }


        }

    }

}