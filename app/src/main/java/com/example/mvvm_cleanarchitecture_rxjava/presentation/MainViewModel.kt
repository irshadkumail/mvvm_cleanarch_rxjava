package com.example.mvvm_cleanarchitecture_rxjava.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mvvm_cleanarchitecture_rxjava.data.models.Album
import com.example.mvvm_cleanarchitecture_rxjava.data.models.Photo
import com.example.mvvm_cleanarchitecture_rxjava.domain.models.AlbumModel
import com.example.mvvm_cleanarchitecture_rxjava.domain.models.PhotoModel
import com.example.mvvm_cleanarchitecture_rxjava.domain.models.UserModel
import com.example.mvvm_cleanarchitecture_rxjava.domain.usecase.GetAlbumsUseCase
import com.example.mvvm_cleanarchitecture_rxjava.domain.usecase.GetPhotosUseCase
import com.example.mvvm_cleanarchitecture_rxjava.domain.usecase.GetUsersUseCase
import com.example.mvvm_cleanarchitecture_rxjava.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import okhttp3.internal.wait
import java.util.*
import javax.inject.Inject

data class HomeListUiStateItem(
    val id: Int,
    val url: String = "",
    val name: String = "",
    val albumName: String = "",
    val username: String = ""
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val albumsUseCase: GetAlbumsUseCase,
    private val photosUseCase: GetPhotosUseCase,
    private val usersUseCase: GetUsersUseCase
) : BaseViewModel() {

    protected var lastDisposable: Disposable? = null

    val uiList = mutableStateListOf<HomeListUiStateItem>()

    init {
        fetchList()
    }

    private fun buildUiList(
        albums: List<AlbumModel>,
        photos: List<PhotoModel>,
        users: List<UserModel>
    ): List<HomeListUiStateItem> {


        return photos.map { photo ->

            val selectedAlbum: AlbumModel? = albums.find { album -> album.id == photo.albumId }

            HomeListUiStateItem(
                id = photo.id,
                url = photo.thumbnailUrl,
                name = photo.title,
                albumName = selectedAlbum?.title ?: "",
                username = users.find { user -> user.id == selectedAlbum?.id }?.name ?: ""
            )
        }
    }

     private fun fetchList() {

        lastDisposable = Observable.zip(
            albumsUseCase.execute(),
            photosUseCase.execute(),
            usersUseCase.execute(),
            this::buildUiList
        ).doOnSubscribe {
            loadingState = true
        }.subscribe({
            loadingState = false
            uiList.addAll(it)
        }, {
            loadingState = false
            handleError = it.message ?: "Error encountered"
        })
    }

    override fun onCleared() {
        super.onCleared()
        if (lastDisposable?.isDisposed == false)
            lastDisposable?.dispose()
    }

}