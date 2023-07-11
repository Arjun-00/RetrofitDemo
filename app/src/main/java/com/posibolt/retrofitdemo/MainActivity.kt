package com.posibolt.retrofitdemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text_view : TextView = findViewById(R.id.textView)

        val retService : AlbumService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)

        val responceLiveData : LiveData<Response<Album>> = liveData {
            val  response: Response<Album> = retService.getAlbums()
           // val  response: Response<Album> = retService.getSortedAlbums(3)
            emit(response)
        }
        responceLiveData.observe(this, Observer {
            val  albumList : MutableListIterator<AlbumItem>? = it.body()?.listIterator()
            if(albumList != null){
                while (albumList.hasNext()){
                    val albumItem : AlbumItem = albumList.next()
                    Log.i("MYTAG",albumItem.title)
                    val result: String = " "+"Album id : ${albumItem.id}"+"\n"+
                            " "+"Album title : ${albumItem.title}"+"\n"+
                            " "+"Album userid : ${albumItem.userId}"+"\n"+"\n"
                    text_view.append(result)
                }
            }
        })
    }
}