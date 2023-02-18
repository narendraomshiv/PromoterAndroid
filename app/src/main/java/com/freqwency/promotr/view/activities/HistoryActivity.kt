package com.freqwency.promotr.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freqwency.promotr.R
import com.freqwency.promotr.adapter.PromosAdapter
import com.freqwency.promotr.databinding.ActivityHistoryBinding
import com.freqwency.promotr.databinding.ActivityPromoOwnerRegisterBinding
import com.freqwency.promotr.model.PromoModel

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val suggestions: RecyclerView = binding.rcvhistory
        suggestions.setHasFixedSize(true)
        // Creating an instance of our NameAdapter class and setting it to our RecyclerView
        val promoList =  getListOfPromos()
        val promoAdapter = PromosAdapter(this@HistoryActivity, promoList)
        suggestions.adapter = promoAdapter
        suggestions.isHorizontalScrollBarEnabled = true
        suggestions.isHorizontalFadingEdgeEnabled = true
        //suggestions.layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.HORIZONTAL, false)
        suggestions.layoutManager = LinearLayoutManager(this@HistoryActivity, RecyclerView.VERTICAL, false)
    }

    private fun getListOfPromos () : ArrayList<PromoModel> {

        val list = ArrayList<PromoModel>()

        val offersObject = PromoModel()
        offersObject.icon = "ic_promo"
        offersObject.title = "AB2343241"
        offersObject.descr = "!0% of bla bla bla"
        offersObject.shop = "Adidas"
        offersObject.type = "shop"

        list.add(offersObject)

        val offersObject1 = PromoModel()
        offersObject1.icon = "influencer"
        offersObject1.title = "AB2343241"
        offersObject1.descr = "!0% of bla bla bla"
        offersObject1.shop = "Ramzy fazah"
        offersObject1.type = "inf"

        list.add(offersObject1)

        val offersObject2 = PromoModel()

        offersObject2.icon = "ic_promo"
        offersObject2.title = "AB2343241"
        offersObject2.descr = "10% of bla bla bla"
        offersObject2.shop = "Adidas"
        offersObject2.type = "shop"

        list.add(offersObject2)

        val offersObject3 = PromoModel()
        offersObject3.icon = "influencer"
        offersObject3.title = "AB2343241"
        offersObject3.descr = "!0% of bla bla bla"
        offersObject3.shop = "Ramzy fazah"
        offersObject3.type = "inf"

        list.add(offersObject3)

        return list
    }
}