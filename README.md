# teyyihan1 - dev.akademi2020


## Installation
1. run `gradlew clean assemble jar` in Spring Boot project (/spring)
2. Copy `devakademi-1.jar` from `/spring/build/libs` to `/docker-compose/spring_app` (it should be in same directory with Dockerfile)
3. run `docker-compose -p did_i_win up -d` in /docker-compose directory

The docker compose will be up. **mongo_seed** container will populate the MongoDB container with **classified.json file** and will exit when it's done\
The **spring** container will wait for localhost:27017 for mongo instance to go online with the help of [wait-for-it.sh](https://github.com/vishnubob/wait-for-it/blob/master/wait-for-it.sh) 

#### Android Installation
You need to set your IPv4 address on /util/Consts.kt only if you will run it on a real device that is on the same network with host. 
\
If you will run it on emulator, check [this](https://developer.android.com/studio/run/emulator-networking.html) doc for local address 

# Backend
Language: **Kotlin** \
I've setup Spring Boot (WebFlux) as backend and MongoDB for database. I've also used Kotlin Coroutines with suspend functions.
\
In `ResourceContoller` there is three endpoints.
1. You can get specific item with its id
2. You can get items belongs to specific seller (I was going to use this on Android side to list seller's items but time was up already)
3. This is the big search.
    1. You can select `SellerType`
    2. You can search words in `title` it will return items that has that text in their title. Similar to LIKE query in SQL
    3. You can arrange the price with min and max value.
    4. I also implemented my own pagination using MongoDB. You can fetch any page by specifying the page.



I also containerized the project using Docker.

# Android
**Caching**, **Pagination**, **Dependency Injection**, **Coroutines** \
Language: **Kotlin** \
Libraries I've used:
1. Jetpack Navigation Component with Safe-Args
2. New Hilt (Dependency Injection)
3. Jetpack Room for persistence database
4. New Paging 3 Library
5. Retrofit2
6. Not library but I've used Kotlin Coroutines effectively

When app launches there is a `SplashFragment` with Sahibinden logo on the background. It navigates after 2 seconds. \


`MainFragment` will list all the items from `https://devakademi.sahibinden.com/api/classified/load` by default. I've used `RemoteMediator` from Paging3 library. It downloads the data, **caches** on the Room database and the displays the. It will **not** fetch the anymore data if there are items in database. Whenever it runs out, it will fetch the next page. \


In the bottom right corner there is a FAB. If you click it, new `QueryBottomSheetDialog` will pop up. You can select your filters. It will trigger the `viewmodel->repository->AdNetworkPagingSpring`. This flow will not be cached in Room. Becaues filtered items can be break the `.prevKey` and the `.nextKey` values.


### The things I wanted to implement
1. Category selection on android. This failed because I couldn't send categories from backend and it's so hard to hardcode on Android side
2. Seller's items. I was going to implement this but caching with Paging3 took me hours the fix
3. The endpoints only available for Admin
4. Authorization and Authentication with Keycloak and Spring Security

### The things I wish we had 
1. Categories. Other than that dataset was perfect. It fits all diciplinces in CS