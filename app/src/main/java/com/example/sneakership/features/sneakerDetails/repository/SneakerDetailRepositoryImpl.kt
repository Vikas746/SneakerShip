package com.example.sneakership.features.sneakerDetails.repository

import com.example.sneakership.features.sneakerDetails.dao.SneakerDetailDao
import com.example.sneakership.features.sneakerDetails.models.SneakerDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SneakerDetailRepositoryImpl @Inject constructor(private val sneakerDetailDao: SneakerDetailDao) :
    SneakerDetailRepository {

    override suspend fun insertSneakerDetail(sneakerDetail: SneakerDetail) {
        sneakerDetailDao.insertSneaker(sneakerDetail)
    }

    override suspend fun deleteSneakerDetail(sneakerDetail: SneakerDetail) {
        sneakerDetailDao.insertSneaker(sneakerDetail)
    }

    override suspend fun fetchSneakers(): Flow<List<SneakerDetail>> {
        return sneakerDetailDao.fetchSneakers()
    }
}