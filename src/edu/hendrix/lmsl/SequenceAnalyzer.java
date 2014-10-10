package edu.hendrix.lmsl;

import java.util.ArrayList;
import java.util.EnumSet;

public class SequenceAnalyzer<F extends Enum<F>, M extends Enum<M> & ModeNameInterface> {
	private Layer<F,M> top;
	private ArrayList<SequenceSpecification<F,M>> specifications;
	private Class<F> flagType;
	private Class<M> modeType;
	
	public SequenceAnalyzer(Class<F> flagType, Class<M> modeType, Layer<F,M> top) {
		this.flagType = flagType;
		this.modeType = modeType;
		this.top = top;
		specifications = new ArrayList<SequenceSpecification<F,M>>();
	}
	
	public Sequence<F,M> getSequenceFor(ArrayList<FlagSet<F>> flagSets) {
		return new Sequence<F,M>(top, modeType, flagSets);
	}
	
	public void addSpecificationSequence(SequenceSpecification<F,M> spec) {
		specifications.add(spec);
	}
	
	public boolean matchesSpecification() {
		for (SequenceSpecification<F,M> spec: specifications) {
			if (!spec.matchesSpecification(top)) {
				return false;
			}
		}
		return true;
	}
	
	public EnumSet<M> getMissingModes() {
		EnumSet<M> missing = EnumSet.allOf(modeType);
		for (SequenceSpecification<F,M> spec: specifications) {
			missing.retainAll(spec.toSequence(top).getMissingModes(modeType));
		}
		return missing;
	}
	
	public boolean allModesCovered() {
		return getMissingModes().isEmpty();
	}
}
