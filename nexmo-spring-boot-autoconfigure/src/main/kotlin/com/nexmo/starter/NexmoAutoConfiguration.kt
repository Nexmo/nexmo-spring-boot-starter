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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnClass(NexmoClient::class)
@EnableConfigurationProperties(NexmoCredentialsProperties::class)
open class NexmoAutoConfiguration {
    @Autowired
    lateinit var nexmoProperties: NexmoCredentialsProperties

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "nexmo.creds", value = ["api-key", "secret"])
    open fun nexmoBuilder(): NexmoClient.Builder {
        if (nexmoProperties.privateKeyContents.isNotBlank() && nexmoProperties.privateKeyContents.isNotBlank()) {
            throw IllegalArgumentException("Found both private key path and private key contents. Only one option can be used at a time.")
        }

        val builder = NexmoClient.builder()
            .apiKey(nexmoProperties.apiKey)
            .apiSecret(nexmoProperties.secret)

        if (nexmoProperties.privateKeyContents.isNotBlank()) {
            builder.privateKeyContents(nexmoProperties.privateKeyContents)
        }

        if (nexmoProperties.privateKeyPath.isNotBlank()) {
            builder.privateKeyPath(nexmoProperties.privateKeyPath)
        }

        return builder
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "nexmo.creds", value = ["api-key", "secret"])
    open fun nexmoClient(builder: NexmoClient.Builder): NexmoClient = builder.build()
}
