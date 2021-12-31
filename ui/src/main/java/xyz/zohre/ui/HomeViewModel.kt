package xyz.zohre.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import xyz.zohre.data.discovery.ProductRepository
import xyz.zohre.data.model.CoolBlueResponse
import xyz.zohre.data.model.Products
import xyz.zohre.domain.entities.Result
import xyz.zohre.presentation.Event
import xyz.zohre.presentation.TextData
import xyz.zohre.presentation.parseErrorStringRes
import javax.inject.Inject

class HomeViewModel @Inject constructor(val productRepository: ProductRepository) : ViewModel() {

    private var searchJob: Job? = null

    private val _products = MutableLiveData<List<Products>?>()
    val products: LiveData<List<Products>?> get() = _products

    private val _showError = MutableLiveData<Event<TextData>>()
    val showError: LiveData<Event<TextData>> = _showError

    fun searchProducts() {
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            productRepository().collect { showResults(it) }
        }
    }

    private fun showResults(productsDataResults: Result<CoolBlueResponse>) {

        when (productsDataResults) {
            is Result.Success -> {
                val productsData = productsDataResults.data.products
                _products.value = productsData
            }
            Result.Loading -> {
            }
            is Result.Error -> {
                productsDataResults.exception.let {
                    _showError.value = Event(it.parseErrorStringRes())
                }
            }
        }
    }

}