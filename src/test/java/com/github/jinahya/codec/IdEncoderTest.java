/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.github.jinahya.codec;


import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class IdEncoderTest {


    @Test(invocationCount = 1024)
    public static void encode() {

        final long decoded = ThreadLocalRandom.current().nextLong();

        final String encoded = new IdEncoder().encode(decoded);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public static void encodeUUID_nullDecoded_nullPointerException() {

        new IdEncoder().encodeUuid(null);
    }


    @Test(invocationCount = 1024)
    public static void encodeUUID() {

        final UUID decoded = UUID.randomUUID();

        final String encoded = new IdEncoder().encodeUuid(decoded);
    }

}

