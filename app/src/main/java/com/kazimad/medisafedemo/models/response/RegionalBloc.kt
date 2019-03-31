package com.kazimad.medisafedemo.models.response

data class RegionalBloc(
    val acronym: String,
    val name: String,
    val otherAcronyms: List<Any>,
    val otherNames: List<Any>
)