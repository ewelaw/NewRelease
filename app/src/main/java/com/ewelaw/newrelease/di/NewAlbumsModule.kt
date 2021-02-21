package com.ewelaw.newrelease.di

import com.ewelaw.newrelease.api.AlbumApi
import com.ewelaw.newrelease.api.AuthorizationApi
import com.ewelaw.newrelease.ui.viewmodels.AlbumDetailsViewModel
import com.ewelaw.newrelease.ui.viewmodels.AlbumsViewModel
import com.ewelaw.newrelease.utils.ApiConstants
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { retrofit() }
    single { retrofitAuth() }
    viewModel { AlbumsViewModel(get()) }
    viewModel { AlbumDetailsViewModel(get()) }
    single { okHttp() }
}

private fun retrofit() =
    Retrofit.Builder().callFactory(okHttp())
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(AlbumApi::class.java)

private fun retrofitAuth() =
    Retrofit.Builder().callFactory(okHttp())
        .baseUrl(ApiConstants.AUTH_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(AuthorizationApi::class.java)

private fun okHttp(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.HEADERS
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder().addInterceptor(interceptor)
        .addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                if (chain.request().url.toString()
                        .contains(ApiConstants.BASE_URL) && ApiConstants.TOKEN.isNotEmpty()
                ) {
                    var newRequest =
                        chain.request().newBuilder().header("Authorization", ApiConstants.TOKEN)
                            .build()
                    return chain.proceed(newRequest)
                }
                return chain.proceed(chain.request())
            }
        })
        .authenticator(object : Authenticator {
            override fun authenticate(route: Route?, response: Response): Request? {
                if (ApiConstants.TOKEN.isEmpty() || response.code == 401) {
                    synchronized(this) {
                        var call = retrofitAuth().getAccesToken(
                            ApiConstants.CONTENT_TYPE,
                            ApiConstants.ENCODED_AUTHORIZATION, ApiConstants.GRANT_TYPE
                        )
                        val res = call.execute().body()
                        ApiConstants.TOKEN = res?.getAuthorizationString().toString()
                    }
                }
                return response.request.newBuilder().header("Authorization", ApiConstants.TOKEN)
                    .build()
            }

        })
        .build()
}
