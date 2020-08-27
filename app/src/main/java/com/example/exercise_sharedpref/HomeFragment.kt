package com.example.exercise_sharedpref

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), View.OnClickListener {

    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = activity?.getSharedPreferences(getString(R.string.shared_preference_name), Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = sharedPreferences?.getString(getString(R.string.username_key), getString(R.string.default_value))
        textView.text = "Hello, $username"
        logoutButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v) {
            logoutButton -> {
                with(sharedPreferences?.edit()) {
                    this?.remove(getString(R.string.username_key))
                    this?.commit()
                }
                v.findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }
        }
    }
}