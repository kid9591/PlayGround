package com.kid.playground.activity



//https://gist.github.com/vgrem/73451bd7273d8b5ba949c4d0fb654ec6
object UTMConverter {

    //Some changes here

    /* Ellipsoid model constants (actual values here are for WGS84) */
    private const val MAJOR_RADIUS = 6378137.0
    private const val MINOR_RADIUS = 6356752.314
    private const val SCALE_FACTOR = 0.9996

    /**
     * Converts x and y coordinates in the Universal Transverse Mercator
     * projection to a latitude/longitude pair.
     * @param x  The easting of the point, in meters.
     * @param y  The northing of the point, in meters.
     * @param zone The UTM zone in which the point lies.
     * @param southhemi True if the point is in the southern hemisphere;
     * false otherwise.
     */
    fun convertToLatLng(x: Double, y: Double, zone: Int, zoneLetter: Char): Pair<Double, Double> {
        val southhemi = zoneLetter.toInt() < 80
        //Some changes here
        var x = x
        var y = y
        x -= 500000.0
        x /= SCALE_FACTOR

        /* If in southern hemisphere, adjust y accordingly. */if (southhemi) y -= 10000000.0
        y /= SCALE_FACTOR
        val cmeridian = getCentralMeridian(zone)
        return mapPointToLatLng(x, y, cmeridian)
    }

    /**
     * * Converts x and y coordinates in the Transverse Mercator projection to
     * a latitude/longitude pair.  Note that Transverse Mercator is not
     * the same as UTM; a scale factor is required to convert between them.
     *
     * Reference: Hoffmann-Wellenhof, B., Lichtenegger, H., and Collins, J.,
     * GPS: Theory and Practice, 3rd ed.  New York: Springer-Verlag Wien, 1994.
     * @param x The easting of the point, in meters.
     * @param y The northing of the point, in meters.
     * @param lambda0 Longitude of the central meridian to be used, in radians.
     * @return latitude/longitude pair of coordinates
     */
    private fun mapPointToLatLng(x: Double, y: Double, lambda0: Double): Pair<Double, Double> {

        /* Get the value of phif, the footpoint latitude. */
        val phif = getFootpointLatitude(y)

        /* Precalculate ep2 */
        val ep2 = ((Math.pow(MAJOR_RADIUS, 2.0) - Math.pow(MINOR_RADIUS, 2.0))
                / Math.pow(MINOR_RADIUS, 2.0))

        /* Precalculate cos (phif) */
        val cf = Math.cos(phif)

        /* Precalculate nuf2 */
        val nuf2 = ep2 * Math.pow(cf, 2.0)

        /* Precalculate Nf and initialize Nfpow */
        val Nf = Math.pow(MAJOR_RADIUS, 2.0) / (MINOR_RADIUS * Math.sqrt(1 + nuf2))
        var Nfpow = Nf

        /* Precalculate tf */
        val tf = Math.tan(phif)
        val tf2 = tf * tf
        val tf4 = tf2 * tf2

        /* Precalculate fractional coefficients for x**n in the equations
           below to simplify the expressions for latitude and longitude. */
        val x1frac = 1.0 / (Nfpow * cf)
        Nfpow *= Nf /* now equals Nf**2) */
        val x2frac = tf / (2.0 * Nfpow)
        Nfpow *= Nf /* now equals Nf**3) */
        val x3frac = 1.0 / (6.0 * Nfpow * cf)
        Nfpow *= Nf /* now equals Nf**4) */
        val x4frac = tf / (24.0 * Nfpow)
        Nfpow *= Nf /* now equals Nf**5) */
        val x5frac = 1.0 / (120.0 * Nfpow * cf)
        Nfpow *= Nf /* now equals Nf**6) */
        val x6frac = tf / (720.0 * Nfpow)
        Nfpow *= Nf /* now equals Nf**7) */
        val x7frac = 1.0 / (5040.0 * Nfpow * cf)
        Nfpow *= Nf /* now equals Nf**8) */
        val x8frac = tf / (40320.0 * Nfpow)

        /* Precalculate polynomial coefficients for x**n.
           -- x**1 does not have a polynomial coefficient. */
        val x2poly = -1.0 - nuf2
        val x3poly = -1.0 - 2 * tf2 - nuf2
        val x4poly =
            5.0 + 3.0 * tf2 + 6.0 * nuf2 - 6.0 * tf2 * nuf2 - 3.0 * (nuf2 * nuf2) - 9.0 * tf2 * (nuf2 * nuf2)
        val x5poly = 5.0 + 28.0 * tf2 + 24.0 * tf4 + 6.0 * nuf2 + 8.0 * tf2 * nuf2
        val x6poly = (-61.0 - 90.0 * tf2 - 45.0 * tf4 - 107.0 * nuf2
                + 162.0 * tf2 * nuf2)
        val x7poly = -61.0 - 662.0 * tf2 - 1320.0 * tf4 - 720.0 * (tf4 * tf2)
        val x8poly = 1385.0 + 3633.0 * tf2 + 4095.0 * tf4 + 1575 * (tf4 * tf2)

        /* Calculate latitude */
        val lat_rad = phif + x2frac * x2poly * (x * x) + x4frac * x4poly * Math.pow(
            x,
            4.0
        ) + x6frac * x6poly * Math.pow(x, 6.0) + x8frac * x8poly * Math.pow(x, 8.0)

        /* Calculate longitude */
        val lng_rad = lambda0 + x1frac * x + x3frac * x3poly * Math.pow(
            x,
            3.0
        ) + x5frac * x5poly * Math.pow(x, 5.0) + x7frac * x7poly * Math.pow(x, 7.0)
        return Pair(Math.toDegrees(lat_rad), Math.toDegrees(lng_rad))
    }

