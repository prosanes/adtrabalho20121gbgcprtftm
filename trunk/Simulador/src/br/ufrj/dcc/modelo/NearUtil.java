package br.ufrj.dcc.modelo;

import java.util.Arrays;


/**
 * <p>Static methods for doing useful math</p><hr>
 *
 * @author  : $Author: brian $
 * @version : $Revision: 1.1 $
 *
 * <hr><p><font size="-1" color="#336699"><a href="http://www.mbari.org">
 * The Monterey Bay Aquarium Research Institute (MBARI)</a> provides this
 * documentation and code &quot;as is&quot;, with no warranty, express or
 * implied, of its quality or consistency. It is provided without support and
 * without obligation on the part of MBARI to assist in its use, correction,
 * modification, or enhancement. This information should not be published or
 * distributed to third parties without specific written permission from
 * MBARI.</font></p><br>
 *
 * <font size="-1" color="#336699">Copyright 2002 MBARI.<br>
 * MBARI Proprietary Information. All rights reserved.</font><br><hr><br>
 *
 */

public class NearUtil{

    
    /**
     * Find the index of the array nearest to the value. The values array can 
     * contain only unique values. If it doesn't the first occurence of a value
     * in the values array is the one used, subsequent duplicate are ignored. If
     * the value falls outside the bounds of the array, <b>null</b> is returned
     *
     * @param array Values to search through for the nearest point
     * @param value THe value to search for the nearest neighbor in the array
     * @return The index of the array value nearest the value. null if the value
     *      is larger or smaller than any values in the array.
     */
    public static Integer nearInclusive(final Double[] array, final Double value) {
        Integer i = null;
        int idx = binarySearch(array, value);
        if (idx < 0) {
            idx = -(idx) - 1;
            if (idx == 0 || idx >= array.length) {
                // Do nothing. This point is outside the array bounds return value will be null
            }
            else {
                // Find nearest point
                double d0 = Math.abs(array[idx - 1] - value);
                double d1 = Math.abs(array[idx] - value);
                i = (d0 <= d1) ? idx - 1 : idx;
            }
        }
        else {
            i = idx;
        }
        return i;
    }



    /**
     * Searches the specified array of doubles for the specified value using
     * the binary search algorithm.  The array <strong>must</strong> be sorted
     * (as by the <tt>sort</tt> method, above) prior to making this call.  If
     * it is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found. The array can be sorted from low values to high or
     * from high values to low.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *         otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the list: the index of the first
     *         element greater than the key, or <tt>list.size()</tt>, if all
     *         elements in the list are less than the specified key.  Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     */
    public static int binarySearch(Double[] a, Double key) {
        int index = -1;
        if (a[0] < a[1]) {
            index = Arrays.binarySearch(a, key);
        }
        else {
            index = binarySearch(a, key, 0, a.length - 1);
        }
        return index;
    }

    private static int binarySearch(Double[] a, Double key, int low, int high) {
        while (low <= high) {
            int mid = (low + high) / 2;
            double midVal = a[mid];

            int cmp;
            if (midVal > key) {
                cmp = -1; // Neither val is NaN, thisVal is smaller
            }
            else if (midVal < key) {
                cmp = 1; // Neither val is NaN, thisVal is larger
            }
            else {
                long midBits = Double.doubleToLongBits(midVal);
                long keyBits = Double.doubleToLongBits(key);
                cmp = (midBits == keyBits ? 0 : (midBits < keyBits ? -1 : 1)); // (0.0, -0.0) or (NaN, !NaN)
            }

            if (cmp < 0) {
                low = mid + 1;
            }
            else if (cmp > 0) {
                high = mid - 1;
            }
            else {
                return mid; // key found
            }
        }
        return -(low + 1); // key not found.
    }
}

   
    
    