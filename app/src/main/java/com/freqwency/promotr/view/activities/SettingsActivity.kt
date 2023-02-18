package com.freqwency.promotr.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.freqwency.promotr.databinding.ActivityIntroBinding
import com.freqwency.promotr.databinding.ActivitySettingsBinding

class SettingsActivity : BaseActivity() {

    lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.llBackBtn.setOnClickListener {
            finish()
        }

    }

}