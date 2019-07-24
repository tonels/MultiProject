package tonels.mbdemo3.convertor;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import tonels.mbdemo3.common.AbstractConvertor;
import tonels.mbdemo3.entity.Offices;
import tonels.mbdemo3.vo.OfficesVO;

@Component
public class UserConverter extends AbstractConvertor<Offices, OfficesVO> {
	@Override
	public Offices toModel(OfficesVO arg0) {
		return null;
	}

	@Override
	public OfficesVO toDTO(Offices model, boolean arg1) {

		OfficesVO vo = new OfficesVO();

		BeanUtils.copyProperties(model, vo);

		return vo;
	}
}
