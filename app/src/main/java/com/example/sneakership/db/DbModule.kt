package com.example.sneakership.db

import android.content.Context
import androidx.room.Room
import com.example.sneakership.features.sneakerDetails.dao.SneakerDetailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): SneakerShipDataBase {
        return Room.databaseBuilder(context, SneakerShipDataBase::class.java, "SNEAKER_SHIP_DB")
            .build()
    }

    @Provides
    @Singleton
    fun provideSneakerDetailDao(dataBase: SneakerShipDataBase): SneakerDetailDao {
        return dataBase.sneakerDetailDao()
    }
}