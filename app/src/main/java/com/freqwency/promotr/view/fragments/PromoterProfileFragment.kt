package com.freqwency.promotr.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freqwency.promotr.adapter.CategoriesAdapter
import com.freqwency.promotr.adapter.PromosAdapter
import com.freqwency.promotr.databinding.FragmentPromoterProfileBinding
import com.freqwency.promotr.model.PromoModel
import com.freqwency.promotr.view.activities.AddNewPromoCodeActivity
import com.freqwency.promotr.view.activities.HistoryActivity

class PromoterProfileFragment : Fragment() {
    private lateinit var activity: Activity
    private var _binding: FragmentPromoterProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity = requireActivity()

        _binding = FragmentPromoterProfileBinding.inflate(inflater, container, false)

        val suggestions: RecyclerView = binding.rcvongoing
        suggestions.setHasFixedSize(true)
        // Creating an instance of our NameAdapter class and setting it to our RecyclerView
        val promoList =  getListOfPromos()
        val promoAdapter = PromosAdapter(activity, promoList)
        suggestions.adapter = promoAdapter
        suggestions.isHorizontalScrollBarEnabled = true
        suggestions.isHorizontalFadingEdgeEnabled = true
        //suggestions.layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.HORIZONTAL, false)
        suggestions.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        val button: Button = binding.btnAddnew
        button.setOnClickListener {
            val intent = Intent(activity, AddNewPromoCodeActivity::class.java)
            activity.startActivity(intent)
        }

        val textView: TextView = binding.historyTxt
        textView.setOnClickListener {
            val intent = Intent(activity, HistoryActivity::class.java)
            activity.startActivity(intent)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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