package com.freqwency.promotr.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.freqwency.promotr.R
import com.freqwency.promotr.databinding.PromoItemBinding
import com.freqwency.promotr.model.PromoModel
import com.freqwency.promotr.view.activities.PromoActivity
import com.freqwency.promotr.view.activities.SearchActivity

class PromosAdapter(private val activity: Activity,
                        private val actions: ArrayList<PromoModel>) :
    RecyclerView.Adapter<PromosAdapter.ViewHolder>() {

    class ViewHolder(view: PromoItemBinding) : RecyclerView.ViewHolder(view.root) {
        val itemViewLayout: LinearLayout = view.itemView
        val ivIcon: ImageView = view.ivIcon
        val tvPromo: TextView = view.tvPromo
        val tvPromoDescription: TextView = view.tvPromoDescription
        val tvPromoType: TextView = view.tvPromoType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: PromoItemBinding = PromoItemBinding.inflate(LayoutInflater.from(activity), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = actions[position]

        val resourceId = activity.resources.getIdentifier(item.icon, "drawable", activity.packageName)
        holder.ivIcon.setImageResource(resourceId)
        holder.tvPromo.text = item.title
        holder.tvPromoDescription.text = item.descr
        holder.tvPromoType.text = item.shop
        if (item.type === "shop") {
            holder.tvPromoType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_shop, 0, 0, 0);
        } else {
            holder.tvPromoType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_influencer, 0, 0, 0);
        }

        holder.itemViewLayout.setOnClickListener {
            val intent = Intent(activity, PromoActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return actions.size
    }
}