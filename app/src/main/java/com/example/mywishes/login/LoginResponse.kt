package com.example.mywishes.login

data class LoginResponse(
    val created_at: String,
    val dob: String,
    val email: String,
    val id: Int,
    val is_active: String,
    val is_number_verified: String,
    val mobile: String,
    val name: String,
    val profile_image: String,
    val updated_at: String,
    val user_id: String
)