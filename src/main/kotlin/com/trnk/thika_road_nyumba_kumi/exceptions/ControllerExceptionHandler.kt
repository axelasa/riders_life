package com.trnk.thika_road_nyumba_kumi.exceptions

import com.trnk.thika_road_nyumba_kumi.model.ApiResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception

@RestControllerAdvice
class ControllerExceptionHandler :ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val error = ex.bindingResult.allErrors
        val description = if (error.isEmpty())ex.message else error[0].defaultMessage
        return ResponseEntity(ApiResponse(status.value(),description!!,null),status)
    }

    override fun handleExceptionInternal(
        ex: Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        return ResponseEntity(ApiResponse(status.value(),ex.message!!,null),status)
    }

    @ExceptionHandler(HttpServerErrorException::class)
    fun onHttpServerException(ex:HttpServerErrorException):ResponseEntity<Any>{
        return ResponseEntity(ApiResponse(ex.statusCode.value(),ex.statusText,null),ex.statusCode)
    }
    companion object{
        //below private function is a method that is used to create all http exceptions within this app
        private fun createHttpExceptions(statusCode: HttpStatus,message:String):HttpServerErrorException{
            return HttpServerErrorException.create(statusCode,message,HttpHeaders.EMPTY, byteArrayOf(),null)
        }
        fun conflicts(message: String):HttpServerErrorException{
            return createHttpExceptions(HttpStatus.CONFLICT,message)
        }fun notFound(message: String):HttpServerErrorException{
            return createHttpExceptions(HttpStatus.NOT_FOUND,message)
        }fun unAuthorized(message: String):HttpServerErrorException{
            return createHttpExceptions(HttpStatus.UNAUTHORIZED,message)
        }
    }
}