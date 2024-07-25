package com.alawneh.shoppingapplicationtesting.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alawneh.shoppingapplicationtesting.data.local.ShoppingItem
import com.alawneh.shoppingapplicationtesting.data.remote.ImageResponse
import com.alawneh.shoppingapplicationtesting.repositories.ShoppingListRepository
import com.alawneh.shoppingapplicationtesting.util.Event
import com.alawneh.shoppingapplicationtesting.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val shoppingRepository: ShoppingListRepository
) : ViewModel() {

    val shoppingItemsList: LiveData<List<ShoppingItem>> =
        shoppingRepository.observeAllShoppingItems()

    val totalPrice: LiveData<Float> = shoppingRepository.observeTotalPrice()

    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val images: LiveData<Event<Resource<ImageResponse>>> = _images

    private val _currentImageUrl = MutableLiveData<String>()
    val currentImageUrl: LiveData<String> = _currentImageUrl

    private val _insertItemStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertItemStatus: LiveData<Event<Resource<ShoppingItem>>> = _insertItemStatus

    fun setCurrentUrl(url: String) {
        _currentImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            shoppingRepository.deleteShoppingItem(shoppingItem)
        }
    }

    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            shoppingRepository.insertShoppingItem(shoppingItem)
        }
    }


    fun insertShoppingItem(itemName: String, amount: String, price: String) {
            // TODO:  validate insertion}
    }


        fun searchForImage(imageQuery: String) {
            // TODO:  implement search for image
        }


    }