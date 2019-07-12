package demo2.hosting.bo;

import demo2.hosting.dao.HostingDao;
import demo2.hosting.model.Hosting;
import demo2.seq.dao.SequenceDao;
import demo2.seq.exception.SequenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostingBoImpl implements HostingBo {

	private static final String HOSTING_SEQ_KEY = "hosting";

	@Autowired
	private SequenceDao sequenceDao;

	@Autowired
	private HostingDao hostingDao;

	@Override
	public void save(String name) throws SequenceException {

		Hosting hosting = new Hosting();

		hosting.setId(sequenceDao.getNextSequenceId(HOSTING_SEQ_KEY));
		hosting.setName(name);
		hostingDao.save(hosting);

		System.out.println(hosting);

	}

}