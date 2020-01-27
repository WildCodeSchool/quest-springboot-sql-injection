# BankZecure Web Application

All the instructions are in the quest!

The fake customer accounts' credentials are listed [here](https://github.com/WildCodeSchool/quest-springboot-sql-injection/blob/master/FakeAccountsCredentials.md).

## Fix vulnerabilities via stored procedures

You have to create the stored procedures in MySQL.

**If you copy-pasted the `getCustomerCreditCards` procedure from the quest, you have to delete it before you proceed!**

Which you can do by running this from the MySQL console (being in `springboot_bankzecure` database):

```sql
DROP PROCEDURE getCustomerCreditCards;
```

Then, back to the shell, run this from the root of the repository:

```sh
mysql -uroot -p springboot_bankzecure < src/main/resources/db/stored-procedures.sql
```

This will create all the required procedures.
