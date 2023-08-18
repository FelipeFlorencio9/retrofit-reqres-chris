package br.senai.sp.jandira.retrofit_reqres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {


    private lateinit var apiService: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)


        //AÇÃO DO BOTÃO POST:
        findViewById<Button>(R.id.btnPOST).setOnClickListener {
            createUser()
        }

        //AÇÃO DO BOTÃO GET:
        findViewById<Button>(R.id.btnGET).setOnClickListener {
            getUserByID()
        }

        //AÇÃO DO BOTÃO PUT:
        findViewById<Button>(R.id.btnPUT).setOnClickListener {
            updateUser()
        }

        //AÇÃO DO BOTÃO DELETE
        findViewById<Button>(R.id.btnDELETE).setOnClickListener {
            deleteUser()
        }
    }



    //INSERE DADOS DE USUÁRIO
    private fun createUser() {
        lifecycleScope.launch {
            val body = JsonObject().apply {
                addProperty("name", "Felipe Florencio")
                addProperty("job", "Estudante")
            }
            val result = apiService.createUser(body)
            if(result.isSuccessful){
                Log.e("CREATING-DATA", "${result.body()}")
            } else {   Log.e("CREATING-DATA", "$result")}
        }
    }
    private fun getUserByID(){
        lifecycleScope.launch {
            //CHAMADA PARA O ENDPOINT
            val result = apiService.getUserByID("2")
            if(result.isSuccessful){
                Log.e("GETTING-DATA", "${result.body()?.data}")
            }else {
                Log.e("GETTING-DATA", result.message())
            }
        }
    }


    private fun updateUser() {
        lifecycleScope.launch {
            val body = JsonObject().apply {
                addProperty("name", "Felipe Oliveira")
                addProperty("job", "Trabalhando")
            }
            val result = apiService.updateUser("2", body)
            if(result.isSuccessful){
                Log.e("UPDATING-DATA", "${result.body()}")
            } else { Log.e("UPDATING-DATA", "${result.body()}")}
        }
    }

    private fun deleteUser() {
        lifecycleScope.launch {
            val result = apiService.deleteUser("2")

            if(result.isSuccessful){
                Log.e("DELETE-DATA", "${result.code()}")
            } else{
                Log.e("DELETE-DATA", "${result.body()}")
            }
        }
    }


}