package com.assignment.bongotalkies.views.movie_details

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.assignment.bongotalkies.R
import com.assignment.bongotalkies.databinding.FragmentProfileBinding
import com.assignment.bongotalkies.util.Constants
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profile, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel.refreshUserDetails(args.user)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* viewModel.getMovieDetailsFromLocal(args.movieId).observe(viewLifecycleOwner, {
            viewModel.rqstUserDetails.set(it)
        })*/

        viewModel.movieList.observe(viewLifecycleOwner){
            val media = Constants.IMAGE_CDN + it.backdrop_path
            val id = it.id
            binding.name.setText(it.original_title)
            if (media !== null) {
                Glide.with(this)
                    .load(media)
                    .into(binding.image)
            } else {
                binding.image.setImageResource(R.drawable.ic_launcher_background)
            }

        }


        viewModel.fetchMovieDetails(args.movieId)




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}