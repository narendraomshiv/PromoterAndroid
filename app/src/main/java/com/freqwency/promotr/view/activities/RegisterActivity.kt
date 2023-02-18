package com.freqwency.promotr.view.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.freqwency.promotr.R
import com.freqwency.promotr.application.PromotrApp
import com.freqwency.promotr.beans.registration.RegistrationRequest
import com.freqwency.promotr.beans.registration.RegistrationResponse
import com.freqwency.promotr.databinding.ActivityRegisterBinding
import com.freqwency.promotr.services.AuthenticationAPI
import com.freqwency.promotr.utils.DialogsManager
import com.freqwency.promotr.utils.GlobalFunctions
import com.skydoves.powerspinner.IconSpinnerAdapter
import com.skydoves.powerspinner.IconSpinnerItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : BaseActivity() {

    lateinit var registerBinding: ActivityRegisterBinding
    private lateinit var registerRequest: RegistrationRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        GlobalFunctions.addUIPadding(registerBinding.root)
        registerBinding.llBackBtn.setOnClickListener {
            this.finish()
        }

        registerBinding.btnSignUp.setOnClickListener {
            validateInputs()
        }

        registerBinding.spinnerViewCountry.setOnClickListener {
            DialogsManager.openCountryBottomSheet(this) { countryModel ->
                registerBinding.llMobileLayout.visibility = View.VISIBLE
                registerBinding.etCountryCode.setText(countryModel.phoneCode)
                registerBinding.spinnerViewCountry.setText(if (PromotrApp.encryptedPrefs.appLanguage == "en") countryModel.name else countryModel.nameAR)
            }
        }

        // access the items of the list
        val languages = resources.getStringArray(R.array.Languages)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, languages)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@RegisterActivity, getString(R.string.selected_item) + " " +"" + languages[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        registerBinding.spinnerViewGender.apply {
            setSpinnerAdapter(IconSpinnerAdapter(this))
            setItems(
                arrayListOf(
                    IconSpinnerItem(text = "Male"),
                    IconSpinnerItem(text = "Female"),
                )
            )
            setOnSpinnerItemSelectedListener<IconSpinnerItem> { _, _, _, item ->
                Toast.makeText(applicationContext, item.text, Toast.LENGTH_SHORT).show()
            }
            getSpinnerRecyclerView().layoutManager = GridLayoutManager(baseContext, 1)
            //selectItemByIndex(4)
            //preferenceName = "country"
        }
    }

    private fun validateInputs() {
        var isValid = true

        with(registerBinding) {
            if (etFirstName.text.isNullOrBlank()) {
                etFirstName.background = getDrawable(R.drawable.edittext_error)
                isValid = false
            }

            if (etLastName.text.isNullOrBlank()) {
                etLastName.background = getDrawable(R.drawable.edittext_error)
                isValid = false
            }
            if (etGender.text.isNullOrBlank()) {
                etGender.background = getDrawable(R.drawable.edittext_error)
                isValid = false
            }
            if (etEmail.text.isNullOrBlank()) {
                etEmail.background = getDrawable(R.drawable.edittext_error)
                isValid = false
            }

            if (etCountry.text.isNullOrBlank()) {
                etCountry.background = getDrawable(R.drawable.edittext_error)
                isValid = false
            } else {
                etCountry.background = getDrawable(R.drawable.edittext)
                llMobileLayout.visibility = View.VISIBLE
            }

            if (etMobileNumber.text.isNullOrBlank()) {
                etMobileNumber.background = getDrawable(R.drawable.edittext_error)
                isValid = false
            }
            if (etGender.text.isNullOrBlank()) {
                etGender.background = getDrawable(R.drawable.edittext_error)
                isValid = false
            }

            if (etPassword.text.isNullOrBlank() || etPassword.text.length < 8) {
                etPassword.background = getDrawable(R.drawable.edittext_error)
                isValid = false
            }
            if (etConfirmPassword.text.isNullOrBlank()) {
                etConfirmPassword.background = getDrawable(R.drawable.edittext_error)
                isValid = false
            }

            if (etPassword.text.isNotEmpty() && etConfirmPassword.text.isNotEmpty()) {
                if (etPassword.text.toString() != etConfirmPassword.text.toString()) {
                    tvError.visibility = View.VISIBLE
                } else {
                    tvError.visibility = View.GONE
                }
            }
        }

        if (isValid) {
            registerCall()
        }
    }

    private fun registerCall() {
        registerRequest = RegistrationRequest(
            firstName = registerBinding.etFirstName.text.toString(),
            lastName = registerBinding.etLastName.text.toString(),
            mobileNumber = registerBinding.etCountryCode.text.toString() + registerBinding.etMobileNumber.text.toString(),
            email = registerBinding.etEmail.text.toString(),
            country = registerBinding.etCountry.text.toString(),
            password = registerBinding.etPassword.text.toString(),
            passwordConfirmation = registerBinding.etConfirmPassword.text.toString(),
            gender = registerBinding.etGender.text.toString(),
            hasAcceptedTerms = true,
            deviceName = PromotrApp.encryptedPrefs.deviceID
        )

        val registerCall = AuthenticationAPI.client.register(PromotrApp.encryptedPrefs.appLanguage, registerRequest)
        registerCall.enqueue(object : Callback<RegistrationResponse> {
            override fun onResponse(
                call: Call<RegistrationResponse>,
                response: Response<RegistrationResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    PromotrApp.encryptedPrefs.token = response.body()!!.data?.accessToken ?: ""
                    PromotrApp.encryptedPrefs.user = response.body()!!.data?.user

                    val intent = Intent(this@RegisterActivity, VerifyMobileActivity::class.java)
                    intent.putExtra("mobileNumber", response.body()!!.data?.user?.mobileNumber ?: "")
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                Log.d("+++++", t.message.toString())

            }
        })
    }

}