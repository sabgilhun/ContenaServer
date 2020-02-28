package com.sabgil.contena.exceptiom

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class BadRequestException(message: String?) : RuntimeException(message)

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class NotFoundException(message: String?) : RuntimeException(message)