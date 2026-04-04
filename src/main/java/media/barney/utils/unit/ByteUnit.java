package media.barney.utils.unit;


/*
 * Copyright 2011 Fabian Barney
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Objects;

/**
 * Defines binary (power-of-two) and decimal (power-of-ten) byte units and
 * provides helper methods for converting between them as well as to the
 * corresponding {@link BitUnit bit-based units}. All conversions operate on
 * {@code double} values, require non-negative finite inputs, and guard against
 * overflow using {@link #safeMulti(double, double)}.
 *
 * <p>The enum constants describe both IEC (KiB, MiB, &hellip;) and SI (kB, MB, &hellip;)
 * units so the implementation can convert values expressed with either
 * convention.</p>
 *
 * @author Fabian Barney
 */
public enum ByteUnit {

	/** Byte (B), equal to 1 byte. */
	BYTE {
		@Override
		public double toBytes(double d) { return UnitInputValidator.requireNonNegativeFinite(d); }

		@Override
		public double convert(double d, ByteUnit u) {
			ByteUnit sourceUnit = Objects.requireNonNull(u, "u");
			double value = UnitInputValidator.requireNonNegativeFinite(d);
			return sourceUnit.toBytes(value);
		}
	},

	/** Kibibyte (KiB), equal to 2^10 bytes. */
	KIB {
		@Override
		public double toBytes(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_KIB); }

