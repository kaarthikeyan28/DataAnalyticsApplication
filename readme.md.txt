DataAnalyticsApplication

A console level multithreading application where it dumps dynamic csv files into database using threads.

Here I have used MySQL for database and JDBC for connectivity.

Modules :

1) UI - Initial booting class comprises of User Login .

2) ThreadQueue - Allows user to import files and view status. Threading is done using Executor Service. 

3) JDBC - A  Static Class for getting Connection.

4) Import - The imported files are dumped into the database(MySQL).

5) File - Files names and Current status of the files(Uploaded/Pending)  are stored.

6) View - Shows ongoing file imports and pending file imports.



program starts with main function.