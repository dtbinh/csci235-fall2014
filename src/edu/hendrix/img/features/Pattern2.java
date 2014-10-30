package edu.hendrix.img.features;

import edu.hendrix.ev3webcam.Point;

public class Pattern2 extends PointFieldPattern {
	public Pattern2() {
		super(20, 15);
	}
	protected PointPair[] makePairs() {
		PointPair[] result = new PointPair[64];
		result[0] = new PointPair(new Point(15,12), new Point(19,14));
		result[1] = new PointPair(new Point(16,9), new Point(0,5));
		result[2] = new PointPair(new Point(8,7), new Point(2,11));
		result[3] = new PointPair(new Point(17,5), new Point(9,12));
		result[4] = new PointPair(new Point(8,6), new Point(13,9));
		result[5] = new PointPair(new Point(12,12), new Point(10,13));
		result[6] = new PointPair(new Point(8,10), new Point(6,12));
		result[7] = new PointPair(new Point(3,2), new Point(9,7));
		result[8] = new PointPair(new Point(0,12), new Point(3,11));
		result[9] = new PointPair(new Point(12,2), new Point(16,1));
		result[10] = new PointPair(new Point(4,9), new Point(1,9));
		result[11] = new PointPair(new Point(12,9), new Point(1,2));
		result[12] = new PointPair(new Point(6,3), new Point(19,13));
		result[13] = new PointPair(new Point(13,5), new Point(2,6));
		result[14] = new PointPair(new Point(14,8), new Point(5,12));
		result[15] = new PointPair(new Point(4,0), new Point(2,5));
		result[16] = new PointPair(new Point(16,5), new Point(17,0));
		result[17] = new PointPair(new Point(16,13), new Point(2,14));
		result[18] = new PointPair(new Point(13,10), new Point(19,10));
		result[19] = new PointPair(new Point(0,0), new Point(16,12));
		result[20] = new PointPair(new Point(2,2), new Point(14,8));
		result[21] = new PointPair(new Point(16,2), new Point(19,9));
		result[22] = new PointPair(new Point(14,11), new Point(12,7));
		result[23] = new PointPair(new Point(8,0), new Point(5,8));
		result[24] = new PointPair(new Point(11,13), new Point(5,3));
		result[25] = new PointPair(new Point(11,4), new Point(11,8));
		result[26] = new PointPair(new Point(1,2), new Point(4,11));
		result[27] = new PointPair(new Point(5,13), new Point(8,11));
		result[28] = new PointPair(new Point(18,9), new Point(9,4));
		result[29] = new PointPair(new Point(12,6), new Point(9,9));
		result[30] = new PointPair(new Point(8,12), new Point(17,11));
		result[31] = new PointPair(new Point(14,8), new Point(17,12));
		result[32] = new PointPair(new Point(17,12), new Point(4,5));
		result[33] = new PointPair(new Point(9,4), new Point(2,8));
		result[34] = new PointPair(new Point(2,8), new Point(1,0));
		result[35] = new PointPair(new Point(18,9), new Point(3,2));
		result[36] = new PointPair(new Point(3,6), new Point(16,8));
		result[37] = new PointPair(new Point(10,1), new Point(11,4));
		result[38] = new PointPair(new Point(12,14), new Point(18,1));
		result[39] = new PointPair(new Point(3,10), new Point(4,6));
		result[40] = new PointPair(new Point(2,7), new Point(17,4));
		result[41] = new PointPair(new Point(19,13), new Point(17,8));
		result[42] = new PointPair(new Point(2,5), new Point(1,3));
		result[43] = new PointPair(new Point(7,13), new Point(13,3));
		result[44] = new PointPair(new Point(5,10), new Point(7,3));
		result[45] = new PointPair(new Point(6,6), new Point(19,2));
		result[46] = new PointPair(new Point(10,5), new Point(14,12));
		result[47] = new PointPair(new Point(7,10), new Point(12,3));
		result[48] = new PointPair(new Point(7,8), new Point(16,1));
		result[49] = new PointPair(new Point(9,10), new Point(2,14));
		result[50] = new PointPair(new Point(7,10), new Point(8,3));
		result[51] = new PointPair(new Point(13,5), new Point(0,10));
		result[52] = new PointPair(new Point(16,11), new Point(11,3));
		result[53] = new PointPair(new Point(10,1), new Point(7,5));
		result[54] = new PointPair(new Point(2,5), new Point(2,9));
		result[55] = new PointPair(new Point(12,12), new Point(2,12));
		result[56] = new PointPair(new Point(18,0), new Point(13,11));
		result[57] = new PointPair(new Point(14,3), new Point(18,8));
		result[58] = new PointPair(new Point(0,14), new Point(3,3));
		result[59] = new PointPair(new Point(4,0), new Point(10,3));
		result[60] = new PointPair(new Point(0,10), new Point(15,14));
		result[61] = new PointPair(new Point(5,10), new Point(12,3));
		result[62] = new PointPair(new Point(8,5), new Point(0,7));
		result[63] = new PointPair(new Point(5,14), new Point(17,2));
		return result;
	}
}
