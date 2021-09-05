package com.prolearn.navigationdemo

import androidx.lifecycle.ViewModel
import com.prolearn.navigationdemo.model.UserModel
import com.prolearn.navigationdemo.repository.UserRepository

class AppViewModel  : ViewModel{
    val userRepository:UserRepository
    constructor(){

    }
    init {
        userRepository = UserRepository()
    }
    fun getUserList() : List<UserModel> {
        return userRepository.userList;
    }
    fun getUserById(userId : Int) : UserModel?{

        val userModel: List<UserModel> = userRepository.userList.filter { item ->
             item.id == userId
        }
        if(userModel.size >0){
            return userModel.get(0);
        }
        else{
            return null;
        }

    }
}