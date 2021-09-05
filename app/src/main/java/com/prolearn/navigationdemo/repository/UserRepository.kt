package com.prolearn.navigationdemo.repository;

import com.prolearn.navigationdemo.model.UserModel

class UserRepository{

    val userList : List<UserModel>;


    init {
        userList = arrayListOf(
            UserModel(1,"tom","chennai","construction"),
            UserModel(2,"dick","madurai","real estate"),
            UserModel(3,"din","trichy","lawyer"),
            UserModel(4,"rose","theni","fisherman"),

        )
    }
}