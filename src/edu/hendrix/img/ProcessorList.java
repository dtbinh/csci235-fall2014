package edu.hendrix.img;

public class ProcessorList implements Processor {
	private Processor[] procs;
	
	public ProcessorList(Processor...procs) {
		this.procs = procs;
	}

	@Override
	public IntImage process(IntImage input) {
		for (Processor proc: procs) {
			input = proc.process(input);
		}
		return input;
	}

}
