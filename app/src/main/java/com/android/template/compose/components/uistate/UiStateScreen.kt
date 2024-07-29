package com.android.template.compose.components.uistate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.android.template.compose.components.error.ErrorDialog
import com.android.template.compose.components.loading.Loading
import com.android.template.compose.uistate.mappers.toReadableMessage
import com.android.template.compose.uistate.viewmodel.UiStateViewModel

@Composable
fun <UiState, Event> UiStateScreen(
    viewModel: UiStateViewModel<UiState, Event>,
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
    lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    onEvent: (Event) -> Unit = {},
    content: @Composable (uiState: UiState) -> Unit,
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val error by viewModel.error.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        content(uiState)
        AnimatedVisibility(
            visible = isLoading,
            enter = fadeIn(),
            exit = fadeOut(),
            content = { Loading() },
        )
        if (error.hasError()) {
            ErrorDialog(
                message = error.throwable.toReadableMessage(context),
                onDismiss = viewModel::hideError,
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.singleEvent.flowWithLifecycle(
            lifecycle = lifecycle,
            minActiveState = lifecycleState,
        ).collect { event ->
            onEvent(event)
        }
    }
}