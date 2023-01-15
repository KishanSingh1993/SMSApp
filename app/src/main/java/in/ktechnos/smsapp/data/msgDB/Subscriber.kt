package `in`.ktechnos.smsapp.data.msgDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_table")
data class Subscriber (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "msg_id")
    val id : Int,

    @ColumnInfo(name = "contact_name")
    var name : String,

    @ColumnInfo(name = "contact_number")
    var phone : String,

    @ColumnInfo(name = "contact_message")
    var message : String,

    @ColumnInfo(name = "date_time")
    var dateTime : String

)