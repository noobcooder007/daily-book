package com.bonsaisoftware.dailybook.data

import com.bonsaisoftware.dailybook.db.Database
import com.bonsaisoftware.dailybook.db.GoalQueries
import com.bonsaisoftware.dailybook.domain.GoalRepository
import com.bonsaisoftware.dailybook.model.Goal
import com.bonsaisoftware.dailybook.model.GoalStatus
import java.util.Date

class GoalRepoImpl(database: Database): GoalRepository {
    private val goalQueries: GoalQueries = database.goalQueries
    override fun getAllGoals(): List<Goal> {
        return goalQueries.selectAll().executeAsList().map {
            Goal(
                goalId = it.goalId,
                goalName = it.goalName,
                goalAmount = it.goalAmount,
                goalDate = Date(it.goalDate),
                goalStatus = GoalStatus.valueOf(it.goalStatus),
                goalIsActive = it.goalIsActive == 1L
            )
        }
    }

    override fun addGoal(goal: Goal) {
        goalQueries.transaction {
            goalQueries.insert(
                goalName = goal.goalName,
                goalAmount = goal.goalAmount,
                goalDate = goal.goalDate.toString(),
                goalStatus = goal.goalStatus.name,
                goalIsActive = if (goal.goalIsActive) 1L else 0L
            )
        }
    }

    override fun editGoal(goal: Goal) {
        goalQueries.transaction {
            goalQueries.update(
                goalName = goal.goalName,
                goalAmount = goal.goalAmount,
                goalStatus = goal.goalStatus.name,
                goalId = goal.goalId
            )
        }
    }

    override fun deleteGoal(goal: Goal) {
        goalQueries.transaction {
            goalQueries.delete(goalId = goal.goalId)
        }
    }

    override fun getGoalWithID(id: Long): Goal? {
        val goal = goalQueries.selectById(id).executeAsOneOrNull()
        return goal?.let {
            Goal(
                goalId = it.goalId,
                goalName = it.goalName,
                goalAmount = it.goalAmount,
                goalDate = Date(it.goalDate),
                goalStatus = GoalStatus.valueOf(it.goalStatus),
                goalIsActive = it.goalIsActive == 1L
            )
        }
    }

}