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

## Technologies

- Java
- Spring Boot
- MySQL
- Thymeleaf templates
- Bootstrap
- Google Maps API

## What the Team Will Need to Learn
- How to work together on a team to produce a product from concept through demonstration of product
- How to interact as a team using Github and Trello
- How to interact with Google maps to display multiple pins and select a pin for directions


# Development
## Database Order
To create tables which include sample data, copy/paste code into workbench in this order:
1. features
2. owner
3. garden
4. user
5. user_tour
