package com.gauvain.seigneur.design

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp


/**
 * This file is about defining the shape attributes of all component thanks to the theme
 * Go on this page to check widgets that supports Shape attributes : https://material.io/develop/android/theming/shape
 */
// for what
private val extraSmallComponentShape = RoundedCornerShape(4.dp)

// Button, Toggle Button, EditText
private val smallComponentShape = RoundedCornerShape(4.dp)

// Dialog, DatePicker, Card
private val mediumComponentShape = RoundedCornerShape(8.dp)

// NavigationDrawer, BottomSheet
private val largeComponentShape = RoundedCornerShape(0.dp)

/**
 * Default shapes from all types of components
 * Can be override when we create a @composable
 */
internal val Shapes = Shapes(
    extraSmall = extraSmallComponentShape,
    small = smallComponentShape,
    medium = mediumComponentShape,
    large = largeComponentShape
)


