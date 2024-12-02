package com.bonsaisoftware.dailybook.data

import com.bonsaisoftware.dailybook.db.BagQueries
import com.bonsaisoftware.dailybook.db.Database
import com.bonsaisoftware.dailybook.db.GoalQueries
import com.bonsaisoftware.dailybook.domain.BagRepository
import com.bonsaisoftware.dailybook.model.Bag
import com.bonsaisoftware.dailybook.model.Goal
import com.bonsaisoftware.dailybook.model.GoalStatus
import java.util.Date

class BagRepoImpl(database: Database) : BagRepository {
    private val bagQueries: BagQueries = database.bagQueries
    private val goalQueries: GoalQueries = database.goalQueries
    override fun getAllBags(): List<Bag> {
        return bagQueries.selectAll().executeAsList().map { bagEntity ->
            Bag(
                bagId = bagEntity.bagId,
                bagName = bagEntity.bagName,
                bagAmount = bagEntity.bagAmount,
                bagDate = Date(bagEntity.bagDate),
                bagIsActive = bagEntity.bagIsActive == 1L,
                goal = bagEntity.goalId?.let {
                    goalQueries.selectById(it).executeAsOne().let { goalEntity ->
                        Goal(
                            goalId = goalEntity.goalId,
                            goalName = goalEntity.goalName,
                            goalAmount = goalEntity.goalAmount,
                            goalDate = Date(goalEntity.goalDate),
                            goalStatus = GoalStatus.valueOf(goalEntity.goalStatus),
                            goalIsActive = goalEntity.goalIsActive == 1L
                        )
                    }
                }
            )
        }
    }

    override suspend fun addBag(bag: Bag) {
        bagQueries.transaction {
            bagQueries.insert(
                bagName = bag.bagName,
                bagAmount = bag.bagAmount,
                bagDate = bag.bagDate.toString(),
                bagIsActive = if (bag.bagIsActive) 1L else 0L,
                goalId = bag.goal?.goalId,
            )
        }
    }

    override suspend fun editBag(bag: Bag) {
        bagQueries.transaction {
            bagQueries.update(
                bagName = bag.bagName,
                bagAmount = bag.bagAmount,
                goalId = bag.goal?.goalId,
                bagId = bag.bagId
            )
        }
    }
}
