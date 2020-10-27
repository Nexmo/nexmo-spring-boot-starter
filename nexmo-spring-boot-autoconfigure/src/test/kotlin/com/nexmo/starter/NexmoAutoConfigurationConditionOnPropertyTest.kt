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
import org.junit.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import kotlin.test.assertNotNull

class NexmoAutoConfigurationConditionOnPropertyTest {
    val contextRunner = ApplicationContextRunner().withConfiguration(
        AutoConfigurations.of(NexmoAutoConfiguration::class.java)
    )

    @Test
    fun `when api key is missing nexmo builder is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).doesNotHaveBean(VonageClient.Builder::class.java)
        }
    }

    @Test
    fun `when api secret is missing nexmo builder is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).doesNotHaveBean(VonageClient.Builder::class.java)
        }
    }

    @Test
    fun `when api key and secret exist nexmo builder is registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).hasSingleBean(VonageClient.Builder::class.java)
            assertNotNull(it.getBean(VonageClient.Builder::class.java))
        }
    }

    @Test
    fun `when api key is missing nexmo client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).doesNotHaveBean(VonageClient::class.java)
        }
    }

    @Test
    fun `when api secret is missing nexmo client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).doesNotHaveBean(VonageClient::class.java)
        }
    }

    @Test
    fun `when api key and secret exist nexmo client is registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).hasSingleBean(VonageClient::class.java)
            assertNotNull(it.getBean(VonageClient::class.java))
        }
    }

    @Test
    fun `when api key is missing account client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).doesNotHaveBean(AccountClient::class.java)
        }
    }

    @Test
    fun `when api secret is missing account client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).doesNotHaveBean(AccountClient::class.java)
        }
    }

    @Test
    fun `when api key and secret exist account client is registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).hasSingleBean(AccountClient::class.java)
            assertNotNull(it.getBean(AccountClient::class.java))
        }
    }

    @Test
    fun `when api key is missing application client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).doesNotHaveBean(ApplicationClient::class.java)
        }
    }

    @Test
    fun `when api secret is missing application client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).doesNotHaveBean(ApplicationClient::class.java)
        }
    }

    @Test
    fun `when api key and secret exist application client is registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).hasSingleBean(ApplicationClient::class.java)
            assertNotNull(it.getBean(ApplicationClient::class.java))
        }
    }

    @Test
    fun `when api key is missing conversion client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).doesNotHaveBean(ConversionClient::class.java)
        }
    }

    @Test
    fun `when api secret is missing conversion client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).doesNotHaveBean(ConversionClient::class.java)
        }
    }

    @Test
    fun `when api key and secret exist conversion client is registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).hasSingleBean(ConversionClient::class.java)
            assertNotNull(it.getBean(ConversionClient::class.java))
        }
    }

    @Test
    fun `when api key is missing insight client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).doesNotHaveBean(InsightClient::class.java)
        }
    }

    @Test
    fun `when api secret is missing insight client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).doesNotHaveBean(InsightClient::class.java)
        }
    }

    @Test
    fun `when api key and secret exist insight client is registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).hasSingleBean(InsightClient::class.java)
            assertNotNull(it.getBean(InsightClient::class.java))
        }
    }

    @Test
    fun `when api key is missing numbers client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).doesNotHaveBean(NumbersClient::class.java)
        }
    }

    @Test
    fun `when api secret is missing numbers client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).doesNotHaveBean(NumbersClient::class.java)
        }
    }

    @Test
    fun `when api key and secret exist numbers client is registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).hasSingleBean(NumbersClient::class.java)
            assertNotNull(it.getBean(NumbersClient::class.java))
        }
    }

    @Test
    fun `when api key is missing redact client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).doesNotHaveBean(RedactClient::class.java)
        }
    }

    @Test
    fun `when api secret is missing redact client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).doesNotHaveBean(RedactClient::class.java)
        }
    }

    @Test
    fun `when api key and secret exist redact client is registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).hasSingleBean(RedactClient::class.java)
            assertNotNull(it.getBean(RedactClient::class.java))
        }
    }

    @Test
    fun `when api key is missing sms client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).doesNotHaveBean(SmsClient::class.java)
        }
    }

    @Test
    fun `when api secret is missing sms client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).doesNotHaveBean(SmsClient::class.java)
        }
    }

    @Test
    fun `when api key and secret exist sms client is registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).hasSingleBean(SmsClient::class.java)
            assertNotNull(it.getBean(SmsClient::class.java))
        }
    }

    @Test
    fun `when api key is missing sns client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).doesNotHaveBean(SnsClient::class.java)
        }
    }

    @Test
    fun `when api secret is missing sns client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).doesNotHaveBean(SnsClient::class.java)
        }
    }

    @Test
    fun `when api key and secret exist sns client is registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).hasSingleBean(SnsClient::class.java)
            assertNotNull(it.getBean(SnsClient::class.java))
        }
    }

    @Test
    fun `when api key is missing verify client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).doesNotHaveBean(VerifyClient::class.java)
        }
    }

    @Test
    fun `when api secret is missing verify client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).doesNotHaveBean(VerifyClient::class.java)
        }
    }

    @Test
    fun `when api key and secret exist verify client is registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).hasSingleBean(VerifyClient::class.java)
            assertNotNull(it.getBean(VerifyClient::class.java))
        }
    }

    @Test
    fun `when api key is missing voice client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.application-id=id"
        ).run {
            assertThat(it).doesNotHaveBean(VoiceClient::class.java)
        }
    }

    @Test
    fun `when api secret is missing voice client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=key", "nexmo.creds.application-id=id"
        ).run {
            assertThat(it).doesNotHaveBean(VoiceClient::class.java)
        }
    }

    @Test
    fun `when application id is missing voice client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).doesNotHaveBean(VoiceClient::class.java)
        }
    }

    @Test
    fun `when api key and secret and private key and application id exists voice client is registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.api-key=key", "nexmo.creds.application-id=id", "nexmo.creds.private-key-path=src/test/resources/testing.key"
        ).run {
            assertThat(it).hasSingleBean(VoiceClient::class.java)
            assertNotNull(it.getBean(VoiceClient::class.java))
        }
    }
}