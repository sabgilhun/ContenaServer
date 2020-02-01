package com.sabgil.contena.controller

import com.sabgil.contena.entitiy.Post
import com.sabgil.contena.repository.ItemRepository
import com.sabgil.contena.repository.PostRepository
import com.sabgil.contena.repository.ShopRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired
import java.lang.StringBuilder


@RestController
class PostController(
        private val itemRepository: ItemRepository,
        private val postRepository: PostRepository,
        private val shopRepository: ShopRepository
) {

    @RequestMapping("/")
    fun home(): String {
        val shop = shopRepository.findById("bluesman")
        var posts : List<Post> = emptyList()

        shop.ifPresent {
             posts = postRepository.findByShop(it)
        }

        return if (posts.isNotEmpty()) {
            val stringBuilder = StringBuilder()
            for (post: Post in posts) {
                stringBuilder.append(post.toString()).append("\n")
            }

            stringBuilder.toString()
        } else {
            ""
        }
    }
}
