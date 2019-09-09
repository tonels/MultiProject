package demo3.hosting.bo;


import demo3.seq.exception.SequenceException;

public interface HostingBo {

	void save(String name) throws SequenceException;

}