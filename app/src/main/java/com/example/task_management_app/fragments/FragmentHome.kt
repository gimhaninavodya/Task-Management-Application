package com.example.task_management_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.task_management_app.R
import com.example.task_management_app.adapter.ListAdapter
import com.example.task_management_app.databinding.FragmentHomeBinding
import com.example.task_management_app.model.ListModelNew
import com.example.task_management_app.viewmodel.ListViewModel

class FragmentHome : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener {

    private var homeBinding: FragmentHomeBinding? = null
    private val binding get() = homeBinding!!

    private lateinit var listViewModel: ListViewModel
    private lateinit var listAdapter: ListAdapter
    private lateinit var constraintSet: ConstraintSet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        listViewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]

        // Initialize ConstraintSet
        constraintSet = ConstraintSet()
        constraintSet.clone(binding.constraintLayout) // Use the ID of the parent layout

        // Setup RecyclerView
        setupHomeRecyclerView()

        // Setup SearchView
        binding.searchMenu.setOnQueryTextListener(this) // Listen for search query changes

        // Handle Add Button Click
        binding.addBtn.setOnClickListener {
            // Navigate to the add fragment
            view.findNavController().navigate(R.id.action_fragmentHome_to_fragmentAdd)
        }
        binding.searchMenu.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // Update constraints to keep RecyclerView at the bottom
                constraintSet.connect(
                    binding.homeRecyclerView.id,
                    ConstraintSet.TOP,
                    binding.searchMenu.id,
                    ConstraintSet.BOTTOM
                )
                constraintSet.applyTo(binding.constraintLayout)
            } else {
                // Update constraints to keep RecyclerView at the bottom of the parent
                constraintSet.connect(
                    binding.homeRecyclerView.id,
                    ConstraintSet.TOP,
                    binding.addBtn.id,
                    ConstraintSet.BOTTOM
                )
                constraintSet.applyTo(binding.constraintLayout)
            }
        }
    }

    private fun updateUI(list: List<ListModelNew>?) {
        if (!list.isNullOrEmpty()) {
            binding.emptyImg.visibility = View.GONE
            binding.homeRecyclerView.visibility = View.VISIBLE
        } else {
            binding.emptyImg.visibility = View.VISIBLE
            binding.homeRecyclerView.visibility = View.GONE
        }
    }

    private fun setupHomeRecyclerView() {
        listAdapter = ListAdapter()
        binding.homeRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = listAdapter
        }

        listViewModel.getAllList().observe(viewLifecycleOwner) { list ->
            listAdapter.differ.submitList(list)
            updateUI(list)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        // Handle text submission if needed
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchList(newText)
        return true
    }

    private fun searchList(query: String?) {
        val searchQuery = "%$query%"
        listViewModel.searchList(searchQuery).observe(viewLifecycleOwner) { list ->
            listAdapter.differ.submitList(list)
            updateUI(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeBinding = null // Cleanup homeBinding to avoid memory leaks
    }
}
