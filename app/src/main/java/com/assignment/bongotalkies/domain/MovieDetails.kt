package com.assignment.bongotalkies.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetails(
    val avatar: String? = "",
    val username: String? = "",
    val city: String? = "",
    val state: String? = "",
    val country: String? = "",
    val email: String? = "",
    val cellPhone: String? = ""
): Parcelable