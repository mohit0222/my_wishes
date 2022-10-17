package com.example.mywishes.signUp

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("name") val name: String,
    @SerializedName("password") val password: String,
    @SerializedName("email") val email:String,
    @SerializedName("mobile") val mobile:String,
    @SerializedName("confirmPassword") val confirmPassword:String

)

/*name:Mohit
password:Mohit@123
email:mohitdhiman701@gmail.com
dob:22-08-1999
mobile:7018508085*/
