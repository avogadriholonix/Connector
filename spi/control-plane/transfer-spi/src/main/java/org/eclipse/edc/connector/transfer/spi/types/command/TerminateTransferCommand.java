/*
 *  Copyright (c) 2023 Bayerische Motoren Werke Aktiengesellschaft (BMW AG)
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Bayerische Motoren Werke Aktiengesellschaft (BMW AG) - initial API and implementation
 *
 */

package org.eclipse.edc.connector.transfer.spi.types.command;

/**
 * Terminates a transfer process by sending it to the TERMINATED state
 */
public class TerminateTransferCommand extends SingleTransferProcessCommand {

    private final String reason;

    public TerminateTransferCommand(String transferProcessId, String reason) {
        super(transferProcessId);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
