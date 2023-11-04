package com.tudg.basic;

import java.util.ArrayList;

public class Manage {
    String name;
    String count;
    String password;
    public boolean add_user(String userId, String password, String name,ArrayList<User> userList){
        for(User users: userList){
            if(users.getId().equals(userId)){
                System.out.println("该用户id已存在~~");
                return false;
            }
        }
        if (userList.add(new User(userId,password,name))){
            return true;
        }else{
            return false;
        }
    }
}
