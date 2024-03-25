package com.pol.sane.jove.digitalshelter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pol.sane.jove.digitalshelter.model.service.interfaces.UserDetailsServiceInterface
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivityViewModel: ViewModel(), KoinComponent {

    private val  userDetailsService: UserDetailsServiceInterface by inject()
    suspend fun getAppInitialScreen(): String{
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

        Log.i("getAppInitialScreen::screenRoute::final ", "${screenRoute}")
        return screenRoute
    }
}