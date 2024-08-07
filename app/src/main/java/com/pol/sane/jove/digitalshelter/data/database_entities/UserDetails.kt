
package com.pol.sane.jove.digitalshelter.data.database_entities

import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.PropertyName

data class UserDetails(
    @PropertyName("authUserId")
    val authUserId: String? = "",
    @PropertyName("userName")
    val userName: String? = "",
    @PropertyName("isUserShelter")
    val isUserShelter: Boolean? = false,
    @PropertyName("shelterLocation")
    val shelterLocation: GeoPoint? = GeoPoint(0.0,0.0)
)
