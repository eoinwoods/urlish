URLish
======
[![Gitter](https://badges.gitter.im/Join Chat.svg)](https://gitter.im/eoinwoods/urlish?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

My URL shortening mockup for experimentation.

I started this little project for two reasons.  Firstly I wanted to experiment
with Play to see what it was like to create an application using it, and 
secondly I've become interested in trust concerns and models around URL
shortening and similar applications, so I wanted a simple one to play with.

So far, it's been useful for the first goal (I have a better understanding of
Play) but I've not started to think about the trust implications.

The application provides a simple landing page that invites you to shorten
a URL (with a second page to display the result) along with the JSON web 
services:

* json/urls - list the shortened URLs in the database
* json/url/uuu - return the shortened form of URL "uuu"
* json/shorten/uuu - shorten the URL "uuu" and return the result

At present the application is very ugly and uses an in-memory database, but
is functional.  It was also developed in a reasonably test-driven way, so 
has a decent set of tests, albeit slightly clumsy ones in places as I'm
still finding my way around Scala Test and Play's testing conventions.


