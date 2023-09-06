package com.github.jinahya.codec;

final class IdCodecConstraints {

    static int requireValidScale(final int scale) {
        if (scale < IdCodecConstants.MIN_SCALE) {
            throw new IllegalArgumentException("scale(" + scale + ") < " + IdCodecConstants.MIN_SCALE);
        }
        if (scale > IdCodecConstants.MAX_SCALE) {
            throw new IllegalArgumentException("scale(" + scale + ") > " + IdCodecConstants.MAX_SCALE);
        }
        return scale;
    }

    static int requireValidRadix(final int radix) {
        if (radix < IdCodecConstants.MIN_RADIX) {
            throw new IllegalArgumentException("radix(" + radix + ") < " + IdCodecConstants.MIN_RADIX);
        }
        if (radix > IdCodecConstants.MAX_RADIX) {
            throw new IllegalArgumentException("radix(" + radix + ") > " + IdCodecConstants.MAX_RADIX);
        }
        return radix;
    }

    private IdCodecConstraints() {
        throw new AssertionError("instantiation is not allowed");
    }
}
