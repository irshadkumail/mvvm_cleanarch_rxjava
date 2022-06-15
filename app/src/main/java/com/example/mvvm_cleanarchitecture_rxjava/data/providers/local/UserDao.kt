package com.example.mvvm_cleanarchitecture_rxjava.data.providers.local

import androidx.room.*
import com.example.mvvm_cleanarchitecture_rxjava.data.models.User
import io.reactivex.Observable

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(list: List<User>)

    @Query("SELECT * FROM user_table ORDER BY id")
    fun getUsers(): Observable<List<User>>

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)
}