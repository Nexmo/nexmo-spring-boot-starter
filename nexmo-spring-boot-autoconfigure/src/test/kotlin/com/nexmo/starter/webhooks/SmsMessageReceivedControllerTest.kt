/*
 * Copyright (c) 2011-2019 Nexmo Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.nexmo.starter.webhooks

import com.nexmo.client.incoming.MessageType
import com.nexmo.starter.events.SmsMessageReceivedEvent
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.springframework.context.ApplicationEventPublisher
import java.text.SimpleDateFormat
import java.util.*

class SmsMessageReceivedControllerTest {
    lateinit var controller: SmsMessageReceivedController
    lateinit var applicationEventPublisher: ApplicationEventPublisher

    @Before
    fun setUp() {
        applicationEventPublisher = mock(ApplicationEventPublisher::class.java)
        controller = SmsMessageReceivedController(applicationEventPublisher)
    }

    @Test
    fun `when the get method is called with a map of url parameters then an sms received event is fired`() {
        val captor = argumentCaptor<SmsMessageReceivedEvent>()

        controller.get(
            mapOf(
                "msisdn" to "msisdn",
                "to" to "to",
                "text" to "text",
                "type" to "TEXT",
                "concat" to "true",
                "concat-part" to "1",
                "concat-ref" to "2",
                "concat-total" to "3",
                "data" to "data",
                "keyword" to "keyword",
                "messageId" to "message-id",
                "message-timestamp" to "2019-06-21 14:14:01",
                "nonce" to "nonce",
                "udh" to "udh"
            )
        )

        verify(applicationEventPublisher).publishEvent(captor.capture())
        val event = captor.firstValue
        assertEquals("msisdn", event.message.msisdn)
        assertEquals("to", event.message.to)
        assertEquals("text", event.message.text)
        assertEquals(MessageType.TEXT, event.message.type)
        assertTrue(event.message.concat)
        assertEquals(1, event.message.concatPart)
        assertEquals(2, event.message.concatRef)
        assertEquals(3, event.message.concatTotal)
        assertEquals("data", event.message.data)
        assertEquals("keyword", event.message.keyword)
        assertEquals("message-id", event.message.messageId)
        assertEquals("nonce", event.message.nonce)
        assertEquals("udh", event.message.udh)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        assertEquals(dateFormat.parse("2019-06-21 14:14:01"), event.message.messageTimestamp)
    }

    @Test
    fun `when the post method is called with a map of form parameters then an sms received event is fired`() {
        val captor = argumentCaptor<SmsMessageReceivedEvent>()

        controller.post(
            mapOf(
                "msisdn" to "msisdn",
                "to" to "to",
                "text" to "text",
                "type" to "TEXT",
                "concat" to "true",
                "concat-part" to "1",
                "concat-ref" to "2",
                "concat-total" to "3",
                "data" to "data",
                "keyword" to "keyword",
                "messageId" to "message-id",
                "message-timestamp" to "2019-06-21 14:14:01",
                "nonce" to "nonce",
                "udh" to "udh"
            )
        )

        verify(applicationEventPublisher).publishEvent(captor.capture())
        val event = captor.firstValue
        assertEquals("msisdn", event.message.msisdn)
        assertEquals("to", event.message.to)
        assertEquals("text", event.message.text)
        assertEquals(MessageType.TEXT, event.message.type)
        assertTrue(event.message.concat)
        assertEquals(1, event.message.concatPart)
        assertEquals(2, event.message.concatRef)
        assertEquals(3, event.message.concatTotal)
        assertEquals("data", event.message.data)
        assertEquals("keyword", event.message.keyword)
        assertEquals("message-id", event.message.messageId)
        assertEquals("nonce", event.message.nonce)
        assertEquals("udh", event.message.udh)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        assertEquals(dateFormat.parse("2019-06-21 14:14:01"), event.message.messageTimestamp)
    }

    @Test
    fun `when the post method is called with a json parameters then an sms received event is fired`() {
        val captor = argumentCaptor<SmsMessageReceivedEvent>()

        controller.post(
            "{\"msisdn\":\"msisdn\",\"to\":\"to\",\"text\":\"text\",\"type\":\"TEXT\",\"concat\":\"true\",\"concat-part\":\"1\",\"concat-ref\":\"2\",\"concat-total\":\"3\",\"data\":\"data\",\"keyword\":\"keyword\",\"messageId\":\"message-id\",\"message-timestamp\":\"2019-06-21 14:14:01\",\"nonce\":\"nonce\",\"udh\":\"udh\"}"
        )

        verify(applicationEventPublisher).publishEvent(captor.capture())
        val event = captor.firstValue
        assertEquals("msisdn", event.message.msisdn)
        assertEquals("to", event.message.to)
        assertEquals("text", event.message.text)
        assertEquals(MessageType.TEXT, event.message.type)
        assertTrue(event.message.concat)
        assertEquals(1, event.message.concatPart)
        assertEquals(2, event.message.concatRef)
        assertEquals(3, event.message.concatTotal)
        assertEquals("data", event.message.data)
        assertEquals("keyword", event.message.keyword)
        assertEquals("message-id", event.message.messageId)
        assertEquals("nonce", event.message.nonce)
        assertEquals("udh", event.message.udh)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        assertEquals(dateFormat.parse("2019-06-21 14:14:01"), event.message.messageTimestamp)
    }
}
