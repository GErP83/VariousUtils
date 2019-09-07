package com.gerp83.variousutils.cartesian;

/**
 * Created by gerp83
 * Utils for Cartesian coordinates
 */
public class CartesianUtils {

    /**
     * get distance estimate between two Cartesian coordinate in meters
     *
     * @param coordinateA first Cartesian coordinate
     * @param coordinateB second Cartesian coordinate
     */
    public static double getDistance(CartesianCoordinate coordinateA , CartesianCoordinate coordinateB) {
        double dx = coordinateA.x - coordinateB.x;
        double dy = coordinateA.y - coordinateB.y;
        double dz = coordinateA.z - coordinateB.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     * convert polar coordinate to the equivalent cartesian coordinate
     * convert (lat, lon, alt) to (x, y, z)
     *
     * @param latitude latitude value from Location
     * @param longitude longitude value from Location
     * @param altitude altitude value from Location
     */
    public static CartesianCoordinate fromLocation(double latitude, double longitude, double altitude) {
        CartesianCoordinate coordinate = new CartesianCoordinate();

        double lat = latitude * Math.PI / 180.0;
        double lon = longitude * Math.PI / 180.0;
        double radius = earthRadiusInMeters(lat);
        double cLat = geoCentricLatitude(lat);
        double cosLon = Math.cos(lon);
        double sinLon = Math.sin(lon);
        double cosLat = Math.cos(cLat);
        double sinLat = Math.sin(cLat);
        coordinate.x = radius * cosLon * cosLat;
        coordinate.y = radius * sinLon * cosLat;
        coordinate.z = radius * sinLat;

        // We used geocentric latitude to calculate (x,y,z) on the Earth's ellipsoid.
        // Now we use geodetic latitude to calculate normal vector from the surface, to correct for elevation.
        double cosGeodeticLat = Math.cos(lat);
        double sinGeodeticLat = Math.sin(lat);
        double nx = cosGeodeticLat * cosLon;
        double ny = cosGeodeticLat * sinLon;
        coordinate.x += altitude * nx;
        coordinate.y += altitude * ny;
        coordinate.z += altitude * sinGeodeticLat;
        return coordinate;
    }

    // latitude is geodetic, i.e. that reported by GPS
    // http://en.wikipedia.org/wiki/Earth_radius
    private static double earthRadiusInMeters(double latitudeRadians) {
        double a = 6378137.0;  // equatorial radius in meters
        double b = 6356752.3;  // polar radius in meters
        double cos = Math.cos(latitudeRadians);
        double sin = Math.sin(latitudeRadians);
        double t1 = a * a * cos;
        double t2 = b * b * sin;
        double t3 = a * cos;
        double t4 = b * sin;
        return Math.sqrt((t1 * t1 + t2 * t2) / (t3 * t3 + t4 * t4));
    }

    // Convert geodetic latitude 'lat' to a geocentric latitude 'cLat'.
    // Geodetic latitude is the latitude as given by GPS.
    // Geocentric latitude is the angle measured from center of Earth between a point and the equator.
    // https://en.wikipedia.org/wiki/Latitude#Geocentric_latitude
    private static double geoCentricLatitude(double lat) {
        return Math.atan((1.0 - 0.00669437999014) * Math.tan(lat));
    }

}
