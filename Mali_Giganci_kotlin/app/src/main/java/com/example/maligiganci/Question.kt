package com.example.maligiganci

data class Question(
    var questionId: Int,
    var questionText: String,
    var questionPhoto: String,
    var answerA: String,
    var answerB: String,
    var answerC: String,
    var answerD: String,
    var correctAnswer: Int
) {
    constructor() : this(0, "", "", "", "", "", "", 0)
}