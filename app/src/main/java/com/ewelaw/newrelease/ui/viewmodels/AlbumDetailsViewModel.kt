package com.ewelaw.newrelease.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewelaw.newrelease.BR
import com.ewelaw.newrelease.R
import com.ewelaw.newrelease.api.AlbumApi
import com.ewelaw.newrelease.model.Album
import com.ewelaw.newrelease.model.AlbumTracksResponse
import com.ewelaw.newrelease.model.Track
import com.google.gson.Gson
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding


class AlbumDetailsViewModel(private val albumApi: AlbumApi) : ViewModel() {
    val album: MutableLiveData<Album> = MutableLiveData()
    var albumTracksResponse: MutableLiveData<AlbumTracksResponse> = MutableLiveData()
    val itemBinding = ItemBinding.of<Track>(BR.track, R.layout.item_track)

    fun getAlbumTracks() {
        viewModelScope.launch {
            kotlin.runCatching {
                albumTracksResponse.value = albumApi.getAlbumTracks(
                    album.value!!.id
                )
            }
        }
    }

    fun getMockedAlbumTracks(jsonString: String?) {
        albumTracksResponse.value = Gson().fromJson(jsonString, AlbumTracksResponse::class.java)
    }

}