package com.freqwency.promotr.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.freqwency.promotr.databinding.ActivityPromoOwnerRegisterBinding

class PromoOwnerRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPromoOwnerRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPromoOwnerRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button: Button = binding.btnsignup

        button.setOnClickListener {
            val intent = Intent(this@PromoOwnerRegisterActivity, VerifyMobileActivity::class.java)
            intent.putExtra("mobileNumber", "9717307764")
            startActivity(intent)
            //finish()
        }


    }

}