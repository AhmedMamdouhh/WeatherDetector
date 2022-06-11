package com.musala.weatherdetector.domain.util

interface Mapper<T> {
    fun mapFrom(fromObject:T):Any
}