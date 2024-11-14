package com.bonsaisoftware.dailybook.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonsaisoftware.dailybook.domain.BagRepository
import com.bonsaisoftware.dailybook.model.Bag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BagsUiState(
    val bags: List<Bag> = emptyList(),
    val total: Long = 0
)

class BagsViewModel(private val repo: BagRepository): ViewModel() {
    private val _uiState = MutableStateFlow(BagsUiState())
    val uiState = _uiState.asStateFlow()
    private val allBags = repo.getAllBags()

    init {
        getAllBags()
    }

    private fun updateState() {
        _uiState.update { state ->
            state.copy(bags = allBags, total = allBags.sumOf { it.bagAmount })
        }
    }

    fun getAllBags() {
        viewModelScope.launch {
            updateState()
        }
    }

    fun addBag(bag: Bag) {
        viewModelScope.launch {
            repo.addBag(bag)
            updateState()
        }
    }

    fun editBag(bag: Bag) {
        viewModelScope.launch {
            repo.editBag(bag)
            updateState()
        }
    }
}