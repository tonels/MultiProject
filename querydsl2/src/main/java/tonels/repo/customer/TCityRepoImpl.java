package tonels.repo.customer;

import tonels.entity.QCustomersEntity;
import tonels.entity.QOrderdetailsEntity;
import tonels.entity.QOrdersEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;

public class TCityRepoImpl implements TCityRepoCustom {

    @PersistenceContext
    private EntityManager em;

    private static final QCustomersEntity cm = QCustomersEntity.customersEntity;

    private static final QOrdersEntity od = QOrdersEntity.ordersEntity;

    private static final QOrderdetailsEntity ot = QOrderdetailsEntity.orderdetailsEntity;

    @Override
    public Map<String, Object> groupConcat1() {

        return null;
    }
}