		@Override
		public double convert(double d, ByteUnit u) {
			ByteUnit sourceUnit = Objects.requireNonNull(u, "u");
			double value = UnitInputValidator.requireNonNegativeFinite(d);
			return sourceUnit.toKiB(value);
		}
	},

	/** Mebibyte (MiB), equal to 2^20 bytes. */
	MIB {
		@Override
		public double toBytes(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_MIB); }

		@Override
		public double convert(double d, ByteUnit u) {
			ByteUnit sourceUnit = Objects.requireNonNull(u, "u");
			double value = UnitInputValidator.requireNonNegativeFinite(d);
			return sourceUnit.toMiB(value);
		}
	},

	/** Gibibyte (GiB), equal to 2^30 bytes. */
	GIB {
		@Override
		public double toBytes(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_GIB); }

		@Override
		public double convert(double d, ByteUnit u) {
			ByteUnit sourceUnit = Objects.requireNonNull(u, "u");
			double value = UnitInputValidator.requireNonNegativeFinite(d);
			return sourceUnit.toGiB(value);
		}
	},

	/** Tebibyte (TiB), equal to 2^40 bytes. */
	TIB {
		@Override
		public double toBytes(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_TIB); }

		@Override
		public double convert(double d, ByteUnit u) {
			ByteUnit sourceUnit = Objects.requireNonNull(u, "u");
			double value = UnitInputValidator.requireNonNegativeFinite(d);
			return sourceUnit.toTiB(value);
		}
	},

	/** Pebibyte (PiB), equal to 2^50 bytes. */
	PIB {
		@Override
		public double toBytes(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_PIB); }

		@Override
		public double convert(double d, ByteUnit u) {
			ByteUnit sourceUnit = Objects.requireNonNull(u, "u");
			double value = UnitInputValidator.requireNonNegativeFinite(d);
			return sourceUnit.toPiB(value);
		}
	},

	/** Kilobyte (kB), equal to 10^3 bytes. */
	KB {
		@Override
		public double toBytes(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_KB); }

		@Override
		public double convert(double d, ByteUnit u) {
			ByteUnit sourceUnit = Objects.requireNonNull(u, "u");
			double value = UnitInputValidator.requireNonNegativeFinite(d);
			return sourceUnit.toKB(value);
		}
	},

	/** Megabyte (MB), equal to 10^6 bytes. */
	MB {
		@Override
		public double toBytes(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_MB); }

		@Override
		public double convert(double d, ByteUnit u) {
			ByteUnit sourceUnit = Objects.requireNonNull(u, "u");
			double value = UnitInputValidator.requireNonNegativeFinite(d);
			return sourceUnit.toMB(value);
		}
	},

	/** Gigabyte (GB), equal to 10^9 bytes. */
	GB {
		@Override
		public double toBytes(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_GB); }

		@Override
		public double convert(double d, ByteUnit u) {
			ByteUnit sourceUnit = Objects.requireNonNull(u, "u");
			double value = UnitInputValidator.requireNonNegativeFinite(d);
			return sourceUnit.toGB(value);
		}
	},

	/** Terabyte (TB), equal to 10^12 bytes. */
	TB {
		@Override
		public double toBytes(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_TB); }

		@Override
		public double convert(double d, ByteUnit u) {
			ByteUnit sourceUnit = Objects.requireNonNull(u, "u");
			double value = UnitInputValidator.requireNonNegativeFinite(d);
			return sourceUnit.toTB(value);
		}
	},

	/** Petabyte (PB), equal to 10^15 bytes. */
	PB {
		@Override
		public double toBytes(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_PB); }

		@Override
		public double convert(double d, ByteUnit u) {
			ByteUnit sourceUnit = Objects.requireNonNull(u, "u");
			double value = UnitInputValidator.requireNonNegativeFinite(d);
			return sourceUnit.toPB(value);
		}
	};



	static final double C_KIB = Math.pow(2d, 10d);
	static final double C_MIB = Math.pow(2d, 20d);
	static final double C_GIB = Math.pow(2d, 30d);
	static final double C_TIB = Math.pow(2d, 40d);
	static final double C_PIB = Math.pow(2d, 50d);

	static final double C_KB = Math.pow(10d, 3d);
	static final double C_MB = Math.pow(10d, 6d);
	static final double C_GB = Math.pow(10d, 9d);
	static final double C_TB = Math.pow(10d, 12d);
	static final double C_PB = Math.pow(10d, 15d);


	private static final double MAX = Double.MAX_VALUE;



	static final double safeMulti(double d, double multi) {
		double limit = MAX / multi;

		if (d >  limit) {
			return Double.MAX_VALUE;
		}
		if (d < -limit) {
			return -Double.MAX_VALUE;
		}

		return d * multi;
	}

	
        /**
         * Converts a value in the current unit to bytes.
         *
         * @param d the value to convert
         * @return the converted value expressed in bytes
         */
        public abstract double toBytes(double d);

        /**
         * Converts a value in the current unit to kibibytes (2<sup>10</sup> bytes).
         *
         * @param d the value to convert
         * @return the converted value expressed in kibibytes
         */
        public final double toKiB(double d){
                return toBytes(d) / C_KIB;
        }

        /**
         * Converts a value in the current unit to mebibytes (2<sup>20</sup> bytes).
         *
         * @param d the value to convert
         * @return the converted value expressed in mebibytes
         */
        public final double toMiB(double d) {
                return toBytes(d) / C_MIB;
        }

        /**
         * Converts a value in the current unit to gibibytes (2<sup>30</sup> bytes).
         *
         * @param d the value to convert
         * @return the converted value expressed in gibibytes
         */
        public final double toGiB(double d) {
                return toBytes(d) / C_GIB;
        }

        /**
         * Converts a value in the current unit to tebibytes (2<sup>40</sup> bytes).
         *
         * @param d the value to convert
         * @return the converted value expressed in tebibytes
         */
        public final double toTiB(double d) {
                return toBytes(d) / C_TIB;
        }

        /**
         * Converts a value in the current unit to pebibytes (2<sup>50</sup> bytes).
         *
         * @param d the value to convert
         * @return the converted value expressed in pebibytes
         */
        public final double toPiB(double d) {
                return toBytes(d) / C_PIB;
        }


        /**
         * Converts a value in the current unit to kilobytes (10<sup>3</sup> bytes).
         *
         * @param d the value to convert
         * @return the converted value expressed in kilobytes
         */
        public final double toKB(double d) {
                return toBytes(d) / C_KB;
        }

        /**
         * Converts a value in the current unit to megabytes (10<sup>6</sup> bytes).
         *
         * @param d the value to convert
         * @return the converted value expressed in megabytes
         */
        public final double toMB(double d) {
                return toBytes(d) / C_MB;
        }

        /**
         * Converts a value in the current unit to gigabytes (10<sup>9</sup> bytes).
         *
         * @param d the value to convert
         * @return the converted value expressed in gigabytes
         */
        public final double toGB(double d) {
                return toBytes(d) / C_GB;
        }

        /**
         * Converts a value in the current unit to terabytes (10<sup>12</sup> bytes).
         *
         * @param d the value to convert
         * @return the converted value expressed in terabytes
         */
        public final double toTB(double d) {
                return toBytes(d) / C_TB;
        }

        /**
         * Converts a value in the current unit to petabytes (10<sup>15</sup> bytes).
         *
         * @param d the value to convert
         * @return the converted value expressed in petabytes
         */
        public final double toPB(double d) {
                return toBytes(d) / C_PB;
        }



        /**
         * Converts a value from another {@code ByteUnit} to this unit.
         *
         * @param d the value to convert
         * @param u the unit the value is currently expressed in
         * @return the converted value expressed in this unit
         */
        public abstract double convert(double d, ByteUnit u);

        /**
         * Converts a value expressed in the supplied {@link BitUnit} to this byte unit
         * assuming 8 bits per byte ({@link java.lang.Byte#SIZE}).
         *
         * @param d the value to convert
         * @param u the bit unit the value is expressed in
         * @return the converted value expressed in this unit
         */
        public final double convert(double d, BitUnit u) {
                return convert(d, u, Byte.SIZE);
        }

        /**
         * Converts a value expressed in the supplied {@link BitUnit} to this byte unit
         * using the provided bits-per-byte value.
         *
         * @param d the value to convert
         * @param u the bit unit the value is expressed in
         * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in this unit
         */
        public final double convert(double d, BitUnit u, int bitsPerByte){
                BitUnit sourceUnit = Objects.requireNonNull(u, "u");
                double value = UnitInputValidator.requireNonNegativeFinite(d);
                int validatedBitsPerByte = UnitInputValidator.requirePositiveBitsPerByte(bitsPerByte);
                double bytes = sourceUnit.toBits(value) / validatedBitsPerByte;
                return convert(bytes, BYTE);
        }



        /*
         * Convenience methods for cross-conversion
         */
        /**
         * Converts a value in the current unit to bits.
         *
         * @param d the value to convert
         * @return the converted value expressed in bits
         */
        public final double toBits(double d) {
                return BitUnit.BIT.convert(d, this);
        }

        /**
         * Converts a value in the current unit to bits using the provided bits-per-byte value.
         *
         * @param d the value to convert
         * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in bits
         */
        public final double toBits(double d, int bitsPerByte) {
                return BitUnit.BIT.convert(d, this, bitsPerByte);
        }


        /**
         * Converts a value in the current unit to kibibits (2<sup>10</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in kibibits
         */
        public final double toKibit(double d){
                return BitUnit.KIBIT.convert(d, this);
        }

        /**
         * Converts a value in the current unit to mebibits (2<sup>20</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in mebibits
         */
        public final double toMibit(double d) {
                return BitUnit.MIBIT.convert(d, this);
        }

        /**
         * Converts a value in the current unit to gibibits (2<sup>30</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in gibibits
         */
        public final double toGibit(double d) {
                return BitUnit.GIBIT.convert(d, this);
        }

        /**
         * Converts a value in the current unit to tebibits (2<sup>40</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in tebibits
         */
        public final double toTibit(double d) {
                return BitUnit.TIBIT.convert(d, this);
        }

        /**
         * Converts a value in the current unit to pebibits (2<sup>50</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in pebibits
         */
        public final double toPibit(double d) {
                return BitUnit.PIBIT.convert(d, this);
        }

        /**
         * Converts a value in the current unit to kibibits using the provided bits-per-byte value.
         *
         * @param d the value to convert
         * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in kibibits
         */
        public final double toKibit(double d, int bitsPerByte){
                return BitUnit.KIBIT.convert(d, this, bitsPerByte);
        }

        /**
         * Converts a value in the current unit to mebibits using the provided bits-per-byte value.
         *
         * @param d the value to convert
         * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in mebibits
         */
        public final double toMibit(double d, int bitsPerByte) {
                return BitUnit.MIBIT.convert(d, this, bitsPerByte);
        }

        /**
         * Converts a value in the current unit to gibibits using the provided bits-per-byte value.
         *
         * @param d the value to convert
         * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in gibibits
         */
        public final double toGibit(double d, int bitsPerByte) {
                return BitUnit.GIBIT.convert(d, this, bitsPerByte);
        }

        /**
         * Converts a value in the current unit to tebibits using the provided bits-per-byte value.
         *
         * @param d the value to convert
         * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in tebibits
         */
        public final double toTibit(double d, int bitsPerByte) {
                return BitUnit.TIBIT.convert(d, this, bitsPerByte);
        }

        /**
         * Converts a value in the current unit to pebibits using the provided bits-per-byte value.
         *
         * @param d the value to convert
         * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in pebibits
         */
        public final double toPibit(double d, int bitsPerByte) {
                return BitUnit.PIBIT.convert(d, this, bitsPerByte);
        }


        /**
         * Converts a value in the current unit to kilobits (10<sup>3</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in kilobits
         */
        public final double toKbit(double d) {
                return BitUnit.KBIT.convert(d, this);
        }

        /**
         * Converts a value in the current unit to megabits (10<sup>6</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in megabits
         */
        public final double toMbit(double d) {
                return BitUnit.MBIT.convert(d, this);
        }

        /**
         * Converts a value in the current unit to gigabits (10<sup>9</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in gigabits
         */
        public final double toGbit(double d) {
                return BitUnit.GBIT.convert(d, this);
        }

        /**
         * Converts a value in the current unit to terabits (10<sup>12</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in terabits
         */
        public final double toTbit(double d) {
                return BitUnit.TBIT.convert(d, this);
        }

        /**
         * Converts a value in the current unit to petabits (10<sup>15</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in petabits
         */
        public final double toPbit(double d) {
                return BitUnit.PBIT.convert(d, this);
        }


        /**
         * Converts a value in the current unit to kilobits using the provided bits-per-byte value.
         *
         * @param d the value to convert
         * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in kilobits
         */
        public final double toKbit(double d, int bitsPerByte) {
                return BitUnit.KBIT.convert(d, this, bitsPerByte);
        }

        /**
         * Converts a value in the current unit to megabits using the provided bits-per-byte value.
         *
         * @param d the value to convert
         * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in megabits
         */
        public final double toMbit(double d, int bitsPerByte) {
                return BitUnit.MBIT.convert(d, this, bitsPerByte);
        }

        /**
         * Converts a value in the current unit to gigabits using the provided bits-per-byte value.
         *
         * @param d the value to convert
         * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in gigabits
         */
        public final double toGbit(double d, int bitsPerByte) {
                return BitUnit.GBIT.convert(d, this, bitsPerByte);
        }

        /**
         * Converts a value in the current unit to terabits using the provided bits-per-byte value.
         *
         * @param d the value to convert
         * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in terabits
         */
        public final double toTbit(double d, int bitsPerByte) {
                return BitUnit.TBIT.convert(d, this, bitsPerByte);
        }

        /**
         * Converts a value in the current unit to petabits using the provided bits-per-byte value.
         *
         * @param d the value to convert
         * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in petabits
         */
        public final double toPbit(double d, int bitsPerByte) {
                return BitUnit.PBIT.convert(d, this, bitsPerByte);
        }

}
