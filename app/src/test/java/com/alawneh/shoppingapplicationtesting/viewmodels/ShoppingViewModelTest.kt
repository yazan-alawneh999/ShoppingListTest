package com.alawneh.shoppingapplicationtesting.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alawneh.shoppingapplicationtesting.getOrAwaitValueTest
import com.alawneh.shoppingapplicationtesting.repositories.FakeShoppingListRepository
import com.alawneh.shoppingapplicationtesting.util.ConstantUtil
import com.alawneh.shoppingapplicationtesting.util.Resource
import com.alawneh.shoppingapplicationtesting.util.Status
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class ShoppingViewModelTest {
    private lateinit var shoppingViewModel: ShoppingViewModel

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        shoppingViewModel = ShoppingViewModel(FakeShoppingListRepository())
    }

    @Test
    fun insertShoppingItemWithEmptyField() {
        shoppingViewModel.insertShoppingItem("name", "", "3.0")
        val value = shoppingViewModel.insertItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }


    @Test
    fun insertShoppingItemWithTooLongName() {
        val string = buildString {
            for (i in 1..ConstantUtil.MAX_NAME_LENGTH) {
                append(1)
            }

        }
        shoppingViewModel.insertShoppingItem(string, "5", "3.0")
        val value = shoppingViewModel.insertItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insertShoppingItemWithTooLongPrice() {
        val string = buildString {
            for (i in 1..ConstantUtil.MAX_PRICE_LENGTH) {
                append(1)
            }

        }
        shoppingViewModel.insertShoppingItem("name", "5", string)
        val value = shoppingViewModel.insertItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insertShoppingItemWithTooHighAmount() {
        shoppingViewModel.insertShoppingItem("name", "9999999999999999999999999999999", "3.0")
        val value = shoppingViewModel.insertItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insertShoppingItemWithValidInput() {
        shoppingViewModel.insertShoppingItem("name", "5", "3.0")
        val value = shoppingViewModel.insertItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }


}