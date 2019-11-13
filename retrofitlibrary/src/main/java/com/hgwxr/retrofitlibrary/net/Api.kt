package com.hgwxr.retrofitlibrary.net

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


object Api {
    private const val HOST="https://api.github.com/"
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(HOST)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()


    fun getGitHubService(): GitHubService {
        return retrofit.create(GitHubService::class.java);
    }

}
interface GitHubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<String>
}
data class Repo(val id:Long,val name: String){

}