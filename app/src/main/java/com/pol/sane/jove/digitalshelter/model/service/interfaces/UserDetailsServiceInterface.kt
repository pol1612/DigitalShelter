package com.pol.sane.jove.digitalshelter.model.service.interfaces

import com.pol.sane.jove.digitalshelter.model.service.UserDetails

interface UserDetailsServiceInterface {

   suspend fun getCurrentUserUserDetails(): UserDetails?

    suspend fun createUserDetails(userDetails: UserDetails, setSnackbarText: (String) -> Unit)

    fun getUserDetails(id: String)

    fun deleteCurrentUserUserDetails(id: String)

    suspend fun checkIfUserNameIsTaken(userName: String): Boolean
}