package com.pol.sane.jove.digitalshelter.ui.screens.start

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pol.sane.jove.digitalshelter.RootGraph
import com.pol.sane.jove.digitalshelter.model.service.interfaces.UserDetailsServiceInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class StartScreenViewModel: ViewModel(), KoinComponent {

    private val  userDetailsService: UserDetailsServiceInterface by inject()

    //TODO: move MainActivityViewModel uiState class into file
    private val _uiState = MutableStateFlow(StartScreenUiState())
    val uiState: StateFlow<StartScreenUiState> = _uiState.asStateFlow()

    suspend fun getAppInitialScreen(){
        var screenRoute: String = RootGraph.AUTHENTICATION
        Log.i("getAppInitialScreen::screenRoute::initial ", "${screenRoute}")
        var currentUserDetails = userDetailsService.getCurrentUserUserDetails()

        if (currentUserDetails == null){
            screenRoute = RootGraph.AUTHENTICATION
        }else{
            if (currentUserDetails!!.isUserShelter == true){
                screenRoute = RootGraph.MAIN_SHELTER
            }else{
                screenRoute = RootGraph.MAIN_ADOPTER
            }
        }
        Log.i("getAppInitialScreen::screenRoute::middle","${screenRoute}")


        _uiState.update { it -> it.copy(
            initialScreenRoute = screenRoute,
        ) }

        Log.i("getAppInitialScreen::screenRoute::final ", "${screenRoute}")
    }
}