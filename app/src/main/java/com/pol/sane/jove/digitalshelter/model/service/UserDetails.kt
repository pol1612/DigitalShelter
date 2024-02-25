
package com.pol.sane.jove.digitalshelter.model.service

data class UserDetails(
    val id: String = "",
    val userId: String = "",
    val userName: String = "",
    val isUserShelter: Boolean = false,
    val location: HashMap<String,Float> = hashMapOf("latitude" to 0.0f,"longitude" to 0.0f)
)
