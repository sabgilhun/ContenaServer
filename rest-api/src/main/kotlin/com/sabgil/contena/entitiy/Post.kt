package com.sabgil.contena.entitiy

data class Post constructor(
        val logoUrl: String,
        val newItems: List<Item>,
        val shopName: String,
        val timeStamp: String
)