package com.example.myhead.second.common.utils;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SpecificationBuild {

    public static Specification build(Object object) {
        Specification<?> specification = new Specification<Object>() {
            @Override
            public Predicate toPredicate(Root<Object> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> list = new ArrayList<>();

                Class<?> clazz = object.getClass();
                Field[] declaredFields = clazz.getDeclaredFields();
                try {
                    for (Field field : declaredFields) {
                        if (field.getType() == String.class) {
                            String value = (String) clazz.getMethod("get" + toUpperFirstCode(field.getName())).invoke(object);
                            if (null == value) {
                                continue;
                            }
                            Predicate predicate = criteriaBuilder.equal(root.get(field.getName()).as(field.getType()), value);
                            list.add(predicate);
                        }
                        if (field.getType() == Byte.class || field.getType() == byte.class) {
                            Byte value = (Byte) clazz.getMethod("get" + toUpperFirstCode(field.getName())).invoke(object);
                            if (null == value) {
                                continue;
                            }
                            Predicate predicate = criteriaBuilder.equal(root.get(field.getName()).as(field.getType()), value);
                            list.add(predicate);
                        }

                        if (field.getType() == Integer.class || field.getType() == int.class) {
                            Integer value = (Integer) clazz.getMethod("get" + toUpperFirstCode(field.getName())).invoke(object);
                            if (null == value) {
                                continue;
                            }
                            Predicate predicate = criteriaBuilder.equal(root.get(field.getName()).as(field.getType()), value);
                            list.add(predicate);
                        }

                        if (field.getType() == Long.class || field.getType() == long.class) {
                            Long value = (Long) clazz.getMethod("get" + toUpperFirstCode(field.getName())).invoke(object);
                            if (null == value) {
                                continue;
                            }
                            Predicate predicate = criteriaBuilder.equal(root.get(field.getName()).as(field.getType()), value);
                            list.add(predicate);

                        }
                        if (field.getType() == Float.class || field.getType() == float.class) {
                            Float value = (Float) clazz.getMethod("get" + toUpperFirstCode(field.getName())).invoke(object);
                            if (null == value) {
                                continue;
                            }
                            Predicate predicate = criteriaBuilder.equal(root.get(field.getName()).as(field.getType()), value);
                            list.add(predicate);
                        }
                        if (field.getType() == Double.class || field.getType() == double.class) {
                            Double value = (Double) clazz.getMethod("get" + toUpperFirstCode(field.getName())).invoke(object);
                            if (null == value) {
                                continue;
                            }
                            Predicate predicate = criteriaBuilder.equal(root.get(field.getName()).as(field.getType()), value);
                            list.add(predicate);

                        }
                        if (field.getType() == Boolean.class || field.getType() == boolean.class) {
                            Boolean value = (Boolean) clazz.getMethod("get" + toUpperFirstCode(field.getName())).invoke(object);
                            if (null == value) {
                                continue;
                            }
                            Predicate predicate = criteriaBuilder.equal(root.get(field.getName()).as(field.getType()), value);
                            list.add(predicate);
                        }

                    }

                    return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

                return criteriaBuilder.and();
            }
        };

        return specification;

    }

    public static String toUpperFirstCode(String str) {
        String[] strs = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String strTmp : strs) {
            char[] ch = strTmp.toCharArray();
            if (ch[0] >= 'a' && ch[0] <= 'z') {
                ch[0] = (char) (ch[0] - 32);
            }
            String strT = new String(ch);
            sb.append(strT).append(" ");
        }
        return sb.toString().trim();
    }


}
