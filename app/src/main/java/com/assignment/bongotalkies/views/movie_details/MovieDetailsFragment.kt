package com.assignment.bongotalkies.views.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.assignment.bongotalkies.R
import com.assignment.bongotalkies.databinding.FragmentMovieDetailsBinding
import com.assignment.bongotalkies.databinding.FragmentProfileBinding
import com.assignment.bongotalkies.util.Constants
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_details, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.movieList.observe(viewLifecycleOwner){
            val media = Constants.IMAGE_CDN + it.backdrop_path
            val id = it.id
            binding.movieName.setText(it.original_title)
            binding.movieRelease.setText(it.release_date)
            if (media !== null) {
                Glide.with(this)
                    .load(media)
                    .into(binding.image)
            } else {
                binding.image.setImageResource(R.drawable.ic_launcher_background)
            }
            binding.tvVote.setText(it.vote_average.toString())
            binding.tvPopularity.setText(it.popularity.toString())
            binding.tvDescription.setText(it.overview)

        }


        viewModel.fetchMovieDetails(args.movieId)




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}