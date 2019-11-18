package io.arkstud.wordbox_roberto.network

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException

/**
 *
 * @param gson
 * @param adapter
 */
class GsonPResponseBodyConverter<T> internal constructor(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<ResponseBody, T> {

    /**
     *
     * @param value
     * @return [T]
     * @throws IOException
     */
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T? {
        val reader = value.charStream()
        var item = reader.read()
        while (item != '('.toInt() && item != -1) {
            item = reader.read()
        }
        val jsonReader = gson.newJsonReader(reader)
        reader.use {
            return adapter.read(jsonReader)
        }
    }

}