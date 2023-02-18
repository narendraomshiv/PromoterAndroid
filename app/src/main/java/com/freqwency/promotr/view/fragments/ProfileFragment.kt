package com.freqwency.promotr.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.lifecycle.ViewModelProvider
import com.freqwency.promotr.databinding.FragmentProfileBinding
import com.freqwency.promotr.view.activities.PromoOwnerRegisterActivity
import com.freqwency.promotr.view.activities.SettingsActivity
import com.freqwency.promotr.viewmodel.ProfileViewModel


class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private lateinit var activity: Activity

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        profileViewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]
        activity = requireActivity()
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val searchView: ImageView = binding.settingsBtn
        searchView.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            activity.startActivity(intent)
        }

        val button: Button = binding.btnsignIn
        button.setOnClickListener {
            val intent = Intent(activity, PromoOwnerRegisterActivity::class.java)
            activity.startActivity(intent)
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}