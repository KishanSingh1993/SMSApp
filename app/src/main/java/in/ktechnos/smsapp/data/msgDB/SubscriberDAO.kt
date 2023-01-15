package `in`.ktechnos.smsapp.data.msgDB

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDAO {

    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber) : Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber) : Int

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber) : Int

    @Query("DELETE FROM message_table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM message_table")
    fun getAllSubscribers():LiveData<List<Subscriber>>
}