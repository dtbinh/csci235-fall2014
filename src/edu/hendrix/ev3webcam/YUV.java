package edu.hendrix.ev3webcam;

public enum YUV {
	Y {
		@Override
		public int get(YUYVImage img, int x, int y) {
			return img.getValueAt(2 * (y * img.getWidth() + x));
		}
	}, U {
		@Override
		public int get(YUYVImage img, int x, int y) {
			return img.getValueAt(img.getPairBase(x, y) + 1);
		}
	}, V {
		@Override
		public int get(YUYVImage img, int x, int y) {
			return img.getValueAt(img.getPairBase(x, y) + 3);
		}
	};
	abstract public int get(YUYVImage img, int x, int y);
}
