/*
 * Copyright (c) 2011-2019 Vonage Inc
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
package com.vonage.starter

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
import org.jetbrains.annotations.NotNull
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy

@Configuration
@ConditionalOnClass(VonageClient::class)
@EnableConfigurationProperties(VonageCredentialsProperties::class)
open class VonageAutoConfiguration(
    private val vonageProperties: VonageCredentialsProperties
) {
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "vonage.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun vonageBuilder(): VonageClient.Builder {
        if (vonageProperties.privateKeyContents.isNotBlank() && vonageProperties.privateKeyPath.isNotBlank()) {
            throw IllegalArgumentException("Found both private key path and private key contents. Only one option can be used at a time.")
        }

        val builder = VonageClient.builder()
            .apiKey(vonageProperties.apiKey)
            .apiSecret(vonageProperties.secret)

        if (vonageProperties.privateKeyContents.isNotBlank()) {
            builder.privateKeyContents(vonageProperties.privateKeyContents)
        }

        if (vonageProperties.privateKeyPath.isNotBlank()) {
            builder.privateKeyPath(vonageProperties.privateKeyPath)
        }

        if (vonageProperties.applicationId.isNotBlank()) {
            builder.applicationId(vonageProperties.applicationId)
        }

        if (vonageProperties.signature.isNotBlank()) {
            builder.signatureSecret(vonageProperties.signature)
        }

        return builder
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "vonage.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun vonageClient(builder: VonageClient.Builder): VonageClient = builder.build()

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "vonage.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun accountClient(vonageClient: VonageClient): AccountClient = vonageClient.accountClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "vonage.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun applicationClient(vonageClient: VonageClient): ApplicationClient = vonageClient.applicationClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "vonage.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun conversionClient(vonageClient: VonageClient): ConversionClient = vonageClient.conversionClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "vonage.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun insightClient(vonageClient: VonageClient): InsightClient = vonageClient.insightClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "vonage.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun numbersClient(vonageClient: VonageClient): NumbersClient = vonageClient.numbersClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "vonage.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun redactClient(vonageClient: VonageClient): RedactClient = vonageClient.redactClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "vonage.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun smsClient(vonageClient: VonageClient): SmsClient = vonageClient.smsClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "vonage.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun snsClient(vonageClient: VonageClient): SnsClient = vonageClient.snsClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "vonage.creds", value = ["api-key", "secret"])
    @Lazy
    @NotNull
    open fun verifyClient(vonageClient: VonageClient): VerifyClient = vonageClient.verifyClient

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "vonage.creds", value = ["api-key", "secret", "application-id"])
    @Lazy
    @NotNull
    open fun voiceClient(vonageClient: VonageClient): VoiceClient = vonageClient.voiceClient
}
