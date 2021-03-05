package com.example.playgroundapp.data.mappers

interface Mapper<Input, Output> {
    fun map(input: Input): Output
}

interface ListMapper<Input, Output> : Mapper<List<Input>, List<Output>>
