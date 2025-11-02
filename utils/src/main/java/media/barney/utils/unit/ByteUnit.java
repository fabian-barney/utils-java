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

/**
 * Defines binary (power-of-two) and decimal (power-of-ten) byte units and
 * provides helper methods for converting between them as well as to the
 * corresponding {@link BitUnit bit-based units}. All conversions operate on
 * {@code double} values and guard against overflow using {@link #safeMulti(double, double)}.
 *
 * <p>The enum constants describe both IEC (KiB, MiB, &hellip;) and SI (kB, MB, &hellip;)
 * units so the implementation can convert values expressed with either
 * convention.</p>
 *
 * @author Fabian Barney
 */
public enum ByteUnit {

	/** <pre>
	 * Byte (B)
	 * 1 Byte
	 */
	BYTE {
		@Override
		public double toBytes(double d) { return d; }

		@Override
		public double convert(double d, ByteUnit u) { return u.toBytes(d); }
	},

	/** <pre>
	 * Kibibyte (KiB)
	 * 2^10 Byte = 1.024 Byte
	 */
	KIB {
		@Override
		public double toBytes(double d) { return safeMulti(d, C_KIB); }

		@Override
		public double convert(double d, ByteUnit u) { return u.toKiB(d); }
	},

	/** <pre>
	 * Mebibyte (MiB)
	 * 2^20 Byte = 1.024 * 1.024 Byte = 1.048.576 Byte
	 */
	MIB {
		@Override
		public double toBytes(double d) { return safeMulti(d, C_MIB); }

		@Override
		public double convert(double d, ByteUnit u) { return u.toMiB(d); }
	},

	/** <pre>
	 * Gibibyte (GiB)
	 * 2^30 Byte = 1.024 * 1.024 * 1.024 Byte = 1.073.741.824 Byte
	 */
	GIB {
		@Override
		public double toBytes(double d) { return safeMulti(d, C_GIB); }

		@Override
		public double convert(double d, ByteUnit u) { return u.toGiB(d); }
	},

	/** <pre>
	 * Tebibyte (TiB)
	 * 2^40 Byte = 1.024 * 1.024 * 1.024 * 1.024 Byte = 1.099.511.627.776 Byte
	 */
	TIB {
		@Override
		public double toBytes(double d) { return safeMulti(d, C_TIB); }

		@Override
		public double convert(double d, ByteUnit u) { return u.toTiB(d); }
	},

	/** <pre>
	 * Pebibyte (PiB)
	 * 2^50 Byte = 1.024 * 1.024 * 1.024 * 1.024 * 1.024 Byte = 1.125.899.906.842.624 Byte
	 */
	PIB {
		@Override
		public double toBytes(double d) { return safeMulti(d, C_PIB); }

		@Override
		public double convert(double d, ByteUnit u) { return u.toPiB(d); }
	},

	/** <pre>
	 * Kilobyte (kB)
	 * 10^3 Byte = 1.000 Byte
	 */
	KB {
		@Override
		public double toBytes(double d) { return safeMulti(d, C_KB); }

		@Override
		public double convert(double d, ByteUnit u) { return u.toKB(d); }
	},

	/** <pre>
	 * Megabyte (MB)
	 * 10^6 Byte = 1.000.000 Byte
	 */
	MB {
		@Override
		public double toBytes(double d) { return safeMulti(d, C_MB); }

		@Override
		public double convert(double d, ByteUnit u) { return u.toMB(d); }
	},

	/** <pre>
	 * Gigabyte (GB)
	 * 10^9 Byte = 1.000.000.000 Byte
	 */
	GB {
		@Override
		public double toBytes(double d) { return safeMulti(d, C_GB); }

		@Override
		public double convert(double d, ByteUnit u) { return u.toGB(d); }
	},

	/** <pre>
	 * Terabyte (TB)
	 * 10^12 Byte = 1.000.000.000.000 Byte
	 */
	TB {
		@Override
		public double toBytes(double d) { return safeMulti(d, C_TB); }

		@Override
		public double convert(double d, ByteUnit u) { return u.toTB(d); }
	},

