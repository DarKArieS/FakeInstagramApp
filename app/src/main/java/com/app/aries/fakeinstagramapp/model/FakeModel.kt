package com.app.aries.fakeinstagramapp.model

import com.thedeanda.lorem.LoremIpsum
import java.util.*

object FakeModel{

    val lorem = LoremIpsum.getInstance()

    val imageList = listOf(
        "https://images.unsplash.com/photo-1561444210-3959508dbb79?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=701&q=80",
        "https://images.unsplash.com/photo-1561419060-1850e1f5f5b9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1561478908-d067fe75a553?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1561471026-0bbb77535d25?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1561453772-6834534c06fd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1561483853-2c89f4b47eeb?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1561478908-b96d7c7e831e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1561444327-24d88c39cc21?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1561444209-17feef437363?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1561451077-4ccdc3879606?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1561489422-45de3d015e3e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1561442748-c50715dc32f6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1561444477-8e04418dc258?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/flagged/photo-1561413754-c8f0be68e0ac?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1561492735-bca8951b8282?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1560872207-9030f0555edf?ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"
    )

    val avatarList:List<String> = listOf(
        "https://images.unsplash.com/photo-1531427186611-ecfd6d936c79?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1524593689594-aae2f26b75ab?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1544725176-7c40e5a71c5e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1527980965255-d3b416303d12?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60",
        "https://images.unsplash.com/photo-1543610892-0b1f7e6d8ac1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60"
    )

    val selfUser = User(
        999,
        "Aries",
        "https://images.unsplash.com/photo-1528763380143-65b3ac89a3ff?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60"
    )

    val userList = List(avatarList.size){
        User(
            it,
            lorem.name,
            avatarList[it]
        )
    }

    fun getHomePost(userid:Int = -1):List<PostData>{
        return List(10){
            var tmpUser = userList.random()
            if (userid!=-1)
                tmpUser = userList[userid]

            PostData(
                tmpUser,
                imageList.random(),
                lorem.getWords(10,15),
                (0 .. 999).random(),
                (0 .. 999).random()
            )
        }
    }

    fun getHomeStory():List<StoryData>{
        return List(10){
            val tmpUser = userList.random()
            StoryData(
                tmpUser.userName,
                tmpUser.userAvatar
            )
        }
    }

    fun getRandomTVStory():List<StoryData>{
        return List(10){
            StoryData(
                lorem.getWords(2,5),
                imageList.random()
            )
        }
    }

    fun getProfilePost(userid:Int):ProfileData{
        return ProfileData(
            userList[userid],
            (0 .. 999).random().toString(),
            (0 .. 999).random().toString(),
            (0 .. 999).random().toString(),
            getRandomTVStory(),
            getHomePost(userid)
        )
    }
}

data class User(
    var userID:Int,
    var userName:String,
    var userAvatar:String
)

data class ProfileData(
    var user:User,
    var numberPost:String,
    var numberFans:String,
    var numberFollows:String,

    var storyData: List<StoryData>,
    var postData:List<PostData>
)