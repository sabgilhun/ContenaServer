package com.sabgil.contena.controller

import com.sabgil.contena.repository.ItemRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired


@RestController
class PostController(private val itemRepository: ItemRepository) {

    @RequestMapping("/")
    fun home(): String {
        val items = itemRepository.findByShopName("bluesman")
        return if (items.isNotEmpty()) {
            items.toString()
        } else {
            ""
        }
    }
}
