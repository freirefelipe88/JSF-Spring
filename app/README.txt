
To convert te project from tomcat to jboss you'll need to change the datasource in class PersistenceConfig.class to a JNDI " JndiObjectFactoryBean ".

To test the application you should ignore the bug in eclipse that gives an error saying that the persistence.xml is missing.
Create in mysql a db called mydb and a schema called freire.
In PersistenceConfig.class change the value of "hibernate.hbm2ddl.auto" from "update" to "create-drop" for the first time you run the application so the database can be created,
after that you can change it back to "update".

I did a CRUD with xhtml for all (Department, user and permissions).

There is a rest controller for users.

The tests were implemented only for department.