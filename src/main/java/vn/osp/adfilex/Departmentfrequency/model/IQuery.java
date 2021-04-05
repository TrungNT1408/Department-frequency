/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.model;

/**
 *
 * @author abc
 */
public interface IQuery {

    public String build(String fieldName, boolean hasWhere);
}
