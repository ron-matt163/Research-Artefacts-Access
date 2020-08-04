RESEARCH ARTEFACTS ACCESS SYSTEM

To run the application, the following steps must be carried out:
1) Import the project folder 'ResearchArtefactsAccess' to any suitable IDE (eg. Netbeans, Eclipse)
2) Enter the project folder 'ResearchArtefactsAccess' and open the 'src' folder.
3) Now, open the 'logingui' package
4) Click on 'App.java' and run the program. Now you can view the home page of the application,
   which is a login page.

To use the application, database tables compatible to the program code should be created.Therefore:
1) Open your MySQL Command Line Client and create a database called 'testing'
2) Use 'testing' (database) and create the following tables:
	i) Create a table named 'user' with the following statement:
		 CREATE TABLE `user` (
  		`username` varchar(30) NOT NULL,
  		`password` varchar(30) DEFAULT NULL,
  		`position` varchar(25) NOT NULL DEFAULT 'Researcher',
  		PRIMARY KEY (`username`)
		);
	ii) Create a table named 'researchers' with the following statement:
		 CREATE TABLE `researchers` (
		  `id` int NOT NULL AUTO_INCREMENT,
		  `firstname` varchar(20) DEFAULT NULL,
  		`lastname` varchar(20) DEFAULT NULL,
  		`username` varchar(30) DEFAULT NULL,
		  `AdvisorApproval` tinyint(1) DEFAULT '1',
  		PRIMARY KEY (`id`),
  		KEY `username` (`username`),
  		CONSTRAINT `researchers_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
		);
	iii) Create a table named 'superior' with the following statement:
		CREATE TABLE `superior` (
 		 `id` int NOT NULL AUTO_INCREMENT,
  		`firstname` varchar(20) DEFAULT NULL,
  		`lastname` varchar(20) DEFAULT NULL,
  		`username` varchar(30) DEFAULT NULL,
  		PRIMARY KEY (`id`),
  		KEY `username` (`username`),
  		CONSTRAINT `superior_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
		);
	iv) Create a table named 'superior_researcher' with the following statement:
		 CREATE TABLE `superior_researcher` (
		  `superior` varchar(30) DEFAULT NULL,
 		 `researcher` varchar(30) DEFAULT NULL,
 		 KEY `superior` (`superior`),
  		KEY `researcher` (`researcher`),
 		 CONSTRAINT `superior_researcher_ibfk_1` FOREIGN KEY (`superior`) REFERENCES `superior` (`username`),
  		CONSTRAINT `superior_researcher_ibfk_2` FOREIGN KEY (`researcher`) REFERENCES `researchers` (`username`)
		);
	v) Create a table name 'researcher_work' with the following statement:
		 CREATE TABLE `researcher_work` (
 		 `researcher` varchar(30) NOT NULL,
  		`title` varchar(30) NOT NULL,
  		`filename` varchar(40) DEFAULT NULL,
  		`year` varchar(4) DEFAULT NULL,
  		`topic` varchar(20) DEFAULT NULL,
  		PRIMARY KEY (`researcher`,`title`),
  		CONSTRAINT `researcher_work_ibfk_1` FOREIGN KEY (`researcher`) REFERENCES `researchers` (`username`)
		);

3) Now , register a few 'Superiors' and a few 'Researchers'. The number of registration of researchers is preferably 
   greater than the number of registrations of superiors.

4) Login into the accounts of researchers, and add/upload files by clicking the corresponding buttons.

5) Now, login to the accounts of superiors, and add unassigned researchers to be working under them.

6) The application is now fit to use, since there are sufficient research papers to perform search operations.