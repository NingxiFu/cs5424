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

I have implment `CommandLineRunner ` class, so once the application is running, it will read the twenty x_act files and execute automatically. In this way, you can either run it on idea or use ` java -jar cs5424-0.0.4-SNAPSHOT.jar` command.

<img width="1194" alt="image" src="https://user-images.githubusercontent.com/39428811/200172721-2746ef17-ae2e-4949-80d2-3c2a52e17d59.png">



### 4. get reuslt

it will generate 20 files each corressponding to a client performance result.

<img width="539" alt="image" src="https://user-images.githubusercontent.com/39428811/200172756-906824b8-0064-4e54-8c30-6c7c0bbb77ff.png">


```shell
Total Number of transactions processed: 8762
Total elapsed time for processing the transactions: 9357.772
Transaction throughput: 0.9363339906122953
Average transaction latency: 1067.994978315453
Median transaction latency: 485.0
95th percentile transaction latency: 1714.0
99th percentile transaction latency: 15559.68

```


### 5. get transaction output log

<img width="1194" alt="image" src="https://user-images.githubusercontent.com/39428811/200172606-4095ee88-5771-4f6b-bdcc-75cfbee6c027.png">


