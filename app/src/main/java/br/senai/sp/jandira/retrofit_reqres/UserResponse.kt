package br.senai.sp.jandira.retrofit_reqres

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") var id: Int? = 0,
    @SerializedName("email") var email: String? = "",
    @SerializedName("first_name") var first_name: String? = "",
    @SerializedName("last_name") var last_name: String? = "",
    @SerializedName("avatar") var avatar: String? = ""

)
