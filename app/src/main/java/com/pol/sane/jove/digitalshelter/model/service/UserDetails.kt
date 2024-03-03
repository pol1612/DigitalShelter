
package com.pol.sane.jove.digitalshelter.model.service

import com.google.firebase.firestore.GeoPoint

data class UserDetails(
    val id: String = "",
    val authUserId: String = "",
    val userName: String = "",
    val isUserShelter: Boolean = false,
    val shelterLocation: GeoPoint = GeoPoint(0.0,0.0)
)
