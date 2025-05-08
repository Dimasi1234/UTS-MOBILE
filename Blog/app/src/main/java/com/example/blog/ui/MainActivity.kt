package com.example.blog

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blog.api.ApiClient
import com.example.blog.model.Berita
import com.example.blog.model.BeritaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var beritaAdapter: BeritaAdapter
    private val beritaList = mutableListOf<Berita>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        beritaAdapter = BeritaAdapter(beritaList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = beritaAdapter

        fetchBerita()
    }

    private fun fetchBerita() {
        ApiClient.instance.getBerita().enqueue(object : Callback<BeritaResponse> {
            override fun onResponse(call: Call<BeritaResponse>, response: Response<BeritaResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    beritaList.clear()
                    beritaList.addAll(response.body()!!.data)
                    beritaAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "Gagal memuat data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<BeritaResponse>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
                Toast.makeText(this@MainActivity, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
