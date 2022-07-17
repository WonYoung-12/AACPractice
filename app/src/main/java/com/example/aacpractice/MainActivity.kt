package com.example.aacpractice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aacpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"

    }

    private lateinit var contactViewModel: ContactViewModel
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        contactViewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        contactViewModel.getAll().observe(this, Observer<List<Contact>> { contacts ->
            // Update UI
        })

        binding.apply {
            lifecycleOwner = this@MainActivity
            viewModel = contactViewModel
        }

        val adapter = ContactAdapter(contactViewModel.getAll())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)

        val listener = object : ItemViewClickListener {
            override fun onItemViewClick(contact: Contact) {
                Log.i(TAG, "onItemViewClick: ")
            }

            override fun onItemViewLongClick(contact: Contact) {
                Log.i(TAG, "onItemViewLongClick: ")
            }
        }
        adapter.setListener(listener)

    }
}