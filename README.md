# ERP-system

## Setting up Postgres
1. First, pull the docker image of postgres15
   
   ```js
   PS> docker pull postgres:15-alpine
   ```
2. Then use the makefile
   1. To Initialize the image for local
      ```js
       PS> make postgresinit
      ```
   2. To open the console (psql)
      ```js
       PS> make postgres
      ```
   3. To Create the db for users
      ```js
       PS> make createdbusers
      ```
   4. To drop the users db
      ```js
       PS> make dropdbusers
      ```
   5. To open the console (bash)
      ```js
       PS> make bash
      ```
3. For using the psql
   1. To see all the databases
      ```js
       root=# \l
      ```
   2. connect to the db wanted
      ```js
       root=# \c <yourdb>
      ```
   3. to see all the relations or tables
      ```js
       root=# \dt
      ```
   5. to see all values in the table
      ```js
       root=# SELECT * FROM <tablename>;
      ```
   6. to quit
      ```js
       root=# \q
      ```
## For any problems while using Postgres
1. If it says SSL Enabled error
      1. Then Use bash:
         ```js
         PS> make bash
         ```
      2. go to postgres.conf file
         ```js
         f92622037df6:/# cd /var/lib/postgresql/data
         ```
      3. check the SSL setting
         ```js
         f92622037df6:/# cat postgresql.conf | grep ssl
         ```
         If it is not this in the first line.
         <br>
         ![Image Description](https://github.com/sriganeshres/WorkHub-Pro/assets/120654479/0a663247-df52-41f5-964a-c8983ccb9979)
      4. Uncomment the line: `# ssl = off`
          ```js
         f92622037df6:/# vi postgresql.conf
         ```
          use only vi, as it is the only one that is supported.
      5. Then demote yourself as a normal user and restart the postgres service:
         ```js
         f92622037df6:/# su - postgres
         f92622037df6:~$ pg_ctl restart
         ```
      6. If the image has stopped running, then run the image from Docker Desktop.
      7. Then try to use the service again. It should work.
