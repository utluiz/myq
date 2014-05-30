MyQ
===

MyQ (you can say "mike") is a Java library for organizing your queries in a functional and... organized way.

## How?

MyQ works as a query manager. 

You can store your queries in text files in some fixed location or along with your DAO classes.

So, your code just need to ask MyQ: "please, give me query X".

And there goes MyQ find the file or the resource "X.sql", process it, load it, and return it for you. 

## Why?

Why just not keep all your SQL code in static attributes within classes and intafaces? Or even directly in your methods?

Well, there are simple answers:

### Queries change with your application

Developers often copy&paste code between Java source and SQL class. This is boring and error prone.

If you could just save your queries without all those quotes and plus signs...

### Dynamic queries are common

For instance, a usual requirement is to a `WHERE` clause that changes according to some parameters.

Using `StringBuilder` or even plain String concatenation is, again, error prone and boring.

Many people get lost as they have to manage a lot of sparse SQL snippets.

### Java is not the best for text processing

Java is not the best way to process text templates and generate text output. 

Would you write HTML that way? For this purpose technologies like JSP and JSF exist!   

So there are libraries like Freemarker or Velocity that excels at generating tet output with a specific and easy markup language.

### Where's this SQL comes from?

Bad things happen to good and bad developers. When queries cause errors you need to find them in the code.

Of course you can "search in files" for the query with problem.

Nonetheless, if you organize your project early you can benefit knowing where to find all queries.

Let all your queries be in their own text files and use a single "manager" to manage all of them.

### Hurry! Fix that error!

If you need to do a simple change to your database like renaming a column, fix a query filter clause or some other little modification you would need recompile the entire project, isn't it?

Storing queries in separated text files allows you to fix little mistakes easilym jsut editing text.

In an extraordinary situation, you can even store your queries in a directory in the filesystem and then change then at runtime.