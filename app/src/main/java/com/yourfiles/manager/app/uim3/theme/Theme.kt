package com.yourfiles.manager.app.uim3.theme
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.yourfiles.manager.app.uim3.theme.backgroundDark
import com.yourfiles.manager.app.uim3.theme.backgroundDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.backgroundDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.backgroundLight
import com.yourfiles.manager.app.uim3.theme.backgroundLightHighContrast
import com.yourfiles.manager.app.uim3.theme.backgroundLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.errorContainerDark
import com.yourfiles.manager.app.uim3.theme.errorContainerDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.errorContainerDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.errorContainerLight
import com.yourfiles.manager.app.uim3.theme.errorContainerLightHighContrast
import com.yourfiles.manager.app.uim3.theme.errorContainerLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.errorDark
import com.yourfiles.manager.app.uim3.theme.errorDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.errorDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.errorLight
import com.yourfiles.manager.app.uim3.theme.errorLightHighContrast
import com.yourfiles.manager.app.uim3.theme.errorLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.inverseOnSurfaceDark
import com.yourfiles.manager.app.uim3.theme.inverseOnSurfaceDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.inverseOnSurfaceDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.inverseOnSurfaceLight
import com.yourfiles.manager.app.uim3.theme.inverseOnSurfaceLightHighContrast
import com.yourfiles.manager.app.uim3.theme.inverseOnSurfaceLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.inversePrimaryDark
import com.yourfiles.manager.app.uim3.theme.inversePrimaryDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.inversePrimaryDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.inversePrimaryLight
import com.yourfiles.manager.app.uim3.theme.inversePrimaryLightHighContrast
import com.yourfiles.manager.app.uim3.theme.inversePrimaryLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.inverseSurfaceDark
import com.yourfiles.manager.app.uim3.theme.inverseSurfaceDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.inverseSurfaceDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.inverseSurfaceLight
import com.yourfiles.manager.app.uim3.theme.inverseSurfaceLightHighContrast
import com.yourfiles.manager.app.uim3.theme.inverseSurfaceLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.onBackgroundDark
import com.yourfiles.manager.app.uim3.theme.onBackgroundDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.onBackgroundDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.onBackgroundLight
import com.yourfiles.manager.app.uim3.theme.onBackgroundLightHighContrast
import com.yourfiles.manager.app.uim3.theme.onBackgroundLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.onErrorContainerDark
import com.yourfiles.manager.app.uim3.theme.onErrorContainerDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.onErrorContainerDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.onErrorContainerLight
import com.yourfiles.manager.app.uim3.theme.onErrorContainerLightHighContrast
import com.yourfiles.manager.app.uim3.theme.onErrorContainerLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.onErrorDark
import com.yourfiles.manager.app.uim3.theme.onErrorDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.onErrorDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.onErrorLight
import com.yourfiles.manager.app.uim3.theme.onErrorLightHighContrast
import com.yourfiles.manager.app.uim3.theme.onErrorLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.onPrimaryContainerDark
import com.yourfiles.manager.app.uim3.theme.onPrimaryContainerDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.onPrimaryContainerDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.onPrimaryContainerLight
import com.yourfiles.manager.app.uim3.theme.onPrimaryContainerLightHighContrast
import com.yourfiles.manager.app.uim3.theme.onPrimaryContainerLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.onPrimaryDark
import com.yourfiles.manager.app.uim3.theme.onPrimaryDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.onPrimaryDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.onPrimaryLight
import com.yourfiles.manager.app.uim3.theme.onPrimaryLightHighContrast
import com.yourfiles.manager.app.uim3.theme.onPrimaryLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.onSecondaryContainerDark
import com.yourfiles.manager.app.uim3.theme.onSecondaryContainerDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.onSecondaryContainerDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.onSecondaryContainerLight
import com.yourfiles.manager.app.uim3.theme.onSecondaryContainerLightHighContrast
import com.yourfiles.manager.app.uim3.theme.onSecondaryContainerLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.onSecondaryDark
import com.yourfiles.manager.app.uim3.theme.onSecondaryDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.onSecondaryDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.onSecondaryLight
import com.yourfiles.manager.app.uim3.theme.onSecondaryLightHighContrast
import com.yourfiles.manager.app.uim3.theme.onSecondaryLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.onSurfaceDark
import com.yourfiles.manager.app.uim3.theme.onSurfaceDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.onSurfaceDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.onSurfaceLight
import com.yourfiles.manager.app.uim3.theme.onSurfaceLightHighContrast
import com.yourfiles.manager.app.uim3.theme.onSurfaceLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.onSurfaceVariantDark
import com.yourfiles.manager.app.uim3.theme.onSurfaceVariantDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.onSurfaceVariantDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.onSurfaceVariantLight
import com.yourfiles.manager.app.uim3.theme.onSurfaceVariantLightHighContrast
import com.yourfiles.manager.app.uim3.theme.onSurfaceVariantLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.onTertiaryContainerDark
import com.yourfiles.manager.app.uim3.theme.onTertiaryContainerDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.onTertiaryContainerDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.onTertiaryContainerLight
import com.yourfiles.manager.app.uim3.theme.onTertiaryContainerLightHighContrast
import com.yourfiles.manager.app.uim3.theme.onTertiaryContainerLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.onTertiaryDark
import com.yourfiles.manager.app.uim3.theme.onTertiaryDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.onTertiaryDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.onTertiaryLight
import com.yourfiles.manager.app.uim3.theme.onTertiaryLightHighContrast
import com.yourfiles.manager.app.uim3.theme.onTertiaryLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.outlineDark
import com.yourfiles.manager.app.uim3.theme.outlineDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.outlineDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.outlineLight
import com.yourfiles.manager.app.uim3.theme.outlineLightHighContrast
import com.yourfiles.manager.app.uim3.theme.outlineLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.outlineVariantDark
import com.yourfiles.manager.app.uim3.theme.outlineVariantDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.outlineVariantDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.outlineVariantLight
import com.yourfiles.manager.app.uim3.theme.outlineVariantLightHighContrast
import com.yourfiles.manager.app.uim3.theme.outlineVariantLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.primaryContainerDark
import com.yourfiles.manager.app.uim3.theme.primaryContainerDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.primaryContainerDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.primaryContainerLight
import com.yourfiles.manager.app.uim3.theme.primaryContainerLightHighContrast
import com.yourfiles.manager.app.uim3.theme.primaryContainerLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.primaryDark
import com.yourfiles.manager.app.uim3.theme.primaryDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.primaryDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.primaryLight
import com.yourfiles.manager.app.uim3.theme.primaryLightHighContrast
import com.yourfiles.manager.app.uim3.theme.primaryLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.scrimDark
import com.yourfiles.manager.app.uim3.theme.scrimDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.scrimDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.scrimLight
import com.yourfiles.manager.app.uim3.theme.scrimLightHighContrast
import com.yourfiles.manager.app.uim3.theme.scrimLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.secondaryContainerDark
import com.yourfiles.manager.app.uim3.theme.secondaryContainerDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.secondaryContainerDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.secondaryContainerLight
import com.yourfiles.manager.app.uim3.theme.secondaryContainerLightHighContrast
import com.yourfiles.manager.app.uim3.theme.secondaryContainerLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.secondaryDark
import com.yourfiles.manager.app.uim3.theme.secondaryDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.secondaryDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.secondaryLight
import com.yourfiles.manager.app.uim3.theme.secondaryLightHighContrast
import com.yourfiles.manager.app.uim3.theme.secondaryLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceBrightDark
import com.yourfiles.manager.app.uim3.theme.surfaceBrightDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceBrightDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceBrightLight
import com.yourfiles.manager.app.uim3.theme.surfaceBrightLightHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceBrightLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerDark
import com.yourfiles.manager.app.uim3.theme.surfaceContainerDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerHighDark
import com.yourfiles.manager.app.uim3.theme.surfaceContainerHighDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerHighDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerHighLight
import com.yourfiles.manager.app.uim3.theme.surfaceContainerHighLightHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerHighLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerHighestDark
import com.yourfiles.manager.app.uim3.theme.surfaceContainerHighestDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerHighestDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerHighestLight
import com.yourfiles.manager.app.uim3.theme.surfaceContainerHighestLightHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerHighestLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerLight
import com.yourfiles.manager.app.uim3.theme.surfaceContainerLightHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerLowDark
import com.yourfiles.manager.app.uim3.theme.surfaceContainerLowDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerLowDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerLowLight
import com.yourfiles.manager.app.uim3.theme.surfaceContainerLowLightHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerLowLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerLowestDark
import com.yourfiles.manager.app.uim3.theme.surfaceContainerLowestDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerLowestDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerLowestLight
import com.yourfiles.manager.app.uim3.theme.surfaceContainerLowestLightHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceContainerLowestLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceDark
import com.yourfiles.manager.app.uim3.theme.surfaceDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceDimDark
import com.yourfiles.manager.app.uim3.theme.surfaceDimDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceDimDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceDimLight
import com.yourfiles.manager.app.uim3.theme.surfaceDimLightHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceDimLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceLight
import com.yourfiles.manager.app.uim3.theme.surfaceLightHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceVariantDark
import com.yourfiles.manager.app.uim3.theme.surfaceVariantDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceVariantDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.surfaceVariantLight
import com.yourfiles.manager.app.uim3.theme.surfaceVariantLightHighContrast
import com.yourfiles.manager.app.uim3.theme.surfaceVariantLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.tertiaryContainerDark
import com.yourfiles.manager.app.uim3.theme.tertiaryContainerDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.tertiaryContainerDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.tertiaryContainerLight
import com.yourfiles.manager.app.uim3.theme.tertiaryContainerLightHighContrast
import com.yourfiles.manager.app.uim3.theme.tertiaryContainerLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.tertiaryDark
import com.yourfiles.manager.app.uim3.theme.tertiaryDarkHighContrast
import com.yourfiles.manager.app.uim3.theme.tertiaryDarkMediumContrast
import com.yourfiles.manager.app.uim3.theme.tertiaryLight
import com.yourfiles.manager.app.uim3.theme.tertiaryLightHighContrast
import com.yourfiles.manager.app.uim3.theme.tertiaryLightMediumContrast
import com.yourfiles.manager.app.uim3.theme.AppTypography

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
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
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

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}

