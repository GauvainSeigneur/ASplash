package com.gauvain.seigneur.asplash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.gauvain.seigneur.asplash.paging.PhotoListDataSource
import com.gauvain.seigneur.domain.usecase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val photosUseCase: GetPhotosUseCase,
) : ViewModel() {

    val photoPager = Pager(
        config = PagingConfig(pageSize = 20),
        initialKey = 1
    ) {
        PhotoListDataSource(photosUseCase)
    }.flow.cachedIn(viewModelScope)
}