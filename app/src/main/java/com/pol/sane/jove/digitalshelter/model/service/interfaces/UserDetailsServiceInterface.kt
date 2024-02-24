package com.pol.sane.jove.digitalshelter.model.service.interfaces

import com.pol.sane.jove.digitalshelter.model.UserDetails

interface UserDetailsServiceInterface {

    val currentUserUserDetails: UserDetails

    fun loadCurrentUserUserDetailsIntoApp()
    fun unloadCurrentUserUserDetailsFromApp()

    fun createUserDetails(userDetails: UserDetails)

    fun getUserDetails(id: String)

    fun deleteCurrentUserUserDetails(id: String)
}