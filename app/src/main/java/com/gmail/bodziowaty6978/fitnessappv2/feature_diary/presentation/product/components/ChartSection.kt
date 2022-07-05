package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product.components

import android.graphics.Color
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.formatter.PercentFormatter
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.BlueViolet3
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.DarkGreyElevation3
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.LightGreen3
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.OrangeYellow3

@Composable
fun ChartSection(
    nutritionData: NutritionData,
) {

    val backgroundColor = DarkGreyElevation3.toArgb()

    val colors = listOf<Int>(
        BlueViolet3.toArgb(),
        backgroundColor,
        OrangeYellow3.toArgb(),
        backgroundColor,
        LightGreen3.toArgb(),
        backgroundColor
    )

    AndroidView(
        modifier = Modifier.size(100.dp),
        factory = { context ->

        PieChart(context).apply {
            setUsePercentValues(true)
            description.isEnabled = false
            dragDecelerationFrictionCoef = 0.95f

            extraRightOffset = 5F

            isDrawHoleEnabled = true

            minAngleForSlices = 5F

            setTransparentCircleColor(Color.WHITE)
            setTransparentCircleAlpha(110)

            isClickable = false
            isFocusable = false
            isContextClickable = false

            holeRadius = 82f
            setHoleColor(backgroundColor)

            setCenterTextColor(Color.WHITE)
            setCenterTextSize(14F)
            transparentCircleRadius = 0f

            setDrawCenterText(true)

            setEntryLabelColor(Color.WHITE)
            setEntryLabelTextSize(0f)

            val legend = this.legend
            legend.apply {
                isEnabled = false
                textColor = Color.WHITE
                textSize = 10F

            }

        }
    },
        update = {
            val data = nutritionData.pieEntries

            val calories = nutritionData.nutritionValues.calories

            val dataSet = PieDataSet(data, "")
            dataSet.colors = colors

            val pieData = PieData(dataSet).apply {
                setDrawValues(false)
                setValueFormatter(PercentFormatter(it))
                setValueTextColor(Color.WHITE)
                setValueTextSize(10f)
            }

            it.centerText = "$calories kcal"
            it.data = pieData
            it.invalidate()
        }
    )
}