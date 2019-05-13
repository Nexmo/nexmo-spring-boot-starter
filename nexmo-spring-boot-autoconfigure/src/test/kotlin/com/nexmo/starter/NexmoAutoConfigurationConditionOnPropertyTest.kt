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
import com.nexmo.client.account.AccountClient
import com.nexmo.client.applications.ApplicationClient
import com.nexmo.client.conversion.ConversionClient
import com.nexmo.client.insight.InsightClient
import com.nexmo.client.numbers.NumbersClient
import com.nexmo.client.redact.RedactClient
import com.nexmo.client.sms.SmsClient
import com.nexmo.client.sns.SnsClient
import com.nexmo.client.verify.VerifyClient
import com.nexmo.client.voice.VoiceClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner

class NexmoAutoConfigurationConditionOnPropertyTest {
    val contextRunner = ApplicationContextRunner().withConfiguration(
        AutoConfigurations.of(NexmoAutoConfiguration::class.java)
    )

    @Test
    fun `when api key is missing nexmo builder is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).doesNotHaveBean(NexmoClient.Builder::class.java)
        }
    }

    @Test
    fun `when api secret is missing nexmo builder is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).doesNotHaveBean(NexmoClient.Builder::class.java)
        }
    }

    @Test
    fun `when api key and secret exist nexmo builder is registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).hasSingleBean(NexmoClient.Builder::class.java)
        }
    }

    @Test
    fun `when api key is missing nexmo client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret"
        ).run {
            assertThat(it).doesNotHaveBean(NexmoClient::class.java)
        }
    }

    @Test
    fun `when api secret is missing nexmo client is not registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).doesNotHaveBean(NexmoClient::class.java)
        }
    }

    @Test
    fun `when api key and secret exist nexmo client is registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.api-key=key"
        ).run {
            assertThat(it).hasSingleBean(NexmoClient::class.java)
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
    fun `when api key and secret and application id exists voice client is registered`() {
        contextRunner.withPropertyValues(
            "nexmo.creds.secret=secret", "nexmo.creds.api-key=key", "nexmo.creds.application-id=id"
        ).run {
            assertThat(it).hasSingleBean(VoiceClient::class.java)
        }
    }
}