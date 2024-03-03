package com.pol.sane.jove.digitalshelter.model.service.interfaces

import com.pol.sane.jove.digitalshelter.model.service.UserDetails

interface UserDetailsServiceInterface {

   suspend fun getCurrentUserUserDetails(): UserDetails?

    fun createUserDetails(userDetails: UserDetails)

    fun getUserDetails(id: String)

    fun deleteCurrentUserUserDetails(id: String)

    fun checkIfUserNameIsTaken(userName: String): Boolean
}