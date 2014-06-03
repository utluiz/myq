MyQ
===

MyQ (you can say "mike") is a Java library for organizing your queries in a functional and... organized way.

## How?

MyQ works as a query manager. You can store your queries in text files, at some fixed location, or along with your DAO classes.

Then you just need to ask MyQ: "please, give me query X". And MyQ goes and finds the file or resource "X.sql", process it, load it, and return it, just for you. 

## Why?

Why just not keep all your SQL code in classes and interfaces? Or even directly inside your methods?

Well, there are simple answers for using separated text files:

### Queries change with your application

Developers often copy&paste code between Java source and SQL class. This is boring and error prone. If you could just save your queries without all those quotes and plus signs...

### Dynamic queries are common

For instance, it's usual having a `WHERE` clause that changes according to some parameters. Using `StringBuilder` or plain String concatenation is, again, error prone and boring.

**Many people get lost as they have to manage a lot of SQL scattered throughout the code.**

### Java itself is not that good for text processing

Nobody writes HTML using String concatenation? For that we have JSP and JSF!   

For general text processing there are great engines like Freemarker and Velocity that excels at generating text output with their markup languages.

### Where's this SQL comes from?

Bad things happen to developers. When queries cause errors you need to find them in the code. Of course you can "search in files" for the query with problem. Nonetheless, if you organize your project early you can benefit of knowing where to find them.

Let all your queries be in their own files and use a single manager.

### Hurry! Fix that error!

If you need to do a simple change to your database like renaming a column, fix a `WHERE` clause clause or some other little modification you would need recompile the entire project, isn't it?

Storing queries in text files allows you to fix little mistakes easily. In an extraordinary situation, you can even store your queries in a directory and then change them at runtime.

## Roadmap

- Spring integration
- Reloading options/cache duration: time interval, by request, detect changes
- Debug mode (for developers)
- Velocity integration: allow velocity templates