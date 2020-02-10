package com.example.secondexercise.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
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

        myAdapter = MyAdapter(mUsers) {
            Log.d(TAG, "An item is clicked ${it.login}")
            startDisplayActivity(it)
        }

        rv_id.layoutManager = LinearLayoutManager(this)
        rv_id.adapter = myAdapter
        apiService = RestClient.client.create(APIService::class.java)
        getUsers("android")
        Log.d(TAG, "Calling getUsers method")
    }


    private fun getUsers(searchedText: String) {
        Log.d(TAG, "inside getUsers")
        val call = apiService!!.getUserDetails(searchedText)
        call.enqueue(object : Callback<Data> {

            override fun onResponse(
                call: Call<Data>?,
                response: Response<Data>
            ) {

                val mData = response.body()
                if (mData != null) {
                    mUsers.clear()
                    mUsers.addAll(mData.items)
                    myAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d(TAG, "Got error : " + t.localizedMessage)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.userlist_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d(TAG, "Query text listener called")
                if (query != null) {
                    Log.d(TAG, "Calling getUsers method")
                    getUsers(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return true
    }

    private fun startDisplayActivity(it : DetailedData){
        Log.d(TAG,"Activity started")
        val displayIntent = Intent(this, DisplayActivity::class.java)

        val inputName = it.login
        val inputImg = it.avatarUrl
        displayIntent.putExtra("login", inputName)
        displayIntent.putExtra("avatarURL", inputImg)

        startActivity(displayIntent)
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}

