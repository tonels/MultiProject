package com.tonels.modelTable.repo;

import com.tonels.modelTable.model.S1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StuRepo extends JpaRepository<S1, Integer>, JpaSpecificationExecutor<S1> {

    @Modifying
    @Transactional
    @Query("update S1 set sName = ?1 where sId = ?2")
    int updateNameById(String sname,Integer id);

}
