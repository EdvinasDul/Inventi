# Inventi

# Created by Edvinas Dulskas
# Used IDEs: NetBeans IDE 8.2; Eclipse IDE for Enterprice Java Developers

First run REST api in Eclipse EE
1) Go to Database.java class and configure host, username and password for your database
2) Run project (it will run Tomcat) and leave.

Second run REST Client
1) go to Inventi/dist
2) run Inventi.jar

How to use REST Client Inventi.jar
1) Choose account number from drop down menu located under "Choose Account:"
    After you choose the desired account number, all the statements, that belong to chosen account, appear in table located below "Selected       Account Bank Statements". 
2) If desired, choose dates "Date From" and "Date To"
3) Press button "Check Balance"
4) To export desired statement, choose statement from Statements table
5) Press button "Export Statement". The statement will be exported to root/exports directory and saved as .CSV file
6) To import .CSV file, simply press "Import Statement"
7) In the pop-up window navigate to your desired .CSV file and select it
8) Click "Open File"
9) Done !
