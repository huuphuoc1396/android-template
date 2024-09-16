package com.android.template.compose.components.error

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.template.R

@Composable
fun ErrorDialog(
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        shape = RoundedCornerShape(16.dp),
        onDismissRequest = onDismiss,
        modifier = modifier,
        confirmButton = { TextButton(onClick = onDismiss) { Text(text = stringResource(id = R.string.ok)) } },
        title = { Text(text = stringResource(id = R.string.error_title)) },
        text = { Text(text = message) },
    )
}

@Preview
@Composable
internal fun ErrorDialogPreview() {
    ErrorDialog(
        message = "Error message",
        onDismiss = {},
    )
}