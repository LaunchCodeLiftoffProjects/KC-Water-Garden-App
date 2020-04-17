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

## Branches Notes

## Rebase Notes

How a Rebase Works:

1.	After completing a Pull Request but before merging changes to master, you might want to rebase your changes to include the changes made to master since you started coding in you only branch.

2.	Branches contain all commits of their parent when they are created - these are referred to as the base.

3.	Merging is very simple when the commits in your base are the same as the commits that are in your parent

4.	A rebase is the process of taking your base and replacing it with another base.

5.	`git rebase -i HEAD~#`: rebases you top level commit with the provided number (#) of commits

6.	`git rebase origin/master -i` : Replaces your base with the current code in master and your work on top of that.
NOTE: The -i inside of numbers 5 and 6 stand for interactive 

7.	If there are conflicts between changes in master and changes in your code, the rebase will stop and ask you to re-write the conflicting commit

More information can be found: https://github.com/Junjie-Chen/git-rebase

## Running a Rebase:

1.	Before running rebase - `git checkout master`. Then run the command `git pull`. This updates your local repository with the current remote master branch

2.	Run rebase command `git rebase origin/master -i`

3.	Rebasing opens a text file, called VIM, which contains the SHA (git hash) and the commit message of each commit contained within the branch.

4.	The available commands that are open to use are shown at the bottom of the text file.
*	***Pick*** is chosen by default. It means use this commit in the rebase
*	***Reword*** allows you to use the commit, but you want to edit the commit message
*	***Squash*** will append a commit to the commit before it and concatenate the commit message
*	***Fixup*** is the same as squash, however it does not append the commit message
*	***Drop*** will remove a commit - do not use unless you mean it

5.	`git rebase -i` opens a vim editor, hit `i` for insert then move with your area keys. When you are done choosing your commands hit `esc` to stop typing and then type `:wq!` on the command line to save and close the editor. 

6.	If your rebase fails, run `git status` to see the conflicted files

7.	The conflict will show you their changes, followed by a line of <<<, ===, & >>> signs, and followed by your changes

8.	Resolve conflicts by making the file look like it's supposed to after all changes, theirs and yours. Do not just accept one or the other unless you're sure.

9.	After you fix the file, use `git add .` to stage the file to let git know you have resolved the conflict 10. git rm removes a file from your git repo

10.	Once all files have been resolved and staged, run `git rebase --continue` to continue your rebase

11.	Remember to test your code during the rebase to make sure that the changes are correct!

12.	Rebases are done locally, not on remote. So you must push your changes after completing your rebase and if anything breaks you don’t break the overall app.

13.	Use `git push origin --force` to do what's called a force push and overwrite your remote commit history with your local history
NOTE: you have to use `--force` when rewriting history because GitHub and Git will be out of sync and will not know how to track the changes

14.	Try to never use the force flag unless you're doing it on purpose and for a rebase!
