# YSQL_BechMark Instruction

## Usage

**overview**: we use java to complete sql benchMark under Springboot framework. 

### Prerequisites

Environment:  
* JDK 1.8 and Above
* MAVEN 3.6

### 0. construct porject

```shell
  git clone https://github.com/guochenghui13/cs5424
```
You can open it either in Idea or other IDE, which support spring framework.

### 1. create relational table.

You can find the SQL statments of createing table in our folders. Just paste it into yugabyte database to create tables.

### 2. load data

After you downloading the dataset, you could load it into tables.

```SQL
COPY "Warehouse" FROM '/home/stuproj/cs4224n/project_data/data_files/warehouse_1.csv' DELIMITER ',' Null 'null';
COPY "WarehouseDistrict" FROM '/home/stuproj/cs4224n/project_data/data_files/warehousedistrict.csv' DELIMITER ',' Null 'null';
COPY "WarehouseDistrict_Read" FROM '/home/stuproj/cs4224n/project_data/data_files/warehousedistrict.csv' DELIMITER ',' Null 'null';
COPY "Customer" FROM '/home/stuproj/cs4224n/project_data/data_files/customer.csv' DELIMITER ',' Null 'null';
COPY "Item" FROM '/home/stuproj/cs4224n/project_data/data_files/item.csv' DELIMITER ',' Null 'null';
COPY "Stock" FROM '/home/stuproj/cs4224n/project_data/data_files/stock.csv' DELIMITER ',' Null 'null';
COPY "Order" FROM '/home/stuproj/cs4224n/project_data/data_files/order.csv' DELIMITER ',' Null 'null';
COPY "OrderLine" FROM '/home/stuproj/cs4224n/project_data/data_files/order-line.csv' DELIMITER ',' Null 'null';

```

### 3. run the application


### 4. get reuslt




