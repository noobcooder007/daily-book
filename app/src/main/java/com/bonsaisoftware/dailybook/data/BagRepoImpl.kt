package com.bonsaisoftware.dailybook.data

import com.bonsaisoftware.dailybook.domain.BagRepository
import com.bonsaisoftware.dailybook.model.Bag

class BagRepoImpl(private val bagManager: BagManager) : BagRepository {
    override fun getAllBags(): List<Bag> {
        return bagManager.bags
    }

    override suspend fun addBag(bag: Bag) {
        bagManager.addBag(bag)
    }

    override suspend fun editBag(bag: Bag) {
        bagManager.editBag(bag)
    }
}
