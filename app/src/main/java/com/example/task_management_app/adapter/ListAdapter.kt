package com.example.task_management_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task_management_app.databinding.ListBinding
import com.example.task_management_app.fragments.FragmentHomeDirections
import com.example.task_management_app.model.ListModel


class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    class ListViewHolder(val itemBinding: ListBinding) : RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<ListModel>(){
        override fun areItemsTheSame(oldItem: ListModel, newItem: ListModel): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.listDes == newItem.listDes &&
                    oldItem.listTitle == newItem.listTitle &&
                    oldItem.listCategory == newItem.listCategory &&
                    oldItem.listDate == newItem.listDate
        }

        override fun areContentsTheSame(oldItem: ListModel, newItem: ListModel): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentList = differ.currentList[position]

        holder.itemBinding.textTitle.text = currentList.listTitle
        holder.itemBinding.textDes.text = currentList.listDes
        holder.itemBinding.textDate.text = currentList.listDate.toString()

        holder.itemView.setOnClickListener {
            val direction = FragmentHomeDirections.actionFragmentHomeToFragmentEdit(currentList)
            it.findNavController().navigate(direction)

        }
    }

}
