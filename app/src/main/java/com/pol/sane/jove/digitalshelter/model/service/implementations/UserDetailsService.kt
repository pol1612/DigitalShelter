package com.pol.sane.jove.digitalshelter.model.service.implementations

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pol.sane.jove.digitalshelter.model.service.UserDetails
import com.pol.sane.jove.digitalshelter.model.service.interfaces.UserDetailsServiceInterface
import kotlinx.coroutines.tasks.await

class UserDetailsService(
    private val database: FirebaseFirestore,
    private val auth: FirebaseAuth
) : UserDetailsServiceInterface {

   override val currentUserUserDetails: UserDetails
        get() {
            var result = UserDetails()
            if(auth.currentUser != null){

                database.collection("UserDetails")
                    .whereEqualTo("userId",auth.currentUser!!.uid).get()
                    .addOnFailureListener { task ->
                        Log.d("UserDetailsService::currentUserUserDetails","${task.message}")
                    }
                    .addOnSuccessListener { task ->
                        result = task.documents.get(0).toObject(UserDetails::class.java)!!

                    }
            }
            return result
        }
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