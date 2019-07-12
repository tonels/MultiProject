package demo2.seq.dao;


import demo2.seq.exception.SequenceException;

public interface SequenceDao {

	long getNextSequenceId(String key) throws SequenceException;

}