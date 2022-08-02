package com.babistone.kitumaini.model.util

import com.babistone.kitumaini.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.model.mutation.Precondition.exists
import java.nio.file.Files.exists

object FirestoreUtil {
    private val firestoreinstance :FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private val currentUserDocRef: DocumentReference
    get() = firestoreinstance.document("usera/${FirebaseAuth.getInstance().uid
    
        ?:throw NullPointerException("UID is null.")}")

    fun initCurrentUserIffirstTime(onComplet: () -> Unit){
        currentUserDocRef.get().addOnSuccessListener { documentsnapshot ->
            if (!documentsnapshot.exists()){
                val newUser = User(FirebaseAuth.getInstance().currentUser?.displayName?: "","",null)
                currentUserDocRef.set(newUser).addOnSuccessListener {
                    onComplet()
                }

            }else{
                onComplet
            }
        }
    }
    fun updateCurrentUser(name:String="",bio:String="",profilPicturepath:String?=null){
        val userFielMap = mutableMapOf<String,Any>()
        if (name.isBlank()) userFielMap["name"] = name
        if (bio.isBlank())  userFielMap["bio"] = bio
        if (profilPicturepath != null)
            userFielMap["profilPicturePath"] = profilPicturepath
        currentUserDocRef.update(userFielMap)
    }
}