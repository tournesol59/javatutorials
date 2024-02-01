
README

I have a problem with this code. (jdbc with MySQL Database driver, customerdb
database with 4 Tables, whose I only do a query for the 'tblcustomers")
It is strange because the log that I write after every step seems to indicate
that the ResultSet object is eventually completed but cannot be parsed for
showing MetaData nor any fields after the query, I see the error in the last
log output that is called in the exception block. 
I used jdbc for other projects without any problem.


01:43:40.931 [com.baeldung.demo.QuickTestJdbc.main()] ERROR com.baeldung.demo.QuickTestJdbc - LoggerName :: com.baeldung.demo.QuickTestJdbc:: connection object created
01:43:44.010 [com.baeldung.demo.QuickTestJdbc.main()] ERROR com.baeldung.demo.QuickTestJdbc - LoggerName :: com.baeldung.demo.QuickTestJdbc:: request executed
1
01:43:44.010 [com.baeldung.demo.QuickTestJdbc.main()] ERROR com.baeldung.demo.QuickTestJdbc - LoggerName :: com.baeldung.demo.QuickTestJdbc:: extract from result query failed
[WARNING] thread Thread[mysql-cj-abandoned-connection-cleanup,5,com.baeldung.demo.QuickTestJdbc] was interrupted but is still alive after waiting at least 15000msecs
