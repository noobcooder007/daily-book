package com.bonsaisoftware.dailybook.data

import com.bonsaisoftware.dailybook.model.Bag

object BagManager {
    private val _bags = mutableListOf<Bag>(
        Bag(
            bagId = 1,
            bagName = "Cochinito",
            bagAmount = 50,
            bagDate = "2023-04-01",
        )
    )

    val bags = _bags

    fun addBag(bag: Bag) {
        bags.add(bag)
    }

    fun editBag(bag: Bag) {
        val index = bags.indexOfFirst { it.bagId == bag.bagId }
        if (index != -1) {
            _bags[index] = bags[index].copy(
                bagName = bag.bagName,
                bagAmount = bag.bagAmount,
                goalId = bag.goalId
            )
        }
    }

}