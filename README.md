# JOSMA
Java Open Street Map API (JOSMA) is a simple and minimal API to interact with OSM API using Java.

## Basic Installation
1. Go to your OpenStreetMap account and [create](https://www.openstreetmap.org/user/x/oauth_clients/new) a new project.
2. Select the Consumer Key and Consumer Secret [from the project](https://www.openstreetmap.org/user/x/oauth_clients/9914).
3. Create a file named "Keys.ini" in the root of your project and put the keys into it like the following:
```ini
[AuthenticationKeys]
ConsumerKey = WW91IGhhdmUgdG9vIG11Y2ggdGltZQ==
ConsumerSecret = U3RvcCBkb2luZyB0aGlz
```
Now you can use the application with your credentials.

## Extra Configuration
Inside the [Main.java](./com/josma/Main.java) file you can find some other basic information:
```java
public class Main 
{
    public static final String KEYS_FILEPATH = "Keys.ini"; // Path to the file containing the keys.
    public static final String API_URL = "https://api.openstreetmap.org/api/0.6/"; // URL to the OSM API.
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0;..."; // Headers to use for requests.
    ....
}
```

### External Dependencies & Licenses
[OpenStreetMap API](https://github.com/westnordost/osmapi) (GNU Lesser General Public License v3.0)
<br>
[Signpost OAuth Core 1.0](https://github.com/mttkay/signpost) (Apache License Version 2.0)
<br>
[Ini4j](http://ini4j.sourceforge.net/) (Apache License Version 2.0)