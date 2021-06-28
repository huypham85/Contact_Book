package com.example.contactbook

import java.io.Serializable

data class UserData(var name:String, var phone:String, var email:String, var facebook:String, var photo:Int):Serializable {

}
