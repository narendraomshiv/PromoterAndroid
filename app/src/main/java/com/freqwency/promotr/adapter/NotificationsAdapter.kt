package com.freqwency.promotr.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.freqwency.promotr.databinding.NotificationItemBinding
import com.freqwency.promotr.model.NotificationModel


class NotificationsAdapter(private val activity: Activity,
                        private val actions: ArrayList<NotificationModel>) :
    RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {

    class ViewHolder(view: NotificationItemBinding) : RecyclerView.ViewHolder(view.root) {
        val itemViewLayout: LinearLayout = view.itemView
        val tvName: TextView = view.tvNotif
        val tvDate: TextView = view.tvDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: NotificationItemBinding = NotificationItemBinding.inflate(LayoutInflater.from(activity), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = actions[position]

        holder.tvName.text = item.text
        holder.tvDate.text = item.date


        holder.itemViewLayout.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return actions.size
    }

}
