package org.example.journey.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PermissionsViewModel(
    private val controller: PermissionsController
): ViewModel() {
    private val _state = MutableStateFlow(PermissionState.NotDetermined)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = controller.getPermissionState(Permission.GALLERY)
        }
    }

    fun provideOrRequestGalleryPermission() {
        viewModelScope.launch {
            try {
                controller.providePermission(Permission.GALLERY)
                _state.value = PermissionState.Granted
            } catch(e: DeniedAlwaysException) {
                _state.value = PermissionState.DeniedAlways
            } catch(e: DeniedException) {
                _state.value = PermissionState.Denied
            } catch(e: RequestCanceledException) {
                e.printStackTrace()
            }
        }
    }
}