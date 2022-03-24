package com.cry.flutter.admin.common;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * <p>
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2022-03-24
 */
public class Utils {

    public static double euclideanDistance(String[] face1, String[] face2) {
        double sum = 0.0;
        for (int i = 0; i < face1.length; i++) {
            sum += pow((Double.parseDouble(face1[i]) - Double.parseDouble(face2[i])), 2);
        }
        return sqrt(sum);
    }
}
