package com.yourfiles.manager.presentation.ui.pages

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteSweep
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yourfiles.manager.R
import com.yourfiles.manager.presentation.ui.components.BackNavigationIconCompose

private const val PREFS_NAME = "yourfiles_settings"
private const val KEY_CONFIRM_BEFORE_DELETE = "confirm_before_delete"
private const val KEY_DARK_MODE = "dark_mode"

/**
 * Settings page for the Your Files app.
 *
 * Provides toggles for confirming before delete and dark mode, along with
 * informational cards for About, Privacy, and Version.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPage() {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) }

    // ── Persisted states ─────────────────────────────────────────────────────
    var confirmBeforeDelete by remember {
        mutableStateOf(prefs.getBoolean(KEY_CONFIRM_BEFORE_DELETE, true))
    }
    val systemDarkMode = isSystemInDarkTheme()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = try { stringResource(R.string.settings) }
                            catch (_: Exception) { "Settings" }
                    )
                },
                navigationIcon = { BackNavigationIconCompose() }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            // ── Behaviour Section ──────────────────────────────────────────
            SettingsSectionHeader(title = "Behaviour")

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                ),
            ) {
                SettingsSwitchRow(
                    icon = Icons.Outlined.DeleteSweep,
                    title = "Confirm before delete",
                    subtitle = "Show a confirmation dialog before deleting files",
                    checked = confirmBeforeDelete,
                    onCheckedChange = { checked ->
                        confirmBeforeDelete = checked
                        prefs.edit().putBoolean(KEY_CONFIRM_BEFORE_DELETE, checked).apply()
                    },
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ── Appearance Section ─────────────────────────────────────────
            SettingsSectionHeader(title = "Appearance")

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                ),
            ) {
                SettingsSwitchRow(
                    icon = Icons.Outlined.DarkMode,
                    title = "Dark Mode",
                    subtitle = when {
                        systemDarkMode -> "Currently active (following system)"
                        else -> "Currently inactive (following system)"
                    },
                    checked = systemDarkMode,
                    onCheckedChange = { /* Follows system – read only */ },
                    enabled = false,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ── About Section ──────────────────────────────────────────────
            SettingsSectionHeader(title = "About")

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                ),
            ) {
                SettingsInfoRow(
                    icon = Icons.Outlined.Info,
                    title = "Your Apps Suite",
                    description = "Part of Your Apps suite \u2013 YourNotes, Your Gallery, Your Files"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ── Privacy Section ───────────────────────────────────────────
            SettingsSectionHeader(title = "Privacy")

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                ),
            ) {
                SettingsInfoRow(
                    icon = Icons.Outlined.Shield,
                    title = "Privacy Policy",
                    description = "No data collected, all processing on-device"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ── Version Section ────────────────────────────────────────────
            SettingsSectionHeader(title = "Version")

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                ),
            ) {
                SettingsInfoRow(
                    icon = Icons.Outlined.VerifiedUser,
                    title = "Your Files",
                    description = "Your Files v1.0.0"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// ────────────────────────────────────────────────────────────────────────────────
// Private helper composables
// ────────────────────────────────────────────────────────────────────────────────

@Composable
private fun SettingsSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
    )
}

@Composable
private fun SettingsSwitchRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(end = 16.dp),
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
        )
    }
}

@Composable
private fun SettingsInfoRow(
    icon: ImageVector,
    title: String,
    description: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(end = 16.dp),
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
