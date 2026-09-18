package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = Color.White) {
                    TipCalculatorScreen()
                }
            }
        }
    }
}

// Colors matching the reference screenshot
private val PinkFieldBg = Color(0xFFF4D9DE)
private val PinkFieldLine = Color(0xFFD68A9A)
private val PinkFieldText = Color(0xFFB23A54)
private val KeyAltBg = Color(0xFFE7EDFB)
private val KeyClearBg = Color(0xFFDCD0F7)
private val KeyConfirmBg = Color(0xFF3A8EE6)
private val TextMain = Color(0xFF1A1A1A)

@Composable
fun TipCalculatorScreen() {
    var billText by remember { mutableStateOf("0") }
    val tipRatePercent = 15.0 // fixed 15% tip, matching the reference screenshot

    val billValue = billText.toDoubleOrNull() ?: 0.0
    val tipAmount = billValue * (tipRatePercent / 100.0)
    val currencyFormat = remember { DecimalFormat("$#,##0.00") }

    fun appendDigit(digit: String) {
        billText = when {
            billText == "0" && digit != "." -> digit
            digit == "." && billText.contains(".") -> billText
            else -> billText + digit
        }
    }

    fun backspace() {
        billText = billText.dropLast(1)
        if (billText.isEmpty()) billText = "0"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Content area
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Calculate Tip",
                fontSize = 13.sp,
                color = TextMain
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Pink bill amount field
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                    .background(PinkFieldBg)
                    .padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "Bill Amount",
                    fontSize = 12.sp,
                    color = PinkFieldText
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = billText,
                    fontSize = 20.sp,
                    color = TextMain
                )
            }
            // underline
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(PinkFieldLine)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Tip Amount: ${currencyFormat.format(tipAmount)}",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = TextMain
            )
        }

        // Keypad
        Keypad(
            onDigit = { appendDigit(it) },
            onBackspace = { backspace() },
            onConfirm = { /* no-op: value already live-updates */ }
        )
    }
}

@Composable
fun Keypad(
    onDigit: (String) -> Unit,
    onBackspace: () -> Unit,
    onConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        KeyRow {
            DigitKey("1", Modifier.weight(1f), onDigit)
            DigitKey("2", Modifier.weight(1f), onDigit)
            DigitKey("3", Modifier.weight(1f), onDigit)
            FuncKey("\u2212", Modifier.weight(1f)) { /* minus: no-op */ }
        }
        KeyRow {
            DigitKey("4", Modifier.weight(1f), onDigit)
            DigitKey("5", Modifier.weight(1f), onDigit)
            DigitKey("6", Modifier.weight(1f), onDigit)
            FuncKey("\u23CE", Modifier.weight(1f)) { onConfirm() }
        }
        KeyRow {
            DigitKey("7", Modifier.weight(1f), onDigit)
            DigitKey("8", Modifier.weight(1f), onDigit)
            DigitKey("9", Modifier.weight(1f), onDigit)
            ClearKey(Modifier.weight(1f)) { onBackspace() }
        }
        KeyRow {
            DigitKey(",", Modifier.weight(1f)) { /* thousands separator: no-op */ }
            DigitKey("0", Modifier.weight(1f), onDigit)
            DigitKey(".", Modifier.weight(1f), onDigit)
            ConfirmKey(Modifier.weight(1f)) { onConfirm() }
        }
    }
}

@Composable
private fun KeyRow(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        content = content
    )
}

@Composable
private fun DigitKey(label: String, modifier: Modifier, onClick: (String) -> Unit) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(Color.White)
            .clickable { onClick(label) },
        contentAlignment = Alignment.Center
    ) {
        Text(text = label, fontSize = 22.sp, color = TextMain)
    }
}

@Composable
private fun FuncKey(label: String, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(KeyAltBg)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = label, fontSize = 20.sp, color = TextMain)
    }
}

@Composable
private fun ClearKey(modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(KeyClearBg)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = "\u232B", fontSize = 20.sp, color = TextMain)
    }
}

@Composable
private fun ConfirmKey(modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(KeyConfirmBg)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = "\u2713", fontSize = 20.sp, color = Color.White)
    }
}