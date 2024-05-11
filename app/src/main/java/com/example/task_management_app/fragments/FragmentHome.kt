package com.example.task_management_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.task_management_app.R
import com.example.task_management_app.adapter.ListAdapter
import com.example.task_management_app.databinding.FragmentHomeBinding
import com.example.task_management_app.model.ListModelNew
import com.example.task_management_app.viewmodel.ListViewModel

class FragmentHome : Fragment(R.layout.fragment_home) , SearchView.OnQueryTextListener, MenuProvider {

    private var homeBinding: FragmentHomeBinding? = null
    private val binding get() = homeBinding!!

    private lateinit var listViewModel: ListViewModel
    private lateinit var listAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, androidx.lifecycle.Lifecycle.State.RESUMED)

        listViewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]
        setupHomeRecyclerView()

        binding.addBtn.setOnClickListener {
            // Pop back stack to ensure a new instance of the fragment is created
            view.findNavController().popBackStack(R.id.fragmentHome, false)
            // Navigate to the add fragment
            view.findNavController().navigate(R.id.action_fragmentHome_to_fragmentAdd)
        }
    }

    private fun updateUI(list: List<ListModelNew>?) {
        if (!list.isNullOrEmpty()) {
            if (list.isNotEmpty()){
                binding.emptyImg.visibility = View.GONE
                binding.homeRecyclerView.visibility = View.VISIBLE
            }else{
                binding.emptyImg.visibility = View.VISIBLE
                binding.homeRecyclerView.visibility = View.GONE
            }

        }
    }

    private fun setupHomeRecyclerView() {
        listAdapter = ListAdapter()
        binding.homeRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = listAdapter
        }

        activity?.let{
            listViewModel.getAllList().observe(viewLifecycleOwner) { list ->
                listAdapter.differ.submitList(list)
                updateUI(list)
            }
        }

    }

    private fun searchList(query: String?) {
        query?.let { // Perform search only if query is not null
            val searchQuery = "%$query"
            listViewModel.searchList(searchQuery).observe(viewLifecycleOwner) { list ->
                listAdapter.differ.submitList(list)
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchList(newText)
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeBinding = null // Cleanup homeBinding to avoid memory leaks
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.home_menu, menu)

        val menuSearch = menu.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }

}