package com.github.jinahya.codec;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Character.MAX_RADIX;
import static java.lang.Integer.parseInt;

@Slf4j
class IdCodecNumeric {

    static String encode2(int decoded) {
        if (true) {
            return Integer.toString(((decoded >> 4) & 0xF) + 20, MAX_RADIX) + Integer.toString((decoded & 0xF) + 20, MAX_RADIX);
        }
        int v = 0;
        for (int i = 0; i < 2; i++) {
            v <<= 5;
            v |= 0b10000 | (decoded & 0b1111);
            decoded >>= 4;
        }
        return Integer.toString(v, MAX_RADIX);
    }

    static int decode2(final String encoded) {
        assert encoded != null;
        assert encoded.length() == 2;
        if (true) {
            return (Integer.parseInt(encoded.substring(0, 1), MAX_RADIX) - 20) << 4 | Integer.parseInt(encoded.substring(1), MAX_RADIX) - 20;
        }
        int decoded = 0;
        int v = parseInt(encoded, MAX_RADIX);
        for (int i = 0; i < 2; i++) {
            decoded <<= 4;
            decoded |= v & 0b1111;
            v >>= 5;
        }
        return decoded;
    }
}
