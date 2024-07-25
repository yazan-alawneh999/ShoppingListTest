package com.alawneh.shoppingapplicationtesting.repositories

import androidx.lifecycle.LiveData
import com.alawneh.shoppingapplicationtesting.data.local.ShoppingItem
import com.alawneh.shoppingapplicationtesting.data.remote.ImageResponse
import com.alawneh.shoppingapplicationtesting.util.Resource

interface ShoppingListRepository {
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)
    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>
    fun observeTotalPrice(): LiveData<Float>
    fun searchForImage(imageQuery: String): Resource<ImageResponse>
}