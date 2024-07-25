package com.alawneh.shoppingapplicationtesting.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alawneh.shoppingapplicationtesting.data.local.ShoppingItem
import com.alawneh.shoppingapplicationtesting.data.remote.ImageResponse
import com.alawneh.shoppingapplicationtesting.util.Resource

class FakeShoppingListRepository : ShoppingListRepository {
    private val shoppingItems = mutableListOf<ShoppingItem>()
    private val observableList = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    private val observableTotalPrice = MutableLiveData<Float>()
    private var isShouldReturnError = false


    private fun setShouldReturnError(value: Boolean) {
        isShouldReturnError = value
    }

    private fun refreshShoppingItems() {
        observableList.postValue(shoppingItems)
        observableTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice(): Float {
        return shoppingItems.sumOf { it.price.toDouble() }.toFloat()

    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        refreshShoppingItems()
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshShoppingItems()
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return observableList
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return observableTotalPrice
    }

    override fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        if (isShouldReturnError) {
            return Resource.error("Error", null)
        } else {
            return Resource.success(ImageResponse(listOf(), 0, 0))
        }
    }
}