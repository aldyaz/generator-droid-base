object Net {

    private const val retrofitVersion = "2.9.0"
    private const val okHttpVersion = "4.10.0"
    private const val moshiVersion = "1.14.0"
    private const val chuckerVersion = "3.5.2"

    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"
    const val chuckerDebug = "com.github.chuckerteam.chucker:library:$chuckerVersion"
    const val chuckerRelease = "com.github.chuckerteam.chucker:library-no-op:$chuckerVersion"

}