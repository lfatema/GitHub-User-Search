package com.example.secondexercise.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondexercise.R
import com.example.secondexercise.adapter.MyAdapter
import com.example.secondexercise.model.Data
import com.example.secondexercise.model.DetailedData
import com.example.secondexercise.rest.APIService
import com.example.secondexercise.rest.RestClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var apiService: APIService? = null
    private val mUsers: MutableList<DetailedData> = mutableListOf()

    lateinit var myAdapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myAdapter = MyAdapter(mUsers)

        rv_id.layoutManager = LinearLayoutManager(this)
        rv_id.adapter = myAdapter
        apiService = RestClient.client.create(APIService::class.java)
        getUsers()
    }

    private fun getUsers() {
        val call = apiService!!.getUserDetails("android")
        call.enqueue(object : Callback<Data> {

            override fun onResponse(
                call: Call<Data>?,
                response: Response<Data>
            ) {

                val mData = response.body()
                if (mData != null) {
                    mUsers.addAll(mData.items)
                    myAdapter.notifyDataSetChanged()
                }
            }

           override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d(TAG, "Got error : " + t.localizedMessage)
            }
        })
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
