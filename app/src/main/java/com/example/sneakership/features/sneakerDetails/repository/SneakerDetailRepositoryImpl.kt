package com.example.sneakership.features.sneakerDetails.repository

import com.example.sneakership.features.sneakerDetails.dao.SneakerDetailDao
import com.example.sneakership.features.sneakerDetails.models.SneakerDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SneakerDetailRepositoryImpl @Inject constructor(private val sneakerDetailDao: SneakerDetailDao) :
    SneakerDetailRepository {

    /**
     * This method adds the sneaker to database.
     */
    override suspend fun insertSneakerDetail(sneakerDetail: SneakerDetail) {
        sneakerDetailDao.insertSneaker(sneakerDetail)
    }

    /**
     * This method deletes the sneaker from database.
     */
    override suspend fun deleteSneakerDetail(sneakerDetail: SneakerDetail) {
        sneakerDetailDao.deleteSneaker(sneakerDetail)
    }

    /**
     * This method provides all the sneakers in database.
     */
    override fun fetchSneakers(): Flow<List<SneakerDetail>> {
        return sneakerDetailDao.fetchSneakers()
    }
}