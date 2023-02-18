package com.freqwency.promotr.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freqwency.promotr.R
import com.freqwency.promotr.adapter.PromosAdapter
import com.freqwency.promotr.databinding.ActivitySearchResultBinding
import com.freqwency.promotr.model.PromoModel
import com.freqwency.promotr.viewmodel.FavoriteViewModel

class SearchResultActivity : AppCompatActivity() {
    private lateinit var favoriteViewModel: FavoriteViewModel
    private var binding: ActivitySearchResultBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        favoriteViewModel =
            ViewModelProvider(this).get(FavoriteViewModel::class.java)

        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val suggestions: RecyclerView = binding!!.rcvfavoritess
        suggestions.setHasFixedSize(true)
        // Creating an instance of our NameAdapter class and setting it to our RecyclerView
        val promoList =  getListOfPromos()
        val promoAdapter = PromosAdapter(this@SearchResultActivity, promoList)
        suggestions.adapter = promoAdapter
        suggestions.isVerticalFadingEdgeEnabled = true
        suggestions.isVerticalScrollBarEnabled = true
        suggestions.layoutManager = LinearLayoutManager(this@SearchResultActivity)
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
        offersObject1.shop = "Ramzy Fazah"
        offersObject1.type = "inf"
        list.add(offersObject1)

        val offersObject2 = PromoModel()
        offersObject2.icon = "influencer"
        offersObject2.title = "AB2343241"
        offersObject2.descr = "!0% of bla bla bla"
        offersObject2.shop = "Ramzy Fazah"
        offersObject2.type = "inf"
        list.add(offersObject2)

        val offersObject3 = PromoModel()
        offersObject3.icon = "ic_promo"
        offersObject3.title = "AB2343241"
        offersObject3.descr = "!0% of bla bla bla"
        offersObject3.shop = "Adidas"
        offersObject3.type = "shop"
        list.add(offersObject3)

        return list
    }
}