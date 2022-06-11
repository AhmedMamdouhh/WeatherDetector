package com.musala.weatherdetector.domain.util

import com.musala.weatherdetector.data.utils.Resource
import retrofit2.Response
import java.lang.Exception


abstract class BaseDataSource {

    suspend fun <T> safeApiCall(
        mapper: Mapper<Any>?,
        apiCall: suspend () -> Response<T>
    ): Resource<T> {
        val mappedObject :Any
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                var body = response.body()
                if (body != null) {
                    if (mapper != null) {
                        mappedObject = mapper.mapFrom(body)
                        body = mappedObject as T
                    }
                    return Resource.Success(body, "")
                }
            }
            return Resource.Failed("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return Resource.Failed(e.message ?: e.toString())
        }
    }

}