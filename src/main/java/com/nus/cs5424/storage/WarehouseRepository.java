package com.nus.cs5424.storage;

import com.nus.cs5424.data.Employee;
import com.nus.cs5424.data.Warehouse;
import com.yugabyte.data.jdbc.repository.YsqlRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends YsqlRepository<Warehouse, Integer> {

    //Warehouse findById(final Integer Id);
}
