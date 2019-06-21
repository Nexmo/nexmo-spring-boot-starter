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

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner

class NexmoWebhookAutoConfigurationTest {
    val contextRunner = ApplicationContextRunner().withConfiguration(
        AutoConfigurations.of(NexmoWebhookAutoConfiguration::class.java)
    )

    @Test
    fun `when webhooks are disabled the incoming sms controller is not in the container`() {
        contextRunner.withPropertyValues(
            "nexmo.webhooks.disabled=true"
        ).run {
            assertThat(it).doesNotHaveBean(SmsMessageReceivedController::class.java)
        }
    }

    @Test
    fun `when webhooks are not disabled the incoming sms controller is in in the container`() {
        contextRunner.withPropertyValues(
            "nexmo.webhooks.disabled=false"
        ).run {
            assertThat(it).hasSingleBean(SmsMessageReceivedController::class.java)
        }
    }

    @Test
    fun `when webhooks disabled property is missing the incoming sms controller is in the container`() {
        contextRunner.withPropertyValues().run {
            assertThat(it).hasSingleBean(SmsMessageReceivedController::class.java)
        }
    }
}