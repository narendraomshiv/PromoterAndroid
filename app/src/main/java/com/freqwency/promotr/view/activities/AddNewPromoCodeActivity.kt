package com.freqwency.promotr.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.freqwency.promotr.databinding.ActivityAddNewPromoCodeBinding
import com.freqwency.promotr.databinding.ActivityHistoryBinding

class AddNewPromoCodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewPromoCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNewPromoCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}