package com.moon.libbase

import com.moon.libbase.entity.AnswerRequest
import org.junit.Test

/**
 * @author ry
 * @date 2019-11-14
 */
class KotlinTest {

    @Test
    fun testSplit() {
        val str = "1"
        val split = str.split("-")
        println(split)
        println(split[0])

        val request = AnswerRequest(1,0,null)
        println(request.getNumTitle())
        println(request.getNumItem())
    }
    @Test
    fun test(){
        val  list = listOf<String>("1","2")
        val str=list.get(0)
        val tempList = list.toMutableList()
        tempList.remove(str)
        println(tempList)
        println(list)
    }

}