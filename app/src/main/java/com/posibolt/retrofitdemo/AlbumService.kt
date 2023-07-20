package com.posibolt.retrofitdemo

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumService {

    @GET("/albums")
    suspend fun getAlbums(): Response<Album>
    //https://jsonplaceholder.typicode.com/albums

    @GET("/albums") //https://jsonplaceholder.typicode.com/albums?userId=3
    suspend fun getSortedAlbums(@Query("userId") userId :Int): Response<Album>
    //Path parametter

    //Album parametter https://jsonplaceholder.typicode.com/albums/3 like this
    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") id : Int) : Response<AlbumItem>

    @POST(value = "/album")
    suspend fun uploadAlbum(@Body album: AlbumItem) : Response<AlbumItem>
}