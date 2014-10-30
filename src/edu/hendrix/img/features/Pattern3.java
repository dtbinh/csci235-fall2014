package edu.hendrix.img.features;

import edu.hendrix.ev3webcam.Point;

public class Pattern3 extends PointFieldPattern {
	public Pattern3() {
		super(8, 6);
	}
	protected PointPair[] makePairs() {
		PointPair[] result = new PointPair[64];
		result[0] = new PointPair(new Point(4,1), new Point(0,4));
		result[1] = new PointPair(new Point(7,5), new Point(0,2));
		result[2] = new PointPair(new Point(1,2), new Point(6,5));
		result[3] = new PointPair(new Point(0,0), new Point(5,1));
		result[4] = new PointPair(new Point(4,5), new Point(4,1));
		result[5] = new PointPair(new Point(7,1), new Point(1,3));
		result[6] = new PointPair(new Point(3,0), new Point(0,3));
		result[7] = new PointPair(new Point(2,5), new Point(7,5));
		result[8] = new PointPair(new Point(1,3), new Point(1,4));
		result[9] = new PointPair(new Point(0,5), new Point(2,1));
		result[10] = new PointPair(new Point(1,3), new Point(3,4));
		result[11] = new PointPair(new Point(3,1), new Point(3,5));
		result[12] = new PointPair(new Point(5,5), new Point(5,2));
		result[13] = new PointPair(new Point(5,4), new Point(3,4));
		result[14] = new PointPair(new Point(6,4), new Point(4,1));
		result[15] = new PointPair(new Point(0,1), new Point(2,2));
		result[16] = new PointPair(new Point(4,5), new Point(1,2));
		result[17] = new PointPair(new Point(7,0), new Point(4,3));
		result[18] = new PointPair(new Point(3,4), new Point(0,4));
		result[19] = new PointPair(new Point(4,1), new Point(3,3));
		result[20] = new PointPair(new Point(0,0), new Point(6,3));
		result[21] = new PointPair(new Point(4,5), new Point(4,4));
		result[22] = new PointPair(new Point(6,5), new Point(5,5));
		result[23] = new PointPair(new Point(2,3), new Point(5,1));
		result[24] = new PointPair(new Point(2,4), new Point(4,4));
		result[25] = new PointPair(new Point(2,5), new Point(0,2));
		result[26] = new PointPair(new Point(2,4), new Point(5,0));
		result[27] = new PointPair(new Point(2,2), new Point(6,2));
		result[28] = new PointPair(new Point(1,2), new Point(2,5));
		result[29] = new PointPair(new Point(6,0), new Point(0,1));
		result[30] = new PointPair(new Point(3,4), new Point(6,4));
		result[31] = new PointPair(new Point(7,3), new Point(3,4));
		result[32] = new PointPair(new Point(1,2), new Point(6,5));
		result[33] = new PointPair(new Point(3,4), new Point(7,1));
		result[34] = new PointPair(new Point(2,0), new Point(1,0));
		result[35] = new PointPair(new Point(3,2), new Point(5,5));
		result[36] = new PointPair(new Point(1,4), new Point(2,2));
		result[37] = new PointPair(new Point(3,5), new Point(2,4));
		result[38] = new PointPair(new Point(6,5), new Point(7,1));
		result[39] = new PointPair(new Point(7,2), new Point(0,5));
		result[40] = new PointPair(new Point(6,2), new Point(4,2));
		result[41] = new PointPair(new Point(4,5), new Point(4,2));
		result[42] = new PointPair(new Point(5,3), new Point(3,5));
		result[43] = new PointPair(new Point(7,4), new Point(0,5));
		result[44] = new PointPair(new Point(6,1), new Point(5,5));
		result[45] = new PointPair(new Point(4,1), new Point(1,5));
		result[46] = new PointPair(new Point(7,1), new Point(7,2));
		result[47] = new PointPair(new Point(7,4), new Point(5,3));
		result[48] = new PointPair(new Point(0,4), new Point(4,3));
		result[49] = new PointPair(new Point(1,5), new Point(5,1));
		result[50] = new PointPair(new Point(5,5), new Point(2,2));
		result[51] = new PointPair(new Point(2,2), new Point(5,4));
		result[52] = new PointPair(new Point(5,1), new Point(6,1));
		result[53] = new PointPair(new Point(4,3), new Point(1,2));
		result[54] = new PointPair(new Point(4,5), new Point(6,0));
		result[55] = new PointPair(new Point(2,4), new Point(6,5));
		result[56] = new PointPair(new Point(7,5), new Point(7,1));
		result[57] = new PointPair(new Point(3,2), new Point(6,5));
		result[58] = new PointPair(new Point(2,3), new Point(4,1));
		result[59] = new PointPair(new Point(2,0), new Point(3,0));
		result[60] = new PointPair(new Point(5,1), new Point(5,0));
		result[61] = new PointPair(new Point(5,5), new Point(2,4));
		result[62] = new PointPair(new Point(3,1), new Point(5,0));
		result[63] = new PointPair(new Point(4,1), new Point(3,4));
		return result;
	}
}
