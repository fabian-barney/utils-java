package com.barney4j.utils.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BitUnitTest {

    /**
     * Tolerance for comparing floating-point results, reflecting the precision of
     * the conversion helpers that return double values.
     */
    private static final double DELTA = 1e-9;

    private static Stream<BitUnit> allBitUnits() {
        return Stream.of(BitUnit.values());
    }

    private static Stream<Arguments> bitToBitConversions() {
        return Stream.of(BitUnit.values())
                .flatMap(target -> Stream.of(BitUnit.values()).map(source -> Arguments.of(target, source)));
    }

    private static Stream<Arguments> bitToByteConversions() {
        return Stream.of(BitUnit.values())
                .flatMap(target -> Stream.of(ByteUnit.values()).map(source -> Arguments.of(target, source)));
    }

    private static Stream<Arguments> byteToBitConversions() {
        return Stream.of(ByteUnit.values())
                .flatMap(target -> Stream.of(BitUnit.values()).map(source -> Arguments.of(target, source)));
    }

    private static double bitsPerUnit(BitUnit unit) {
        return switch (unit) {
            case BIT -> 1d;
            case KIBIT -> BitUnit.C_KIBIT;
            case MIBIT -> BitUnit.C_MIBIT;
            case GIBIT -> BitUnit.C_GIBIT;
            case TIBIT -> BitUnit.C_TIBIT;
            case PIBIT -> BitUnit.C_PIBIT;
            case KBIT -> BitUnit.C_KBIT;
            case MBIT -> BitUnit.C_MBIT;
            case GBIT -> BitUnit.C_GBIT;
            case TBIT -> BitUnit.C_TBIT;
            case PBIT -> BitUnit.C_PBIT;
        };
    }

    private static double bytesPerUnit(ByteUnit unit) {
        return switch (unit) {
            case BYTE -> 1d;
            case KIB -> ByteUnit.C_KIB;
            case MIB -> ByteUnit.C_MIB;
            case GIB -> ByteUnit.C_GIB;
            case TIB -> ByteUnit.C_TIB;
            case PIB -> ByteUnit.C_PIB;
            case KB -> ByteUnit.C_KB;
            case MB -> ByteUnit.C_MB;
            case GB -> ByteUnit.C_GB;
            case TB -> ByteUnit.C_TB;
            case PB -> ByteUnit.C_PB;
        };
    }

    @ParameterizedTest(name = "{0} exposes toBits")
    @MethodSource("allBitUnits")
    @DisplayName("Bit units convert to plain bits")
    void convertsToBits(BitUnit unit) {
        double value = 4.5d;
        double expected = value * bitsPerUnit(unit);
        assertEquals(expected, unit.toBits(value), DELTA);
    }

    @ParameterizedTest(name = "{1} to {0}")
    @MethodSource("bitToBitConversions")
    @DisplayName("Bit unit converts values originating from other bit units")
    void convertsBetweenBitUnits(BitUnit target, BitUnit source) {
        double value = 8.75d;
        double bits = value * bitsPerUnit(source);
        double expected = bits / bitsPerUnit(target);

        assertEquals(expected, target.convert(value, source), DELTA);
    }

    @ParameterizedTest(name = "{1} to {0}")
    @MethodSource("bitToByteConversions")
    @DisplayName("Bit unit converts values originating from byte units")
    void convertsByteInputs(BitUnit target, ByteUnit source) {
        double value = 6.125d;
        double bytes = value * bytesPerUnit(source);
        double bits = bytes * Byte.SIZE;
        double expected = bits / bitsPerUnit(target);

        assertEquals(expected, target.convert(value, source), DELTA);

        int wordSize = 24;
        double wordBits = bytes * wordSize;
        assertEquals(wordBits / bitsPerUnit(target), target.convert(value, source, wordSize), DELTA);
    }

    @ParameterizedTest(name = "{1} to {0}")
    @MethodSource("byteToBitConversions")
    @DisplayName("Byte unit converts values originating from bit units")
    void convertsBitInputs(ByteUnit target, BitUnit source) {
        double value = 1.375d;
        double bits = value * bitsPerUnit(source);
        double bytes = bits / Byte.SIZE;
        double expected = bytes / bytesPerUnit(target);

        assertEquals(expected, target.convert(value, source), DELTA);

        int wordSize = 32;
        double customBytes = bits / wordSize;
        assertEquals(customBytes / bytesPerUnit(target), target.convert(value, source, wordSize), DELTA);
    }

    @ParameterizedTest(name = "{0} converts to byte scales")
    @MethodSource("allBitUnits")
    @DisplayName("Bit units convert to byte representations")
    void convertsToByteScales(BitUnit unit) {
        double value = 2.0d;
        double bits = value * bitsPerUnit(unit);
        double bytes = bits / Byte.SIZE;
        int wordSize = 10;
        double wordBytes = bits / wordSize;

        assertEquals(bytes, unit.toBytes(value), DELTA);
        assertEquals(wordBytes, unit.toBytes(value, wordSize), DELTA);

        assertEquals(bytes / ByteUnit.C_KIB, unit.toKiB(value), DELTA);
        assertEquals(bytes / ByteUnit.C_MIB, unit.toMiB(value), DELTA);
        assertEquals(bytes / ByteUnit.C_GIB, unit.toGiB(value), DELTA);
        assertEquals(bytes / ByteUnit.C_TIB, unit.toTiB(value), DELTA);
        assertEquals(bytes / ByteUnit.C_PIB, unit.toPiB(value), DELTA);

        assertEquals(wordBytes / ByteUnit.C_KIB, unit.toKiB(value, wordSize), DELTA);
        assertEquals(wordBytes / ByteUnit.C_MIB, unit.toMiB(value, wordSize), DELTA);
        assertEquals(wordBytes / ByteUnit.C_GIB, unit.toGiB(value, wordSize), DELTA);
        assertEquals(wordBytes / ByteUnit.C_TIB, unit.toTiB(value, wordSize), DELTA);
        assertEquals(wordBytes / ByteUnit.C_PIB, unit.toPiB(value, wordSize), DELTA);

        assertEquals(bytes / ByteUnit.C_KB, unit.toKB(value), DELTA);
        assertEquals(bytes / ByteUnit.C_MB, unit.toMB(value), DELTA);
        assertEquals(bytes / ByteUnit.C_GB, unit.toGB(value), DELTA);
        assertEquals(bytes / ByteUnit.C_TB, unit.toTB(value), DELTA);
        assertEquals(bytes / ByteUnit.C_PB, unit.toPB(value), DELTA);

        assertEquals(wordBytes / ByteUnit.C_KB, unit.toKB(value, wordSize), DELTA);
        assertEquals(wordBytes / ByteUnit.C_MB, unit.toMB(value, wordSize), DELTA);
        assertEquals(wordBytes / ByteUnit.C_GB, unit.toGB(value, wordSize), DELTA);
        assertEquals(wordBytes / ByteUnit.C_TB, unit.toTB(value, wordSize), DELTA);
        assertEquals(wordBytes / ByteUnit.C_PB, unit.toPB(value, wordSize), DELTA);
    }

    @ParameterizedTest
    @MethodSource("allBitUnits")
    @DisplayName("Bit units expose their identity constants via values()")
    void enumProvidesSelf(BitUnit unit) {
        assertSame(unit, BitUnit.valueOf(unit.name()));
    }

    @ParameterizedTest
    @MethodSource("allBitUnits")
    @DisplayName("safeMulti handles multiplication extremes")
    void safeMultiCoversBranches(BitUnit unit) {
        assertEquals(Double.MAX_VALUE, BitUnit.safeMulti(Double.MAX_VALUE, 2), DELTA);
        assertEquals(Double.MIN_VALUE, BitUnit.safeMulti(-Double.MAX_VALUE, 2), DELTA);
        assertEquals(4d, BitUnit.safeMulti(2d, 2d), DELTA);
    }
}
