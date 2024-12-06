package com.bonsaisoftware.dailybook.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.bonsaisoftware.dailybook.model.CreditCard
import com.bonsaisoftware.dailybook.model.Debt
import com.bonsaisoftware.dailybook.util.currencyFormat
import java.util.Date

@Composable
fun DebtListItem(debt: Debt, onItemClick: (debtId: Long) -> Unit = {}) {
    ListItem(modifier = Modifier.clickable { onItemClick(debt.debtId) },
        headlineContent = {
            Text(
                text = debt.debtName, style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            )
        },
        trailingContent = {
            Text(
                text = currencyFormat(debt.debtAmount.toBigDecimal()), style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
        },
        supportingContent = {
            Text(
                text = "${debt.debtDate} - ${debt.debtCreditCard.name}",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                )
            )
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun DebtListItemPreview() {
//    DebtListItem(
//        debt = Debt(
//            debtId = 1,
//            debtName = "Comida",
//            debtAmount = 10000,
//            debtDate = Date(),
//            debtCreditCard = CreditCard.BBVA,
//            debtIsActive = true
//        )
//    )
//}