package com.bonsaisoftware.dailybook.data

import com.bonsaisoftware.dailybook.domain.GoalRepository
import com.bonsaisoftware.dailybook.model.Goal

class GoalRepoImpl(private val goalManager: GoalManager): GoalRepository {
    override fun getAllGoals(): List<Goal> {
        return goalManager.goals
    }

    override fun addGoal(goal: Goal) {
        goalManager.addGoal(goal)
    }

    override fun editGoal(goal: Goal) {
        goalManager.editGoal(goal)
    }

}