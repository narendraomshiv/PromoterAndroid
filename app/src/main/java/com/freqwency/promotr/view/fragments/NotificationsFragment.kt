package com.freqwency.promotr.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.freqwency.promotr.adapter.NotificationsAdapter
import com.freqwency.promotr.databinding.FragmentNotificationsBinding
import com.freqwency.promotr.model.NotificationModel
import com.freqwency.promotr.viewmodel.NotificationsViewModel

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rclNames = binding.rcvNotifications
        rclNames.setHasFixedSize(true)
        val nameList =  getListOfNames()
        val namesAdapter = NotificationsAdapter(requireActivity(), nameList)
        rclNames.adapter = namesAdapter
        rclNames.layoutManager = LinearLayoutManager(requireActivity())
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getListOfNames(): ArrayList<NotificationModel> {
        val nameList = ArrayList<NotificationModel>()

        val obj1 = NotificationModel()
        obj1.text = "Hello world"
        obj1.date = "Jun 20,2020"
        nameList.add(obj1)

        val obj2 = NotificationModel()
        obj2.text = "Hello world wuied uiweduie uedude uehdue"
        obj2.date = "Jun 20,2020"
        nameList.add(obj2)

        val obj3 = NotificationModel()
        obj3.text = "Hello world wuied uiweduie uedude uehdue"
        obj3.date = "Jun 20,2020"
        nameList.add(obj3)

        val obj4 = NotificationModel()
        obj4.text = "Hello world wuied uiweduie uedude uehdue"
        obj4.date = "Jun 20,2020"
        nameList.add(obj4)

        return nameList
    }
}