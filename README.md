# MercariApplication
### Choice A: A sample app for training
An Application that looks like the Mercari App, loads products based on the static json files from AWS S3 URL , in a tab layout using viewpager.
The application is built using Dagger2(for dependency injections), Android architecture components( Viewmodel, LiveData, MutableLiveData), Moshi(JSON adapter), AutoValue ( for generating the equals, hashcode and other useful boilerplate generations for the models), Retrofit( for network calls), ButterKnife(for view bindings).
The app follows the (Model View ViewModel) MVVM architecture where the ViewModel is contructed by extending the ViewModel of android architecture comonents.

The apps dependencies are satisfied using the Dagger2.0 library.

## The following is the dependency diagram:
![Dagger2 dependency diagram](./doc-img/Dagger2.JPG)

## Architecture
The android architecture component's viewmodel survive's configuration changes like device orientation etc.
The LiveData has been used to intimate the various states the service call go through like loading starts, response arrives or error occurs. On both, success as well failure the loading is propmptly stopped and error is intimated via the text field in a graceful way without exiting the app.

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
