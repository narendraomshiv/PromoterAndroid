package com.freqwency.promotr.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.freqwency.promotr.R
import com.freqwency.promotr.databinding.ActivitySearchBinding
import com.skydoves.powerspinner.IconSpinnerAdapter
import com.skydoves.powerspinner.IconSpinnerItem
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.view.*

class SearchActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val searchBinding: ActivitySearchBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(searchBinding.root)

        searchBinding.btnClose.setOnClickListener {
            finish()
        }

        searchBinding.spinnerViewCategory.apply {
            setSpinnerAdapter(IconSpinnerAdapter(this))
            setItems(
                arrayListOf(
                    IconSpinnerItem(text = "Shoe"),
                    IconSpinnerItem(text = "Restaurants"),
                    IconSpinnerItem(text = "Home"),
                    IconSpinnerItem(text = "Addidas"),
                    IconSpinnerItem(text = "Building"),
                )
            )
            setOnSpinnerItemSelectedListener<IconSpinnerItem> { _, _, _, item ->
                Toast.makeText(applicationContext, item.text, Toast.LENGTH_SHORT).show()
            }
            getSpinnerRecyclerView().layoutManager = GridLayoutManager(baseContext, 1)
            //selectItemByIndex(4)
            //preferenceName = "country"
        }

        searchBinding.spinnerViewDiscount.apply {
            setSpinnerAdapter(IconSpinnerAdapter(this))
            setItems(
                arrayListOf(
                    IconSpinnerItem(text = "10"),
                    IconSpinnerItem(text = "20"),
                    IconSpinnerItem(text = "30"),
                    IconSpinnerItem(text = "40"),
                    IconSpinnerItem(text = "50"),
                )
            )
            setOnSpinnerItemSelectedListener<IconSpinnerItem> { _, _, _, item ->
                Toast.makeText(applicationContext, item.text, Toast.LENGTH_SHORT).show()
            }
            getSpinnerRecyclerView().layoutManager = GridLayoutManager(baseContext, 1)
            //selectItemByIndex(4)
            //preferenceName = "country"
        }

        val button: Button = searchBinding.btnsignin
        button.setOnClickListener {
            val intent = Intent(applicationContext, SearchResultActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            applicationContext.startActivity(intent)
        }
    }
}