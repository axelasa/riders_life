package com.trnk.thika_road_nyumba_kumi.controller

import com.trnk.thika_road_nyumba_kumi.dto.UserDto
import com.trnk.thika_road_nyumba_kumi.globalService.GlobalServices.Companion.userService
import com.trnk.thika_road_nyumba_kumi.model.ApiResponse
import com.trnk.thika_road_nyumba_kumi.model.UserModel
import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController {
    @PostMapping("new_user")
    fun createUser(@Valid @RequestBody user:UserModel):ResponseEntity<Any>{
        val newUser = userService.createUser(user)
        val result = ApiResponse(HttpStatus.CREATED.value(),"Success User Created Successfully",UserDto.fromUserEntity(newUser))
        return ResponseEntity(result,HttpStatus.OK)
    }

    @GetMapping("search_user")
    fun searchUser(@RequestParam("idNumber", required = true)idNumber:String,
                   @RequestParam(value = "page", defaultValue = "0", required = false)page:Int,
                   @RequestParam(value = "page_size", defaultValue = "10", required = false)pageSize:Int):ResponseEntity<Any>{
        val response = ApiResponse(HttpStatus.NOT_FOUND.value(),"This User Does Not Exist",null)
        val pageTask = userService.searchUserByIdNumber(idNumber, pageable = PageRequest.of(page,pageSize, Sort.by("id")))
        if (pageTask.isEmpty){
            return ResponseEntity(response,HttpStatus.NOT_FOUND)
        }
        val data = pageTask.get().map { UserDto.fromUserEntity(it) }.toList()
        val result = ApiResponse(HttpStatus.OK.value(),"User Found",data)
        return ResponseEntity(result,HttpStatus.OK)
    }
    @GetMapping("users")
    fun getAllUsers():List<UserDto> = userService.getAllUsers().stream().map {
        UserDto.fromUserEntity(it)
    }.toList()
    @GetMapping
    fun getSpecificUser(@RequestParam("idNumber")idNumber:String):ResponseEntity<Any>{
        val optionalUser = userService.getUserByIdNumber(idNumber)
        val response =ApiResponse(HttpStatus.NOT_FOUND.value(),"User $idNumber not found",null)
        if (optionalUser.isEmpty){
            return ResponseEntity(response,HttpStatus.NOT_FOUND)
        }
        val result = ApiResponse(HttpStatus.OK.value(),"User $idNumber Found Successfully",UserDto.fromUserEntity(optionalUser.get()))
        return ResponseEntity(result,HttpStatus.OK)
    }
}