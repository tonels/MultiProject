package demo2.hosting.bo;


import demo2.seq.exception.SequenceException;

public interface HostingBo {

	void save(String name) throws SequenceException;

}