package com.work.workhubpro.models

import javax.inject.Inject

data class Organisation @Inject
constructor(
    val companyName: String,
    val companyEmail: String,
    val adminName:String,
    val domainName:String,
    val companyType:String
)