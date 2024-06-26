package com.hefengbao.jingmo.ui.screen.classicpoem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.ClassicPoemEntity
import com.hefengbao.jingmo.data.repository.ClassicPoemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassicPoemSearchScreenViewModel @Inject constructor(
    private val repository: ClassicPoemRepository
) : ViewModel() {
    private val _poems: MutableStateFlow<List<ClassicPoemEntity>> = MutableStateFlow(emptyList())
    val poems: SharedFlow<List<ClassicPoemEntity>> = _poems
    fun search(query: String) {
        viewModelScope.launch {
            repository.search(query).collectLatest {
                _poems.value = it
            }
        }
    }
}