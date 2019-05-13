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
package com.nexmo.starter

import com.nexmo.client.NexmoClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test
import org.springframework.beans.factory.BeanCreationException
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.test.util.ReflectionTestUtils
import java.io.File
import java.nio.charset.Charset

class NexmoAutoConfigurationBuilderTest {
    val keyContents = File("src/test/resources/testing.key").readText()

    val contextRunner = ApplicationContextRunner().withConfiguration(
        AutoConfigurations.of(NexmoAutoConfiguration::class.java)
    )

    @Test
    fun `when api key and secret are defined then builder has api key and secret`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=api-key",
            "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).hasSingleBean(NexmoClient.Builder::class.java)
            // Since the bean is @Lazy, it needs to be requested.
            val builder = it.getBean("nexmoBuilder")
            val apiKey = ReflectionTestUtils.getField(builder, "apiKey") as String
            val secret = ReflectionTestUtils.getField(builder, "apiSecret") as String

            assertEquals("api-key", apiKey)
            assertEquals("secret", secret)
        }
    }

    @Test
    fun `when application id is defined then builder has application id`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=api-key",
            "nexmo.creds.secret=secret",
            "nexmo.creds.application-id=app-id"
        ).run {
            assertThat(it).hasSingleBean(NexmoClient.Builder::class.java)
            // Since the bean is @Lazy, it needs to be requested.
            val builder = it.getBean("nexmoBuilder")
            val appId = ReflectionTestUtils.getField(builder, "applicationId") as String

            assertEquals("app-id", appId)
        }
    }

    @Test(expected = BeanCreationException::class)
    fun `when private key contents and private key path are both defined then an exception is thrown`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=api-key",
            "nexmo.creds.secret=secret",
            "nexmo.creds.private-key-path=src/test/resources/testing.key",
            "nexmo.creds.private-key-contents=${keyContents}"
        ).run {
            assertThat(it).hasSingleBean(NexmoClient.Builder::class.java)
            // Since the bean is @Lazy, it needs to be requested.
            it.getBean("nexmoBuilder")
        }
    }

    @Test
    fun `when private key contents are defined then builder has private key contents`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=api-key",
            "nexmo.creds.secret=secret",
            "nexmo.creds.private-key-contents=${keyContents}"
        ).run {
            assertThat(it).hasSingleBean(NexmoClient.Builder::class.java)
            // Since the bean is @Lazy, it needs to be requested.
            val builder = it.getBean("nexmoBuilder")
            val builderKeyContents = ReflectionTestUtils.getField(builder, "privateKeyContents") as ByteArray

            assertEquals(keyContents, builderKeyContents.toString(Charset.forName("UTF-8")))
        }
    }

    @Test
    fun `when private key path is defined then builder has private key contents`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=api-key",
            "nexmo.creds.secret=secret",
            "nexmo.creds.private-key-path=src/test/resources/testing.key"
        ).run {
            assertThat(it).hasSingleBean(NexmoClient.Builder::class.java)
            // Since the bean is @Lazy, it needs to be requested.
            val builder = it.getBean("nexmoBuilder")
            val builderKeyContents = ReflectionTestUtils.getField(builder, "privateKeyContents") as ByteArray

            assertEquals(keyContents, builderKeyContents.toString(Charset.forName("UTF-8")))
        }
    }
}