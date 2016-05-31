#Image Comparison

##Requirements

1. Write a program in Java or C# that compares any 2 images and shows the differences visually.
2. Implementation should use only standard core language and platform features, no 3rd party libraries and plagiarized code is permitted.
3. Pixels (with the same coordinates in two images) can be visually similar, but have different values of RGB. We should only mark pixel as 'different' if difference between them is more than 10%.
4. Differences should be shown as a generated output image with different regions outlined with red rectangle as shown below.
5. We need to see your own code. No third party libraries and borrowed code is allowed.

##Run
1. Put the files to compare to 'in' dir (see some examples here)
2. Run app
```
    $ mvn compile exec:java

```
3. You are able to see the result in 'out/3.jpg'
