import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    /**
     * Solution Explanation
     * ============================
     * After reading through the problem, it is immediately obvious that the problem is 
     * a relaxed version of the convex hull problem. The convex hull problem, in my own
     * words, is the following problem: Given a set S of points in a plane (or space),
     * find the smallest shape H such that for every pair of points in S, i and j, a 
     * straight line L can be drawn to connect points i and j where every point along
     * L is contained within H (including the boundary). In other words, we have a bunch
     * of points and we want the smallest shape possible that both contains all the 
     * points as well as any straight line drawn between two points. Since convex hull
     * problems frequently occur in computational geometry, a myriad of algorithms have
     * been created in order to solve the problem. For the solution I choose to use a 
     * modified version of the Graham scan algorithm. Before giving my algorithm I'll
     * give a general overview on the process of Graham scan in the below paragraph. 
     * 
     * Graham scan is probably the most commonly used algorithm when it comes to finding
     * a convex hull. The algorithm utilizes two main facts about convex hull to find a 
     * solution. The first fact it utilizes is that any convex hull for a series of points
     * will possess a boundary consisting of straight lines between points within the 
     * initial set points. Intuitively, this fact can be visualized by thinking of a set
     * of pins on a board. Now imagine if you try to use a rubberband to create a shape 
     * that includes all of the pins within it. It's not hard to convince yourself that 
     * for every arrangement of pins the rubberband will shrink (or stretch) so that the
     * shape outline consists of rubber band segments stretched between two of the pins. 
     * The second fact the algorithm builds upon is the following: If H is the solution
     * hull and E is the point on H that has a Y-coordinate lower than all other points 
     * on H and an X-coordinate lower than all points on H with the same Y-coordinate (in
     * other words the most bottom left point), then for any consecutive 3 points a, b, 
     * and c along H that increase their angle with E the path moving from a to b and b 
     * to c will be counterclockwise. The algorithm uses both these facts in order to 
     * generate a solution to the problem. The basic algorithm is as follows:
     * 
     *          1. Let points be the array of size N of all points in the initial set.
     *          2. Let ccw(a, b, c) be the function producing the cross product between 
     *             the vector (a,b) and the vector (a,c).
     *          3. Find the most bottom left point and set points[1] to be this point.
     *          4. Sort the points array from 2 to N by using the following comparator for
     *             two points a and b:
     *              i) Set CP to be the rsult of ccw(points[1], a, b).
     *              ii) If CP does not equal 0, then return CP. 
     *              iii) Otherwise:
     *                  a) If point a is closer to points[1], then return 1.
     *                  b) If point b is closer to points[1], then return -1.
     *          5. Initialize an empty stack S, and push points[0], points[1], and 
     *             points[2] onto S in that order. 
     *          6. For i = 3 to N do the following:
     *              i) While ccw(top of stack, second element on stack, points[i]) < 0:
     *                  a) Pop the top element off the stack
     *              ii) Push points[i] onto the stack.
     *          7. The points still on S are the solution hull. 
     * 
     * The solution to the problem uses the above algorithm with one minor modification.  
     * After sorting the points by their polar angle from the most bottom left point, the
     * section of points in the sorted array that are colinear with the bottom left point
     * in the X direction need to be reversed so that the first point encountered in this
     * colinear direction is the one farthest from bottom left point. The purpose of this
     * is to ensure that the point farthest away from the bottom left point along this 
     * line is the one used when calculating the cross product with the two points prior 
     * to the colinear section.
     * 
     * Note: There is a special case to consider when solving this problem:
     *      1. The size of points < 4 => Every input meeting this condition will also be 
     *         the solution to the problem.
     * ============================
     *
     *
     * Solution Complexities
     * ============================
     * Let n be the number of input points provided.
     * 
     * Time Complexity:  O(nlogn) => The main time cost comes from sorting the provided 
     * points, which takes O(nlogn) time. The while loop inside the for loop in the 
     * solution might might make it seem like the runtime is O(n^2); however, since every
     * element in points can be processed at most twice (once to be added to the stack
     * and once to be removed from the stack) this is actually O(n). Therefore, the 
     * overall runtime will be O(nlogn).
     * Space Complexity: O(n) => The entirety of space usage comes from elements living
     * upon the stack used in the algorithm. Because the stack at the end has all of the 
     * solution points and since the solution can have up to n points, the maximum size 
     * the stack could be is at most O(n). Therefore, the overall space complexity will be
     * O(n).
     * ============================
     */



    /**
     * Definition for a point.
     * class Point {
     *     int x;
     *     int y;
     *     Point() { x = 0; y = 0; }
     *     Point(int a, int b) { x = a; y = b; }
     * }
    */
    public List<Point> outerTrees(Point[] points) {
        /**
         * Special case is when no point can be contained within
         * the hull, which is for a number of points less than 4.
         */
        if(points.length < 4) return Arrays.asList(points);

        /**
         * Sort the points by their polar relationship with the 
         * bottom left point. If two points possess the same polar
         * relationship with the bottom left point, then the distance
         * to the bottom left point will be the deciding factor.
         */ 
        Point bottomLeftPoint = findBottomLeftPoint(points);
        sortPointsByPolar(points, bottomLeftPoint);

        /**
         * Perform the Graham scan over the points to produce a 
         * stack with only points that are consecutively ccw with
         * one another.
         */
        Stack<Point> stk = new Stack<>();
        stk.push(points[0]);
        stk.push(points[1]);
        for(int i = 2; i < points.length; i++) {
            Point top = stk.pop();
            while(ccw(stk.peek(), top, points[i]) < 0) {
                top = stk.pop();
            }
            stk.push(top);
            stk.push(points[i]);
        }
        return new ArrayList<>(stk);
    }

    public Point findBottomLeftPoint(Point[] points) {
        Point bottomLeft = points[0];
        for(Point p : points) {
            if(p.y < bottomLeft.y || (p.y == bottomLeft.y && p.x < bottomLeft.x)) {
                bottomLeft = p;
            }
        }
        return bottomLeft;
    }

    public int ccw(Point p1, Point p2, Point p3) {
        /**
         * Determines if the three points are positioned ccw to
         * one another or not by using the cross product of the
         * vectors (p2,p1) and (p2, p3). It returns:
         *     A positive number => If the points are ccw relative to one another
         *     0                 => If the points are colinear
         *     A negative number => If the points are cw relative to one another
         */
        return p1.x * p2.y - p1.y * p2.x + 
               p2.x * p3.y - p2.y * p3.x +
               p3.x * p1.y - p3.y * p1.x;       
    }

    public int distSq(Point p1, Point p2) {
        // Calculates the distance squared between the two given points
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    public void sortPointsByPolar(Point[] points, Point bottomLeftPoint) {
        /**
         * Sort the points by polar distance from bottomleft first,
         * and use distance from bottomLeft to break a tie.
         */
        Arrays.sort(points, (p, q) -> {
            int polarComparison = ccw(p, bottomLeftPoint, q);
            int distComparison = distSq(p, bottomLeftPoint) - distSq(q, bottomLeftPoint);
            return polarComparison == 0 ? distComparison : polarComparison;
        });
        /**
         * Reverse sorted order of all points colinear with the bottomLeft
         * point that are located at the end of the sorted array.
         */
        Point p = points[0];
        Point q = points[points.length - 1];
        int i = points.length - 2;
        while(i >= 0 && ccw(p, q, points[i]) == 0) i--;
        for(int j = i + 1, k = points.length - 1; j < k; j++, k--) {
            Point temp = points[j];
            points[j] = points[k];
            points[k] = temp;
        }
    }
}
