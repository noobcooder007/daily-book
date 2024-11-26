package com.bonsaisoftware.dailybook.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bonsaisoftware.dailybook.model.CreditCard
import com.bonsaisoftware.dailybook.model.Debt
import com.bonsaisoftware.dailybook.ui.components.CustomTopBar
import com.bonsaisoftware.dailybook.ui.components.DebtForm
import java.util.Date

@Composable
fun AddOrEditDebtScreen(
    debt: Debt? = null,
    onCanBackClick: Boolean = false,
    onBackClick: () -> Unit = {},
    onSaveChanges: (Debt) -> Unit = {}
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                onCanBackClick = onCanBackClick,
                onBackClick = { onBackClick() },
                title = debt.let {
                    if (it == null) {
                        "Add debt"
                    } else {
                        "Edit debt"
                    }
                }
            )
        }
    ) { innerPadding ->
        DebtForm(debt = debt, innerPadding = innerPadding, onSaveChanges = onSaveChanges)
    }
}

@Preview(showBackground = true)
@Composable
fun EditDebtScreenPreview() {
    AddOrEditDebtScreen(
        debt = Debt(
            debtId = 1L,
            debtName = "Test",
            debtAmount = 100,
            debtIsActive = true,
            debtDate = Date(),
            debtCreditCard = CreditCard.MERCADOPAGO
        ),
        onCanBackClick = true,
        onBackClick = {},
        onSaveChanges = {},
    )
}

@Preview(showBackground = true)
@Composable
fun AddDebtScreenPreview() {
    AddOrEditDebtScreen(
        debt = null,
        onCanBackClick = true,
        onBackClick = {},
        onSaveChanges = {},
    )
}
