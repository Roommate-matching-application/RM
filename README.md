# Motivation

College students become roommates without knowing each other.

Roommates may not match a personality, tendency, etc., which may casuse discord among roommates or may be painful to stay at home.

so, someone can become roomates with the people match well as much as possible through **the survey**.



# System Flow

* signUp
* login
* afterlogin
* myChecklist
* roommatechecklist
* getMatches
* card_MainActivity
* ShowMatches
* Chatting
* Post



# Required technology for implementation

## 1. Firebase real-time database

   - User account

   - Chatting

   - Board system

## 2. Server-Client Connection

   - Use data in the database on app

   - Update the data the database in the app
 


# Content

## 1. Login

   User can log in by entering email and password
   
   If you don't have an account, you can create an account by pressing the Membership
   
   ![image](https://user-images.githubusercontent.com/84308922/173223945-77e05445-1c52-4ad7-a552-9f179d7e42e8.png)


## 2. SignUp
   
   Create an account by entering an email and password to log in
   
   *Restriction*
   
   - ID Must be in email format

   - Password must be at least 8 characters

![image](https://user-images.githubusercontent.com/84308922/173224227-41fc63da-1bce-4244-bb4f-00ef35291c97.png)


## 3. afterLogin
   
   *Three button*
   
   - Servey about me
   
   - Servey about roommate
   
   - button to move to community
   
   *Toast message output if login is successful*
   
![image](https://user-images.githubusercontent.com/84308922/173224304-5fc202e5-e427-45b0-b0da-cbcdda714910.png)

## 4. MyChecklist
   
   Show where you conduct a survey about yourself
   
![ezgif com-gif-maker](https://user-images.githubusercontent.com/84308922/173226981-dc4fe637-f386-45d1-b1ce-38e2e50f6e94.gif)


## 5. RoommateChecklist
   
   Show where you conduct a survey about roommate
   
   ![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/84308922/173224738-5030efcf-bba5-475b-bc78-480552d9a993.gif)

## 6. GetMatches
   
   The screen that shows you when the survey is completed
   
   - 'Check result' button
   - Calculate match rates based on surveys

   ![image](https://user-images.githubusercontent.com/84308922/173224812-84a494e2-cc01-4400-9e8d-1d1a9d133fe4.png)
   
## 7. CardView
   
   Build UI to turn left and right
   
   If you turn left, it's rejected
   
   If you turn right, it's accepted (move to chat room)
   
   ![ezgif com-gif-maker (2)](https://user-images.githubusercontent.com/84308922/173224940-4cc609a5-e025-449b-9015-8c48267c0539.gif)

## 8. Chatting 
   
   Can chat with person with high match rates
   
   'Board button' on the top allows you to go to board
   
   ![image](https://user-images.githubusercontent.com/84308922/173226649-4f99da2a-2f93-400e-b003-8b36e28e92a4.png)


## 9. Post

   Can write and see what others have written

   ![ezgif com-gif-maker (3)](https://user-images.githubusercontent.com/84308922/173226928-6a4094b2-98f8-46de-8335-b1df94cc7400.gif)

