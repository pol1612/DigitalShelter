package com.pol.sane.jove.digitalshelter.model.service.implementations

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.pol.sane.jove.digitalshelter.model.UserDetails
import com.pol.sane.jove.digitalshelter.model.service.interfaces.UserDetailsServiceInterface

class UserDetailsService(private val database: FirebaseFirestore
) : UserDetailsServiceInterface {

    override val currentUserUserDetails: UserDetails
        get() = UserDetails()
    override fun loadCurrentUserUserDetailsIntoApp() {
        TODO("Not yet implemented")
    }

    override fun unloadCurrentUserUserDetailsFromApp() {
        TODO("Not yet implemented")
    }

    override fun createUserDetails(userDetails: UserDetails) {
        TODO("Not yet implemented")
    }

    override fun getUserDetails(id: String) {
        TODO("Not yet implemented")
    }

    override fun deleteCurrentUserUserDetails(id: String) {
        TODO("Not yet implemented")
    }
}