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
import org.jetbrains.annotations.NotNull
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy

@Configuration
@ConditionalOnClass(NexmoClient::class)
@EnableConfigurationProperties(NexmoCredentialsProperties::class)
open class NexmoAutoConfiguration(private val nexmoCredentialsProperties: NexmoCredentialsProperties) {
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "nexmo.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun nexmoBuilder(): NexmoClient.Builder {
        if (nexmoCredentialsProperties.privateKeyContents.isNotBlank() && nexmoCredentialsProperties.privateKeyPath.isNotBlank()) {
            throw IllegalArgumentException("Found both private key path and private key contents. Only one option can be used at a time.")
        }

        val builder = NexmoClient.builder()
            .apiKey(nexmoCredentialsProperties.apiKey)
            .apiSecret(nexmoCredentialsProperties.secret)

        if (nexmoCredentialsProperties.privateKeyContents.isNotBlank()) {
            builder.privateKeyContents(nexmoCredentialsProperties.privateKeyContents)
        }

        if (nexmoCredentialsProperties.privateKeyPath.isNotBlank()) {
            builder.privateKeyPath(nexmoCredentialsProperties.privateKeyPath)
        }

        if (nexmoCredentialsProperties.applicationId.isNotBlank()) {
            builder.applicationId(nexmoCredentialsProperties.applicationId)
        }

        if (nexmoCredentialsProperties.signature.isNotBlank()) {
            builder.signatureSecret(nexmoCredentialsProperties.signature)
        }

        return builder
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "nexmo.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun nexmoClient(builder: NexmoClient.Builder): NexmoClient = builder.build()

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "nexmo.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun accountClient(nexmoClient: NexmoClient): AccountClient = nexmoClient.accountClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "nexmo.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun applicationClient(nexmoClient: NexmoClient): ApplicationClient = nexmoClient.applicationClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "nexmo.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun conversionClient(nexmoClient: NexmoClient): ConversionClient = nexmoClient.conversionClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "nexmo.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun insightClient(nexmoClient: NexmoClient): InsightClient = nexmoClient.insightClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "nexmo.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun numbersClient(nexmoClient: NexmoClient): NumbersClient = nexmoClient.numbersClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "nexmo.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun redactClient(nexmoClient: NexmoClient): RedactClient = nexmoClient.redactClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "nexmo.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun smsClient(nexmoClient: NexmoClient): SmsClient = nexmoClient.smsClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "nexmo.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun snsClient(nexmoClient: NexmoClient): SnsClient = nexmoClient.snsClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "nexmo.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun verifyClient(nexmoClient: NexmoClient): VerifyClient = nexmoClient.verifyClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "nexmo.creds", value = ["api-key", "secret", "application-id"])
    @Lazy
    @NotNull
    open fun voiceClient(nexmoClient: NexmoClient): VoiceClient = nexmoClient.voiceClient
}
