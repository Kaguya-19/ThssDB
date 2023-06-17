# 用户手册

登录: `connect <username> <password>`

语句不分大小写。

## 数据库操作

对数据库进行操作。

#### 语句

| 语句                         | 功能           |
| ---------------------------- | -------------- |
| `create database dbName;` | 创建数据库     |
| `drop database dbName;`   | 删除数据库     |
| `use dbName;`             | 激活数据库     |
| `show databases`             | 查看所有数据库 |

#### 示例

```sql
create database test;
create database test2;
drop database test2;
use test;
```

## 表操作

对数据库进行操作。

#### 语句

| 语句                      | 功能       |
| ------------------------- | ---------- |
| `CREATE TABLE tableName(attrName1 Type1,attrName2 Type2,attrNameN TypeN NOT NULL, …, PRIMARY KEY(attrName1));` | 创建表     |
| `DROP TABLE tableName;`      | 删除表     |
| `show tables`             | 查看所有表 |
| `SHOW TABLE tableName;`      | 查看表结构 |

#### 示例

```sql
create table test(id int, name varchar(20), primary key(id));
create table test2(id int, name varchar(20), primary key(id));
drop table test2;
show tables;
show table test;
```

## 数据操作

对表中数据进行操作。

#### 语句

| 语句                                       | 功能     |
| ------------------------------------------ | -------- |
| `INSERT INTO [tableName(attrName1, attrName2,…, attrNameN)] VALUES (attrValue1, attrValue2,…, attrValueN)`            | 插入数据 |
| `DELETE FROM tableName WHERE attrName = attValue` | 删除数据 |
| `UPDATE tableName SET attrName=attrValue WHERE attrName=attrValue` | 更新数据 |

#### 示例

```sql
insert into test(id, name) values(1, 'test');
insert into test(id, name) values(2, 'test2');
delete from test where id = 2;
update test set name = 'test3' where id = 1;
```

## 查询操作
查询表中数据。
#### 语句
| 语句                                       | 功能     |
| ------------------------------------------ | -------- |
| `SELECT attrName1, attrName2, … attrNameN FROM tableName [WHERE attrName1 = attrValue];`| 查询数据 |
| `SELECT tableName1.AttrName1, tableName1.AttrName2…, tableName2.AttrName1, tableName2.AttrName2,… FROM tableName1 JOIN tableName2 ON  tableName1attrName1=tableName2.attrName2 [WHERE  tableName1.attrName1 = attrValue];`| 查询数据 |
#### 示例
```sql
select * from test;
select * from test where id = 1;
select test.id, test.name from test join test2 on test.id = test2.id;
```

## 事务操作

对数据库进行操作。

#### 语句

| 语句                                                         | 功能         |
| ------------------------------------------------------------ | ------------ |
| `set transaction isolation level <serializable/read committed>` | 设置隔离级别 |
| `begin transaction`                                          | 开始事务     |
| `commit`                                                     | 提交事务     |

#### 示例

```sql
set transaction isolation level read committed;
begin transaction
insert into test(id, name) values(1, 'test');
commit;
```