package lesson11.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.math.PI

internal class ComplexTest {

    private fun assertApproxEquals(expected: Complex, actual: Complex, eps: Double) {
        assertEquals(expected.re, actual.re, eps)
        assertEquals(expected.im, actual.im, eps)
    }

    @Test
    fun plus() {
        assertApproxEquals(Complex("4-2i"), Complex("1+2i") + Complex("3-4i"), 1e-10)
        assertApproxEquals(Complex("3.3-2.0i"), Complex("1+2i") + Complex("2.3-4.0i"), 1e-10)
        assertApproxEquals(Complex("-3.3-${PI + 2}i"), Complex("-1-2i") + Complex("-2.3-${PI}i"), 1e-10)
    }

    @Test
    operator fun unaryMinus() {
        assertApproxEquals(Complex(-2.0, 1.0), -Complex(2.0, -1.0), 1e-10)
        assertThrows(NumberFormatException::class.java) {
            -Complex("2.0.-1.0i")
        }
        assertThrows(NumberFormatException::class.java) {
            -Complex("2.0-1.0I")
        }
        assertThrows(NumberFormatException::class.java) {
            -Complex("2.-1.0i")
        }
    }

    @Test
    fun minus() {
        assertApproxEquals(Complex("-2+6i"), Complex("1+2i") - Complex("3-4i"), 1e-10)
        assertApproxEquals(Complex("-2+4i"), Complex(1.0) - Complex("3-4i"), 1e-10)
        assertThrows(NumberFormatException::class.java) {
            Complex("2.-1.0i") - Complex(2.0)
        }
    }

    @Test
    fun times() {
        assertApproxEquals(Complex("11+2i"), Complex("1+2i") * Complex("3-4i"), 1e-10)
    }

    @Test
    fun div() {
        assertApproxEquals(Complex("2.6+0.8i"), Complex("11-8i") / Complex("3-4i"), 1e-10)
    }

    @Test
    fun equals() {
        assertApproxEquals(Complex(1.0, 2.0), Complex("1+2i"), 1e-12)
        assertApproxEquals(Complex(1.0, 0.0), Complex(1.0), 1e-12)
    }

    @Test
    fun testHashCode() {
        assertTrue(Complex(1.0, 2.0).hashCode() == Complex("1+2i").hashCode())
    }

    @Test
    fun testToString() {
        assertTrue(Complex(1.0, 2.0).toString() == "1+2i")
    }
}