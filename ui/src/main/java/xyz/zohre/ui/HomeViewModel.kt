package xyz.zohre.ui

import androidx.lifecycle.ViewModel
import xyz.zohre.data.discovery.ProductRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(val productRepository: ProductRepository) : ViewModel() {
}