	/** <pre>
	 * Petabyte (PB)
	 * 10^15 Byte = 1.000.000.000.000.000 Byte
	 */
	PB {
		@Override
		public double toBytes(double d) { return safeMulti(d, C_PB); }

		@Override
		public double convert(double d, ByteUnit u) { return u.toPB(d); }
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
			return Double.MIN_VALUE;
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
         * assuming an 8-bit word size.
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
         * using the provided word size.
         *
         * @param d the value to convert
         * @param u the bit unit the value is expressed in
         * @param wordSize the number of bits in a word, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in this unit
         */
        public final double convert(double d, BitUnit u, int wordSize){
                double bytes = u.toBits(d) / wordSize;
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
         * Converts a value in the current unit to bits using the provided word size.
         *
         * @param d the value to convert
         * @param wordSize the number of bits in a word, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in bits
         */
        public final double toBits(double d, int wordSize) {
                return BitUnit.BIT.convert(d, this, wordSize);
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
         * Converts a value in the current unit to kibibits using the provided word size.
         *
         * @param d the value to convert
         * @param wordSize the number of bits in a word, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in kibibits
         */
        public final double toKibit(double d, int wordSize){
                return BitUnit.KIBIT.convert(d, this, wordSize);
        }

        /**
         * Converts a value in the current unit to mebibits using the provided word size.
         *
         * @param d the value to convert
         * @param wordSize the number of bits in a word, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in mebibits
         */
        public final double toMibit(double d, int wordSize) {
                return BitUnit.MIBIT.convert(d, this, wordSize);
        }

        /**
         * Converts a value in the current unit to gibibits using the provided word size.
         *
         * @param d the value to convert
         * @param wordSize the number of bits in a word, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in gibibits
         */
        public final double toGibit(double d, int wordSize) {
                return BitUnit.GIBIT.convert(d, this, wordSize);
        }

        /**
         * Converts a value in the current unit to tebibits using the provided word size.
         *
         * @param d the value to convert
         * @param wordSize the number of bits in a word, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in tebibits
         */
        public final double toTibit(double d, int wordSize) {
                return BitUnit.TIBIT.convert(d, this, wordSize);
        }

        /**
         * Converts a value in the current unit to pebibits using the provided word size.
         *
         * @param d the value to convert
         * @param wordSize the number of bits in a word, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in pebibits
         */
        public final double toPibit(double d, int wordSize) {
                return BitUnit.PIBIT.convert(d, this, wordSize);
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
         * Converts a value in the current unit to kilobits using the provided word size.
         *
         * @param d the value to convert
         * @param wordSize the number of bits in a word, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in kilobits
         */
        public final double toKbit(double d, int wordSize) {
                return BitUnit.KBIT.convert(d, this, wordSize);
        }

        /**
         * Converts a value in the current unit to megabits using the provided word size.
         *
         * @param d the value to convert
         * @param wordSize the number of bits in a word, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in megabits
         */
        public final double toMbit(double d, int wordSize) {
                return BitUnit.MBIT.convert(d, this, wordSize);
        }

        /**
         * Converts a value in the current unit to gigabits using the provided word size.
         *
         * @param d the value to convert
         * @param wordSize the number of bits in a word, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in gigabits
         */
        public final double toGbit(double d, int wordSize) {
                return BitUnit.GBIT.convert(d, this, wordSize);
        }

        /**
         * Converts a value in the current unit to terabits using the provided word size.
         *
         * @param d the value to convert
         * @param wordSize the number of bits in a word, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in terabits
         */
        public final double toTbit(double d, int wordSize) {
                return BitUnit.TBIT.convert(d, this, wordSize);
        }

        /**
         * Converts a value in the current unit to petabits using the provided word size.
         *
         * @param d the value to convert
         * @param wordSize the number of bits in a word, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in petabits
         */
        public final double toPbit(double d, int wordSize) {
                return BitUnit.PBIT.convert(d, this, wordSize);
        }

}
