package com.ewelaw.newrelease.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ewelaw.newrelease.api.AlbumApi
import com.ewelaw.newrelease.model.Album
import com.ewelaw.newrelease.utils.ApiConstants

class AlbumsDataSource(private val albumApi: AlbumApi) : PagingSource<Int, Album>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> {
        return try {
            val nextPage = params.key ?: 0
            val offset = if (nextPage == 0) params.loadSize
            else nextPage * params.loadSize + params.loadSize

            val response = albumApi.getAllNewReleases(
                "PL", ApiConstants.ABUMS_PAGE_SIZE, offset
            )

            LoadResult.Page(
                data = response.albums.items,
                prevKey = if (nextPage > 0)
                    nextPage - 1
                else null,
                nextKey = if (nextPage < response.albums.total)
                    nextPage + 1
                else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Album>): Int? {
        TODO("Not yet implemented")
    }

}