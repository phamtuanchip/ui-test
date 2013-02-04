ui-test
=======

UI Test set for eXo platform product

Structure introduction
==================
This set is composed of 2 modules: ui-testsuite and ui-common

ui-common includes all common functions that are used in testcases. Common functions are divided in .java files according to features, modules of GateIn. There are 8 files in package org.exoplatform.selenium.gatein, including DashBoard, GateInBase, GroupNavigation, ManageAccount, ManageApplications, NavigationManagement, NavigationToolbar, PageEditor, PageManagement, PortalManagement, UserGroupManagement

ui-testsuite includes all testcases which are executed to test our product - GateIn. It is divided in packages according to features, modules of GateIn. Each package contains one or more .java files  which are testsuites. Each testsuite that includes one or more testcases is respective to one feature of GateIn.

How to build the project
=======================
1. Prerequisite
* Make sure that mvn 3.0.4 or later version is installed. 
* Package GateIn 3.5 must be available. 
* Browsers must be available, at least Firefox
2. Launch test
Here are steps to build this project.
* Step 1: Start server to run GateIn product.
* Step 2: clone this project from github: type a command: git clone git@github.com:exoplatform/ui-test.git
* Step 3: On terminal, go to ui-test folder.
* Step 4: type a command: mvn clean install [-DbaseUrl] [-Dbrowser] to run all tests on all browsers
	+ baseUrl: url of server, default localhost
	+ browser: default firefox. (Chrome, ie which is Internet Explorer are not supported yet)



