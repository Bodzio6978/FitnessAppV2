package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.data.repository.remote

import com.gmail.bodziowaty6978.fitnessappv2.common.data.room.dao.ProductDao
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Constants
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.DiaryEntry
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.DiaryEntryWithId
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository.DiaryRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class DiaryRepositoryImp(
    private val firebaseFirestore:FirebaseFirestore,
    private val productDao: ProductDao,
    private val userId:String?
): DiaryRepository {

    private val userCollection = firebaseFirestore.collection(Constants.FIRESTORE_USER_COLLECTION)
    private val productCollection = firebaseFirestore.collection(Constants.FIRESTORE_PRODUCT_COLLECTION)

    override suspend fun getDiaryEntries(date: String): Resource<List<DiaryEntryWithId>> {
        return try {
            val query = userCollection.document(userId!!).collection("journal").whereEqualTo("date", date)
                .orderBy("time", Query.Direction.DESCENDING)
                .get().await().documents

            val mappedEntries = query.map {
                DiaryEntryWithId(it.id,it.toObject(DiaryEntry::class.java)!!)
            }
            Resource.Success(mappedEntries)
        }catch (e:Exception){
            Resource.Error(uiText = e.message.toString(), data = emptyList())
        }
    }

    override suspend fun getLocalProductHistory(): Resource<List<ProductWithId>> {
        return try {
            val result = productDao.getHistory()
            Resource.Success(data = result)
        }catch (e:Exception){
            Resource.Error(data = emptyList(), uiText = e.message.toString())
        }
    }

    override suspend fun searchForProducts(productName: String): Resource<List<ProductWithId>> {
        return try {
            val query = productCollection.whereArrayContains("searchKeywords",productName).limit(20).get().await()
            val mappedQuery = query.map { querySnapshot ->
                ProductWithId(querySnapshot.id,querySnapshot.toObject(Product::class.java))
            }
            return Resource.Success(data = mappedQuery)
        }catch (e:Exception){
            Resource.Error(uiText = e.message.toString(), data = emptyList())
        }
    }

    override suspend fun addDiaryEntry(diaryEntry: DiaryEntry): Result {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDiaryEntry(diaryEntryId: String): Result {
        TODO("Not yet implemented")
    }

    override suspend fun editDiaryEntry(diaryEntryWithId: DiaryEntryWithId): Result {
        TODO("Not yet implemented")
    }
}