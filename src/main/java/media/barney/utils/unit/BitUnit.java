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
 * Defines binary (power-of-two) and decimal (power-of-ten) bit units and
 * provides helper methods for converting between them as well as to the
 * corresponding {@link ByteUnit byte-based units}. All conversions operate on
 * {@code double} values, require non-negative finite inputs, and guard against
 * overflow using {@link #safeMulti(double, double)}.
 *
 * @author Fabian Barney
 */
public enum BitUnit {

        /** Bit, equal to 1 bit. */
        BIT {
		@Override
		public double toBits(double d) { return UnitInputValidator.requireNonNegativeFinite(d); }

		@Override
		public double convert(double d, BitUnit u) {
			return u.toBits(UnitInputValidator.requireNonNegativeFinite(d));
		}
	},

        /** Kibibit (Kibit), equal to 2^10 bits. */
        KIBIT {
		@Override
		public double toBits(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_KIBIT); }

		@Override
		public double convert(double d, BitUnit u) {
			return u.toKibit(UnitInputValidator.requireNonNegativeFinite(d));
		}
	},

        /** Mebibit (Mibit), equal to 2^20 bits. */
        MIBIT {
		@Override
		public double toBits(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_MIBIT); }

		@Override
		public double convert(double d, BitUnit u) {
			return u.toMibit(UnitInputValidator.requireNonNegativeFinite(d));
		}
	},

        /** Gibibit (Gibit), equal to 2^30 bits. */
        GIBIT {
		@Override
		public double toBits(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_GIBIT); }

		@Override
		public double convert(double d, BitUnit u) {
			return u.toGibit(UnitInputValidator.requireNonNegativeFinite(d));
		}
	},

        /** Tebibit (Tibit), equal to 2^40 bits. */
        TIBIT {
		@Override
		public double toBits(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_TIBIT); }

		@Override
		public double convert(double d, BitUnit u) {
			return u.toTibit(UnitInputValidator.requireNonNegativeFinite(d));
		}
	},

        /** Pebibit (Pibit), equal to 2^50 bits. */
        PIBIT {
		@Override
		public double toBits(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_PIBIT); }

		@Override
		public double convert(double d, BitUnit u) {
			return u.toPibit(UnitInputValidator.requireNonNegativeFinite(d));
		}
	},

        /** Kilobit (kbit), equal to 10^3 bits. */
        KBIT {
		@Override
		public double toBits(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_KBIT); }

		@Override
		public double convert(double d, BitUnit u) {
			return u.toKbit(UnitInputValidator.requireNonNegativeFinite(d));
		}
	},

        /** Megabit (Mbit), equal to 10^6 bits. */
        MBIT {
		@Override
		public double toBits(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_MBIT); }

		@Override
		public double convert(double d, BitUnit u) {
			return u.toMbit(UnitInputValidator.requireNonNegativeFinite(d));
		}
	},

        /** Gigabit (Gbit), equal to 10^9 bits. */
        GBIT {
		@Override
		public double toBits(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_GBIT); }

		@Override
		public double convert(double d, BitUnit u) {
			return u.toGbit(UnitInputValidator.requireNonNegativeFinite(d));
		}
	},

        /** Terabit (Tbit), equal to 10^12 bits. */
        TBIT {
		@Override
		public double toBits(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_TBIT); }

		@Override
		public double convert(double d, BitUnit u) {
			return u.toTbit(UnitInputValidator.requireNonNegativeFinite(d));
		}
	},

        /** Petabit (Pbit), equal to 10^15 bits. */
        PBIT {
		@Override
		public double toBits(double d) { return safeMulti(UnitInputValidator.requireNonNegativeFinite(d), C_PBIT); }

		@Override
		public double convert(double d, BitUnit u) {
			return u.toPbit(UnitInputValidator.requireNonNegativeFinite(d));
		}
	};


	static final double C_KIBIT = Math.pow(2d, 10d);
	static final double C_MIBIT = Math.pow(2d, 20d);
	static final double C_GIBIT = Math.pow(2d, 30d);
	static final double C_TIBIT = Math.pow(2d, 40d);
	static final double C_PIBIT = Math.pow(2d, 50d);

	static final double C_KBIT = Math.pow(10d, 3d);
	static final double C_MBIT = Math.pow(10d, 6d);
	static final double C_GBIT = Math.pow(10d, 9d);
	static final double C_TBIT = Math.pow(10d, 12d);
	static final double C_PBIT = Math.pow(10d, 15d);


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
         * Converts a value in the current unit to bits.
         *
         * @param d the value to convert
         * @return the converted value expressed in bits
         */
        public abstract double toBits(double d);


        /**
         * Converts a value in the current unit to kibibits (2<sup>10</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in kibibits
         */
        public final double toKibit(double d){
                return toBits(d) / C_KIBIT;
        }

        /**
         * Converts a value in the current unit to mebibits (2<sup>20</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in mebibits
         */
        public final double toMibit(double d) {
                return toBits(d) / C_MIBIT;
        }

        /**
         * Converts a value in the current unit to gibibits (2<sup>30</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in gibibits
         */
        public final double toGibit(double d) {
                return toBits(d) / C_GIBIT;
        }

        /**
         * Converts a value in the current unit to tebibits (2<sup>40</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in tebibits
         */
        public final double toTibit(double d) {
                return toBits(d) / C_TIBIT;
        }

        /**
         * Converts a value in the current unit to pebibits (2<sup>50</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in pebibits
         */
        public final double toPibit(double d) {
                return toBits(d) / C_PIBIT;
        }


        /**
         * Converts a value in the current unit to kilobits (10<sup>3</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in kilobits
         */
        public final double toKbit(double d) {
                return toBits(d) / C_KBIT;
        }

        /**
         * Converts a value in the current unit to megabits (10<sup>6</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in megabits
         */
        public final double toMbit(double d) {
                return toBits(d) / C_MBIT;
        }

        /**
         * Converts a value in the current unit to gigabits (10<sup>9</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in gigabits
         */
        public final double toGbit(double d) {
                return toBits(d) / C_GBIT;
        }

        /**
         * Converts a value in the current unit to terabits (10<sup>12</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in terabits
         */
        public final double toTbit(double d) {
                return toBits(d) / C_TBIT;
        }

        /**
         * Converts a value in the current unit to petabits (10<sup>15</sup> bits).
         *
         * @param d the value to convert
         * @return the converted value expressed in petabits
         */
        public final double toPbit(double d) {
                return toBits(d) / C_PBIT;
        }


        /**
         * Converts a value from another {@code BitUnit} to this unit.
         *
         * @param d the value to convert
         * @param u the unit the value is currently expressed in
         * @return the converted value expressed in this unit
         */
        public abstract double convert(double d, BitUnit u);

        /**
         * Converts a value expressed in the supplied {@link ByteUnit} to this bit unit
         * assuming 8 bits per byte ({@link java.lang.Byte#SIZE}).
         *
         * @param d the value to convert
         * @param u the byte unit the value is expressed in
         * @return the converted value expressed in this unit
         */
        public final double convert(double d, ByteUnit u){
                return convert(d, u, Byte.SIZE);
        }

        /**
         * Converts a value expressed in the supplied {@link ByteUnit} to this bit unit
         * using the provided bits-per-byte value.
         *
         * @param d the value to convert
         * @param u the byte unit the value is expressed in
         * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
         * @return the converted value expressed in this unit
         */
        public final double convert(double d, ByteUnit u, int bitsPerByte){
                double bits = safeMulti(u.toBytes(UnitInputValidator.requireNonNegativeFinite(d)), UnitInputValidator.requirePositiveBitsPerByte(bitsPerByte));
                return convert(bits, BIT);
        }



        /*
         * Convenience methods for cross-conversion
         */

	/**
	 * Converts a value in the current unit to bytes.
	 *
	 * @param d the value to convert
	 * @return the converted value expressed in bytes
	 */
	public final double toBytes(double d){
		return ByteUnit.BYTE.convert(d, this);
	}

	/**
	 * Converts a value in the current unit to bytes using the provided bits-per-byte value.
	 *
	 * @param d the value to convert
	 * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
	 * @return the converted value expressed in bytes
	 */
	public final double toBytes(double d, int bitsPerByte){
		return ByteUnit.BYTE.convert(d, this, bitsPerByte);
	}


	/**
	 * Converts a value in the current unit to kibibytes (2<sup>10</sup> bytes).
	 *
	 * @param d the value to convert
	 * @return the converted value expressed in kibibytes
	 */
	public final double toKiB(double d){
		return ByteUnit.KIB.convert(d, this);
	}

	/**
	 * Converts a value in the current unit to mebibytes (2<sup>20</sup> bytes).
	 *
	 * @param d the value to convert
	 * @return the converted value expressed in mebibytes
	 */
	public final double toMiB(double d) {
		return ByteUnit.MIB.convert(d, this);
	}

	/**
	 * Converts a value in the current unit to gibibytes (2<sup>30</sup> bytes).
	 *
	 * @param d the value to convert
	 * @return the converted value expressed in gibibytes
	 */
	public final double toGiB(double d) {
		return ByteUnit.GIB.convert(d, this);
	}

	/**
	 * Converts a value in the current unit to tebibytes (2<sup>40</sup> bytes).
	 *
	 * @param d the value to convert
	 * @return the converted value expressed in tebibytes
	 */
	public final double toTiB(double d) {
		return ByteUnit.TIB.convert(d, this);
	}

	/**
	 * Converts a value in the current unit to pebibytes (2<sup>50</sup> bytes).
	 *
	 * @param d the value to convert
	 * @return the converted value expressed in pebibytes
	 */
	public final double toPiB(double d) {
		return ByteUnit.PIB.convert(d, this);
	}



	/**
	 * Converts a value in the current unit to kibibytes using the provided bits-per-byte value.
	 *
	 * @param d the value to convert
	 * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
	 * @return the converted value expressed in kibibytes
	 */
	public final double toKiB(double d, int bitsPerByte){
		return ByteUnit.KIB.convert(d, this, bitsPerByte);
	}

	/**
	 * Converts a value in the current unit to mebibytes using the provided bits-per-byte value.
	 *
	 * @param d the value to convert
	 * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
	 * @return the converted value expressed in mebibytes
	 */
	public final double toMiB(double d, int bitsPerByte) {
		return ByteUnit.MIB.convert(d, this, bitsPerByte);
	}

	/**
	 * Converts a value in the current unit to gibibytes using the provided bits-per-byte value.
	 *
	 * @param d the value to convert
	 * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
	 * @return the converted value expressed in gibibytes
	 */
	public final double toGiB(double d, int bitsPerByte) {
		return ByteUnit.GIB.convert(d, this, bitsPerByte);
	}

	/**
	 * Converts a value in the current unit to tebibytes using the provided bits-per-byte value.
	 *
	 * @param d the value to convert
	 * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
	 * @return the converted value expressed in tebibytes
	 */
	public final double toTiB(double d, int bitsPerByte) {
		return ByteUnit.TIB.convert(d, this, bitsPerByte);
	}

	/**
	 * Converts a value in the current unit to pebibytes using the provided bits-per-byte value.
	 *
	 * @param d the value to convert
	 * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
	 * @return the converted value expressed in pebibytes
	 */
	public final double toPiB(double d, int bitsPerByte) {
		return ByteUnit.PIB.convert(d, this, bitsPerByte);
	}




	/**
	 * Converts a value in the current unit to kilobytes (10<sup>3</sup> bytes).
	 *
	 * @param d the value to convert
	 * @return the converted value expressed in kilobytes
	 */
	public final double toKB(double d) {
		return ByteUnit.KB.convert(d, this);
	}

	/**
	 * Converts a value in the current unit to megabytes (10<sup>6</sup> bytes).
	 *
	 * @param d the value to convert
	 * @return the converted value expressed in megabytes
	 */
	public final double toMB(double d) {
		return ByteUnit.MB.convert(d, this);
	}

	/**
	 * Converts a value in the current unit to gigabytes (10<sup>9</sup> bytes).
	 *
	 * @param d the value to convert
	 * @return the converted value expressed in gigabytes
	 */
	public final double toGB(double d) {
		return ByteUnit.GB.convert(d, this);
	}

	/**
	 * Converts a value in the current unit to terabytes (10<sup>12</sup> bytes).
	 *
	 * @param d the value to convert
	 * @return the converted value expressed in terabytes
	 */
	public final double toTB(double d) {
		return ByteUnit.TB.convert(d, this);
	}

	/**
	 * Converts a value in the current unit to petabytes (10<sup>15</sup> bytes).
	 *
	 * @param d the value to convert
	 * @return the converted value expressed in petabytes
	 */
	public final double toPB(double d) {
		return ByteUnit.PB.convert(d, this);
	}


	/**
	 * Converts a value in the current unit to kilobytes using the provided bits-per-byte value.
	 *
	 * @param d the value to convert
	 * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
	 * @return the converted value expressed in kilobytes
	 */
	public final double toKB(double d, int bitsPerByte) {
		return ByteUnit.KB.convert(d, this, bitsPerByte);
	}

	/**
	 * Converts a value in the current unit to megabytes using the provided bits-per-byte value.
	 *
	 * @param d the value to convert
	 * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
	 * @return the converted value expressed in megabytes
	 */
	public final double toMB(double d, int bitsPerByte) {
		return ByteUnit.MB.convert(d, this, bitsPerByte);
	}

	/**
	 * Converts a value in the current unit to gigabytes using the provided bits-per-byte value.
	 *
	 * @param d the value to convert
	 * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
	 * @return the converted value expressed in gigabytes
	 */
	public final double toGB(double d, int bitsPerByte) {
		return ByteUnit.GB.convert(d, this, bitsPerByte);
	}

	/**
	 * Converts a value in the current unit to terabytes using the provided bits-per-byte value.
	 *
	 * @param d the value to convert
	 * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
	 * @return the converted value expressed in terabytes
	 */
	public final double toTB(double d, int bitsPerByte) {
		return ByteUnit.TB.convert(d, this, bitsPerByte);
	}

	/**
	 * Converts a value in the current unit to petabytes using the provided bits-per-byte value.
	 *
	 * @param d the value to convert
	 * @param bitsPerByte the number of bits in one byte, typically {@link java.lang.Byte#SIZE}
	 * @return the converted value expressed in petabytes
	 */
	public final double toPB(double d, int bitsPerByte) {
		return ByteUnit.PB.convert(d, this, bitsPerByte);
	}


}
