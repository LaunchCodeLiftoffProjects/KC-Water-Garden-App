# Water Garden Tour
Repo for KC 2020 Liftoff's Water Garden Application Team

## Background

The Water Garden Society of the Greater Kansas City area (WGSGKC) holds an annual tour of water gardens in the area. 
WGSGKC is a 501(c)3 not-for-profit organization dedicated to the construction, preservation, and appreciation of 
water gardens throughout the Kansas City metro area.

WGSGKC publishes paper tour guides which they sell in local businesses and at local venues. The paper tour guide 
serves as the 'ticket' for access to the gardens on the tour. The tour guide contains information on each of the 
water gardens, maps showing recommended routes from one to another, and advertising. The tour proceeds and 
advertising revenue provide for the vast majority of the society's limited annual operating budget.

## Overview 

WGSGKC presently does not have a way of providing the tour guide either online or in a phone app. This project seeks to
provide a functioning website on which a phone app may be developed and improved upon.

## Minimum viable product objectives

- User Login
- Database with info on gardens
-- Owner name, address, description, category features
- Map with pins showing garden locations
-- Ability to get directions to pinned location
- List of information on Gardens
- Responsive Design

## Technologies

- Java
- JavaScript
- Spring Boot
- Spring Security
- MySQL
- Spring Data JPA
- Thymeleaf templates & extras for Spring Security
- Bootstrap
- Google Maps API

## What the Team Will Need to Learn
- How to work together on a team to produce a product from concept through demonstration of product
- How to interact as a team using Github and Trello
- How to interact with Google maps to display multiple pins and select a pin for directions

## Requirements

**Java 13** 
https://www.oracle.com/java/technologies/javase-jdk13-downloads.html

Integrated development environment (IDE) such as IntelliJ or Eclipse. 

MySQL Workbench

Google Maps API Key 
- An account can be created at https://cloud.google.com/maps-platform/ and then used to generate the API key.  

## Setup

In MySQL Workbench create a Water_Garden schema. Under Administration - Users and Privileges create a user with login name "Water_Garden" and password "Water_Garden" and Select All under the user's Schema Privileges.

Open the project in your IDE through version control: git@github.com:LaunchCodeLiftoffProjects/KC-Water-Garden-App.git

Add your Google Maps API key in place of "INSERTapiKEYhere" in the last script tag on the following pages. 
-src/main/resources/templates/map.html
-src/main/resources/templates/gardens/tour.html

With MySQL Workbench running start the application and then visit http://localhost:8080 in your browser. 

For full functionality after registering a user you must manually update the user's role in MySQL Workbench by running the query: 
UPDATE user
set role = "ROLE_ADMIN"
where id = 1; 

## Development
Please see our Development README for more information:
[Development README](./Development.md)
