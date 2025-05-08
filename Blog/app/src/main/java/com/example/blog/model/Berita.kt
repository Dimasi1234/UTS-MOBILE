package com.example.blog.model

data class Berita(
    val id: Int,
    val title: String,
    val slug: String,
    val content: String,
    val created_at: String,
    val featured_image: String
)
