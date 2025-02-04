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

package org.eclipse.edc.protocol.dsp.transform.util;

import jakarta.json.Json;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TypeUtilTest {

    @Test
    public void nodeType() {
        var expected = "type";
        var object = Json.createObjectBuilder()
                .add("@type", expected)
                .build();

        var actual = TypeUtil.nodeType(object);

        assertEquals(expected, actual);
    }

    @Test
    public void nodeType_array() {
        var expected = "type";
        var array = Json.createArrayBuilder()
                .add(expected)
                .add("foo")
                .build();
        var object = Json.createObjectBuilder()
                .add("@type", array)
                .build();

        var actual = TypeUtil.nodeType(object);

        assertEquals(expected, actual);
    }

    @Test
    public void typeValueArray_invalidNodeType() {
        var object = Json.createObjectBuilder().build();

        var actual = TypeUtil.nodeType(object);

        assertNull(actual);
    }

    @Test
    public void typeValueArray() {
        var expected = Json.createArrayBuilder()
                .add("foo")
                .add("bar")
                .build();

        var actual = TypeUtil.typeValueArray(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void typeValueArray_invalidValue() {
        var value = Json.createObjectBuilder().build();

        var actual = TypeUtil.typeValueArray(value);

        assertNull(actual);
    }

    @Test
    public void isOfExpectedType_false() {
        var expected = "type";
        var object = Json.createObjectBuilder()
                .add("@type", expected)
                .build();

        var actual = TypeUtil.isOfExpectedType(object, "invalid_type");

        Assertions.assertFalse(actual);
    }

    @Test
    public void isOfExpectedType_true() {
        var expected = "type";
        var object = Json.createObjectBuilder()
                .add("@type", expected)
                .build();

        var actual = TypeUtil.isOfExpectedType(object, expected);

        Assertions.assertTrue(actual);
    }
}