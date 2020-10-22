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

import com.vonage.client.HttpWrapper
import com.vonage.client.VonageClient
import com.vonage.client.account.AccountClient
import com.vonage.client.application.ApplicationClient
import com.vonage.client.conversion.ConversionClient
import com.vonage.client.insight.InsightClient
import com.vonage.client.numbers.NumbersClient
import com.vonage.client.redact.RedactClient
import com.vonage.client.sms.SmsClient
import com.vonage.client.sns.SnsClient
import com.vonage.client.verify.VerifyClient
import com.vonage.client.voice.VoiceClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.test.util.ReflectionTestUtils

class NexmoAutoConfigurationConditionOnMissingBeanTest {
    val contextRunner = ApplicationContextRunner().withConfiguration(
        AutoConfigurations.of(NexmoAutoConfiguration::class.java)
    )

    @Test
    fun `when user defines a builder then only that builder is in the container`() {
        contextRunner.withUserConfiguration(
            WithVonageClientBuilder::class.java
        ).withPropertyValues(
            "nexmo.creds.api-key=api-key", "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).hasSingleBean(VonageClient.Builder::class.java)
            assertThat(it).doesNotHaveBean("vonageBuilder")
            assertThat(it).hasBean("testBuilder")

        }
    }

    @Test
    fun `when user defines a nexmo client then only that client is in the container`() {
        contextRunner.withUserConfiguration(
            WithVonageClient::class.java
        ).withPropertyValues(
            "nexmo.creds.api-key=api-key", "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).hasSingleBean(VonageClient::class.java)
            assertThat(it).doesNotHaveBean("vonageClient")
            assertThat(it).hasBean("testVonageClient")

        }
    }

    @Test
    fun `when user defines an account client then only that client is in the container`() {
        contextRunner.withUserConfiguration(
            WithAccountClient::class.java
        ).withPropertyValues(
            "nexmo.creds.api-key=api-key", "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).hasSingleBean(AccountClient::class.java)
            assertThat(it).doesNotHaveBean("accountClient")
            assertThat(it).hasBean("testAccountClient")

        }
    }

    @Test
    fun `when user defines an application client then only that client is in the container`() {
        contextRunner.withUserConfiguration(
            WithApplicationClient::class.java
        ).withPropertyValues(
            "nexmo.creds.api-key=api-key", "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).hasSingleBean(ApplicationClient::class.java)
            assertThat(it).doesNotHaveBean("applicationClient")
            assertThat(it).hasBean("testApplicationClient")

        }
    }

    @Test
    fun `when user defines a conversion client then only that client is in the container`() {
        contextRunner.withUserConfiguration(
            WithConversionClient::class.java
        ).withPropertyValues(
            "nexmo.creds.api-key=api-key", "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).hasSingleBean(ConversionClient::class.java)
            assertThat(it).doesNotHaveBean("conversionClient")
            assertThat(it).hasBean("testConversionClient")

        }
    }

    @Test
    fun `when user defines an insight client then only that client is in the container`() {
        contextRunner.withUserConfiguration(
            WithInsightClient::class.java
        ).withPropertyValues(
            "nexmo.creds.api-key=api-key", "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).hasSingleBean(InsightClient::class.java)
            assertThat(it).doesNotHaveBean("insightClient")
            assertThat(it).hasBean("testInsightClient")

        }
    }

    @Test
    fun `when user defines a numbers client then only that client is in the container`() {
        contextRunner.withUserConfiguration(
            WithNumbersClient::class.java
        ).withPropertyValues(
            "nexmo.creds.api-key=api-key", "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).hasSingleBean(NumbersClient::class.java)
            assertThat(it).doesNotHaveBean("numbersClient")
            assertThat(it).hasBean("testNumbersClient")

        }
    }

    @Test
    fun `when user defines a redact client then only that client is in the container`() {
        contextRunner.withUserConfiguration(
            WithRedactClient::class.java
        ).withPropertyValues(
            "nexmo.creds.api-key=api-key", "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).hasSingleBean(RedactClient::class.java)
            assertThat(it).doesNotHaveBean("redactClient")
            assertThat(it).hasBean("testRedactClient")

        }
    }

    @Test
    fun `when user defines an sms client then only that client is in the container`() {
        contextRunner.withUserConfiguration(
            WithSmsClient::class.java
        ).withPropertyValues(
            "nexmo.creds.api-key=api-key", "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).hasSingleBean(SmsClient::class.java)
            assertThat(it).doesNotHaveBean("smsClient")
            assertThat(it).hasBean("testSmsClient")

        }
    }

    @Test
    fun `when user defines an sns client then only that client is in the container`() {
        contextRunner.withUserConfiguration(
            WithSnsClient::class.java
        ).withPropertyValues(
            "nexmo.creds.api-key=api-key", "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).hasSingleBean(SnsClient::class.java)
            assertThat(it).doesNotHaveBean("snsClient")
            assertThat(it).hasBean("testSnsClient")

        }
    }

    @Test
    fun `when user defines a verify client then only that client is in the container`() {
        contextRunner.withUserConfiguration(
            WithVerifyClient::class.java
        ).withPropertyValues(
            "nexmo.creds.api-key=api-key", "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).hasSingleBean(VerifyClient::class.java)
            assertThat(it).doesNotHaveBean("verifyClient")
            assertThat(it).hasBean("testVerifyClient")

        }
    }

    @Test
    fun `when user defines a voice client then only that client is in the container`() {
        contextRunner.withUserConfiguration(
            WithVoiceClient::class.java
        ).withPropertyValues(
            "nexmo.creds.api-key=api-key", "nexmo.creds.secret=secret", "nexmo.creds.application-id=id"
        ).run {
            assertThat(it).hasSingleBean(VoiceClient::class.java)
            assertThat(it).doesNotHaveBean("voiceClient")
            assertThat(it).hasBean("testVoiceClient")

        }
    }

    @Test
    fun `when user defines a builder then that builder is used to build the Nexmo Client`() {
        contextRunner.withUserConfiguration(
            WithVonageClientBuilderHavingCustomBaseUri::class.java
        ).withPropertyValues(
            "nexmo.creds.api-key=api-key", "nexmo.creds.secret=secret"
        ).run {
            val client = it.getBean(VonageClient::class.java)
            val wrapper = ReflectionTestUtils.getField(client, "httpWrapper") as HttpWrapper

            assertEquals("https://example.com", wrapper.httpConfig.apiBaseUri)
        }
    }
}