# Project One
This is a university project by Máté Molnár and Ákos Hofmeister for ELTE Faculty Of Informatics.

Our project is about uploading images. Everybody can upload an image to different rooms and can reply to a post with
text or image. You can't post only text to a room, it has to contain at least one image 
(multiple images are supported too). 

After registration you only have access to the Global room. You can create a room anytime you want, and you can invite 
people by their username. You can also accept or deny any incoming invitation, and you can block people, so you won't 
see their messages and they can't invite you to rooms.

Freshly registered users start off with the User rank. After uploading several images and get others to reply on it, 
you will be awarded with several interesting features as your rank increases.

Features
---
- Image posting with optional text
- Replying to images with other images or text
- Reply and image reply counts for posts
- List and catalog view 
- Room chooser (rooms have names and icons)
- Unregistered users can:
  - view the posts and replies in the Global room
- Registered users can:
  - delete their own replies
  - post an image and reply to them
- Higher ranked users get the following features:
  - TBD
- Administrators can:
  - delete posts and replies 
  - change room properties
  - remove/ban users

Used frameworks and languages
---
- Client
  - TypeScript
  - Angular 4 (using Angular CLI)
- Server
  - Java
  - Spring

Dependencies
---
- JDK (1.8)
- Maven (3.5.0)
- Node 8, NPM 3

To install node dependencies, run `npm install` in the project root.

Commands
---

Launching the application in development mode:
```
npm start
```

Starting the development server or the client separately:
```
npm run start-server
npm run start-client
```

Building the application:
```
npm run build
```

Building the server or the client separately:
```
npm run build-server
npm run build-client
```
