package com.alawneh.shoppingapplicationtesting.di

import android.content.Context
import com.alawneh.shoppingapplicationtesting.util.ConstantUtil
import androidx.room.Room
import com.alawneh.shoppingapplicationtesting.ShoppingApplication
import com.alawneh.shoppingapplicationtesting.data.local.ShoppingDao
import com.alawneh.shoppingapplicationtesting.data.local.ShoppingItemDatabase
import com.alawneh.shoppingapplicationtesting.network.retrofit.PixabayApi
import com.alawneh.shoppingapplicationtesting.repositories.DefaultRepository
import com.alawneh.shoppingapplicationtesting.repositories.ShoppingListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleApp {


    @Provides
    @Singleton
    fun provideShoppingItemDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, ShoppingItemDatabase::class.java, ConstantUtil.SHOPPING_DB_NAME
    ).build()


    @Provides
    @Singleton
    fun provideDefaultRepository(
        dao: ShoppingDao,
        api: PixabayApi
    ) = DefaultRepository(api, dao) as ShoppingListRepository

    @Provides
    @Singleton
    fun provideShoppingDao(database: ShoppingItemDatabase) = database.shoppingDao()

    @Provides
    @Singleton
    fun providePixabayApi() = Retrofit.Builder()
        .baseUrl(ConstantUtil.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PixabayApi::class.java)


}