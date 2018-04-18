# SQLBuilder 
SQLBuilder是包含一些工具类方便生成和格式化SQL.

SQLBuilder支持如下的SQL语句生成:

* SELECT
* INSERT
* UPDATE
* DELETE
* CREATE

## SELECT

使用 `SelectBuilder` 支持操作符:

* 算术运算符 +, -, *, /, %
* 布尔表达式 AND, OR, NOT
* 比较运算 >, >=, <, <=, =, <>, IS NULL, IS NOT NULL, ...
* CASE WHEN
* 函数调用表达式 SUM, COUNT, AVG, MAX, MIN, ...
* 子查询
* ......


```java
Select select = new SelectBuilder()
                .select("a.id", "student_id")
                .select("b")
                .select("c")
                .from("student", "a")
                .from("course")
                .join("g", JoinType.INNER_JOIN, new PredicateBuilder().basicPredicate("g.id = a.id").and("g.id > 10000").build())
                .where("1 = 1")
                .where("2 = 2")
                .where(new In("1", new SelectBuilder().select("id").from("person").build()))
                .where(new NotIn("1", Arrays.asList("2", "3", "4")))
                .or(new Or(new BasicPredicate("1 > 1"), new BasicPredicate("2 < 1")))
                .or(new PredicateBuilder().between(ColumnRef.of("person", "age"), IntegerValue.of(2), IntegerValue.of(1)).build())
                .and(new GreaterThan(FunctionRef.of("LENGTH", ColumnRef.of("person", "name")), StringValue.of("2012-01-01")))
                .and(Exists.of(new SelectBuilder().select("id").from("person").build()))
                .orderBy("a", false)
                .orderBy("b", true)
                .having("COUNT(*) > 1").having("SUM(aaa) < 1")
                .limit(122)
                .build();


System.out.println(select.toSQL());
System.out.println(select.format());

```

## INSERT

使用 `InsertBuilder`

```java
Insert insert = new InsertBuilder()
                .table("person")
                .set("id", "1")
                .set("name", "benben")
                .set("age", "333")
                .build();

        System.out.println(insert.toSQL());
```

## UPDATE

使用 `UpdateBuilder`

```java
Update update = new UpdateBuilder()
                .table("person")
                .set("id", "1")
                .set("name", "benben")
                .set("age", "21")
                .where("id = 111")
                .where("age = 222")
                .build();

System.out.println(update.toSQL()); 
```

## DELETE

使用 `DeleteBuilder`

```java
        Delete delete = new DeleteBuilder()
                .table("person")
                .where("id = 111")
                .where("age = 222").build();

System.out.println(delete.toSQL());
```

## CREATE

使用 `CreateBuilder`

```java
Create create = new CreateBuilder()
                .table("person")
                .createIfNotExists(true)
                .column("id", JDBCType.BIGINT)
                .column("age", JDBCType.INTEGER)
                .column(new Column("name", JDBCType.VARCHAR, true, false, null, 1, 255))
                .build();

System.out.println(create.toSQL());
```