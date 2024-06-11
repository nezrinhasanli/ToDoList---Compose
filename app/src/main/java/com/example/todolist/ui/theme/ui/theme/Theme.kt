package com.example.compose
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.todolist.ui.theme.ui.theme.backgroundDark
import com.example.todolist.ui.theme.ui.theme.backgroundDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.backgroundDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.backgroundLight
import com.example.todolist.ui.theme.ui.theme.backgroundLightHighContrast
import com.example.todolist.ui.theme.ui.theme.backgroundLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.errorContainerDark
import com.example.todolist.ui.theme.ui.theme.errorContainerDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.errorContainerDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.errorContainerLight
import com.example.todolist.ui.theme.ui.theme.errorContainerLightHighContrast
import com.example.todolist.ui.theme.ui.theme.errorContainerLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.errorDark
import com.example.todolist.ui.theme.ui.theme.errorDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.errorDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.errorLight
import com.example.todolist.ui.theme.ui.theme.errorLightHighContrast
import com.example.todolist.ui.theme.ui.theme.errorLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.inverseOnSurfaceDark
import com.example.todolist.ui.theme.ui.theme.inverseOnSurfaceDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.inverseOnSurfaceLight
import com.example.todolist.ui.theme.ui.theme.inverseOnSurfaceLightHighContrast
import com.example.todolist.ui.theme.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.inversePrimaryDark
import com.example.todolist.ui.theme.ui.theme.inversePrimaryDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.inversePrimaryDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.inversePrimaryLight
import com.example.todolist.ui.theme.ui.theme.inversePrimaryLightHighContrast
import com.example.todolist.ui.theme.ui.theme.inversePrimaryLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.inverseSurfaceDark
import com.example.todolist.ui.theme.ui.theme.inverseSurfaceDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.inverseSurfaceDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.inverseSurfaceLight
import com.example.todolist.ui.theme.ui.theme.inverseSurfaceLightHighContrast
import com.example.todolist.ui.theme.ui.theme.inverseSurfaceLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.onBackgroundDark
import com.example.todolist.ui.theme.ui.theme.onBackgroundDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.onBackgroundDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.onBackgroundLight
import com.example.todolist.ui.theme.ui.theme.onBackgroundLightHighContrast
import com.example.todolist.ui.theme.ui.theme.onBackgroundLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.onErrorContainerDark
import com.example.todolist.ui.theme.ui.theme.onErrorContainerDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.onErrorContainerDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.onErrorContainerLight
import com.example.todolist.ui.theme.ui.theme.onErrorContainerLightHighContrast
import com.example.todolist.ui.theme.ui.theme.onErrorContainerLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.onErrorDark
import com.example.todolist.ui.theme.ui.theme.onErrorDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.onErrorDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.onErrorLight
import com.example.todolist.ui.theme.ui.theme.onErrorLightHighContrast
import com.example.todolist.ui.theme.ui.theme.onErrorLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.onPrimaryContainerDark
import com.example.todolist.ui.theme.ui.theme.onPrimaryContainerDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.onPrimaryContainerDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.onPrimaryContainerLight
import com.example.todolist.ui.theme.ui.theme.onPrimaryContainerLightHighContrast
import com.example.todolist.ui.theme.ui.theme.onPrimaryContainerLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.onPrimaryDark
import com.example.todolist.ui.theme.ui.theme.onPrimaryDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.onPrimaryDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.onPrimaryLight
import com.example.todolist.ui.theme.ui.theme.onPrimaryLightHighContrast
import com.example.todolist.ui.theme.ui.theme.onPrimaryLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.onSecondaryContainerDark
import com.example.todolist.ui.theme.ui.theme.onSecondaryContainerDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.onSecondaryContainerDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.onSecondaryContainerLight
import com.example.todolist.ui.theme.ui.theme.onSecondaryContainerLightHighContrast
import com.example.todolist.ui.theme.ui.theme.onSecondaryContainerLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.onSecondaryDark
import com.example.todolist.ui.theme.ui.theme.onSecondaryDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.onSecondaryDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.onSecondaryLight
import com.example.todolist.ui.theme.ui.theme.onSecondaryLightHighContrast
import com.example.todolist.ui.theme.ui.theme.onSecondaryLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.onSurfaceDark
import com.example.todolist.ui.theme.ui.theme.onSurfaceDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.onSurfaceDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.onSurfaceLight
import com.example.todolist.ui.theme.ui.theme.onSurfaceLightHighContrast
import com.example.todolist.ui.theme.ui.theme.onSurfaceLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.onSurfaceVariantDark
import com.example.todolist.ui.theme.ui.theme.onSurfaceVariantDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.onSurfaceVariantDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.onSurfaceVariantLight
import com.example.todolist.ui.theme.ui.theme.onSurfaceVariantLightHighContrast
import com.example.todolist.ui.theme.ui.theme.onSurfaceVariantLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.onTertiaryContainerDark
import com.example.todolist.ui.theme.ui.theme.onTertiaryContainerDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.onTertiaryContainerDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.onTertiaryContainerLight
import com.example.todolist.ui.theme.ui.theme.onTertiaryContainerLightHighContrast
import com.example.todolist.ui.theme.ui.theme.onTertiaryContainerLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.onTertiaryDark
import com.example.todolist.ui.theme.ui.theme.onTertiaryDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.onTertiaryDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.onTertiaryLight
import com.example.todolist.ui.theme.ui.theme.onTertiaryLightHighContrast
import com.example.todolist.ui.theme.ui.theme.onTertiaryLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.outlineDark
import com.example.todolist.ui.theme.ui.theme.outlineDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.outlineDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.outlineLight
import com.example.todolist.ui.theme.ui.theme.outlineLightHighContrast
import com.example.todolist.ui.theme.ui.theme.outlineLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.outlineVariantDark
import com.example.todolist.ui.theme.ui.theme.outlineVariantDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.outlineVariantDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.outlineVariantLight
import com.example.todolist.ui.theme.ui.theme.outlineVariantLightHighContrast
import com.example.todolist.ui.theme.ui.theme.outlineVariantLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.primaryContainerDark
import com.example.todolist.ui.theme.ui.theme.primaryContainerDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.primaryContainerDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.primaryContainerLight
import com.example.todolist.ui.theme.ui.theme.primaryContainerLightHighContrast
import com.example.todolist.ui.theme.ui.theme.primaryContainerLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.primaryDark
import com.example.todolist.ui.theme.ui.theme.primaryDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.primaryDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.primaryLight
import com.example.todolist.ui.theme.ui.theme.primaryLightHighContrast
import com.example.todolist.ui.theme.ui.theme.primaryLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.scrimDark
import com.example.todolist.ui.theme.ui.theme.scrimDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.scrimDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.scrimLight
import com.example.todolist.ui.theme.ui.theme.scrimLightHighContrast
import com.example.todolist.ui.theme.ui.theme.scrimLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.secondaryContainerDark
import com.example.todolist.ui.theme.ui.theme.secondaryContainerDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.secondaryContainerDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.secondaryContainerLight
import com.example.todolist.ui.theme.ui.theme.secondaryContainerLightHighContrast
import com.example.todolist.ui.theme.ui.theme.secondaryContainerLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.secondaryDark
import com.example.todolist.ui.theme.ui.theme.secondaryDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.secondaryDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.secondaryLight
import com.example.todolist.ui.theme.ui.theme.secondaryLightHighContrast
import com.example.todolist.ui.theme.ui.theme.secondaryLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceBrightDark
import com.example.todolist.ui.theme.ui.theme.surfaceBrightDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceBrightDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceBrightLight
import com.example.todolist.ui.theme.ui.theme.surfaceBrightLightHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceBrightLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerDark
import com.example.todolist.ui.theme.ui.theme.surfaceContainerDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerHighDark
import com.example.todolist.ui.theme.ui.theme.surfaceContainerHighDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerHighDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerHighLight
import com.example.todolist.ui.theme.ui.theme.surfaceContainerHighLightHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerHighLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerHighestDark
import com.example.todolist.ui.theme.ui.theme.surfaceContainerHighestDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerHighestLight
import com.example.todolist.ui.theme.ui.theme.surfaceContainerHighestLightHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerHighestLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerLight
import com.example.todolist.ui.theme.ui.theme.surfaceContainerLightHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerLowDark
import com.example.todolist.ui.theme.ui.theme.surfaceContainerLowDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerLowDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerLowLight
import com.example.todolist.ui.theme.ui.theme.surfaceContainerLowLightHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerLowLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerLowestDark
import com.example.todolist.ui.theme.ui.theme.surfaceContainerLowestDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerLowestLight
import com.example.todolist.ui.theme.ui.theme.surfaceContainerLowestLightHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceContainerLowestLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceDark
import com.example.todolist.ui.theme.ui.theme.surfaceDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceDimDark
import com.example.todolist.ui.theme.ui.theme.surfaceDimDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceDimDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceDimLight
import com.example.todolist.ui.theme.ui.theme.surfaceDimLightHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceDimLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceLight
import com.example.todolist.ui.theme.ui.theme.surfaceLightHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceVariantDark
import com.example.todolist.ui.theme.ui.theme.surfaceVariantDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceVariantDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.surfaceVariantLight
import com.example.todolist.ui.theme.ui.theme.surfaceVariantLightHighContrast
import com.example.todolist.ui.theme.ui.theme.surfaceVariantLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.tertiaryContainerDark
import com.example.todolist.ui.theme.ui.theme.tertiaryContainerDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.tertiaryContainerDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.tertiaryContainerLight
import com.example.todolist.ui.theme.ui.theme.tertiaryContainerLightHighContrast
import com.example.todolist.ui.theme.ui.theme.tertiaryContainerLightMediumContrast
import com.example.todolist.ui.theme.ui.theme.tertiaryDark
import com.example.todolist.ui.theme.ui.theme.tertiaryDarkHighContrast
import com.example.todolist.ui.theme.ui.theme.tertiaryDarkMediumContrast
import com.example.todolist.ui.theme.ui.theme.tertiaryLight
import com.example.todolist.ui.theme.ui.theme.tertiaryLightHighContrast
import com.example.todolist.ui.theme.ui.theme.tertiaryLightMediumContrast
import com.example.ui.theme.AppTypography

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun ToDoListTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = colorScheme.primary.toArgb()
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
    }
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}

