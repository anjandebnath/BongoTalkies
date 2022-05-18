package com.assignment.bongotalkies.views.top_movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.assignment.bongotalkies.R
import com.assignment.bongotalkies.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : Fragment(){

    // Use the 'by viewModels()' Kotlin property delegate
    // from the activity-ktx artifact
    private val viewModel: MovieListViewModel by viewModels()
    @Inject
    //lateinit var adapter: MovieListAdapter
    lateinit var adapter: MoviePagedAdapter

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_list, container, false
        )
        binding.viewModel = viewModel
        // lifecycle of the fragment's view
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.layoutManager = GridLayoutManager(context,2)
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.listData.collect {
                adapter.submitData(it)
            }
        }


//        viewModel.data.observe(viewLifecycleOwner, {
//            adapter.submitList(it)
//        })
        // pass argument to the next fragment
        adapter.clickListener.onItemClick = {
            findNavController().navigate(MovieListFragmentDirections.actionUsersListToUserDetails(it.id))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }
}