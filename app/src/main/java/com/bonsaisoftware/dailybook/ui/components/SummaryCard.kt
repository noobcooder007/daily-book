package com.bonsaisoftware.dailybook.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SummaryCard(total: String, label: String, currency: String, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .clickable { onClick() },
        shape = CutCornerShape(topStart = 12.dp, bottomEnd = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "$$total", style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = label, style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Text(text = currency)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SummaryCardPreview() {
    SummaryCard(total = "5000", label = "Balance actual", currency = "MXN")
}