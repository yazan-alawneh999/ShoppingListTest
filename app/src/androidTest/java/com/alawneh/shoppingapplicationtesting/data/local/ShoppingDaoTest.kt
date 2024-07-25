package com.alawneh.shoppingapplicationtesting.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.alawneh.shoppingapplicationtesting.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ShoppingItemDatabase
    private lateinit var shoppingDao: ShoppingDao

    @Before()
    fun setup() {
        database = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingItemDatabase::class.java, "ShoppingDB"
        ).allowMainThreadQueries().build()

        shoppingDao = database.shoppingDao()
    }

    @After()
    fun teardown() {
        database.close()
    }


    @Test
    fun insertShoppingItem() = runTest {
        val shoppingItem = ShoppingItem("name", 1, 1f, "url", id = 1)
        shoppingDao.insertShoppingItem(shoppingItem)
        val allShoppingItems = shoppingDao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItems).contains(shoppingItem)

    }

    @Test
    fun deleteShoppingItem() = runTest {
        val shoppingItem = ShoppingItem("name", 1, 1f, "url", id = 1)
        shoppingDao.insertShoppingItem(shoppingItem)
        shoppingDao.deleteShoppingItem(shoppingItem)
        val allShoppingItems = shoppingDao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItems).doesNotContain(shoppingItem)

    }

    @Test
    fun observeTotalPrice() = runTest {
        val item1 = ShoppingItem("name", 2, 10f, "url", id = 1)
        val item2 = ShoppingItem("name", 4, 5.5f, "url", id = 2)
        val item3 = ShoppingItem("name", 1, 20f, "url", id = 3)
        shoppingDao.insertShoppingItem(item1)
        shoppingDao.insertShoppingItem(item2)
        shoppingDao.insertShoppingItem(item3)
        val totalPrice = shoppingDao.observeTotalPrice().getOrAwaitValue()
        assertThat(totalPrice).isEqualTo((2 * 10f) + (4 * 5.5f) + (1 * 20f))
    }




}