# MVVM + Clean Architecture + RxJava
Sample app to demostrate using RxJava along with MVVM following Clean architecture guidelines.

# Libraries used
- Jetpack Compose
- RxJava 2
- Retrofit + Okhttp
- Hilt
- Room
- MockK
- Junit


# App Summary

App just has a single screen which fetches Photos, Albums and Users via the https://jsonplaceholder.typicode.com/ API. As per clean architecture the app is divided into three layers Data, Domain or Presentation. Data is fetched with network first-cache fallback stratergy, which means data is first fetched from network and if any error is encountered then we return data from cache db instead
