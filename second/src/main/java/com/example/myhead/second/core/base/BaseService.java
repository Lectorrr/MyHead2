package com.example.myhead.second.core.base;

import com.example.myhead.second.common.entity.Condition;
import com.example.myhead.second.common.utils.RequestParameterProcess;
import com.example.myhead.second.common.entity.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.*;

/**
 * BaseService 类：
 * 是对通用的增删改查方法的封装，
 * 统一定义了包含保存、删除、批量删除、根据ID查询和分页查询的方法，
 * 一般的 Service 类会集成这个类。
 */
@Transactional
public class BaseService<E extends BaseEntity> {

    @Autowired
    private BaseDao<E, String> baseDao;

    /**
     * 增加/改 方法
     * save() 方法：当主键存在时更新数据，当主键不存在时插入数据。
     */
    public E saveOrUpdate(E entity) {
        Object object = baseDao.save(entity);
        return (E) object;
    }

    /**
     * 删除 方法
     *
     * @param id 传入实体的id
     */
    public void deleteById(String id) {
        baseDao.deleteById(id);
    }

    /**
     * 查找所有方法
     */
    public List<E> showList() {
        return baseDao.findAll();
    }

    /**
     * 传入 Id 查找数据
     *
     * @param id 传入实体的id
     */
    public E get(String id) {
        Optional optional = baseDao.findById(id);
        Object result = optional.get();
        return (E) result;
    }

    /**
     * 批量删除
     *
     * @param ids uuids
     */
    public void deletes(String... ids) {
        for (String id : ids) {
            baseDao.deleteById(id);
        }
    }

    /**
     * @param parameterMap parameterMap
     * @param clazz        clazz
     */
    public ResultData<E> findWithPage(Map<String, String[]> parameterMap, Class<?> clazz) {

        Integer length = Integer.valueOf(parameterMap.get("length")[0]);
        Integer start = Integer.valueOf(parameterMap.get("start")[0]);
        PageRequest pageRequest = PageRequest.of(start / length, length);
        List<Condition> conditions = RequestParameterProcess.process(parameterMap, "cn");

        Specification<E> specification = new Specification<E>() {
            @Override
            public Predicate toPredicate(Root<E> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();

                if (!CollectionUtils.isEmpty(conditions)) {

                    Field[] fields = clazz.getDeclaredFields();

                    for (Condition condition : conditions) {

                        String fieldName = condition.getFieldName();

                        for (Field field : fields) {
                            if (field.getName().equals(fieldName)) {
                                String fieldType = field.getGenericType().getTypeName();
                                Predicate predicate = null;
                                if (fieldType.equals("java.lang.String")) {
                                    Path<String> tempField = root.get(fieldName);
                                    predicate = getStringPredicate(criteriaBuilder, condition, predicate, tempField);
                                }
                                if (fieldType.equals("java.lang.Boolean") || fieldType.equals("boolean")) {
                                    Path<Boolean> tempField = root.get(fieldName);
                                    predicate = getBooleanPredicate(criteriaBuilder, condition, predicate, tempField);
                                }
                                if (fieldType.equals("java.lang.Integer") || fieldType.equals("int")) {
                                    Path<Integer> tempField = root.get(fieldName);
                                    predicate = getNumberPredicate(criteriaBuilder, condition, predicate, tempField);
                                }
                                if (fieldType.equals("java.lang.Double") || fieldType.equals("double")) {
                                    Path<Double> tempField = root.get(fieldName);
                                    predicate = getNumberPredicate(criteriaBuilder, condition, predicate, tempField);
                                }
                                if (fieldType.equals("java.lang.Float") || fieldType.equals("float")) {
                                    Path<Float> tempField = root.get(fieldName);
                                    predicate = getNumberPredicate(criteriaBuilder, condition, predicate, tempField);

                                }
                                if (fieldType.equals("java.lang.Byte") || fieldType.equals("byte")) {
                                    Path<Byte> tempField = root.get(fieldName);
                                    predicate = getNumberPredicate(criteriaBuilder, condition, predicate, tempField);
                                }

                                if (predicate == null) {
                                    break;
                                }
                                predicates.add(predicate);
                                break;
                            }
                        }
                    }
                }

                String[] strings = parameterMap.get("search[value]");
                if (null != strings && !StringUtils.isEmpty(strings[0])) {
                    Predicate predicatess[] = new Predicate[predicates.size()];
                    return criteriaBuilder.or(predicates.toArray(predicatess));
                } else {
                    Predicate predicatess[] = new Predicate[predicates.size()];
                    return criteriaBuilder.and(predicates.toArray(predicatess));
                }
            }
        };

        Page<E> all = baseDao.findAll(specification, pageRequest);

        ResultData<E> result = new ResultData<>();
        result.setData(all.getContent());
        result.setDraw(Integer.valueOf(parameterMap.get("draw")[0]));
        result.setRecordsFiltered(all.getTotalElements());
        result.setRecordsTotal(all.getTotalElements());

        return result;
    }

    private Predicate getStringPredicate(CriteriaBuilder criteriaBuilder, Condition condition, Predicate predicate, Path<String> tempField) {

        if ("eq".equals(condition.getOp())) {
            predicate = criteriaBuilder.equal(tempField, String.valueOf(condition.getFieldValue()));
        }

        if ("gt".equals(condition.getOp())) {
            predicate = criteriaBuilder.greaterThan(tempField, String.valueOf(condition.getFieldValue()));
        }

        if ("gte".equals(condition.getOp())) {
            predicate = criteriaBuilder.greaterThanOrEqualTo(tempField, String.valueOf(condition.getFieldValue()));
        }

        if ("lt".equals(condition.getOp())) {
            predicate = criteriaBuilder.lessThan(tempField, String.valueOf(condition.getFieldValue()));
        }

        if ("lte".equals(condition.getOp())) {
            predicate = criteriaBuilder.lessThanOrEqualTo(tempField, String.valueOf(condition.getFieldValue()));
        }

        if ("cn".equals(condition.getOp())) {
            predicate = criteriaBuilder.like(tempField, "%" + String.valueOf(condition.getFieldValue()) + "%");
        }
        return predicate;
    }

    private Predicate getBooleanPredicate(CriteriaBuilder criteriaBuilder, Condition condition, Predicate predicate, Path<Boolean> tempField) {
        if ("eq".equals(condition.getOp()) || "cn".equals(condition.getOp())) {
            predicate = criteriaBuilder.equal(tempField, (Boolean) condition.getFieldValue());
        }
        return predicate;
    }

    private Predicate getNumberPredicate(CriteriaBuilder criteriaBuilder, Condition condition, Predicate predicate, Path<? extends Number> tempField) {
        if ("eq".equals(condition.getOp()) || "cn".equals(condition.getOp())) {
            predicate = criteriaBuilder.equal(tempField, String.valueOf(condition.getFieldValue()));
        }

        if ("gt".equals(condition.getOp())) {
            predicate = criteriaBuilder.gt(tempField, (Number) condition.getFieldValue());
        }

        if ("gte".equals(condition.getOp())) {
            predicate = criteriaBuilder.ge(tempField, (Number) condition.getFieldValue());
        }

        if ("lt".equals(condition.getOp())) {
            predicate = criteriaBuilder.lt(tempField, (Number) condition.getFieldValue());
        }

        if ("lte".equals(condition.getOp())) {
            predicate = criteriaBuilder.le(tempField, (Number) condition.getFieldValue());
        }

        return predicate;
    }

}
