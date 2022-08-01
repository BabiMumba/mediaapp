package com.babistone.kitumaini.model

data class User(
    val name:String,
    val bio:String,
    val profipath: String?
                ){
    constructor():this("","",null)
}
