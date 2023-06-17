# 用户手册

登录: `connect <username> <password>`

语句不分大小写。



## 数据库操作

对数据库进行操作。

#### 语句

| 语句                         | 功能           |
| ---------------------------- | -------------- |
| `create database <database>` | 创建数据库     |
| `drop database <database>`   | 删除数据库     |
| `use <database>`             | 激活数据库     |
| `show databases`             | 查看所有数据库 |

#### 示例

```sql
```





## 表操作

对数据库进行操作。

#### 语句

| 语句                      | 功能       |
| ------------------------- | ---------- |
| `create table <table> ()` | 创建表     |
| `drop table <table>`      | 删除表     |
| `show tables`             | 查看所有表 |
| `show table <table>`      | 查看表结构 |

#### 示例

```sql
```





## 数据操作

对表中数据进行操作。

#### 语句

| 语句                                       | 功能     |
| ------------------------------------------ | -------- |
| `insert into <table> values <>`            | 插入数据 |
| `delete from <table> (where <conditions>)` | 删除数据 |
|                                            | 更新数据 |

#### 示例

```sql

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
```