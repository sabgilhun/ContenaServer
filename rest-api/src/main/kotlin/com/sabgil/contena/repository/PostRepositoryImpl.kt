package com.sabgil.contena.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sabgil.contena.entitiy.Post


class PostRepositoryImpl : PostRepository {
    private val database = FirebaseDatabase.getInstance()

    override fun getPosts(page: Int) {

        val ref = database.getReference("post_data")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError?) {
            }

            override fun onDataChange(snapshot: DataSnapshot?) {
                print(snapshot?.getValue(Post::class.java))
            }
        })
    }
}