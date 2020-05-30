package com.github.jinahya.codec;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Character.MAX_RADIX;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.util.Objects.requireNonNull;

@Slf4j
class CouponNumber {

    private static final int S_MAX_VALUE_DIGITS = Short.toString(Short.MAX_VALUE).length();

    private static final int I_MAX_VALUE_DIGITS = Integer.toString(Integer.MAX_VALUE).length();

    private static final int L_MAX_VALUE_DIGITS = Long.toString(Long.MAX_VALUE).length();

    static String encodeInt4(int decoded) {
        assert decoded >= 0;
        assert decoded < 65566;
        int v = 0;
        for (int i = 0; i < 4; i++) {
            v <<= 5;
            v |= 0b10000 | (decoded & 0b1111);
            decoded >>= 4;
        }
        return Integer.toString(v, MAX_RADIX);
    }

    static int decodeInt4(final String encoded) {
        assert encoded != null;
        assert encoded.length() == 4;
        int decoded = 0;
        int v = parseInt(encoded, MAX_RADIX);
        for (int i = 0; i < 4; i++) {
            decoded <<= 4;
            decoded |= v & 0b1111;
            v >>= 5;
        }
        return decoded;
    }

    static String encodeInt8(final int decoded) {
        return encodeInt4(decoded >>> Short.SIZE).concat(encodeInt4(decoded & 0xFFFF));
    }

    static int decodeInt8(final String encoded) {
        if (requireNonNull(encoded, "encoded is null").length() != 8) {
            throw new IllegalArgumentException("encoded.length(" + encoded.length() + ") != 8");
        }
        return decodeInt4(encoded.substring(0, 4)) << Short.SIZE | decodeInt4(encoded.substring(4));
    }

    static String encodeInt7(long decoded) {
        long v = 0;
        for (int i = 0; i < 3; i++) {
            v <<= 5;
            v |= 0b10000 | (decoded & 0b1111);
            decoded >>= 4;
        }
        for (int i = 0; i < 4; i++) {
            v <<= 5;
            v |= decoded & 0b11111;
            decoded >>= 5;
        }
        final String encoded = Long.toString(v, MAX_RADIX);
        assert encoded.length() == 7;
        return encoded;
    }

    static long decodeInt7(final String encoded) {
        if (requireNonNull(encoded, "encoded is null").length() != 7) {
            throw new IllegalArgumentException("encoded.length(" + encoded.length() + ") != 7");
        }
        long decoded = 0;
        long v = parseLong(encoded, MAX_RADIX);
        for (int i = 0; i < 4; i++) {
            decoded <<= 5;
            decoded |= v & 0b11111;
            v >>= 5;
        }
        for (int i = 0; i < 3; i++) {
            decoded <<= 4;
            decoded |= v & 0b1111;
            v >>= 5;
        }
        return decoded;
    }
}
