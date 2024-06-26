package com.hefengbao.jingmo.ui.screen.idiom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.IdiomCollectionEntity
import com.hefengbao.jingmo.data.repository.IdiomRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class IdiomReadViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val idiomRepository: IdiomRepository
) : ViewModel() {

    private var id = MutableStateFlow(1)

    init {
        viewModelScope.launch {
            preferenceRepository.getReadStatus().collectLatest {
                id.value = it.idiomsLastReadId
            }
        }
    }

    fun setCurrentId(id: Int) {
        this.id.value = id
    }

    val idiom = id.flatMapLatest {
        idiomRepository.getIdiom(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val idiomCollectionEntity = id.flatMapLatest {
        idiomRepository.isCollect(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val prevId = id.flatMapLatest {
        idiomRepository.getPrevId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val nextId = id.flatMapLatest {
        idiomRepository.getNextId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    fun setUncollect(id: Int) {
        viewModelScope.launch {
            idiomRepository.uncollect(id)
        }
    }

    fun setCollect(id: Int) {
        viewModelScope.launch {
            idiomRepository.collect(IdiomCollectionEntity(id))
        }
    }

    fun setLastReadId(id: Int) {
        viewModelScope.launch {
            preferenceRepository.setIdiomsLastReadId(id)
        }
    }
}