/*
 *  Copyright (c) 2023 Fraunhofer Institute for Software and Systems Engineering
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Fraunhofer Institute for Software and Systems Engineering - initial API and implementation
 *
 */

package org.eclipse.edc.protocol.dsp.negotiation.dispatcher.delegate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import okhttp3.Request;
import okhttp3.Response;
import org.eclipse.edc.connector.contract.spi.types.agreement.ContractAgreementVerificationMessage;
import org.eclipse.edc.protocol.dsp.spi.dispatcher.DspHttpDispatcherDelegate;
import org.eclipse.edc.protocol.dsp.spi.serialization.JsonLdRemoteMessageSerializer;

import java.util.function.Function;

import static org.eclipse.edc.protocol.dsp.negotiation.dispatcher.NegotiationApiPaths.AGREEMENT;
import static org.eclipse.edc.protocol.dsp.negotiation.dispatcher.NegotiationApiPaths.BASE_PATH;
import static org.eclipse.edc.protocol.dsp.negotiation.dispatcher.NegotiationApiPaths.VERIFICATION;
import static org.eclipse.edc.protocol.dsp.negotiation.transform.DspNegotiationPropertyAndTypeNames.DSPACE_PREFIX;
import static org.eclipse.edc.protocol.dsp.negotiation.transform.DspNegotiationPropertyAndTypeNames.DSPACE_SCHEMA;

/**
 * Delegate for dispatching contract agreement verification message as defined in the dataspace protocol specification.
 */
public class ContractAgreementVerificationMessageHttpDelegate extends DspHttpDispatcherDelegate<ContractAgreementVerificationMessage, Object> {

    public ContractAgreementVerificationMessageHttpDelegate(JsonLdRemoteMessageSerializer serializer) {
        super(serializer);
    }

    @Override
    public Class<ContractAgreementVerificationMessage> getMessageType() {
        return ContractAgreementVerificationMessage.class;
    }

    /**
     * Sends a contract agreement verification message. The request body is constructed as defined in
     * the dataspace protocol. The request is sent to the remote component using the path from the http
     * binding.
     *
     * @param message the message.
     * @return the built okhttp request.
     */
    @Override
    public Request buildRequest(ContractAgreementVerificationMessage message) {
        return buildRequest(message, BASE_PATH + message.getProcessId() + AGREEMENT + VERIFICATION, jsonLdContext());
    }

    /**
     * Parses the response to an agreement verification message. The JSON-LD structure from the response
     * body is expanded and returned.
     *
     * @return a function that contains the response body or null.
     */
    @Override
    public Function<Response, Object> parseResponse() {
        return response -> null;
    }

    // TODO refactor according to https://github.com/eclipse-edc/Connector/issues/2763
    private JsonObject jsonLdContext() {
        return Json.createObjectBuilder()
                .add(DSPACE_PREFIX, DSPACE_SCHEMA)
                .build();
    }
}
