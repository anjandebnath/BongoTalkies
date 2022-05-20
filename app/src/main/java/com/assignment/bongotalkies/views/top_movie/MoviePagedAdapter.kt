package com.assignment.bongotalkies.views.top_movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.bongotalkies.databinding.GridMovieListBinding
import com.assignment.bongotalkies.domain.ResultMovie
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class MoviePagedAdapter @Inject constructor(val clickListener: ClickListener):
    PagingDataAdapter<ResultMovie, MoviePagedAdapter.MyViewHolder>(UsersListDiffCallback()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        // this method is from Paging
        currentItem?.let { holder.bind(it, clickListener) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }


    class MyViewHolder private constructor(private val binding: GridMovieListBinding) :
       RecyclerView.ViewHolder(binding.root){

            fun bind(item: ResultMovie, clickListener: ClickListener) {
                binding.data = item
                binding.executePendingBindings()
                binding.clickListener = clickListener
            }

        /**
         * companion object is equivalent to Static.
         * A class that has a companion object, the members of the class can easily be accessed
         * without creating an object to access them.
         */
        companion object {
                fun from(parent: ViewGroup): MyViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = GridMovieListBinding.inflate(layoutInflater, parent, false)
                    return MyViewHolder(binding)
                }
            }
       }

}

class UsersListDiffCallback : DiffUtil.ItemCallback<ResultMovie>() {

    override fun areItemsTheSame(oldItem: ResultMovie, newItem: ResultMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResultMovie, newItem: ResultMovie): Boolean {
        return oldItem == newItem
    }

}

class ClickListener @Inject constructor() {

    var onItemClick: ((ResultMovie) -> Unit)? = null

    fun onClick(data: ResultMovie) {
        onItemClick?.invoke(data)
    }
}