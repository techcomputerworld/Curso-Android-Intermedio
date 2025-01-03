plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.techcomputerworld.horoscapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.techcomputerworld.horoscapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    //buildTypes es una feature que tenemos que activar.
    /* buildTypes en realidad sirve para configurar la version release por ejemplo con distintas configuraciones por ejemplo que este ofuscado el codigo, o que se ejeute
    * de x manera, o  cualquier tipo ded configutacion que permite ANdroid
    * Podemos configurar la forma debug y tambien poner diferentes configuraciones.
    * */
    //getByName("release") para poner unos parametros y el otro debug para poner otros distintos
    buildTypes {
        getByName("release")  {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            resValue("string", "onzulinname", "HoroscApp")
            //los signos esos de \"url\" es para que coja la url como nosotros queremos, no lo entiendo muy bien pero bueno
            buildConfigField("String", "BASE_URL", "\"https://newastro.vercel.app/\"")
        }
        getByName("debug")  {
            isDebuggable = true
            resValue("string", "onzulinname", "[Debug]HoroscApp")
            buildConfigField("String", "BASE_URL", "\"https://newastro-debug.vercel.app/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    //buildConfig = true es para activar las buildTypes
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    //liberia de navigation component
    /*implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)*/
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    //Dagger Hilt
    //implementation("com.google.dagger:hilt-android:2.51.1")
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    //Retrofit
    //implementation("com.squareup.retrofit2:retrofit:(insert latest version)")
    //implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation(libs.retrofit2.retrofit)
    implementation(libs.retrofit2.converter.gson)
    //implementation(libs.logging.interceptor)
    //
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}