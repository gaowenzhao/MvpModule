package com.zhao.base.http

object Client {
    val api: RestApi = Httper.createApi(RestApi::class.java)
}