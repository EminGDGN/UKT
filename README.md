# code - UKT Project

## Description

This repository contains the java eclipse project folder ([UKT_Project](https://gricad-gitlab.univ-grenoble-alpes.fr/Projets-INFO4/23-24/06/code/-/tree/main/UKT_Project)).

The code is separated into multiple packages:

#### [ukt.main](https://gricad-gitlab.univ-grenoble-alpes.fr/Projets-INFO4/23-24/06/code/-/tree/main/UKT_Project/src/ukt/main)

This package is the entry point for our application and contains the main method.

#### [ukt.controller](https://gricad-gitlab.univ-grenoble-alpes.fr/Projets-INFO4/23-24/06/code/-/tree/main/UKT_Project/src/ukt/controller)

This package contains all the classes that make up the application controller.

#### [ukt.model](https://gricad-gitlab.univ-grenoble-alpes.fr/Projets-INFO4/23-24/06/code/-/tree/main/UKT_Project/src/ukt/model)

This package contains all the classes that make up the application model.

#### [ukt.parser](https://gricad-gitlab.univ-grenoble-alpes.fr/Projets-INFO4/23-24/06/code/-/tree/main/UKT_Project/src/ukt/parser)

This package contains the first implementation of our CWL parser.

#### [ukt.test](https://gricad-gitlab.univ-grenoble-alpes.fr/Projets-INFO4/23-24/06/code/-/tree/main/UKT_Project/src/ukt/test)

This package contains the first tests of the application, in particular on the CWL model.

#### [ukt.view](https://gricad-gitlab.univ-grenoble-alpes.fr/Projets-INFO4/23-24/06/code/-/tree/main/UKT_Project/src/ukt/view)

This package contains all the classes that make up the graphical interface of the application.

## Get started

- Step 1: open your eclipse IDE
- Step 2: click on File -> Open Projects from File System
- Step 3: browse to the UKT_Project eclipse project folder in the git repository you just have cloned
- Step 4: click on Finish button
- Step 5: build the UKT project on eclipse
- Step 6: run UKT project as java application by running [ukt.main.Main](https://gricad-gitlab.univ-grenoble-alpes.fr/Projets-INFO4/23-24/06/code/-/blob/main/UKT_Project/src/ukt/main/Main.java). 

Important: you must have java SE SDK 17 installed on your machine

In order to run a CWL workflow you also need to have cwltool installed on your computer. Please install this program by following the instructions [here](https://github.com/common-workflow-language/cwltool).

## Diagram of branches
```mermaid
%%{init: { 'logLevel': 'debug', 'theme': 'base', 'gitGraph': {'showBranches': true}} }%%
gitGraph
   commit id:"c0"
   commit id:"c1"
   branch dev
   checkout dev
   commit id:"c2"
   branch username.featureName
   commit tag:"Adding a new feature" id:"c3"
   checkout dev
   checkout username.featureName
   commit id:"c4"
   checkout dev
   merge username.featureName tag:"Merge request required"
   checkout main
   merge dev tag:"version 1"
   checkout dev
   commit id:"c5"
   branch username.bugBugName
   commit tag:"Fixing bug" id:"c6"
   commit id:"c7"
   checkout dev
   merge username.bugBugName tag:"Merge request required"
   commit id:"c8"
   checkout main
   merge dev tag:"version 2"
   commit id:"c9"
   
```

## Contact

- Project manager : alexandre.arle@etu.univ-grenoble-alpes.fr

## Authors
- Alexandre ARLE @arlea (project manager)
- Rémi DEL MEDICO @delmedir
- Axel COLE @coleax
- Emin GUNDOGAN @gundogae
