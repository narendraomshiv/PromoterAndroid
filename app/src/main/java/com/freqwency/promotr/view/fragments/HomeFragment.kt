package com.freqwency.promotr.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freqwency.promotr.adapter.CategoriesAdapter
import com.freqwency.promotr.adapter.PromosAdapter
import com.freqwency.promotr.databinding.FragmentHomeBinding
import com.freqwency.promotr.model.CategoryModel
import com.freqwency.promotr.model.PromoModel
import com.freqwency.promotr.viewmodel.HomeViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.freqwency.promotr.R
import com.freqwency.promotr.view.activities.SearchActivity


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var activity: Activity
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        activity = requireActivity()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val categoriesRecyclerView: RecyclerView = binding.rcvCategories
        categoriesRecyclerView.setHasFixedSize(true)
        // Creating an instance of our NameAdapter class and setting it to our RecyclerView
        val nameList =  getListOfCategories()
        val categoryAdapter = CategoriesAdapter(activity, nameList)
        categoriesRecyclerView.adapter = categoryAdapter
        categoriesRecyclerView.isHorizontalScrollBarEnabled = true
        categoriesRecyclerView.isHorizontalFadingEdgeEnabled = true
        categoriesRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        
        val suggestions: RecyclerView = binding.rcvSuggestions
        suggestions.setHasFixedSize(true)
        // Creating an instance of our NameAdapter class and setting it to our RecyclerView
        val promoList =  getListOfPromos()
        val promoAdapter = PromosAdapter(activity, promoList)
        suggestions.adapter = promoAdapter
        suggestions.isHorizontalScrollBarEnabled = true
        suggestions.isHorizontalFadingEdgeEnabled = true
        suggestions.layoutManager =
            GridLayoutManager(activity, 3, GridLayoutManager.HORIZONTAL, false)

        val searchView: RelativeLayout = binding.searchView
        searchView.setOnClickListener {
            val intent = Intent(activity, SearchActivity::class.java)
            activity.startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getListOfCategories () : ArrayList<CategoryModel> {

        val list = ArrayList<CategoryModel>()

        val offersObject = CategoryModel()
        offersObject.icon = R.drawable.influencer.toString()
        offersObject.title = "SHoes"

        list.add(offersObject)

        val offersObject1 = CategoryModel()
        offersObject1.icon = R.drawable.influencer.toString()
        offersObject1.title = "Restuarnts"

        list.add(offersObject1)


        offersObject1.icon = R.drawable.influencer.toString()
        offersObject1.title = "Restuarnts"

        list.add(offersObject1)

        offersObject1.icon = R.drawable.influencer.toString()
        offersObject1.title = "Restuarnts"

        list.add(offersObject1)

        offersObject1.icon = R.drawable.influencer.toString()
        offersObject1.title = "Restuarnts"

        list.add(offersObject1)

        offersObject1.icon = R.drawable.influencer.toString()
        offersObject1.title = "Restuarnts"

        list.add(offersObject1)

        offersObject1.icon = R.drawable.influencer.toString()
        offersObject1.title = "Restuarnts"

        list.add(offersObject1)

        offersObject1.icon = R.drawable.influencer.toString()
        offersObject1.title = "Restuarnts"

        list.add(offersObject1)

        return list
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