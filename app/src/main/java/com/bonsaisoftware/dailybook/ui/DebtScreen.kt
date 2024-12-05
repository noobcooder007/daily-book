package com.bonsaisoftware.dailybook.ui

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bonsaisoftware.dailybook.model.CreditCard
import com.bonsaisoftware.dailybook.model.Debt
import com.bonsaisoftware.dailybook.presentation.DebtsUiState
import com.bonsaisoftware.dailybook.ui.components.CustomTopBar
import com.bonsaisoftware.dailybook.ui.components.DebtsList
import com.bonsaisoftware.dailybook.ui.components.FAB
import java.util.Date

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DebtScreen(
    uiState: DebtsUiState,
    onCanBackClick: Boolean = false,
    onBackClick: () -> Unit = {},
    onFabClick: (debtId: Long) -> Unit = {},
    onItemClick: (debtId: Long) -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                onCanBackClick = onCanBackClick,
                onBackClick = onBackClick,
                title = "Debts"
            )
        },
        floatingActionButton = {
            FAB(icon = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add debt"
                )
            }, onClick = { onFabClick(-1L) })
        }
    ) {
        DebtsList(
            innerPadding = it,
            uiState = uiState,
            onItemClick = onItemClick,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun EmptyDebtScreenPreview() {
//    DebtScreen(
//        uiState = DebtsUiState(
//            debts = emptyList(),
//            total = 0
//        ),
//        onCanBackClick = true,
//        onBackClick = {}
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun DebtScreenPreview() {
//    DebtScreen(
//        uiState = DebtsUiState(
//            debts = listOf(
//                Debt(
//                    debtId = 1,
//                    debtName = "Comida",
//                    debtAmount = 10000,
//                    debtDate = Date(),
//                    debtCreditCard = CreditCard.BBVA,
//                    debtIsActive = true
//                )
//            ),
//            total = 10000
//        )
//    )
//}
