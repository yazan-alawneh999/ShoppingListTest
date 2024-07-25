package com.alawneh.shoppingapplicationtesting.repositories

import androidx.lifecycle.LiveData
import com.alawneh.shoppingapplicationtesting.data.local.ShoppingDao
import com.alawneh.shoppingapplicationtesting.data.local.ShoppingItem
import com.alawneh.shoppingapplicationtesting.data.remote.ImageResponse
import com.alawneh.shoppingapplicationtesting.network.retrofit.PixabayApi
import com.alawneh.shoppingapplicationtesting.util.Resource
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val api: PixabayApi,
    private val databaseDao: ShoppingDao
) : ShoppingListRepository {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        databaseDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        databaseDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return databaseDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return databaseDao.observeTotalPrice()
    }

    override fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = api.getImage(imageQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Couldn't retrieve results", null)
            } else {
                Resource.error("Couldn't retrieve results", null)
            }
        } catch (e: Exception) {
            return Resource.error(
                "Couldn't reach the server. Check your internet connection.",
                null
            )
        }

    }

}