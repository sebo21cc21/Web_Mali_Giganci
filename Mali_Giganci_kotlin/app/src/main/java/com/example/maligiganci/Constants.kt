package com.example.maligiganci

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(callback: (ArrayList<Question>) -> Unit) {
        val dbRef = FirebaseDatabase.getInstance().getReference("questions")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val questionsList =
                    ArrayList<Question>() // Przeniesienie inicjalizacji listy do metody onDataChange

                if (dataSnapshot.exists()) {
                    for (flagSnapshot in dataSnapshot.children) {
                        val questionData = flagSnapshot.getValue(Question::class.java)
                        print(questionData)
                        val id = questionData?.questionId as Int
                        val question = questionData?.questionText as String
                        val image = questionData?.questionPhoto as String
                        val option1 = questionData?.answerA as String
                        val option2 = questionData?.answerB as String
                        val option3 = questionData?.answerC as String
                        val option4 = questionData?.answerD as String
                        val correctAnswer = questionData?.correctAnswer as Int
                        val questionObject = Question(
                            id,
                            question,
                            image,
                            option1,
                            option2,
                            option3,
                            option4,
                            correctAnswer
                        )
                        questionsList.add(questionObject)

                    }

                    callback(questionsList)

                    fun doSomethingWithQuestions() {
                        getQuestions { questionsList ->
                            for (question in questionsList) {
                                println(question.questionText)
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })
    }
}
