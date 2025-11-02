package media.barney.utils.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ByteUnitTest {

    /**
     * Tolerance for comparing floating-point results, reflecting the precision of
     * the conversion helpers that return double values.
     */
    private static final double DELTA = 1e-9;

    private static Stream<ByteUnit> allByteUnits() {
        return Stream.of(ByteUnit.values());
    }

    private static Stream<Arguments> byteAndBitUnits() {
        return Stream.of(ByteUnit.values())
                .flatMap(target -> Stream.of(BitUnit.values()).map(source -> Arguments.of(target, source)));
    }

    private static Stream<Arguments> bitAndByteUnits() {
        return Stream.of(BitUnit.values())
                .flatMap(target -> Stream.of(ByteUnit.values()).map(source -> Arguments.of(target, source)));
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

    @ParameterizedTest(name = "{0} converts binary scaled bytes")
    @MethodSource("allByteUnits")
    @DisplayName("Byte units convert to binary byte scales")
    void convertsToBinaryByteScales(ByteUnit unit) {
        double value = 7.5d;
        double bytes = value * bytesPerUnit(unit);

        assertEquals(bytes / ByteUnit.C_KIB, unit.toKiB(value), DELTA);
        assertEquals(bytes / ByteUnit.C_MIB, unit.toMiB(value), DELTA);
        assertEquals(bytes / ByteUnit.C_GIB, unit.toGiB(value), DELTA);
        assertEquals(bytes / ByteUnit.C_TIB, unit.toTiB(value), DELTA);
        assertEquals(bytes / ByteUnit.C_PIB, unit.toPiB(value), DELTA);
    }

    @ParameterizedTest(name = "{0} converts decimal scaled bytes")
    @MethodSource("allByteUnits")
    @DisplayName("Byte units convert to decimal byte scales")
    void convertsToDecimalByteScales(ByteUnit unit) {
        double value = 3.25d;
        double bytes = value * bytesPerUnit(unit);

        assertEquals(bytes / ByteUnit.C_KB, unit.toKB(value), DELTA);
        assertEquals(bytes / ByteUnit.C_MB, unit.toMB(value), DELTA);
        assertEquals(bytes / ByteUnit.C_GB, unit.toGB(value), DELTA);
        assertEquals(bytes / ByteUnit.C_TB, unit.toTB(value), DELTA);
        assertEquals(bytes / ByteUnit.C_PB, unit.toPB(value), DELTA);
    }

    @ParameterizedTest(name = "{0} converts to bit based scales")
    @MethodSource("allByteUnits")
    @DisplayName("Byte units convert to bit scales")
    void convertsToBitScales(ByteUnit unit) {
        double value = 5.0d;
        double bytes = value * bytesPerUnit(unit);
        double bits = bytes * Byte.SIZE;
        int wordSize = 16;
        double wordBits = bytes * wordSize;

        assertEquals(bits, unit.toBits(value), DELTA);
        assertEquals(wordBits, unit.toBits(value, wordSize), DELTA);

        assertEquals(bits / BitUnit.C_KIBIT, unit.toKibit(value), DELTA);
        assertEquals(bits / BitUnit.C_MIBIT, unit.toMibit(value), DELTA);
        assertEquals(bits / BitUnit.C_GIBIT, unit.toGibit(value), DELTA);
        assertEquals(bits / BitUnit.C_TIBIT, unit.toTibit(value), DELTA);
        assertEquals(bits / BitUnit.C_PIBIT, unit.toPibit(value), DELTA);

        assertEquals(wordBits / BitUnit.C_KIBIT, unit.toKibit(value, wordSize), DELTA);
        assertEquals(wordBits / BitUnit.C_MIBIT, unit.toMibit(value, wordSize), DELTA);
        assertEquals(wordBits / BitUnit.C_GIBIT, unit.toGibit(value, wordSize), DELTA);
        assertEquals(wordBits / BitUnit.C_TIBIT, unit.toTibit(value, wordSize), DELTA);
        assertEquals(wordBits / BitUnit.C_PIBIT, unit.toPibit(value, wordSize), DELTA);

        assertEquals(bits / BitUnit.C_KBIT, unit.toKbit(value), DELTA);
        assertEquals(bits / BitUnit.C_MBIT, unit.toMbit(value), DELTA);
        assertEquals(bits / BitUnit.C_GBIT, unit.toGbit(value), DELTA);
        assertEquals(bits / BitUnit.C_TBIT, unit.toTbit(value), DELTA);
        assertEquals(bits / BitUnit.C_PBIT, unit.toPbit(value), DELTA);

        assertEquals(wordBits / BitUnit.C_KBIT, unit.toKbit(value, wordSize), DELTA);
        assertEquals(wordBits / BitUnit.C_MBIT, unit.toMbit(value, wordSize), DELTA);
        assertEquals(wordBits / BitUnit.C_GBIT, unit.toGbit(value, wordSize), DELTA);
        assertEquals(wordBits / BitUnit.C_TBIT, unit.toTbit(value, wordSize), DELTA);
        assertEquals(wordBits / BitUnit.C_PBIT, unit.toPbit(value, wordSize), DELTA);
    }

    @ParameterizedTest(name = "{1} to {0}")
    @MethodSource("byteAndBitUnits")
    @DisplayName("Byte unit converts values originating from bit units")
    void convertsFromBitUnits(ByteUnit target, BitUnit source) {
        double value = 2.75d;
        double bits = value * bitsPerUnit(source);
        double bytes = bits / Byte.SIZE;
        double expected = bytes / bytesPerUnit(target);

        assertEquals(expected, target.convert(value, source), DELTA);

        int wordSize = 12;
        double customBytes = bits / wordSize;
        assertEquals(customBytes / bytesPerUnit(target), target.convert(value, source, wordSize), DELTA);
    }

    @ParameterizedTest(name = "{1} to {0}")
    @MethodSource("bitAndByteUnits")
    @DisplayName("Bit unit converts values originating from byte units")
    void convertsByteInputsToBits(BitUnit target, ByteUnit source) {
        double value = 9.125d;
        double bytes = value * bytesPerUnit(source);
        double bits = bytes * Byte.SIZE;
        double expected = bits / bitsPerUnit(target);

        assertEquals(expected, target.convert(value, source), DELTA);

        int wordSize = 20;
        double wordBits = bytes * wordSize;
        assertEquals(wordBits / bitsPerUnit(target), target.convert(value, source, wordSize), DELTA);
    }

    @ParameterizedTest
    @MethodSource("allByteUnits")
    @DisplayName("Byte units expose their identity constants via values()")
    void enumProvidesSelf(ByteUnit unit) {
        assertSame(unit, ByteUnit.valueOf(unit.name()));
    }

    @ParameterizedTest
    @MethodSource("allByteUnits")
    @DisplayName("safeMulti handles multiplication extremes")
    void safeMultiCoversBranches(ByteUnit unit) {
        assertEquals(Double.MAX_VALUE, ByteUnit.safeMulti(Double.MAX_VALUE, 2), DELTA);
        assertEquals(Double.MIN_VALUE, ByteUnit.safeMulti(-Double.MAX_VALUE, 2), DELTA);
        assertEquals(6d, ByteUnit.safeMulti(2d, 3d), DELTA);
    }
}
