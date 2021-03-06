/*
 * Copyright 2010-2014 JetBrains s.r.o.
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

// Auto-generated file. DO NOT EDIT!

package kotlin

public class ByteRange(public override val start: Byte, public override val end: Byte) : Range<Byte>, Progression<Byte> {
    override val increment: Int
        get() = 1

    override fun contains(item: Byte): Boolean = start <= item && item <= end

    override fun iterator(): ByteIterator = ByteProgressionIterator(start, end, 1)

    override fun equals(other: Any?): Boolean =
        other is ByteRange && start == other.start && end == other.end

    override fun hashCode(): Int = 31 * start.toInt() + end

    class object {
        public val EMPTY: ByteRange = ByteRange(1, 0)
    }
}

public class CharRange(public override val start: Char, public override val end: Char) : Range<Char>, Progression<Char> {
    override val increment: Int
        get() = 1

    override fun contains(item: Char): Boolean = start <= item && item <= end

    override fun iterator(): CharIterator = CharProgressionIterator(start, end, 1)

    override fun equals(other: Any?): Boolean =
        other is CharRange && start == other.start && end == other.end

    override fun hashCode(): Int = 31 * start.toInt() + end

    class object {
        public val EMPTY: CharRange = CharRange(1.toChar(), 0.toChar())
    }
}

public class ShortRange(public override val start: Short, public override val end: Short) : Range<Short>, Progression<Short> {
    override val increment: Int
        get() = 1

    override fun contains(item: Short): Boolean = start <= item && item <= end

    override fun iterator(): ShortIterator = ShortProgressionIterator(start, end, 1)

    override fun equals(other: Any?): Boolean =
        other is ShortRange && start == other.start && end == other.end

    override fun hashCode(): Int = 31 * start.toInt() + end

    class object {
        public val EMPTY: ShortRange = ShortRange(1, 0)
    }
}

public class IntRange(public override val start: Int, public override val end: Int) : Range<Int>, Progression<Int> {
    override val increment: Int
        get() = 1

    override fun contains(item: Int): Boolean = start <= item && item <= end

    override fun iterator(): IntIterator = IntProgressionIterator(start, end, 1)

    override fun equals(other: Any?): Boolean =
        other is IntRange && start == other.start && end == other.end

    override fun hashCode(): Int = 31 * start + end

    class object {
        public val EMPTY: IntRange = IntRange(1, 0)
    }
}

public class LongRange(public override val start: Long, public override val end: Long) : Range<Long>, Progression<Long> {
    override val increment: Long
        get() = 1

    override fun contains(item: Long): Boolean = start <= item && item <= end

    override fun iterator(): LongIterator = LongProgressionIterator(start, end, 1)

    override fun equals(other: Any?): Boolean =
        other is LongRange && start == other.start && end == other.end

    override fun hashCode(): Int = (31 * (start xor (start ushr 32)) + (end xor (end ushr 32))).toInt()

    class object {
        public val EMPTY: LongRange = LongRange(1, 0)
    }
}

public class FloatRange(public override val start: Float, public override val end: Float) : Range<Float>, Progression<Float> {
    override val increment: Float
        get() = 1.0f

    override fun contains(item: Float): Boolean = start <= item && item <= end

    override fun iterator(): FloatIterator = FloatProgressionIterator(start, end, 1.0f)

    override fun equals(other: Any?): Boolean =
        other is FloatRange && java.lang.Float.compare(start, other.start) == 0 && java.lang.Float.compare(end, other.end) == 0

    override fun hashCode(): Int = 31 * java.lang.Float.floatToIntBits(start) + java.lang.Float.floatToIntBits(end)

    class object {
        public val EMPTY: FloatRange = FloatRange(1.0f, 0.0f)
    }
}

public class DoubleRange(public override val start: Double, public override val end: Double) : Range<Double>, Progression<Double> {
    override val increment: Double
        get() = 1.0

    override fun contains(item: Double): Boolean = start <= item && item <= end

    override fun iterator(): DoubleIterator = DoubleProgressionIterator(start, end, 1.0)

    override fun equals(other: Any?): Boolean =
        other is DoubleRange && java.lang.Double.compare(start, other.start) == 0 && java.lang.Double.compare(end, other.end) == 0

    override fun hashCode(): Int {
        var temp = java.lang.Double.doubleToLongBits(start)
        val result = (temp xor (temp ushr 32))
        temp = java.lang.Double.doubleToLongBits(end)
        return (31 * result + (temp xor (temp ushr 32))).toInt()
    }

    class object {
        public val EMPTY: DoubleRange = DoubleRange(1.0, 0.0)
    }
}

