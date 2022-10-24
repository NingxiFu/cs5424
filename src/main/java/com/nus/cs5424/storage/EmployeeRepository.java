/**
 * @(#)EmployeeStorage.java, Oct 24, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage;

import com.nus.cs5424.data.Employee;
import com.yugabyte.data.jdbc.repository.YsqlRepository;
import org.springframework.stereotype.Repository;

/**
 * @author guochenghui
 */
@Repository
public interface EmployeeRepository extends YsqlRepository<Employee, Integer> {

    Employee findByEmail(final String email);

}