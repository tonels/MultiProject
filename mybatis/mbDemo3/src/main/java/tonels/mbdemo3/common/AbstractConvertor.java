package tonels.mbdemo3.common;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConvertor<Model, DTO> {

	public abstract Model toModel(DTO arg0);
	
	public DTO toDTO(Model model) {
		return this.toDTO(model, false);
	}
	
	public abstract DTO toDTO(Model arg0, boolean arg1);
	
	public final List<DTO> toListDTO(List<Model> modelList) {
        List<DTO> dtoList = (List<DTO>)modelList.stream().map((model) -> {
            return this.toDTO(model, true);
        }).collect(Collectors.toList());
        return dtoList;
    }
	
	public final List<Model> toListModel(List<DTO> dtoList) {
        List<Model> modelList = (List<Model>)dtoList.stream().map((dto) -> {
            return this.toModel(dto);
        }).collect(Collectors.toList());
        return modelList;
    }
}
