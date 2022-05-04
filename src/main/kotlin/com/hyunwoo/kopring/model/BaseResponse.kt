package com.hyunwoo.kopring.model
import org.springframework.http.HttpStatus

data class BaseResponse<T>(
    val statusCode: Int,
    val success: Boolean,
    val data: T? = null,
    val message: String
) {
    companion object {
        fun <T> success(data: T) = BaseResponse<T>(
            statusCode = HttpStatus.OK.value(),
            success = true,
            data = data,
            message = "SUCCESS"
        )

        fun <T> failure(message: String) = BaseResponse<T>(
            statusCode = HttpStatus.NOT_FOUND.value(),
            success = false,
            message = message
        )
    }
}
