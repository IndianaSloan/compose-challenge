package com.fueled.composechallenge.model

import java.time.Duration

data class Question(
    val id: String,
    val user: User,
    val questionText: String,
    val answers: List<Answer>,
    val duration: Duration
) {
    companion object {
        fun demoQuestions(): List<Question> = listOf(
            Demo1,
            Demo2,
            Demo3,
            Demo4
        )
        private val Demo1: Question
            get() {
                val user = User(
                    id = "1",
                    name = "Goofy",
                    imageUrl = "https://celebrationspress.com/wp-content/uploads/2018/01/010218Goofy.jpg"
                )
                return Question(
                    id = "1",
                    user = user,
                    questionText = "How many players in a football team?",
                    duration = Duration.ofSeconds(10),
                    answers = listOf(
                        Answer(
                            id = "1",
                            option = "A",
                            answerText = "9",
                            isCorrect = false
                        ),
                        Answer(
                            id = "2",
                            option = "B",
                            answerText = "11",
                            isCorrect = true
                        ),
                        Answer(
                            id = "3",
                            option = "C",
                            answerText = "13",
                            isCorrect = false
                        ),
                    )
                )
            }
        private val Demo2: Question
            get() {
                val user = User(
                    id = "2",
                    name = "Daffy D.",
                    imageUrl = "https://comicvine.gamespot.com/a/uploads/scale_small/0/7934/188062-181526-daffy-duck.jpg"
                )
                return Question(
                    id = "2",
                    user = user,
                    questionText = "Whats the diameter of a Basketball hoop in inches?",
                    duration = Duration.ofSeconds(20),
                    answers = listOf(
                        Answer(
                            id = "1",
                            option = "A",
                            answerText = "10",
                            isCorrect = false
                        ),
                        Answer(
                            id = "2",
                            option = "B",
                            answerText = "15",
                            isCorrect = false
                        ),
                        Answer(
                            id = "3",
                            option = "C",
                            answerText = "18",
                            isCorrect = true
                        ),
                    )
                )
            }
        private val Demo3: Question
            get() {
                val user = User(
                    id = "2",
                    name = "Buggs B.",
                    imageUrl = "https://www.cartoonbrew.com/wp-content/uploads/2015/07/bugsbunnyridesagin-1280x600.jpg"
                )
                return Question(
                    id = "2",
                    user = user,
                    questionText = "What sport is known as King of Sports?",
                    duration = Duration.ofSeconds(25),
                    answers = listOf(
                        Answer(
                            id = "1",
                            option = "A",
                            answerText = "Golf",
                            isCorrect = false
                        ),
                        Answer(
                            id = "2",
                            option = "B",
                            answerText = "Soccer",
                            isCorrect = true
                        ),
                        Answer(
                            id = "3",
                            option = "C",
                            answerText = "Baseball",
                            isCorrect = false
                        ),
                    )
                )
            }
        private val Demo4: Question
            get() {
                val user = User(
                    id = "2",
                    name = "Goofy",
                    imageUrl = "https://celebrationspress.com/wp-content/uploads/2018/01/010218Goofy.jpg"
                )
                return Question(
                    id = "2",
                    user = user,
                    questionText = "Australia's national sport is Cricket",
                    duration = Duration.ofSeconds(5),
                    answers = listOf(
                        Answer(
                            id = "1",
                            option = "A",
                            answerText = "True ✅",
                            isCorrect = true
                        ),
                        Answer(
                            id = "2",
                            option = "B",
                            answerText = "False ❎",
                            isCorrect = false
                        ),
                    )
                )
            }
    }
}