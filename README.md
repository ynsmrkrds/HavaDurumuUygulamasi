# HAVA DURUMU UYGULAMASI

Bu uygulama MetaWeather API kullanılarak Kotlin dili ile geliştirilmiştir.

Uygulama içinde üç sayfa vardır. Birinci sayfada GPS üzerinden kullanıcının enlem ve boylam bilgileri alınarak kullanıcıya yakın lokasyonlar listelenmektedir. İkinci sayfada kullanıcıya yakın olan lokasyonlardan şehir olan lokasyonlar gösterilmektedir. Üçüncü sayfada ise kullanıcının seçmiş olduğu şehrin bugüne ve ilerleyen 7 güne ait hava durumu bilgileri gösterilmektedir.

Projede kullanılan teknolojiler ise şunlardır:

* Rest API client olarak [Retrofit](https://square.github.io/retrofit/)
* Mimari design olarak MVVM
* Async işlemleri için LiveData, ViewModel, [RxJava](https://github.com/ReactiveX/RxJava)
* API: [MetaWeather](https://www.metaweather.com/api/)
