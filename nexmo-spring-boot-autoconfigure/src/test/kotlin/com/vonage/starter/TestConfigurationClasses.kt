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
package com.vonage.starter

import com.vonage.client.HttpConfig
import com.vonage.client.VonageClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class WithVonageClientBuilder {
    @Bean(name = ["testBuilder"])
    open fun builder(): VonageClient.Builder {
        return VonageClient.builder()
    }
}

@Configuration
open class WithVonageClientBuilderHavingCustomBaseUri {
    @Bean(name = ["testBuilder"])
    open fun builder(): VonageClient.Builder {
        return VonageClient.builder().httpConfig(HttpConfig.builder().baseUri("https://example.com").build())
    }
}

@Configuration
open class WithVonageClient {
    @Bean(name = ["testVonageClient"])
    open fun client() = VonageClient.builder().build()
}

@Configuration
open class WithAccountClient {
    @Bean(name = ["testAccountClient"])
    open fun builder() = VonageClient.builder().build().accountClient
}

@Configuration
open class WithApplicationClient {
    @Bean(name = ["testApplicationClient"])
    open fun builder() = VonageClient.builder().build().applicationClient
}

@Configuration
open class WithConversionClient {
    @Bean(name = ["testConversionClient"])
    open fun builder() = VonageClient.builder().build().conversionClient
}

@Configuration
open class WithInsightClient {
    @Bean(name = ["testInsightClient"])
    open fun builder() = VonageClient.builder().build().insightClient
}

@Configuration
open class WithNumbersClient {
    @Bean(name = ["testNumbersClient"])
    open fun builder() = VonageClient.builder().build().numbersClient
}

@Configuration
open class WithRedactClient {
    @Bean(name = ["testRedactClient"])
    open fun builder() = VonageClient.builder().build().redactClient
}

@Configuration
open class WithSmsClient {
    @Bean(name = ["testSmsClient"])
    open fun builder() = VonageClient.builder().build().smsClient
}

@Configuration
open class WithSnsClient {
    @Bean(name = ["testSnsClient"])
    open fun builder() = VonageClient.builder().build().snsClient
}

@Configuration
open class WithVerifyClient {
    @Bean(name = ["testVerifyClient"])
    open fun builder() = VonageClient.builder().build().verifyClient
}

@Configuration
open class WithVoiceClient {
    @Bean(name = ["testVoiceClient"])
    open fun builder() = VonageClient.builder().build().voiceClient
}