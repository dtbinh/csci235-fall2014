package edu.hendrix.img.features;

import edu.hendrix.ev3webcam.Point;

public class Pattern1 extends PointFieldPattern {
	public Pattern1() {
		super(20, 15);
	}
	protected PointPair[] makePairs() {
		PointPair[] result = new PointPair[256];
		result[0] = new PointPair(new Point(13,11), new Point(17,10));
		result[1] = new PointPair(new Point(4,11), new Point(6,12));
		result[2] = new PointPair(new Point(2,4), new Point(5,13));
		result[3] = new PointPair(new Point(4,6), new Point(9,4));
		result[4] = new PointPair(new Point(17,7), new Point(18,0));
		result[5] = new PointPair(new Point(15,4), new Point(17,11));
		result[6] = new PointPair(new Point(3,3), new Point(3,6));
		result[7] = new PointPair(new Point(9,10), new Point(17,4));
		result[8] = new PointPair(new Point(8,9), new Point(0,13));
		result[9] = new PointPair(new Point(12,8), new Point(14,6));
		result[10] = new PointPair(new Point(1,0), new Point(19,0));
		result[11] = new PointPair(new Point(13,12), new Point(7,9));
		result[12] = new PointPair(new Point(11,13), new Point(16,11));
		result[13] = new PointPair(new Point(15,14), new Point(16,14));
		result[14] = new PointPair(new Point(18,10), new Point(6,7));
		result[15] = new PointPair(new Point(6,7), new Point(10,12));
		result[16] = new PointPair(new Point(19,7), new Point(16,7));
		result[17] = new PointPair(new Point(16,7), new Point(9,10));
		result[18] = new PointPair(new Point(13,13), new Point(16,12));
		result[19] = new PointPair(new Point(15,3), new Point(16,1));
		result[20] = new PointPair(new Point(3,13), new Point(11,13));
		result[21] = new PointPair(new Point(12,5), new Point(8,13));
		result[22] = new PointPair(new Point(3,2), new Point(3,8));
		result[23] = new PointPair(new Point(13,3), new Point(5,10));
		result[24] = new PointPair(new Point(16,10), new Point(19,11));
		result[25] = new PointPair(new Point(0,2), new Point(7,4));
		result[26] = new PointPair(new Point(1,8), new Point(9,14));
		result[27] = new PointPair(new Point(9,0), new Point(9,4));
		result[28] = new PointPair(new Point(7,14), new Point(3,12));
		result[29] = new PointPair(new Point(3,9), new Point(1,12));
		result[30] = new PointPair(new Point(18,7), new Point(13,1));
		result[31] = new PointPair(new Point(3,8), new Point(5,1));
		result[32] = new PointPair(new Point(4,8), new Point(4,14));
		result[33] = new PointPair(new Point(3,7), new Point(15,3));
		result[34] = new PointPair(new Point(16,2), new Point(6,6));
		result[35] = new PointPair(new Point(0,4), new Point(10,8));
		result[36] = new PointPair(new Point(12,11), new Point(18,2));
		result[37] = new PointPair(new Point(4,5), new Point(2,1));
		result[38] = new PointPair(new Point(3,12), new Point(8,4));
		result[39] = new PointPair(new Point(4,12), new Point(3,3));
		result[40] = new PointPair(new Point(19,9), new Point(7,6));
		result[41] = new PointPair(new Point(2,7), new Point(18,12));
		result[42] = new PointPair(new Point(16,3), new Point(1,3));
		result[43] = new PointPair(new Point(7,12), new Point(6,6));
		result[44] = new PointPair(new Point(10,13), new Point(6,6));
		result[45] = new PointPair(new Point(2,12), new Point(0,3));
		result[46] = new PointPair(new Point(3,3), new Point(17,14));
		result[47] = new PointPair(new Point(11,2), new Point(3,10));
		result[48] = new PointPair(new Point(14,13), new Point(4,3));
		result[49] = new PointPair(new Point(8,13), new Point(5,6));
		result[50] = new PointPair(new Point(4,0), new Point(0,11));
		result[51] = new PointPair(new Point(19,0), new Point(11,14));
		result[52] = new PointPair(new Point(6,3), new Point(18,9));
		result[53] = new PointPair(new Point(17,9), new Point(19,0));
		result[54] = new PointPair(new Point(0,11), new Point(3,0));
		result[55] = new PointPair(new Point(16,13), new Point(9,8));
		result[56] = new PointPair(new Point(11,6), new Point(12,4));
		result[57] = new PointPair(new Point(10,2), new Point(1,7));
		result[58] = new PointPair(new Point(15,6), new Point(18,8));
		result[59] = new PointPair(new Point(11,1), new Point(3,4));
		result[60] = new PointPair(new Point(2,11), new Point(3,9));
		result[61] = new PointPair(new Point(18,10), new Point(11,4));
		result[62] = new PointPair(new Point(19,5), new Point(2,10));
		result[63] = new PointPair(new Point(0,13), new Point(17,4));
		result[64] = new PointPair(new Point(4,8), new Point(18,2));
		result[65] = new PointPair(new Point(11,10), new Point(13,1));
		result[66] = new PointPair(new Point(7,5), new Point(7,6));
		result[67] = new PointPair(new Point(16,1), new Point(11,8));
		result[68] = new PointPair(new Point(12,7), new Point(12,1));
		result[69] = new PointPair(new Point(17,6), new Point(16,13));
		result[70] = new PointPair(new Point(0,8), new Point(6,6));
		result[71] = new PointPair(new Point(3,3), new Point(2,1));
		result[72] = new PointPair(new Point(15,14), new Point(19,10));
		result[73] = new PointPair(new Point(16,2), new Point(0,14));
		result[74] = new PointPair(new Point(3,14), new Point(12,12));
		result[75] = new PointPair(new Point(9,10), new Point(3,4));
		result[76] = new PointPair(new Point(10,0), new Point(9,6));
		result[77] = new PointPair(new Point(13,12), new Point(5,1));
		result[78] = new PointPair(new Point(8,13), new Point(12,3));
		result[79] = new PointPair(new Point(4,3), new Point(1,6));
		result[80] = new PointPair(new Point(5,13), new Point(5,0));
		result[81] = new PointPair(new Point(16,1), new Point(5,13));
		result[82] = new PointPair(new Point(15,5), new Point(15,13));
		result[83] = new PointPair(new Point(19,9), new Point(14,11));
		result[84] = new PointPair(new Point(10,0), new Point(10,5));
		result[85] = new PointPair(new Point(7,8), new Point(7,6));
		result[86] = new PointPair(new Point(5,14), new Point(15,13));
		result[87] = new PointPair(new Point(4,6), new Point(2,13));
		result[88] = new PointPair(new Point(3,2), new Point(14,3));
		result[89] = new PointPair(new Point(12,8), new Point(9,2));
		result[90] = new PointPair(new Point(19,9), new Point(3,10));
		result[91] = new PointPair(new Point(18,13), new Point(6,12));
		result[92] = new PointPair(new Point(2,6), new Point(7,2));
		result[93] = new PointPair(new Point(14,3), new Point(6,3));
		result[94] = new PointPair(new Point(8,8), new Point(7,13));
		result[95] = new PointPair(new Point(2,3), new Point(5,5));
		result[96] = new PointPair(new Point(7,10), new Point(10,5));
		result[97] = new PointPair(new Point(0,0), new Point(16,8));
		result[98] = new PointPair(new Point(11,7), new Point(1,14));
		result[99] = new PointPair(new Point(5,9), new Point(17,5));
		result[100] = new PointPair(new Point(16,8), new Point(9,4));
		result[101] = new PointPair(new Point(11,3), new Point(6,9));
		result[102] = new PointPair(new Point(2,3), new Point(17,4));
		result[103] = new PointPair(new Point(2,4), new Point(10,1));
		result[104] = new PointPair(new Point(7,7), new Point(19,2));
		result[105] = new PointPair(new Point(7,0), new Point(13,13));
		result[106] = new PointPair(new Point(15,2), new Point(11,3));
		result[107] = new PointPair(new Point(8,1), new Point(8,0));
		result[108] = new PointPair(new Point(2,1), new Point(7,14));
		result[109] = new PointPair(new Point(13,3), new Point(10,8));
		result[110] = new PointPair(new Point(5,1), new Point(12,2));
		result[111] = new PointPair(new Point(18,4), new Point(12,1));
		result[112] = new PointPair(new Point(10,6), new Point(15,13));
		result[113] = new PointPair(new Point(5,4), new Point(2,8));
		result[114] = new PointPair(new Point(3,10), new Point(16,1));
		result[115] = new PointPair(new Point(4,12), new Point(10,12));
		result[116] = new PointPair(new Point(18,2), new Point(18,2));
		result[117] = new PointPair(new Point(5,13), new Point(0,12));
		result[118] = new PointPair(new Point(7,8), new Point(0,7));
		result[119] = new PointPair(new Point(19,0), new Point(8,5));
		result[120] = new PointPair(new Point(15,5), new Point(9,5));
		result[121] = new PointPair(new Point(3,5), new Point(1,2));
		result[122] = new PointPair(new Point(3,14), new Point(0,14));
		result[123] = new PointPair(new Point(13,3), new Point(13,8));
		result[124] = new PointPair(new Point(18,13), new Point(13,9));
		result[125] = new PointPair(new Point(10,8), new Point(6,5));
		result[126] = new PointPair(new Point(10,5), new Point(13,14));
		result[127] = new PointPair(new Point(12,8), new Point(14,7));
		result[128] = new PointPair(new Point(16,2), new Point(17,11));
		result[129] = new PointPair(new Point(5,3), new Point(7,13));
		result[130] = new PointPair(new Point(3,1), new Point(12,14));
		result[131] = new PointPair(new Point(6,9), new Point(15,8));
		result[132] = new PointPair(new Point(12,14), new Point(8,9));
		result[133] = new PointPair(new Point(13,10), new Point(0,5));
		result[134] = new PointPair(new Point(4,14), new Point(8,11));
		result[135] = new PointPair(new Point(3,14), new Point(1,11));
		result[136] = new PointPair(new Point(16,11), new Point(8,4));
		result[137] = new PointPair(new Point(10,9), new Point(11,3));
		result[138] = new PointPair(new Point(15,5), new Point(6,9));
		result[139] = new PointPair(new Point(15,3), new Point(13,9));
		result[140] = new PointPair(new Point(15,14), new Point(11,9));
		result[141] = new PointPair(new Point(9,11), new Point(8,1));
		result[142] = new PointPair(new Point(0,10), new Point(16,3));
		result[143] = new PointPair(new Point(8,13), new Point(3,8));
		result[144] = new PointPair(new Point(2,1), new Point(13,7));
		result[145] = new PointPair(new Point(15,2), new Point(7,10));
		result[146] = new PointPair(new Point(11,2), new Point(12,5));
		result[147] = new PointPair(new Point(9,12), new Point(15,5));
		result[148] = new PointPair(new Point(7,11), new Point(6,10));
		result[149] = new PointPair(new Point(19,2), new Point(0,10));
		result[150] = new PointPair(new Point(9,5), new Point(1,3));
		result[151] = new PointPair(new Point(0,2), new Point(6,3));
		result[152] = new PointPair(new Point(8,3), new Point(0,2));
		result[153] = new PointPair(new Point(15,13), new Point(5,13));
		result[154] = new PointPair(new Point(3,13), new Point(6,5));
		result[155] = new PointPair(new Point(4,5), new Point(13,4));
		result[156] = new PointPair(new Point(15,12), new Point(14,5));
		result[157] = new PointPair(new Point(6,13), new Point(17,13));
		result[158] = new PointPair(new Point(1,0), new Point(16,11));
		result[159] = new PointPair(new Point(6,12), new Point(15,3));
		result[160] = new PointPair(new Point(13,9), new Point(10,8));
		result[161] = new PointPair(new Point(18,10), new Point(10,5));
		result[162] = new PointPair(new Point(19,2), new Point(7,14));
		result[163] = new PointPair(new Point(11,8), new Point(6,10));
		result[164] = new PointPair(new Point(5,14), new Point(6,14));
		result[165] = new PointPair(new Point(19,10), new Point(7,9));
		result[166] = new PointPair(new Point(18,7), new Point(3,4));
		result[167] = new PointPair(new Point(7,1), new Point(10,6));
		result[168] = new PointPair(new Point(5,9), new Point(5,9));
		result[169] = new PointPair(new Point(14,14), new Point(10,12));
		result[170] = new PointPair(new Point(11,12), new Point(2,8));
		result[171] = new PointPair(new Point(3,12), new Point(11,7));
		result[172] = new PointPair(new Point(4,3), new Point(3,1));
		result[173] = new PointPair(new Point(18,14), new Point(10,11));
		result[174] = new PointPair(new Point(18,0), new Point(4,7));
		result[175] = new PointPair(new Point(19,4), new Point(18,5));
		result[176] = new PointPair(new Point(16,4), new Point(3,3));
		result[177] = new PointPair(new Point(0,3), new Point(13,1));
		result[178] = new PointPair(new Point(16,5), new Point(6,6));
		result[179] = new PointPair(new Point(11,6), new Point(8,8));
		result[180] = new PointPair(new Point(12,8), new Point(3,9));
		result[181] = new PointPair(new Point(10,13), new Point(10,4));
		result[182] = new PointPair(new Point(0,4), new Point(19,3));
		result[183] = new PointPair(new Point(1,13), new Point(13,6));
		result[184] = new PointPair(new Point(9,0), new Point(8,0));
		result[185] = new PointPair(new Point(7,6), new Point(14,1));
		result[186] = new PointPair(new Point(16,9), new Point(0,9));
		result[187] = new PointPair(new Point(0,14), new Point(1,5));
		result[188] = new PointPair(new Point(5,10), new Point(0,14));
		result[189] = new PointPair(new Point(3,14), new Point(2,12));
		result[190] = new PointPair(new Point(4,2), new Point(10,0));
		result[191] = new PointPair(new Point(17,6), new Point(17,14));
		result[192] = new PointPair(new Point(14,14), new Point(18,5));
		result[193] = new PointPair(new Point(12,1), new Point(14,5));
		result[194] = new PointPair(new Point(8,2), new Point(18,2));
		result[195] = new PointPair(new Point(1,12), new Point(13,12));
		result[196] = new PointPair(new Point(11,1), new Point(6,0));
		result[197] = new PointPair(new Point(2,10), new Point(11,3));
		result[198] = new PointPair(new Point(9,3), new Point(19,7));
		result[199] = new PointPair(new Point(4,0), new Point(15,8));
		result[200] = new PointPair(new Point(2,9), new Point(15,1));
		result[201] = new PointPair(new Point(6,9), new Point(5,6));
		result[202] = new PointPair(new Point(16,5), new Point(7,11));
		result[203] = new PointPair(new Point(19,12), new Point(1,2));
		result[204] = new PointPair(new Point(10,10), new Point(7,11));
		result[205] = new PointPair(new Point(19,7), new Point(13,5));
		result[206] = new PointPair(new Point(13,0), new Point(10,13));
		result[207] = new PointPair(new Point(2,10), new Point(10,11));
		result[208] = new PointPair(new Point(1,2), new Point(10,3));
		result[209] = new PointPair(new Point(3,0), new Point(15,9));
		result[210] = new PointPair(new Point(5,3), new Point(15,7));
		result[211] = new PointPair(new Point(14,11), new Point(17,7));
		result[212] = new PointPair(new Point(2,2), new Point(9,13));
		result[213] = new PointPair(new Point(17,2), new Point(15,7));
		result[214] = new PointPair(new Point(11,3), new Point(13,1));
		result[215] = new PointPair(new Point(18,3), new Point(4,11));
		result[216] = new PointPair(new Point(7,12), new Point(16,9));
		result[217] = new PointPair(new Point(3,9), new Point(19,7));
		result[218] = new PointPair(new Point(17,0), new Point(7,8));
		result[219] = new PointPair(new Point(12,11), new Point(17,7));
		result[220] = new PointPair(new Point(11,2), new Point(19,1));
		result[221] = new PointPair(new Point(1,9), new Point(3,2));
		result[222] = new PointPair(new Point(15,1), new Point(10,9));
		result[223] = new PointPair(new Point(13,0), new Point(11,2));
		result[224] = new PointPair(new Point(16,3), new Point(4,3));
		result[225] = new PointPair(new Point(19,8), new Point(19,6));
		result[226] = new PointPair(new Point(10,11), new Point(18,10));
		result[227] = new PointPair(new Point(0,9), new Point(1,1));
		result[228] = new PointPair(new Point(1,12), new Point(13,1));
		result[229] = new PointPair(new Point(14,10), new Point(19,11));
		result[230] = new PointPair(new Point(9,9), new Point(12,7));
		result[231] = new PointPair(new Point(2,2), new Point(12,14));
		result[232] = new PointPair(new Point(12,14), new Point(10,3));
		result[233] = new PointPair(new Point(11,0), new Point(1,11));
		result[234] = new PointPair(new Point(6,8), new Point(4,10));
		result[235] = new PointPair(new Point(4,7), new Point(4,7));
		result[236] = new PointPair(new Point(4,9), new Point(0,8));
		result[237] = new PointPair(new Point(13,2), new Point(0,13));
		result[238] = new PointPair(new Point(13,5), new Point(3,12));
		result[239] = new PointPair(new Point(18,10), new Point(11,13));
		result[240] = new PointPair(new Point(3,11), new Point(6,11));
		result[241] = new PointPair(new Point(2,5), new Point(9,10));
		result[242] = new PointPair(new Point(18,3), new Point(18,14));
		result[243] = new PointPair(new Point(16,7), new Point(3,2));
		result[244] = new PointPair(new Point(0,10), new Point(3,11));
		result[245] = new PointPair(new Point(9,1), new Point(14,11));
		result[246] = new PointPair(new Point(3,3), new Point(8,4));
		result[247] = new PointPair(new Point(8,5), new Point(3,13));
		result[248] = new PointPair(new Point(9,10), new Point(8,6));
		result[249] = new PointPair(new Point(12,11), new Point(1,0));
		result[250] = new PointPair(new Point(11,14), new Point(18,11));
		result[251] = new PointPair(new Point(9,11), new Point(18,1));
		result[252] = new PointPair(new Point(13,7), new Point(2,1));
		result[253] = new PointPair(new Point(7,12), new Point(12,4));
		result[254] = new PointPair(new Point(15,0), new Point(6,2));
		result[255] = new PointPair(new Point(13,14), new Point(14,10));
		return result;
	}
}
