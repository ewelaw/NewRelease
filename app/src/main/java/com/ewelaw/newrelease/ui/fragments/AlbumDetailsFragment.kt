package com.ewelaw.newrelease.ui.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.ewelaw.newrelease.BuildConfig
import com.ewelaw.newrelease.R
import com.ewelaw.newrelease.databinding.FragmentAlbumDetailsBinding
import com.ewelaw.newrelease.ui.viewmodels.AlbumDetailsViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumDetailsFragment : Fragment() {
    private val albumDetailsViewModel: AlbumDetailsViewModel by viewModel()
    private val args: AlbumDetailsFragmentArgs by navArgs()
    lateinit var binding: FragmentAlbumDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumDetailsViewModel.album.value = args.album
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumDetailsBinding.inflate(layoutInflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = albumDetailsViewModel
        }

        if (BuildConfig.FLAVOR.equals("apiMocked")) {
            val mockAlbumTracks: String = requireContext().resources.openRawResource(
                requireContext().resources.getIdentifier(
                    "mock_data",
                    "raw",
                    this.requireContext().packageName
                )
            )
                .bufferedReader().use {
                    it.readText()
                }
            albumDetailsViewModel.getMockedAlbumTracks(mockAlbumTracks)

            Toast.makeText(requireContext(), "This album tracks are mocked!", Toast.LENGTH_LONG)
                .show()
        } else {
            albumDetailsViewModel.getAlbumTracks()
        }

        return binding.root
    }
}