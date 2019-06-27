package com.app.aries.fakeinstagramapp.model

data class PostData(
    var user: User,
    var postImage:String,
    var postContent:String,
    var numberLikes:Int,
    var numberComments:Int
)