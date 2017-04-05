# jarvis-step-
Java based step-by-step demonstration of the Jarvis Step algorithm applied to build the convex hull of a points distribution

I present here a Java based demonstration of the Jarvis March algorithm that is used to find the convex hull of a random points distribution.

Javascript is used for display and initialization only. Here are the implementation details:

A random points distribution is created (browser side)

The distribution is sent to the server as a JSON object.

Then the Jarvis March is executed step-by-step (server side).

At each step the partial result is sent to the browser as a JSON object.

The partial result is used to update the display.

This implementation does not use any explicit angle. No trigonometric functions are used. Instead a cross product is used for all angle comparisons.

When deployed on Tomcat the root context is:

convex-hull

Dominique Ubersfeld, Cachan, France
