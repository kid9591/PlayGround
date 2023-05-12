package com.kid.playground1.activity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kid.playground.R
import com.kid.playground1.R


//https://stackoverflow.com/questions/15091790/how-to-outline-a-textview
//https://www.android-examples.com/add-set-bitmapshader-texture-effect-on-textview-text-in-android/
//https://stackoverflow.com/questions/14791012/android-textured-text

/*

{
  "histories": {
    "012023": {
      "bahai1": true,
      "bahai3": false
    }
  },
  "houses": {
    "-NRaSjgNMSU-1IPn9xFa": {
      "name": "Ba Hai 5",
      "note": ""
    },
    "bahai1": {
      "name": "Bà Hải 1 editted",
      "note": "Bà Hải 1 note editted"
    },
    "bahai3": {
      "name": "Phòng 3 Bà Hải",
      "note": "Note phòng 3"
    },
    "bahai7": {
      "name": "Ba Hai 7",
      "note": "note bh7"
    },
    "bahai8": {
      "name": "Ba Hai 8",
      "note": "note bh8"
    }
  }
}

 */

class MainActivity : FragmentActivity()  {

    private lateinit var database: DatabaseReference

    //Some changes here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //Some changes here

        database = Firebase.database.reference

        //write
        database.child("houses").child("bahai1").apply {
            child("name").setValue("Bà Hải 1 editted")
            child("note").setValue("Bà Hải 1 note editted")
        }

        //read once
        database.child("houses").child("bahai1").get().addOnSuccessListener {
            it.value // -> HashMap("note","note bh1";"name":"ba hai 1")
        }.addOnFailureListener {
            Log.e("chi.trinh", "Error getting data", it)
        }

        //observe
        val valueListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val post = dataSnapshot.value // return all database tree
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.d("chi.trinh", "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(valueListener)

        //observe child
        val childListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                snapshot.value
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                snapshot.value
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                snapshot.value
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                snapshot.value
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.d("chi.trinh", "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.addChildEventListener(childListener)

        //push (for autogenerated child)
//        val key = database.child("houses").push().key
//        Log.d("chi.trinh", "push key: $key")
//        val newHouse = House("Ba Hai 5","note").toMap()
//        val childUpdate = hashMapOf<String, Any>(
//            "/houses/$key" to newHouse
//        )
//        database.updateChildren(childUpdate)


        //new/update data with key "bahai7"
        val newHouse = House("Ba Hai 9","note bh9").toMap()
        val childUpdate = hashMapOf<String, Any>(
            "/houses/bahai9" to newHouse
        )
        //or
//        val childUpdate = hashMapOf<String, Any>(
//            "/houses/bahai8" to hashMapOf(
//                "name" to "Ba Hai 8",
//                "note" to "note bh8"
//            )
//        )
        database.updateChildren(childUpdate)


        //delete data
//        database.child("houses").child("bahai7").removeValue()
    }
}

@IgnoreExtraProperties
data class House(
    var name: String = "",
    var note: String = ""
) {

    //Some changes here

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "note" to note
        )
    }
}


