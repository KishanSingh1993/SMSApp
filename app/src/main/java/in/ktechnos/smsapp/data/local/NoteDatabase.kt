package `in`.ktechnos.smsapp.data.local

import `in`.ktechnos.smsapp.data.StartingNotes
import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec


@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = true
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val dao: NoteDao

//    @RenameColumn(tableName = "Notes_table", fromColumnName = "noteTitle", toColumnName = "name")
//    class Migration1To2 : AutoMigrationSpec

    companion object{
        @Volatile
        private var instance: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase?{
            if (instance == null){
                synchronized(NoteDatabase::class.java){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "notes"
                    )
                        .addCallback(StartingNotes(context))
                        .build()
                }
            }
            return instance
        }
    }
}