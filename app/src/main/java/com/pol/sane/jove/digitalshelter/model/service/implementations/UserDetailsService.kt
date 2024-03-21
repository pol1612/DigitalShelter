package com.pol.sane.jove.digitalshelter.model.service.implementations

import android.util.Log
import android.widget.ExpandableListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
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
        var currentUserUserDetails: UserDetails? = null
        try {
            if (auth.currentUser != null){
                var querySnapshot = database.collection(USER_DETAILS_COLLECTION)
                    .whereEqualTo(USER_DETAILS_FIELD_AUTH_USER_ID, auth.currentUser!!.uid)
                    .get()
                    .await()
                if(!querySnapshot.documents.isEmpty()){
                    var doc: DocumentSnapshot? = querySnapshot.documents[0]
                    Log.i("getCurrentUserUserDetails::delay","10 seconds")
                    currentUserUserDetails = querySnapshot.documents[0].toObject(UserDetails::class.java)

                    var isUserShelter = doc?.getBoolean(USER_DETAILS_FIELD_IS_SHELTER)
                    var id = doc?.id

                    if((!id.isNullOrEmpty()) && (isUserShelter != null)){
                        Log.i("bol ", "${isUserShelter}")
                        Log.i("id ", "${doc?.id}")
                        currentUserUserDetails = currentUserUserDetails!!.copy(
                            isUserShelter = isUserShelter
                        )
                    }
                    Log.i("getCurrentUserUserDetails::result",
                            "${currentUserUserDetails?.authUserId} " +
                            "${currentUserUserDetails?.userName} " +
                            "${currentUserUserDetails?.isUserShelter.toString()} " +
                            "${currentUserUserDetails?.shelterLocation?.latitude.toString()}")

                }
            }
        } catch (e: Exception){

            Log.d("UserDetailsService::getCurrentUserUserDetails::failure", e.message ?: "Unknown error")
        }
        return currentUserUserDetails
    }

    override suspend fun createUserDetails(userDetails: UserDetails, setSnackbarText: (String) -> Unit) {
        try {
            var result = database.collection(USER_DETAILS_COLLECTION)
                .add(userDetails)
                .await()
            setSnackbarText("User successfully registered.")
        }catch (e: Exception){
            Log.i(" createUserDetails::exception", "message: ${e.message}")
            auth.currentUser?.delete()?.await()
            Log.i(" createUserDetails::exception", "current user deleted")
        }

    }

    override fun getUserDetails(id: String) {
        TODO("Not yet implemented")
    }

    override fun deleteCurrentUserUserDetails(id: String) {
        TODO("Not yet implemented")
    }

    suspend override fun checkIfUserNameIsTaken(userName: String): Boolean {
        var isUserNameTaken = false

        var querySnapshot = database.collection(USER_DETAILS_COLLECTION)
            .whereEqualTo(USER_DETAILS_FIELD_USER_NAME,userName)
            .get()
            .await()

        if(!querySnapshot.documents.isEmpty()){
            isUserNameTaken = true
        }
        Log.i("UserDetailsService::checkIfUserNameIsTaken","${isUserNameTaken}")
        return isUserNameTaken
    }
}