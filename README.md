# MercariApplication
### Choice A: A sample app for training
An Application that looks like the Mercari App, loads products based on the static json files from AWS S3 URL , in a tab layout using viewpager.
The application is built using Dagger2(for dependency injections), Android architecture components( Viewmodel, LiveData, MutableLiveData), Moshi(JSON adapter), AutoValue ( for generating the equals, hashcode and other useful boilerplate generations for the models), Retrofit( for network calls), ButterKnife(for view bindings).
The app follows the (Model View ViewModel) MVVM architecture where the ViewModel is contructed by extending the ViewModel of android architecture comonents.

The apps dependencies are satisfied using the Dagger2.0 library.

## The following is the dependency diagram:
![Dagger2 dependency diagram](./doc-img/Dagger2.JPG)

## Architecture
![App architecture diagram](./doc-img/architecture.JPG)

## Screenshots
<img src="./doc-img/device.png" width="400">

## CI/CD using nevercode.io and HockeyApp (No configuration script required).
![nevercode.io 1](./doc-img/nevercode1.JPG)
![nevercode.io 2](./doc-img/nevercode2.JPG)
![nevercode.io 3](./doc-img/nevercode3.JPG)
![nevercode.io 4](./doc-img/nevercode4.JPG)
![nevercode.io 5](./doc-img/nevercode5.JPG)
![nevercode.io 6](./doc-img/nevercode-pub.JPG)
## HockeyApp Distribution (automatically distributed from nevercode.io)
![hockeyapp distribution](./doc-img/hockey.JPG)
