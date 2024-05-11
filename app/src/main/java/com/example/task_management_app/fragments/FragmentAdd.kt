package com.example.task_management_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.task_management_app.R
import com.example.task_management_app.databinding.FragmentAddBinding
import com.example.task_management_app.model.ListModelNew
import com.example.task_management_app.viewmodel.ListViewModel

class FragmentAdd : Fragment() {

    private var addListBinding: FragmentAddBinding? = null
    private val binding get() = addListBinding!!

    private lateinit var listViewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addListBinding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]

        // Navigate to the desired fragment when the "Create" button is clicked
        view.findViewById<Button>(R.id.btnCreate).setOnClickListener {
            saveList()
        }
    }

    private fun saveList() {
        val listTitle = binding.textTitle.text.toString().trim()
        val listCategory = binding.textCat.text.toString().trim()
        val listDes = binding.textDes.text.toString().trim()


        if (listTitle.isNotEmpty()) {
            val list = ListModelNew(0, listTitle,  listCategory, listDes)
            listViewModel.addList(list)

            Toast.makeText(requireContext(), "Task Saved", Toast.LENGTH_SHORT).show()
            // Navigate to the desired destination
            findNavController().popBackStack(R.id.fragmentHome, false)
        } else {
            Toast.makeText(requireContext(), "Please enter valid date and title!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        addListBinding = null
    }
}
