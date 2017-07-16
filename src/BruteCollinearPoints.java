import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

	private Point[] points = null;
	private int numberOfSegments = 0;
	private LineSegment[] segments = null;

	public BruteCollinearPoints(Point[] points) {
		this.points = points;
		this.segments = new LineSegment[points.length];
	}

	public int numberOfSegments() {
		return numberOfSegments;
	}

	public LineSegment[] segments() {

		for (int i = 0; i < points.length; i++) {
			double[] slopes = new double[points.length];
			for (int j = 0; j < points.length; j++) {
				slopes[j] = points[i].slopeTo(points[j]);
			}

			int linearScopeIndexEnd = -1;
			int linearScopeIndexStart = -1;
			boolean isLineSegmentPresent = false;
			for (int ii = 0; ii < slopes.length; ii++) {
				int count = 0;
				for (int jj = 0; jj < slopes.length; jj++) {
					if (ii == jj) {
						continue;
					} else {
						if (slopes[jj] == slopes[ii]) {
							count += 1;
							linearScopeIndexEnd = jj;
						}
					}
				}
				if (count >= 2) {
					isLineSegmentPresent = true;
					numberOfSegments++;
					break;
				}
			}

			if (isLineSegmentPresent) {
				for (int k = 0; k < slopes.length; k++) {
					if (slopes[k] == slopes[linearScopeIndexEnd]) {
						linearScopeIndexStart = k;
						break;
					}
				}
				LineSegment temp = new LineSegment(
						points[linearScopeIndexStart],
						points[linearScopeIndexEnd]);
				segments[numberOfSegments - 1] = temp;

			}
		}
		LineSegment[] temp = new LineSegment[numberOfSegments];
		for (int i = 0; i < numberOfSegments; i++) {
			temp[i] = segments[i];
		}
		segments = temp;
		
		return segments;
	}

	public static void main(String[] args) {

		// read the n points from a file
		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments

		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();

	}
}