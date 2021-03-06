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
    lateinit var adapter: MoviePagedAdapter
    // To allow nulls, you can declare a variable as a nullable by writing String?:
    private var _binding: FragmentMovieListBinding? = null
    // not-null assertion operator (!!) converts any value to a non-null type and
    // throws an exception if the value is null
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

        // A scope controls the lifetime of coroutines through its job.

        /**
         * https://antonioleiva.com/lambdas-kotlin-android/
         * If the function???s last parameter is a function, it can go outside the parentheses
         * If a function has only one parameter, and this is a function, the parentheses can be deleted
         * If you don???t use the parameter of a lambda, you can remove the left side of the function
         * functions that only receive a parameter, instead of defining the left side,
         * we could use the reserved word it, saving some characters.
         */
        lifecycleScope.launch {
            viewModel.listData.collect {
                adapter.submitData(it)
            }
        }

        // pass argument to the next fragment
        adapter.clickListener.onItemClick = {
            findNavController().navigate(MovieListFragmentDirections.actionMovieIdToDetails(it.id))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }
}