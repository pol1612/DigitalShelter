package com.pol.sane.jove.digitalshelter.data.interfaces

import com.pol.sane.jove.digitalshelter.data.database_entities.UserDetails

interface UserDetailsServiceInterface {

    suspend fun getCurrentUserUserDetails(): UserDetails?

    suspend fun createUserDetails(userDetails: UserDetails, setSnackbarText: (String) -> Unit)

    fun getUserDetails(id: String)

    fun deleteCurrentUserUserDetails(id: String)

    suspend fun checkIfUserNameIsTaken(userName: String): Boolean
}