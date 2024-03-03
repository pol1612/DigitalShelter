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

     val USER_DETAILS_COLLECTION: String = "userDetails"
     val USER_DETAILS_FIELD_AUTH_USER_ID: String = "authUserId"
     val USER_DETAILS_FIELD_USER_NAME: String = "userName"
     val USER_DETAILS_FIELD_IS_SHELTER: String = "isUserShelter"
     val USER_DETAILS_FIELD_SHELTER_LOCATION: String = "shelterLocation"
    override suspend fun getCurrentUserUserDetails(): UserDetails? {
        var result: UserDetails? = null
        try {
            if (auth.currentUser != null){
                var querySnapshot = database.collection(USER_DETAILS_COLLECTION)
                    .whereEqualTo(USER_DETAILS_FIELD_AUTH_USER_ID, auth.currentUser!!.uid)
                    .get()
                    .await()
                if(!querySnapshot.documents.isEmpty()){
                    result = querySnapshot.documents[0].toObject(UserDetails::class.java)
                }
            }
        } catch (e: Exception){

            Log.d("UserDetailsService::getCurrentUserUserDetails::failure", e.message ?: "Unknown error")
        }
        return result

    }
    /*override val currentUserUserDetails: UserDetails?
        get() {
            var result: UserDetails? = null
            if(auth.currentUser != null){

                database.collection(USER_DETAILS_COLLECTION)
                    .whereEqualTo(USER_DETAILS_FIELD_AUTH_USER_ID, auth.currentUser!!.uid).get()
                    .addOnFailureListener { task ->
                        Log.d("UserDetailsService::currentUserUserDetails::failure","${task.message}")
                    }
                    .addOnSuccessListener { task ->
                        result = task.documents[0].toObject(UserDetails::class.java)!!

                    }
            }
            return result
        }*/

    override fun createUserDetails(userDetails: UserDetails) {
        TODO("Not yet implemented")
    }

    override fun getUserDetails(id: String) {
        TODO("Not yet implemented")
    }

    override fun deleteCurrentUserUserDetails(id: String) {
        TODO("Not yet implemented")
    }

    override fun checkIfUserNameIsTaken(userName: String): Boolean {
        var isUserNameTaken = false
        database.collection(USER_DETAILS_COLLECTION)
            .whereEqualTo(USER_DETAILS_FIELD_USER_NAME,userName).get()
            .addOnFailureListener {
                Log.i("UserDetailsService::checkIfUserNameIsTaken::failure", "${it.message}")
            }
            .addOnSuccessListener { task ->
                if (task.documents.size != 0){
                    isUserNameTaken = true
                    Log.i("UserDetailsService::checkIfUserNameIsTaken::success", "userName taken")
                }
            }
        return isUserNameTaken
    }
}