/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.openmessaging.consumer;

import io.openmessaging.exception.OMSRuntimeException;
import io.openmessaging.message.Message;
import java.util.List;

/**
 * A message listener can implement this {@code BatchMessageListener} interface and register itself to a consumer
 * instance to asynchronously receive messages.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface BatchMessageListener {
    /**
     * Callback method to receive incoming messages.
     * <p>
     * A message listener should handle different types of {@code BatchMessage}.
     *
     * @param batchMessage the received batchMessage.
     */
    void onReceived(List<Message> batchMessage, Context context);

    interface Context {
        /**
         * Acknowledges the specified and consumed message, which is related to this {@code MessageContext}.
         * <p>
         * Messages that have been received but not acknowledged may be redelivered.
         *
         * @throws OMSRuntimeException if the consumer fails to acknowledge the messages due to some internal error.
         */
        void success(MessageReceipt... messages);

        /**
         * Acknowledges all messages in this batch, which is related to this {@code MessageContext}.
         * <p>
         *
         * @throws OMSRuntimeException if the consumer fails to acknowledge the messages due to some internal error.
         */
        void ack();
    }
}
