package edu.hendrix.lmsl.unsupervised.gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import edu.hendrix.lmsl.EnumHistogram;
import edu.hendrix.lmsl.unsupervised.controllers.Flag;

@SuppressWarnings("serial")
public class HistogramEditorPanel extends JPanel {
	private JTable values;
	private EnumHistogram<Flag> histogram;
	private FuzzyGNGViewPanel displayPanel;
	
	private class HModel implements TableModel {

		@Override
		public int getRowCount() {
			return Flag.values().length;
		}

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public String getColumnName(int columnIndex) {
			return columnIndex == 0 ? "Flag" : "Count";
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return columnIndex == 0 ? Flag.class : Integer.class;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return columnIndex == 1;
		}
		
		public Flag flagAt(int row) {
			return Flag.values()[row];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex == 0) {
				return flagAt(rowIndex);
			} else if (histogram == null) {
				return 0;
			} else {
				return histogram.getCountFor(flagAt(rowIndex));
			}
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			if (columnIndex == 1) {
				histogram.setCountFor(flagAt(rowIndex), (int)aValue);
				displayPanel.markChanged();
				displayPanel.repaint();
			}
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public HistogramEditorPanel(FuzzyGNGViewPanel displayPanel) {
		this.displayPanel = displayPanel;
		histogram = null;
		values = new JTable(new HModel());
		add(values);
	}
	
	public void resetTo(EnumHistogram<Flag> current) {
		histogram = current;
		repaint();
	}
}
