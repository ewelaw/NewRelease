package com.ewelaw.newrelease.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ewelaw.newrelease.databinding.FragmentAlbumsBinding
import com.ewelaw.newrelease.ui.adapters.AlbumsPagedAdapter
import com.ewelaw.newrelease.ui.viewmodels.AlbumsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumsFragment : Fragment() {
    private lateinit var binding: FragmentAlbumsBinding
    private val albumsViewModel: AlbumsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumsBinding.inflate(inflater, container, false)

        postponeEnterTransition()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val albumsPagedAdapter = AlbumsPagedAdapter()

        binding.rvAlbums.doOnPreDraw {
            startPostponedEnterTransition()
        }
        binding.rvAlbums.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = albumsPagedAdapter
        }

        lifecycleScope.launch {
            albumsViewModel.albumsFlow.collectLatest { pagedDate ->
                albumsPagedAdapter.submitData(pagedDate)
            }
        }
    }
}