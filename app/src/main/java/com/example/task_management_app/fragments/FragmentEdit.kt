package com.example.task_management_app.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.task_management_app.MainFragment
import com.example.task_management_app.R
import com.example.task_management_app.databinding.FragmentEditBinding
import com.example.task_management_app.model.ListModelNew
import com.example.task_management_app.viewmodel.ListViewModel

class FragmentEdit : Fragment(R.layout.fragment_edit), MenuProvider {

    private var editListBinding : FragmentEditBinding? = null
    private val binding get() = editListBinding!!

    private lateinit var listViewModel: ListViewModel
    private lateinit var currentList: ListModelNew

    private val args: FragmentEditArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        editListBinding = FragmentEditBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        listViewModel = (activity as MainFragment).listViewModel
        currentList = args.list!!

        binding.editTextTitle.setText(currentList.listTitle)
        binding.editTextCat.setText(currentList.listCategory)
        binding.editTextDes.setText(currentList.listDes)

        binding.btnSave.setOnClickListener {
            val listTitle = binding.editTextTitle.text.toString().trim()
            val listCategory = binding.editTextCat.text.toString().trim()
            val listDes = binding.editTextDes.text.toString().trim()

            if (listTitle.isNotEmpty()) {
                val list = ListModelNew(currentList.id, listTitle, listCategory, listDes)
                listViewModel.updateList(list)
                view.findNavController().popBackStack(R.id.fragmentHome, false)
            } else {
                Toast.makeText(context, "Enter task title", Toast.LENGTH_SHORT).show()
            }
        }

        // Set OnClickListener for the Delete button
        binding.btnDelete.setOnClickListener {
            deleteList()
        }
    }


    private fun deleteList(){
        AlertDialog.Builder(activity).apply{
            setTitle("Delete Task")
            setMessage("Do you want to delete this task?")
            setPositiveButton("Delete"){_,_ ->
                listViewModel.deleteList(currentList)
                Toast.makeText(context,"Task deleted",Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.fragmentHome,false)
            }
            setNegativeButton("Cancel",null)
        }.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.edit_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.deleteMenu -> {
                deleteList()
                true
            }else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        editListBinding = null
    }
}
