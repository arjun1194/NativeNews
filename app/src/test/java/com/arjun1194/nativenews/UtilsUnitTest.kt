package com.arjun1194.nativenews

import com.arjun1194.nativenews.utils.isTimeGreaterThan
import com.arjun1194.nativenews.utils.toDate
import org.junit.Test

class UtilsUnitTest {
    @Test
    fun toDate_isCorrect() {
        val testDate = "2021-02-15T03:57:13Z"
        val expextedDate = "Mon, 15 Feb 2021"
        assert(testDate.toDate() == expextedDate )
    }

    @Test
    fun isTimeGreaterThan_isCorrect(){
        // make sure test date is atleast 2 hours after current Date
        val testDate = "1644909198000"
        assert(!testDate.isTimeGreaterThan(2))
    }
}