    /**
     * Computes the footpoint latitude for use in converting transverse
     * Mercator coordinates to ellipsoidal coordinates.
     *
     * Reference: Hoffmann-Wellenhof, B., Lichtenegger, H., and Collins, J.,
     * GPS: Theory and Practice, 3rd ed.  New York: Springer-Verlag Wien, 1994.
     * @param y The UTM northing coordinate, in meters.
     * @return The footpoint latitude, in radians.
     */
    private fun getFootpointLatitude(y: Double): Double {

        //Some changes here

        /* Precalculate n (Eq. 10.18) */
        val n =
            (MAJOR_RADIUS - MINOR_RADIUS) / (MAJOR_RADIUS + MINOR_RADIUS)

        /* Precalculate alpha_ (Eq. 10.22) */
        /* (Same as alpha in Eq. 10.17) */
        val alpha_ = ((MAJOR_RADIUS + MINOR_RADIUS) / 2.0
                * (1 + Math.pow(n, 2.0) / 4 + Math.pow(n, 4.0) / 64))

        /* Precalculate y_ (Eq. 10.23) */
        val y_ = y / alpha_

        /* Precalculate beta_ (Eq. 10.22) */
        val beta_ = (3.0 * n / 2.0 + -27.0 * Math.pow(n, 3.0) / 32.0
                + 269.0 * Math.pow(n, 5.0) / 512.0)

        /* Precalculate gamma_ (Eq. 10.22) */
        val gamma_ = (21.0 * Math.pow(n, 2.0) / 16.0
                + -55.0 * Math.pow(n, 4.0) / 32.0)

        /* Precalculate delta_ (Eq. 10.22) */
        val delta_ = (151.0 * Math.pow(n, 3.0) / 96.0
                + -417.0 * Math.pow(n, 5.0) / 128.0)

        /* Precalculate epsilon_ (Eq. 10.22) */
        val epsilon_ = 1097.0 * Math.pow(n, 4.0) / 512.0

        /* Now calculate the sum of the series (Eq. 10.21) */
        return (y_ + beta_ * Math.sin(2.0 * y_)
                + gamma_ * Math.sin(4.0 * y_)
                + delta_ * Math.sin(6.0 * y_)
                + epsilon_ * Math.sin(8.0 * y_))
    }

    /**
     * Determines the central meridian for the given UTM zone
     * @param zone An integer value designating the UTM zone, range [1,60]
     * @return The central meridian for the given UTM zone, in radians, or zero
     * if the UTM zone parameter is outside the range [1,60].
     * Range of the central meridian is the radian equivalent of [-177,+177]
     */
    private fun getCentralMeridian(zone: Int): Double {
        return Math.toRadians(-183.0 + zone * 6.0)
    }
}