package com.freqwency.promotr.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.freqwency.promotr.databinding.CategoryItemBinding
import com.freqwency.promotr.model.CategoryModel

class CategoriesAdapter(private val activity: Activity,
                        private val actions: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: CategoryItemBinding) : RecyclerView.ViewHolder(view.root) {
        val itemViewLayout: LinearLayout = view.girdItem
        val ivIcon: ImageView = view.ivIcon
        val tvName: TextView = view.tvTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding: CategoryItemBinding = CategoryItemBinding.inflate(LayoutInflater.from(activity), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = actions[position]

        val resourceId = activity.resources.getIdentifier(item.icon, "drawable", activity.packageName)
        holder.ivIcon.setImageResource(resourceId)
        holder.tvName.text = item.title

        holder.itemViewLayout.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return actions.size
    }

}
