
package com.pol.sane.jove.digitalshelter.model

data class UserDetails(
    val id: String = "",
    val authUserId: String = "",
    val userName: String = "",
    val isShelter: Boolean = false,
    val location: HashMap<String,Float> = hashMapOf("latitude" to 0.0f,"longitude" to 0.0f)
)
