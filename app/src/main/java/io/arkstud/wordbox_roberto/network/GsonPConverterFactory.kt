package io.arkstud.wordbox_roberto.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit

/**
 *
 * @param gson
 */
class GsonPConverterFactory(private val gson: Gson) : Converter.Factory() {

    /**
     *
     * @param type
     * @param annotations
     * @param retrofit
     */
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return GsonPResponseBodyConverter(gson, adapter)
    }

    /**
     *
     * @param type
     * @param parameterAnnotations
     * @param methodAnnotations
     * @param retrofit
     */
    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? = null

}