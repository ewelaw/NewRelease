package com.ewelaw.newrelease.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ewelaw.newrelease.api.AlbumApi
import com.ewelaw.newrelease.datasource.AlbumsDataSource
import com.ewelaw.newrelease.model.Album
import com.ewelaw.newrelease.utils.ApiConstants
import kotlinx.coroutines.flow.Flow

class AlbumsViewModel(
    private val albumApi: AlbumApi
) : ViewModel() {
    var albumsFlow: Flow<PagingData<Album>> =
        Pager(
            PagingConfig(
                pageSize = ApiConstants.ABUMS_PAGE_SIZE,
                initialLoadSize = ApiConstants.ABUMS_PAGE_SIZE
            )
        ) {
            AlbumsDataSource(albumApi)
        }.flow.cachedIn(viewModelScope)
}