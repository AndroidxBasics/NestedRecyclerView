package com.pratap.gplaystore.adapters

/**
 * Created by pratap.kesaboyina on 24-12-2014.
 */

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.pratap.gplaystore.R
import com.pratap.gplaystore.models.Data

class RecyclerViewDataAdapter(private val mContext: Context, private val dataList: List<Data>?) :
        RecyclerView.Adapter<RecyclerViewDataAdapter.ItemRowHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ItemRowHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, null)
        return ItemRowHolder(v)
    }

    override fun onBindViewHolder(itemRowHolder: ItemRowHolder, i: Int) {

        val sectionName = dataList!![i].title

        val singleSectionItems = dataList[i].section

        itemRowHolder.itemTitle.text = sectionName

        val itemListDataAdapter = SectionListDataAdapter(mContext, singleSectionItems)

        itemRowHolder.recycler_view_list.setHasFixedSize(true)

        itemRowHolder.recycler_view_list.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        itemRowHolder.recycler_view_list.adapter = itemListDataAdapter


        itemRowHolder.recycler_view_list.isNestedScrollingEnabled = false


        /*  itemRowHolder.recycler_view_list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        //Allow ScrollView to intercept touch events once again.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                // Handle RecyclerView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });*/

        itemRowHolder.btnMore.setOnClickListener { v -> Toast.makeText(v.context, "click event on more, "
                + sectionName!!, Toast.LENGTH_SHORT).show() }


        /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    inner class ItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {

        var itemTitle: TextView

        var recycler_view_list: RecyclerView

        var btnMore: Button


        init {

            this.itemTitle = view.findViewById(R.id.itemTitle) as TextView
            this.recycler_view_list = view.findViewById(R.id.recycler_view_list) as RecyclerView
            this.btnMore = view.findViewById(R.id.btnMore) as Button


        }

    }

}