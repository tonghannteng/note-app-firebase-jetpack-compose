package com.tonghannteng.noteapp.data.remote

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.tonghannteng.noteapp.data.model.Note
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.lang.Exception
import javax.inject.Inject

/**
 * @author: Tonghann Teng
 * @since: 1/6/24
 */
class RealtimeDatabaseImpl @Inject constructor(
    private val databaseReference: DatabaseReference
) : IRealtimeDatabase {

    override suspend fun getTodoNote(): Flow<RealtimeDatabaseState<List<Note>>> =
        callbackFlow {
            val listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val todoData = snapshot.children.map {
                        it.getValue<Note>()!!
                    }
                    trySend(RealtimeDatabaseState.Success(todoData))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(RealtimeDatabaseState.Failure(error.toException()))
                }
            }
            databaseReference.addValueEventListener(listener)
            awaitClose { databaseReference.removeEventListener(listener) }
        }

    override suspend fun updateTodoNote(note: Note): Flow<RealtimeDatabaseState<Boolean>> =
        callbackFlow {
            val listener = OnCompleteListener<Void> {
                if (it.isSuccessful) {
                    trySend(RealtimeDatabaseState.Success(true))
                } else {
                    trySend(RealtimeDatabaseState.Failure(Exception("Realtime update DB failed")))
                }
            }
            databaseReference.child(note.id.toString()).setValue(note)
                .addOnCompleteListener(listener)
            awaitClose { close() }
        }
}
