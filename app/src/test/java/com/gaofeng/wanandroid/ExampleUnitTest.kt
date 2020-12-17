package com.gaofeng.wanandroid

import com.gaofeng.wanandroid.bean.Article
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val article1 = Article(
            id = 10,
            title = "article",
            desc = "desc"
        )

        val article2 = Article(
            id = 10,
            title = "article",
            desc = "desc"
        )

        println(article1 == article2)
        println(article1 === article2)


    }
}